import org.junit.jupiter.api.*;
import util.pdfbox.PdfManipulatorService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// @TestInstance(TestInstance.Lifecycle.PER_METHOD)
// @TestPropertySource
// @TestExecutionListeners
class PdfManipulatoreServiceTest {
/*
    @BeforeAll
    // @Test
    void initBeforeAll() {

    }

    @BeforeEach
    // @Test
    void doBeforeEachMethod() {

    }

    @AfterEach
    // @Test
    void doAfterEachMethod() {

    }

    @AfterAll
    // @Test
    // @Testable
    // @TestFactory
    // @TestTemplate
    void doAfterAllMethod() {

    }*/

    /**
     * @Test
     * annotated method is the test method
     * should not be private or static and always void
     */
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

        File parentDirectory = new File(new URI(String.valueOf(this.getClass().getResource("/"))));
        File newFile = new File(parentDirectory, "TestRetrievePagesByPgCount.pdf");

        File file = new File(this.getClass().getResource("/SamplePdfFile.pdf").getFile());
        ByteArrayOutputStream pages = (ByteArrayOutputStream) pdfManipulatorService.retrievePagesByPageCount(file, 1, 1);
        assertNotNull(pages);
        OutputStream outputStream = new FileOutputStream(newFile);
        pages.writeTo(outputStream);

        int i = pdfManipulatorService.retrieveNumberOfPages(new File(this.getClass().getResource("/TestRetrievePagesByPgCount.pdf").getFile()));
        assertEquals(1, i);
        outputStream.close();
        boolean delete = new File(this.getClass().getResource("/TestRetrievePagesByPgCount.pdf").getFile()).delete();
        assertTrue(delete);
    }

    @Test
    void retrievePages() throws Exception {
        PdfManipulatorService pdfManipulatorService = new PdfManipulatorService();

        File parentDirectory = new File(new URI(String.valueOf(this.getClass().getResource("/"))));
        File newFile = new File(parentDirectory, "TestRetrievePages.pdf");

        File file = new File(this.getClass().getResource("/SamplePdfFile.pdf").getFile());
        ByteArrayOutputStream pages = (ByteArrayOutputStream) pdfManipulatorService.retrievePages(file, 1, 1);
        assertNotNull(pages);
        OutputStream outputStream = new FileOutputStream(newFile);
        pages.writeTo(outputStream);

        int i = pdfManipulatorService.retrieveNumberOfPages(new File(this.getClass().getResource("/TestRetrievePages.pdf").getFile()));
        assertEquals(1, i);

        outputStream.close();

        boolean delete = (new File(this.getClass().getResource("/TestRetrievePages.pdf").getFile())).delete();
        assertTrue(delete);
    }

    @Test
    void testMergeFiles() throws Exception {
        PdfManipulatorService pdfManipulatorService = new PdfManipulatorService();

        File parentDirectory = new File(new URI(String.valueOf(this.getClass().getResource("/"))));
        File newFile = new File(parentDirectory, "TestMergedFiles.pdf");

        File file1 = new File(this.getClass().getResource("/SamplePdfFile.pdf").getFile());
        File file2 = new File(this.getClass().getResource("/SamplePdfFile2.pdf").getFile());
        List<File> streamFiles = new ArrayList<>();
        streamFiles.add(file1);
        streamFiles.add(file2);

        ByteArrayOutputStream mergedFile = (ByteArrayOutputStream) pdfManipulatorService.mergeFiles(streamFiles);
        assertNotNull(mergedFile);
        OutputStream outputStream = new FileOutputStream(newFile);
        mergedFile.writeTo(outputStream);

        int i = pdfManipulatorService.retrieveNumberOfPages(new File(this.getClass().getResource("/TestMergedFiles.pdf").getFile()));
        assertEquals(2, i);

        outputStream.close();

        boolean delete = (new File(this.getClass().getResource("/TestMergedFiles.pdf").getFile())).delete();
        assertTrue(delete);
    }

    @Test
    void testMergeTwoSeparateFiles() throws Exception {
        PdfManipulatorService pdfManipulatorService = new PdfManipulatorService();

        File parentDirectory = new File(new URI(String.valueOf(this.getClass().getResource("/"))));
        File newFile = new File(parentDirectory, "TestMergeSeparateFiles.pdf");

        File file1 = new File(this.getClass().getResource("/SamplePdfFile.pdf").getFile());
        File file2 = new File(this.getClass().getResource("/SamplePdfFile2.pdf").getFile());

        ByteArrayOutputStream mergedFile = (ByteArrayOutputStream) pdfManipulatorService.mergeFiles(file2, file1);
        OutputStream outputStream = new FileOutputStream(newFile);
        mergedFile.writeTo(outputStream);
        assertNotNull(mergedFile);

        int i = pdfManipulatorService.retrieveNumberOfPages(new File(this.getClass().getResource("/TestMergeSeparateFiles.pdf").getFile()));
        assertEquals(2, i);

        outputStream.close();

        boolean delete = new File(this.getClass().getResource("/TestMergeSeparateFiles.pdf").getFile()).delete();
        assertTrue(delete);
    }

    @Test
    void testBookmarkPage() throws Exception {
        PdfManipulatorService pdfManipulatorService = new PdfManipulatorService();
        File file = new File(this.getClass().getResource("/SamplePdfFile.pdf").getFile());

        File parentDirectory = new File(new URI(String.valueOf(this.getClass().getResource("/"))));
        File newFile = new File(parentDirectory, "TestBookmark.pdf");

        ByteArrayOutputStream stream = (ByteArrayOutputStream) pdfManipulatorService.addBookmark(file, 0, "ReadThisImportant!");
        assertNotNull(stream);
        OutputStream outputStream = new FileOutputStream(newFile);
        stream.writeTo(outputStream);

        outputStream.close();

        boolean delete = new File(this.getClass().getResource("/TestBookmark.pdf").getFile()).delete();
        assertTrue(delete);
    }

    @Test
    void testThumbnail() throws Exception {
        PdfManipulatorService pdfManipulatorService = new PdfManipulatorService();
        File file = new File(this.getClass().getResource("/SamplePdfFile.pdf").getFile());

        File parentDirectory = new File(new URI(String.valueOf(this.getClass().getResource("/"))));
        File newFile = new File(parentDirectory, "myImage.jpg");

        BufferedImage bufferedImage = pdfManipulatorService.generateThumbnail(file, 0);

        assertTrue(ImageIO.write(bufferedImage, "JPEG", newFile));

        boolean delete = new File(this.getClass().getResource("/myImage.jpg").getFile()).delete();
        assertTrue(delete);
    }
}