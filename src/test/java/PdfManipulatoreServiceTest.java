import org.junit.jupiter.api.*;
import util.pdfbox.PdfManipulatorService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * when we have all/few/none lifecycle methods, TestInstance.Lifecycle.PER_CLASS can be used
 * when we have only beforeEach and/or afterEach lifecycle methods,
 * TestInstance.Lifecycle.PER_METHOD can be used - This is by default
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// @TestPropertySource
// @TestExecutionListeners

// custom display name
@DisplayNameGeneration(PdfManipulatoreServiceTest.CustomDisplayNameGeneration.class)

public class PdfManipulatoreServiceTest {

    /**
     * executed only once per test class
     * void return type
     * must not be private
     * must be static by default
     */

    @BeforeAll
    // @Test
    void initBeforeAll() {
        System.out.println("BeforeAll called");
    }

    /**
     * executes before each test method
     * void return type
     * must not be private
     * must be static
     * Can optionally declare parameters to be resolved by <>@ParameterResolvers</>
     * May be used as meta annotation for custom annotations
     */
    @BeforeEach
    // @Test
    void doBeforeEachMethod() {
        System.out.println(Date.valueOf(LocalDate.now()));
    }

    /**
     * executed after each of @Test, @RepeatedTest, @ParameterizedTest
     * @TestFactory and @TestTemplate methods
     * void, not private, not static
     */
    @AfterEach
    // @Test
    void doAfterEachMethod() {
        System.out.println("If 2 AfterEach methods, there is no order it follows; any one of them gets executed first, similar to before each method");
    }

    /**
     *
     */
    @AfterAll
    // @Test
    // @Testable
    // @TestFactory
    // @TestTemplate
    void doAfterAllMethod() {

    }

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
    @DisplayName("Should return number of pages requested")
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
    @DisplayName("@DisplayName has precedence over @DisplayNameGeneration")
    void retrieve_Pages() throws Exception {
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

    // custom DisplayNameGenerator
    static class CustomDisplayNameGeneration extends DisplayNameGenerator.Standard {
        public CustomDisplayNameGeneration() {

        }

        @Override
        public String generateDisplayNameForClass(Class<?> testClass) {
            return replaceTheName(super.generateDisplayNameForClass(testClass));
        }

        @Override
        public String generateDisplayNameForNestedClass(Class<?> testClass) {
            return replaceTheName(super.generateDisplayNameForNestedClass(testClass));
        }

        @Override
        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            return replaceTheName(super.generateDisplayNameForMethod(testClass, testMethod));
        }

        private String replaceTheName(String name) {
            StringBuilder sb = new StringBuilder();
            sb.append(name.charAt(0));

            for(int i = 1; i <name.length(); i++) {
                if(Character.isUpperCase(name.charAt(i))) {
                    sb.append(" ");
                    //sb.append(Character.toLowerCase(name.charAt(i)));
                }
                sb.append(Character.toLowerCase(name.charAt(i)));
            }
            return String.valueOf(sb);
        }
    }
}