import org.junit.jupiter.api.Test;
import util.pdfbox.PdfManipulatorService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PdfManipulatoreServiceTest {

    @Test
    void getNumberOfPages() throws Exception {
        PdfManipulatorService pdfManipulatorService = new PdfManipulatorService();
        File file = new File(this.getClass().getResource("/SamplePdfFile.pdf").getFile());
        int numberOfPages = pdfManipulatorService.retrieveNumberOfPages(file);
        assertEquals(1, numberOfPages);
    }

    @Test
    void retrievePagesByPageCount() throws Exception {
        PdfManipulatorService pdfManipulatorService = new PdfManipulatorService();
        File file = new File(this.getClass().getResource("/SamplePdfFile.pdf").getFile());
        ByteArrayOutputStream pages = (ByteArrayOutputStream) pdfManipulatorService.retrievePagesByPageCount(file, 1, 1);
        assertNotNull(pages);
        OutputStream outputStream = new FileOutputStream("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestRetrievePagesByPgCount.pdf");
        pages.writeTo(outputStream);

        int i = pdfManipulatorService.retrieveNumberOfPages(new File("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestRetrievePagesByPgCount.pdf"));
        assertEquals(1, i);
        outputStream.close();
        boolean delete = new File("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestRetrievePagesByPgCount.pdf").delete();
        assertTrue(delete);
    }

    @Test
    void retrievePages() throws Exception {
        PdfManipulatorService pdfManipulatorService = new PdfManipulatorService();
        File file = new File(this.getClass().getResource("/SamplePdfFile.pdf").getFile());
        ByteArrayOutputStream pages = (ByteArrayOutputStream) pdfManipulatorService.retrievePages(file, 1, 1);
        assertNotNull(pages);
        OutputStream outputStream = new FileOutputStream("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestRetrievePages.pdf");
        pages.writeTo(outputStream);

        int i = pdfManipulatorService.retrieveNumberOfPages(new File("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestRetrievePages.pdf"));
        assertEquals(1, i);

        outputStream.close();
        boolean delete = new File("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestRetrievePages.pdf").delete();
        assertTrue(delete);
    }

    @Test
    void testMergeFiles() throws Exception {
        PdfManipulatorService pdfManipulatorService = new PdfManipulatorService();
        File file1 = new File(this.getClass().getResource("/SamplePdfFile.pdf").getFile());
        File file2 = new File(this.getClass().getResource("/SamplePdfFile2.pdf").getFile());
        List<File> streamFiles = new ArrayList<>();
        streamFiles.add(file1);
        streamFiles.add(file2);

        ByteArrayOutputStream mergedFile = (ByteArrayOutputStream) pdfManipulatorService.mergeFiles(streamFiles);
        assertNotNull(mergedFile);
        OutputStream outputStream = new FileOutputStream("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestMergedFiles.pdf");
        mergedFile.writeTo(outputStream);

        int i = pdfManipulatorService.retrieveNumberOfPages(new File("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestMergedFiles.pdf"));
        assertEquals(2, i);

        outputStream.close();
        boolean delete = new File("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestMergedFiles.pdf").delete();
        assertTrue(delete);
    }

    @Test
    void testMergeTwoSeparateFiles() throws Exception {
        PdfManipulatorService pdfManipulatorService = new PdfManipulatorService();
        File file1 = new File(this.getClass().getResource("/SamplePdfFile.pdf").getFile());
        File file2 = new File(this.getClass().getResource("/SamplePdfFile2.pdf").getFile());

        ByteArrayOutputStream mergedFile = (ByteArrayOutputStream) pdfManipulatorService.mergeFiles(file2, file1);
        OutputStream outputStream = new FileOutputStream("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestMergeSeparateFiles.pdf");
        mergedFile.writeTo(outputStream);
        assertNotNull(mergedFile);

        int i = pdfManipulatorService.retrieveNumberOfPages(new File("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestMergeSeparateFiles.pdf"));
        assertEquals(2, i);

        outputStream.close();
        boolean delete = new File("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestMergeSeparateFiles.pdf").delete();
        assertTrue(delete);
    }

    @Test
    void testBookmarkPage() throws Exception {
        PdfManipulatorService pdfManipulatorService = new PdfManipulatorService();
        File file = new File(this.getClass().getResource("/SamplePdfFile.pdf").getFile());

        ByteArrayOutputStream stream = (ByteArrayOutputStream) pdfManipulatorService.addBookmark(file, 0, "ReadThisImportant!");
        assertNotNull(stream);
        OutputStream outputStream = new FileOutputStream("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestBookmark.pdf");
        stream.writeTo(outputStream);

        outputStream.close();
        boolean delete = new File("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\TestBookmark.pdf").delete();
        assertTrue(delete);
    }

    @Test
    void testThumbnail() throws Exception {
        PdfManipulatorService pdfManipulatorService = new PdfManipulatorService();
        File file = new File(this.getClass().getResource("/SamplePdfFile.pdf").getFile());

        BufferedImage bufferedImage = pdfManipulatorService.generateThumbnail(file, 0);

        assertTrue(ImageIO.write(bufferedImage, "JPEG", new File("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\myimage.jpg")));

        boolean delete = new File("D:\\Users\\jagtasm\\Projects\\ec-oib-scanning\\scanning-service\\src\\test\\resources\\myimage.jpg").delete();
        assertTrue(delete);
    }
}