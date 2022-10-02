package test;

/**
 * This is used for testing classes in Testbed, represents a specific testcase for the class being tested,
 * with the really generic use of Object, can cause errors if casted wrong
 * @author Michael Liu, Genfu Liu
 */
public class Testcase {

    /**
     * Description of the test case
     */
    private String description;

    /**
     * Instance of the test class that will be tested
     */
    private Object[] testClasses;

    /**
     * Expected output for this test case
     */
    private Object expectedOutput;

    /**
     * Additional optional parameters that might be useful while testing, null if there is none
     */
    private Object[] optionalParams;

    public Testcase(String description, Object expectedOutput, Object[] optionalParams, Object[] testClasses) {
        this.description = description;
        this.expectedOutput = expectedOutput;
        this.optionalParams = optionalParams;
        this.testClasses = testClasses;
    }

    public Testcase(String description, Object expectedOutput, Object[] optionalParams, Object testClass) {
        this(description, expectedOutput, optionalParams, new Object[] { testClass });
    }

    public Object[] getOptionalParams() {
        return optionalParams;
    }

    public Object[] getTestClasses() {
        return testClasses;
    }
    public Object getExpectedOutput() {
        return expectedOutput;
    }

    public String getDescription() {
        return description;
    }
}
