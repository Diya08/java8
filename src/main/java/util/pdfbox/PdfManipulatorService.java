package util.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.PageExtractor;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitWidthDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class PdfManipulatorService {

    public int retrieveNumberOfPages(File file) throws Exception {
        try(PDDocument doc = PDDocument.load(file, MemoryUsageSetting.setupMainMemoryOnly())) {
            return doc.getNumberOfPages();

        } catch (IOException e) {
            log.error("Error retrieving the page count from a file: "+ e.getMessage(), e);
            throw new Exception();
        }
    }

    public OutputStream retrievePagesByPageCount(File file, int startingPage, int numOfPages) throws Exception {
        int endPage = startingPage + numOfPages - 1;
        try(PDDocument doc = PDDocument.load(file)) {

            PageExtractor pageExtractor = new PageExtractor(doc);
            pageExtractor.setStartPage(startingPage);
            pageExtractor.setEndPage(endPage);

            PDDocument extract = pageExtractor.extract();
            OutputStream outputStream = new ByteArrayOutputStream();
            extract.save(outputStream);

            return outputStream;

        } catch (IOException e) {
            log.error("Error retrieving the requested number of pages from a file: "+ e.getMessage(), e);
            throw new Exception();
        }
    }

    public OutputStream retrievePages(File file, int startingPageNumber, int endingPageNumber) throws Exception {
        try (FileInputStream fs = new FileInputStream(file)) {
            PDFParser parser = new PDFParser(new RandomAccessBuffer(fs));
            parser.parse();

            COSDocument cosDoc = parser.getDocument();
            PDDocument doc = new PDDocument(cosDoc);

            PageExtractor pageExtractor = new PageExtractor(doc, startingPageNumber, endingPageNumber);
            PDDocument extract = pageExtractor.extract();

            OutputStream outputStream = new ByteArrayOutputStream();
            extract.save(outputStream);

            return outputStream;

        } catch (IOException e) {
            log.error("Error retrieving the requested pages from a file: "+ e.getMessage(), e);
            throw new Exception();
        }
    }

    public OutputStream mergeFiles(List<File> files) throws Exception {
        List<InputStream> sources =
                files.stream()
                        .filter(file -> file.isFile()).map(file -> {
                    try {
                        InputStream fileInputStream = new FileInputStream(file);
                        return fileInputStream;
                    } catch (FileNotFoundException e) {
                        log.error("Error merging list of files: "+ e.getMessage(), e);
                        return null;
                    }

                }).collect(Collectors.toList());

        try(OutputStream mergedPDFOutputStream = new ByteArrayOutputStream()) {
            PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
            pdfMergerUtility.addSources(sources);
            pdfMergerUtility.setDestinationStream(mergedPDFOutputStream);
            pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
            return pdfMergerUtility.getDestinationStream();

        } catch (IOException e) {
            log.error("Error merging list of files: "+ e.getMessage(), e);
            throw new Exception();
        }
    }

    public OutputStream mergeFiles(File file1, File file2) throws Exception {

        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();

        try (OutputStream outputStream = new ByteArrayOutputStream()) {
            pdfMergerUtility.addSource(file1);
            pdfMergerUtility.addSource(file2);
            pdfMergerUtility.setDestinationStream(outputStream);
            pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
            return pdfMergerUtility.getDestinationStream();

        } catch (IOException e) {
            log.error("Error merging the files: "+ e.getMessage(), e);
            throw new Exception();
        }
    }

    public OutputStream addBookmark(File file, int pageNumber, String bookmarkName) throws Exception {
        try(PDDocument pdDocument = PDDocument.load(file)) {
            PDDocumentOutline outline = new PDDocumentOutline();
            pdDocument.getDocumentCatalog().setDocumentOutline(outline);

            PDOutlineItem pagesOutline = new PDOutlineItem();

            PDPageDestination dest = new PDPageFitWidthDestination();
            dest.setPage(pdDocument.getPage(pageNumber));

            PDOutlineItem bookmark = new PDOutlineItem();
            bookmark.setDestination(dest);
            bookmark.setTitle(bookmarkName);
            pagesOutline.addLast(bookmark);

            outline.addLast(pagesOutline);

            OutputStream outputStream = new ByteArrayOutputStream();
            pdDocument.save(outputStream);

            return outputStream;

        } catch (IOException e) {
            log.error("Error adding bookmark: "+ e.getMessage(), e);
            throw new Exception();
        }
    }

    public BufferedImage generateThumbnail(File file, int pageNumber) throws Exception {
        try(PDDocument document = PDDocument.load(file)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            return pdfRenderer.renderImage(pageNumber);

        } catch (IOException e) {
            log.error("Exception in generating thumbnail"+ e.getMessage(), e);
            throw new Exception();
        }
    }
}
