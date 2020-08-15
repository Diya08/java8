import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
// @TestPropertySource
// @TestExecutionListeners
public class Junit5TestExplore {

    @BeforeEach
        // @Test
    void doBeforeEachMethod() {

    }

    @AfterEach
        // @Test
    void doAfterEachMethod() {

    }

    // execute a single test method multiple times with different parameters.
    @ParameterizedTest
    @ValueSource()
    void testParametrizedTestForEmptyValue() {

    }

    @ParameterizedTest
    @ValueSource()
    void testParametrizedTestForValues() {

    }

    @ParameterizedTest
    @NullSource
    void testParametrizedTestForNull() {

    }

    @ParameterizedTest
    @NullAndEmptySource
    void testParametrizedTestForNullAndEmptySource() {

    }

    @RepeatedTest(3)
    void testRepeatedTests() {

    }
}
