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
     * Instances of the test class that will be tested
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

    /**
     * Constructor to create a testcase
     * @param description Description of the test case
     * @param expectedOutput Expected output for this test case
     * @param optionalParams Additional optional parameters that might be useful while testing, null if there is none
     * @param testClasses Instances of the test class that will be tested
     */
    public Testcase(String description, Object expectedOutput, Object[] optionalParams, Object[] testClasses) {
        this.description = description;
        this.expectedOutput = expectedOutput;
        this.optionalParams = optionalParams;
        this.testClasses = testClasses;
    }

    /**
     * Overloaded constructor with same params except this time only one instance of a class would be tested
     * @param testClass The single instance class to be tested
     */
    public Testcase(String description, Object expectedOutput, Object[] optionalParams, Object testClass) {
        this(description, expectedOutput, optionalParams, new Object[] { testClass });
    }

    /**
     * Getter for all the optional parameters
     * @return array of optional parameters
     */
    public Object[] getOptionalParams() {
        return optionalParams;
    }

    /**
     * Getter to get all the classes being tested
     * @return an array of all the test classes
     */
    public Object[] getTestClasses() {
        return testClasses;
    }

    /**
     * Getter for the expected output
     * @return the expected output
     */
    public Object getExpectedOutput() {
        return expectedOutput;
    }

    /**
     * Getter for description of the test case
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
