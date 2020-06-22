import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

// @TestInstance(TestInstance.Lifecycle.PER_METHOD)
// @TestPropertySource
// @TestExecutionListeners
public class Junit5TestExplore {
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
}
