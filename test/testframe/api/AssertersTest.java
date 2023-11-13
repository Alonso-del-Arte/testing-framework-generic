package testframe.api;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

/**
 * Tests of the Asserters class. These are more elegant than the tests of 
 * testframe.engine.TestRunner, but still more clunky than the tests that can be
 * written once Asserters is fully tested and proven.
 * @author Alonso del Arte
 */
public class AssertersTest {
    
    /**
     * Bit mask for a 64-bit "negative" NaN in which the sign bit, all the 
     * exponent bits and the highest mantissa bit are all on but all other bits 
     * are off.
     */
    private static final long NaN_MASK = -2251799813685248L;
    
    private static final double LOCAL_DELTA = 0.0001;
    
    private static final double HALF_LOCAL_DELTA = LOCAL_DELTA / 2;
    
    private static final double TWICE_LOCAL_DELTA = LOCAL_DELTA * 2;
    
    private static final Random RANDOM = new Random();
    
    private static final String EXAMPLE_ASSERTION_MESSAGE_PART 
            = "Customizable part of assertion message";
    
    @Test
    public void testFail() {
        System.out.println("fail");
        String expected = "This failure message should be retrievable";
        boolean failOccurred = false;
        try {
            Asserters.fail(expected);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        assert failOccurred : "fail() should have occurred when called";
    }
    
    @Test
    public void testAssertNotEqualsInt() {
        int someNumber = RANDOM.nextInt();
        int otherNumber = 2 * someNumber + 1;
        String msgStandardPart = "Expected = " + someNumber + ". Actual = " 
                + otherNumber;
        String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                + msgStandardPart;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + someNumber + " is equal to " + otherNumber 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEquals() {
        System.out.println("assertEquals");
        int someNumber = RANDOM.nextInt();
        int sameNumber = someNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String message = "Asserting " + someNumber + " is equal to " 
                + sameNumber + " should not have failed the test";
        assert !failOccurred : message;
    }
    
    @Test
    public void testAssertNotEqualsIntDefaultMessage() {
        int someNumber = RANDOM.nextInt();
        int otherNumber = 2 * someNumber + 1;
        String expected = "Expected = " + someNumber + ". Actual = " 
                + otherNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + someNumber + " is equal to " + otherNumber 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsLong() {
        long someNumber = RANDOM.nextLong();
        long otherNumber = 2 * someNumber + 1;
        String msgStandardPart = "Expected = " + someNumber + ". Actual = " 
                + otherNumber;
        String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                + msgStandardPart;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + someNumber + " is equal to " + otherNumber 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsLong() {
        long someNumber = RANDOM.nextLong();
        long sameNumber = someNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String message = "Asserting " + someNumber + " is equal to " 
                + sameNumber + " should not have failed the test";
        assert !failOccurred : message;
    }
    
    @Test
    public void testAssertNotEqualsLongDefaultMessage() {
        long someNumber = RANDOM.nextLong();
        long otherNumber = 2 * someNumber + 1;
        String expected = "Expected = " + someNumber + ". Actual = " 
                + otherNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + someNumber + " is equal to " + otherNumber 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsDouble() {
        double someNumber = RANDOM.nextDouble();
        double otherNumber = 2.0 * someNumber + 1.0;
        String msgStandardPart = "Expected " + someNumber 
                + " to not differ from " + otherNumber + " by more than " 
                + LOCAL_DELTA;
        String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                + msgStandardPart;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + someNumber + " does not differ from " 
                + otherNumber + " by more than " + LOCAL_DELTA  
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsDouble() {
        double someNumber = RANDOM.nextDouble();
        double sameNumber = someNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + someNumber + " is equal to " + sameNumber 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsDoubleCloseEnough() {
        double someNumber = RANDOM.nextDouble();
        double nearNumber = someNumber + LOCAL_DELTA / 2;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, nearNumber, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String message = "Asserting " + someNumber + " is equal to " 
                + nearNumber + " within variance " + LOCAL_DELTA 
                + " should not have failed the test";
        assert !failOccurred : message;
    }
    
    // TODO: Reassess this test consider that it failed for 
    // Asserting 0.993872748424878 is equal to 1.0016852484248782 within 
    // variance 0.0078125 should not have failed the test. According to the 
    // Windows 10 Calculator, the variance here is 0.0078125000000002.
//    @Test
    public void testAssertEqualsDoubleJustAtVariance() {
        double someNumber = RANDOM.nextDouble();
        double variance = 0.0078125;
        double nearNumber = someNumber + variance;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, nearNumber, variance, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String message = "Asserting " + someNumber + " is equal to " 
                + nearNumber + " within variance " + variance 
                + " should not have failed the test";
        assert !failOccurred : message;
    }
    
    @Test
    public void testAssertNotEqualsDoubleDefaultMessage() {
        double someNumber = RANDOM.nextDouble();
        double otherNumber = 2.0 * someNumber + 1.0;
        String expected = "Expected " + someNumber + " to not differ from " 
                + otherNumber + " by more than " + LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + someNumber + " does not differ from " 
                + otherNumber + " by more than " + LOCAL_DELTA  
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsDoubleDefaultMessage() {
        double someNumber = RANDOM.nextDouble();
        double sameNumber = someNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + someNumber + " is equal to " + sameNumber 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsDoubleByMoreThanDefaultVariance() {
        double someNumber = RANDOM.nextDouble() + 0.5;
        double otherNumber = 1.0625 * someNumber;
        String msgStandardPart = "Expected " + someNumber 
                + " to not differ from " + otherNumber + " by more than " 
                + Asserters.DEFAULT_TEST_DELTA;
        String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                + msgStandardPart;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + someNumber + " does not differ from " 
                + otherNumber + " by more than " + Asserters.DEFAULT_TEST_DELTA  
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsDoubleDefaultVariance() {
        double someNumber = RANDOM.nextDouble();
        double sameNumber = someNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + someNumber + " is equal to " + sameNumber 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsDoubleDefaultMessageDefaultVariance() {
        double someNumber = RANDOM.nextDouble() + 0.5;
        double otherNumber = 1.0625 * someNumber;
        String expected = "Expected " + someNumber + " to not differ from " 
                + otherNumber + " by more than " + Asserters.DEFAULT_TEST_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + someNumber + " does not differ from " 
                + otherNumber + " by more than " + Asserters.DEFAULT_TEST_DELTA  
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsDoubleDefaultVarianceDefaultMessage() {
        double someNumber = RANDOM.nextDouble();
        double sameNumber = someNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + someNumber + " is equal to " + sameNumber 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsObject() {
        BigInteger someNumber = new BigInteger(72, RANDOM);
        BigInteger otherNumber = someNumber.add(someNumber).add(BigInteger.ONE);
        String msgStandardPart = "Expected = " + someNumber.toString() 
                + ". Actual = " + otherNumber.toString();
        String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                + msgStandardPart;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + someNumber.toString() + " is equal to " 
                + otherNumber.toString() + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsObject() {
        BigInteger someNumber = new BigInteger(64, RANDOM);
        BigInteger sameNumber = someNumber.add(someNumber);
        sameNumber = sameNumber.subtract(someNumber);
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String message = "Asserting " + someNumber.toString() + " is equal to " 
                + sameNumber.toString() + " should not have failed the test";
        assert !failOccurred : message;
    }
    
    @Test
    public void testAssertNotEqualsObjectDefaultMessage() {
        BigInteger someNumber = new BigInteger(72, RANDOM);
        BigInteger otherNumber = someNumber.add(someNumber).add(BigInteger.ONE);
        String expected = "Expected = " + someNumber.toString() + ". Actual = " 
                + otherNumber.toString();
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + someNumber.toString() + " is equal to " 
                + otherNumber.toString() + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsNoExceptionForNullActual() {
        LocalDateTime expected = LocalDateTime.now();
        LocalDateTime actual = null;
        try {
            Asserters.assertEquals(expected, actual);
            String errMsg = "Comparing " + expected.toString() 
                    + " to null should've failed the assertion";
            throw new AssertionError(errMsg);
        } catch (NullPointerException npe) {
            String errMsg = "Comparing " + expected.toString() 
                    + " to null wrongly caused NPE";
            throw new AssertionError(errMsg, npe);
        } catch (RuntimeException re) {
            String errMsg = re.getClass().getName() 
                    + " should not have occurred";
            throw new AssertionError(errMsg, re);
        } catch (AssertionError ae) {
            System.out.println("Comparing " + expected.toString() 
                    + " to null correctly failed the assertion without NPE");
        }
    }
    
    @Test
    public void testAssertNotEqualsArrayIntDiffLengths() {
        int lengthA = RANDOM.nextInt(8) + 2;
        int lengthB = lengthA + RANDOM.nextInt(8) + 2;
        int[] numbersA = new int[lengthA];
        int[] numbersB = new int[lengthB];
        for (int i = 0; i < lengthA; i++) {
            int number = RANDOM.nextInt(256) - 128;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        for (int j = lengthA; j < lengthB; j++) {
            numbersB[j] = j;
        }
        String msgCustomPart = "Arrays should be the same";
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB, msgCustomPart);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = msgCustomPart 
                    + ". Arrays differ in length: expected has " 
                    + numbersA.length + " elements but actual has " 
                    + numbersB.length + " elements";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsArrayInt() {
        int length = RANDOM.nextInt(8) + 2;
        int[] someNumbers = new int[length];
        int[] sameNumbers = new int[length];
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt(256) - 128;
            someNumbers[i] = number;
            sameNumbers[i] = number;
        }
        String msgCustomPart = "Arrays should be the same";
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumbers, sameNumbers, msgCustomPart);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + Arrays.toString(someNumbers) 
                + " is equal to " + Arrays.toString(sameNumbers) 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsIntArraySameLengthDiffNums() {
        int length = RANDOM.nextInt(8) + 2;
        int[] numbersA = new int[length];
        int[] numbersB = new int[length];
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt(256) - 128;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        int origNum = numbersA[changeIndex];
        int diffNum = 2 * origNum + 1025;
        numbersB[changeIndex] = diffNum;
        String msgCustomPart = "Arrays should be the same";
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB, msgCustomPart);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = msgCustomPart 
                    + ". Arrays first differ at index " + changeIndex 
                    + ", expected " + origNum + " but was " + diffNum; 
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsArrayIntDiffLengthsDefaultMsg() {
        int lengthA = RANDOM.nextInt(8) + 2;
        int lengthB = lengthA + RANDOM.nextInt(8) + 2;
        int[] numbersA = new int[lengthA];
        int[] numbersB = new int[lengthB];
        for (int i = 0; i < lengthA; i++) {
            int number = RANDOM.nextInt(256) - 128;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        for (int j = lengthA; j < lengthB; j++) {
            numbersB[j] = j;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Arrays differ in length: expected has "  
                    + numbersA.length + " elements but actual has " 
                    + numbersB.length + " elements";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsIntArraySameLengthDiffNumsDefaultMsg() {
        int length = RANDOM.nextInt(8) + 2;
        int[] numbersA = new int[length];
        int[] numbersB = new int[length];
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt(256) - 128;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        int origNum = numbersA[changeIndex];
        int diffNum = 2 * origNum + 1025;
        numbersB[changeIndex] = diffNum;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Arrays first differ at index " + changeIndex  
                    + ", expected " + origNum + " but was " + diffNum; 
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsArrayIntDefaultMsg() {
        int length = RANDOM.nextInt(8) + 2;
        int[] someNumbers = new int[length];
        int[] sameNumbers = new int[length];
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt(256) - 128;
            someNumbers[i] = number;
            sameNumbers[i] = number;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumbers, sameNumbers);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + Arrays.toString(someNumbers) 
                + " is equal to " + Arrays.toString(sameNumbers) 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }

//    @Test
    public void testAssertEqualsButIsNotArrayDoubleDiffLengths() {
        int lengthA = RANDOM.nextInt(8) + 2;
        int lengthB = lengthA + RANDOM.nextInt(8) + 2;
        double[] numbersA = new double[lengthA];
        double[] numbersB = new double[lengthB];
        for (int i = 0; i < lengthA; i++) {
            double number = RANDOM.nextDouble() + i;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        for (int j = lengthA; j < lengthB; j++) {
            numbersB[j] = RANDOM.nextDouble() + j;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Arrays differ in length: expected has " 
                    + numbersA.length + " elements but actual has " 
                    + numbersB.length + " elements";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " should have failed the test";
        assert failOccurred : msg;
    }
  
    // TODO: Write more tests for assertEquals() for arrays

    @Test
    public void testAssertNullButItIsNot() {
        Object object = LocalDateTime.now();
        boolean failOccurred = false;
        try {
            Asserters.assertNull(object, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Expected null object but found " 
                    + object.toString();
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + object.toString() 
                + " is null should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNull() {
        System.out.println("assertNull");
        boolean failOccurred = false;
        try {
            Asserters.assertNull(null, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting null is null should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNullButItIsNotDefaultMessage() {
        Object object = new BigInteger(96, RANDOM);
        boolean failOccurred = false;
        try {
            Asserters.assertNull(object);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected null object but found " 
                    + object.toString();
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + object.toString() 
                + " is null should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNullDefaultMessage() {
        boolean failOccurred = false;
        try {
            Asserters.assertNull(null);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting null is null should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertBelowMinimumLong() {
        int halfIntMax = -(Integer.MIN_VALUE / 2);
        long minimum = ((long) Integer.MAX_VALUE) + RANDOM.nextInt(halfIntMax);
        long belowMinimum = minimum - RANDOM.nextInt(128) - 1;
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, belowMinimum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Number " + belowMinimum 
                    + " expected to be at least " + minimum;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowMinimum 
                + " is equal to or greater than " + minimum 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMinimum() {
        System.out.println("assertMinimum");
        int halfIntMax = -(Integer.MIN_VALUE / 2);
        long minimum = ((long) Integer.MAX_VALUE) + RANDOM.nextInt(halfIntMax);
        long atOrAboveMinimum = minimum + RANDOM.nextInt(128);
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, atOrAboveMinimum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrAboveMinimum 
                + " is equal to or greater than " + minimum 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertBelowMinimumLongDefaultMessage() {
        int halfIntMax = -(Integer.MIN_VALUE / 2);
        long minimum = ((long) Integer.MAX_VALUE) + RANDOM.nextInt(halfIntMax);
        long belowMinimum = minimum - RANDOM.nextInt(128) - 1;
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, belowMinimum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + belowMinimum 
                    + " expected to be at least " + minimum;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowMinimum 
                + " is equal to or greater than " + minimum 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMinimumLongDefaultMessage() {
        int halfIntMax = -(Integer.MIN_VALUE / 2);
        long minimum = ((long) Integer.MAX_VALUE) + RANDOM.nextInt(halfIntMax);
        long atOrAboveMinimum = minimum + RANDOM.nextInt(128);
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, atOrAboveMinimum);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrAboveMinimum 
                + " is equal to or greater than " + minimum 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertBelowMinimumDouble() {
        double multiplier = RANDOM.nextInt(256) - 128;
        double unitIntervalNumber = RANDOM.nextDouble();
        double minimum = multiplier * unitIntervalNumber;
        double belowMinimum = minimum - unitIntervalNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, belowMinimum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Value " + belowMinimum 
                    + " expected to be at least " + minimum;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowMinimum 
                + " is equal to or greater than " + minimum 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMinimumDouble() {
        double multiplier = RANDOM.nextInt(256) - 128;
        double unitIntervalNumber = RANDOM.nextDouble();
        double minimum = multiplier * unitIntervalNumber;
        double atOrAboveMinimum = minimum + unitIntervalNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, atOrAboveMinimum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrAboveMinimum 
                + " is equal to or greater than " + minimum 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertBelowMinimumDoubleDefaultMessage() {
        double multiplier = RANDOM.nextInt(256) - 128;
        double unitIntervalNumber = RANDOM.nextDouble();
        double minimum = multiplier * unitIntervalNumber;
        double belowMinimum = minimum - unitIntervalNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, belowMinimum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Value " + belowMinimum 
                    + " expected to be at least " + minimum;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowMinimum 
                + " is equal to or greater than " + minimum 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMinimumDoubleDefaultMessage() {
        double multiplier = RANDOM.nextInt(256) - 128;
        double unitIntervalNumber = RANDOM.nextDouble();
        double minimum = multiplier * unitIntervalNumber;
        double atOrAboveMinimum = minimum + unitIntervalNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, atOrAboveMinimum);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrAboveMinimum 
                + " is equal to or greater than " + minimum 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertBelowMinimumComparable() {
        BigInteger minimum = new BigInteger(72, RANDOM);
        BigInteger belowMinimum = minimum.subtract(BigInteger.ONE);
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, belowMinimum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Value " + belowMinimum.toString() 
                    + " expected to be at least " + minimum.toString();
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowMinimum.toString() 
                + " is equal to or greater than " + minimum.toString() 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMinimumComparable() {
        BigInteger minimum = new BigInteger(84, RANDOM);
        BigInteger atOrAboveMinimum 
                = minimum.add(BigInteger.valueOf(RANDOM.nextInt(128)));
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, atOrAboveMinimum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrAboveMinimum.toString() 
                + " is equal to or greater than " + minimum.toString() 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertBelowMinimumComparableDefaultMessage() {
        BigInteger minimum = new BigInteger(72, RANDOM);
        BigInteger belowMinimum = minimum.subtract(BigInteger.ONE);
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, belowMinimum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Value " + belowMinimum.toString() 
                    + " expected to be at least " + minimum.toString();
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowMinimum.toString() 
                + " is equal to or greater than " + minimum.toString() 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMinimumComparableDefaultMessage() {
        BigInteger minimum = new BigInteger(84, RANDOM);
        BigInteger atOrAboveMinimum 
                = minimum.add(BigInteger.valueOf(RANDOM.nextInt(128)));
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, atOrAboveMinimum);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrAboveMinimum.toString() 
                + " is equal to or greater than " + minimum.toString() 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeWhenItIs() {
        int number = -RANDOM.nextInt(65536) - 1;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be at least 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegative() {
        System.out.println("assertNotNegative");
        int number = RANDOM.nextInt(65536);
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeWhenItIsDefaultMessage() {
        int number = -RANDOM.nextInt(65536) - 1;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be at least 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeDefaultMessage() {
        int number = RANDOM.nextInt(65536);
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeWhenDoubleIs() {
        double number = -RANDOM.nextDouble() - Double.MIN_NORMAL;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be at least 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeZeroIsNotActuallyNegative() {
        double number = -0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativePositiveZero() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeDouble() {
        double number = RANDOM.nextDouble() + Double.MIN_NORMAL;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeButNaNIsIndeedNotNegative() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeButNegativeInfinityIs() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be at least 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeButPositiveInfinityIsIndeedNotNegative() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeWhenDoubleIsDefaultMessage() {
        double number = -RANDOM.nextDouble() - Double.MIN_NORMAL;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be at least 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeZeroIsNotActuallyNegativeDefaultMessage() {
        double number = -0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativePositiveZeroDefaultMessage() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeDoubleDefaultMessage() {
        double number = RANDOM.nextDouble() + Double.MIN_NORMAL;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeButNegativeInfinityIsDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be at least 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeButInfinityIsNotNegativeDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeButNaNIsIndeedNotNegativeDefaultMessage() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButItIsNot() {
        int number = RANDOM.nextInt() & Integer.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be at most -1";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegative() {
        System.out.println("assertNegative");
        int number = RANDOM.nextInt() | Integer.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButItIsNotDefaultMessage() {
        int number = RANDOM.nextInt() & Integer.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be at most -1";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeDefaultMessage() {
        int number = RANDOM.nextInt() | Integer.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeDoubleButItIsNot() {
        int floor = RANDOM.nextInt() & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeDouble() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeInfinityIsNegative() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButPositiveInfinityIsNot() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButNaNIsNot() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " is not considered negative, zero or positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number + " from bit pattern " 
                + bitPattern + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButNegativeZeroIsNot() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButPositiveZeroIsNot() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeDoubleButItIsNotDefaultMessage() {
        int floor = RANDOM.nextInt() & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeDoubleDefaultMessage() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeInfinityIsNegativeDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButPositiveInfinityIsNotDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButNaNIsNotDefaultMessage() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " is not considered negative, zero or positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number + " from bit pattern " 
                + bitPattern + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButNegativeZeroIsNotDefaultMessage() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButPositiveZeroIsNotDefaultMessage() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroButItIsNegative() {
        int number = RANDOM.nextInt() | Integer.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroButItIsPositive() {
        int number = (RANDOM.nextInt() | 2) & Integer.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZero() {
        System.out.println("assertZero");
        boolean failOccurred = false;
        try {
            Asserters.assertZero(0L, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that 0 is 0 should not have failed the test";
        assert !failOccurred : msg;
    }
  
    @Test
    public void testAssertZeroButItIsNegativeDefaultMessage() {
        int number = RANDOM.nextInt() | Integer.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDefaultMessage() {
        boolean failOccurred = false;
        try {
            Asserters.assertZero(0L);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that 0 is 0 should not have failed the test";
        assert !failOccurred : msg;
    }
  
    @Test
    public void testAssertZeroButItIsPositiveDefaultMessage() {
        int number = (RANDOM.nextInt() | 4) & Integer.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsNegative() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroPositiveZero() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsPositive() {
        int floor = (RANDOM.nextInt() | 32) & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroNegativeZero() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsNegativeInfinity() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsPositiveInfinity() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsNaN() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsNaNDefaultMessage() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsNegativeDefaultMessage() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroPositiveZeroDefaultMessage() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsPositiveDefaultMessage() {
        int floor = (RANDOM.nextInt() | 64) & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroNegativeZeroDefaultMessage() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsNegativeInfinityDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsPositiveInfinityDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveButItIs() {
        int number = (RANDOM.nextInt() | 8) & Integer.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be less than 1";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not positive should have failed the test";
        assert failOccurred : msg;
    }

    @Test
    public void testAssertNotPositive() {
        System.out.println("assertNotPositive");
        int number = RANDOM.nextInt() | Integer.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroIsNotPositive() {
        int number = 0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }

    @Test
    public void testAssertNotPositiveButItIsDefaultMessage() {
        int number = (RANDOM.nextInt() | 16) & Integer.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be less than 1";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not positive should have failed the test";
        assert failOccurred : msg;
    }

    @Test
    public void testAssertNotPositiveDefaultMessage() {
        int number = RANDOM.nextInt() | Integer.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroIsNotPositiveDefaultMessage() {
        int number = 0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveDoubleButItIs() {
        int floor = RANDOM.nextInt() & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to not be positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveDouble() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveZeroIsNotPositive() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeZeroIsNotPositive() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeInfinityIsIndeedNotPositive() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveInfinityIsNotPositiveButItIs() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to not be positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveButNaNIsIndeedNotPositive() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveDoubleButItIsDefaultMessage() {
        int floor = RANDOM.nextInt() & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to not be positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveDoubleDefaultMessage() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveZeroIsNotPositiveDefaultMessage() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeZeroIsNotPositiveDefaultMessage() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeInfinityIsIndeedNotPositiveDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveInfinityIsNotPositiveButItIsDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to not be positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveButNaNIsIndeedNotPositiveDefaultMessage() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsNegative() {
        long number = RANDOM.nextLong() | Long.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be greater than 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositive() {
        System.out.println("assertPositive");
        long number = RANDOM.nextLong() & Long.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should not have failed the test";
        assert !failOccurred : msg;
    }

    @Test
    public void testAssertPositiveButItIsZero() {
        long number = 0L;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be greater than 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsNegativeDefaultMessage() {
        long number = RANDOM.nextLong() | Long.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be greater than 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveDefaultMessage() {
        long number = RANDOM.nextLong() & Long.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should not have failed the test";
        assert !failOccurred : msg;
    }

    @Test
    public void testAssertPositiveButItIsZeroDefaultMessage() {
        long number = 0L;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be greater than 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveDoubleButItIsNot() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveDouble() {
        int floor = RANDOM.nextInt() & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveInfinityIsPositive() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeInfinityIsNotPositive() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsNaN() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " is not considered negative, zero or positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsNegativeZero() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsPositiveZero() {
        double number = Double.longBitsToDouble(0L);
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveDoubleButItIsNotDefaultMessage() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " 
                    + number + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveDoubleDefaultMessage() {
        int floor = RANDOM.nextInt() & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveInfinityIsPositiveDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeInfinityIsNotPositiveDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = "Number " + number 
                    + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsNaNDefaultMessage() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = "Number " + number 
                    + " is not considered negative, zero or positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsNegativeZeroDefaultMessage() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = "Number " + number 
                    + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsPositiveZeroDefaultMessage() {
        double number = Double.longBitsToDouble(0L);
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = "Number " + number 
                    + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMaximumComparableButItIsMore() {
        BigInteger maximum = new BigInteger(84, RANDOM);
        BigInteger aboveMaximum = maximum.add(BigInteger.ONE);
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(aboveMaximum, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Value " + aboveMaximum.toString() 
                    + " expected to be at most " + maximum.toString();
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + aboveMaximum.toString() 
                + " is equal to or less than " + maximum.toString() 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMaximumComparable() {
        BigInteger maximum = new BigInteger(84, RANDOM);
        BigInteger atOrBelowMaximum 
                = maximum.subtract(BigInteger.valueOf(RANDOM.nextInt(128)));
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(atOrBelowMaximum, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrBelowMaximum.toString() 
                + " is equal to or less than " + maximum.toString() 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    // TODO: Write more tests for assertMaximum()
    
    @Test
    public void testAssertNaNButItIsNot() {
        double number = Math.random();
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Number " + number + " expected to be NaN";
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNaN() {
        System.out.println("assertNaN");
        long bitPattern = NaN_MASK | RANDOM.nextLong();
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNaNButItIsNegativeInfinity() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Number " + number + " expected to be NaN";
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNaNButItIsPositiveInfinity() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Number " + number + " expected to be NaN";
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNaNButItIsNotDefaultMessage() {
        double number = Math.random();
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be NaN";
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNaNDefaultMessage() {
        long bitPattern = NaN_MASK | RANDOM.nextLong();
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNaNButItIsNegativeInfinityDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be NaN";
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNaNButItIsPositiveInfinityDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be NaN";
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNButItIs() {
        double number = Double.NaN;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to not be NaN";
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNNegativeInfinity() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNPositiveInfinity() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaN() {
        System.out.println("assertNotNaN");
        double number = RANDOM.nextDouble() + RANDOM.nextInt();
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNButItIsDefaultMessage() {
        double number = Double.NaN;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to not be NaN";
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNNegativeInfinityDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNPositiveInfinityDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNDefaultMessage() {
        double number = RANDOM.nextDouble() + RANDOM.nextInt();
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    // TODO: Write tests for assertInRange()
    
    @Test
    public void testAssertContainsButDoesNot() {
        int size = RANDOM.nextInt(16) + 4;
        BigInteger[] array = new BigInteger[size];
        BigInteger number = new BigInteger(84, RANDOM);
        for (int i = 0; i < size; i++) {
            number = number.add(BigInteger.valueOf(i));
            array[i] = number;
        }
        BigInteger notPresent = number.negate();
        boolean failOccurred = false;
        String arrayStr = Arrays.toString(array);
        String absentStr = notPresent.toString();
        try {
            Asserters.assertContains(notPresent, array, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Expected element " + absentStr + " to be in " 
                    + arrayStr;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + notPresent.toString() + " is in " 
                + arrayStr + " should have failed the test";
        assert failOccurred : msg;
    }
    @Skip
    @Test
    public void testAssertContains() {
        System.out.println("assertContains");
        int size = RANDOM.nextInt(8) + 2;
        LocalDate[] array = new LocalDate[size];
        LocalDate date = LocalDate.now();
        int powerOfNegativeOne = -1;
        for (int i = 0; i < size; i++) {
            powerOfNegativeOne *= -1;
            date = date.plusDays(i * powerOfNegativeOne);
            array[i] = date;
        }
        LocalDate present = array[RANDOM.nextInt(size)];
        boolean failOccurred = false;
        try {
            Asserters.assertContains(present, array, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + present.toString() + " is in " 
                + Arrays.toString(array) + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertThrows() {
        System.out.println("assertThrows");
        String msg = "Division by zero should cause ArithmeticException";
        ArithmeticException ae = Asserters.assertThrows(() -> {
            System.out.println("3 divided by 0 equals " + (3 / 0));
        }, ArithmeticException.class, msg);
        assert ae != null : "Returned object should not be null";
        assert ae instanceof ArithmeticException : msg;
    }

    @Test
    public void testAssertThrowsButDoesNotThrowAny() {
        boolean failOccurred = false;
        String msg = "Assertion should fail if no exception throws";
        try {
            Asserters.assertThrows(() -> {
                System.out.println("16 divided by 2 equals " + (16 / 2));
            }, ArithmeticException.class, msg);
        } catch (AssertionError ae) {
            failOccurred = true;
            String errMsg = ae.getMessage();
            System.out.println("\"" + errMsg + "\"");
            String checkMsg 
                    = "Failure should report expected exception";
            assert errMsg.contains("ArithmeticException") : checkMsg;
        }
        assert failOccurred : msg;
    }

    @Test
    public void testAssertThrowsButThrowsWrongType() {
        boolean failOccurred = false;
        String msg = "Assertion should fail if wrong exception type thrown";
        try {
            RuntimeException re = Asserters.assertThrows(() -> {
                throw new RuntimeException("Sorry, wrong type exception");
            }, ArithmeticException.class, msg);
            System.out.println("Thrown exception is of type " 
                    + re.getClass().getName());
        } catch (AssertionError ae) {
            failOccurred = true;
            String errMsg = ae.getMessage();
            System.out.println("\"" + errMsg + "\"");
            String checkMsg 
                    = "Failure should report expected, unexpected exceptions";
            assert errMsg.contains("ArithmeticException") : checkMsg;
            assert errMsg.contains("RuntimeException") : checkMsg;
        }
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertThrowsSavesWrongExceptionThrown() {
        RuntimeException expected 
                = new RuntimeException("For testing purposes");
        try {
            IllegalArgumentException iae = Asserters.assertThrows(() -> {
                throw expected;
            }, IllegalArgumentException.class, 
                    "Throwing wrong exception on purpose");
            System.out.println("Thrown exception is of type " 
                    + iae.getClass().getName());
        } catch (AssertionError ae) {
            Throwable actual = ae.getCause();
            String msg = "Expected " + expected.toString() + " but got " 
                    + actual;
            assert expected.equals(actual) : msg;
        }
    }
    
    @Test
    public void testAssertThrowsDefaultMessage() {
        ArithmeticException ae = Asserters.assertThrows(() -> {
            System.out.println("5 divided by 0 equals " + (5 / 0));
        }, ArithmeticException.class);
        assert ae != null : "Returned object should not be null";
        String msg = "Division by zero should cause ArithmeticException";
        assert ae instanceof ArithmeticException : msg;
    }

    @Test
    public void testAssertThrowsButDoesNotThrowAnyDefaultMessage() {
        boolean failOccurred = false;
        String msg = "Assertion should fail if no exception throws";
        try {
            Asserters.assertThrows(() -> {
                System.out.println(msg);
            }, ArithmeticException.class);
        } catch (AssertionError ae) {
            failOccurred = true;
            String errMsg = ae.getMessage();
            assert errMsg != null : "Error message should not be null";
            assert !errMsg.isEmpty() : "Error message should not be empty";
            System.out.println("\"" + errMsg + "\"");
            String checkMsg 
                    = "Failure should report expected exception";
            assert errMsg.contains("ArithmeticException") : checkMsg;
        }
        assert failOccurred : msg;
    }

    @Test
    public void testAssertThrowsButThrowsWrongTypeDefaultMessage() {
        boolean failOccurred = false;
        String msg = "Assertion should fail if wrong exception type thrown";
        try {
            RuntimeException re = Asserters.assertThrows(() -> {
                throw new RuntimeException("Sorry, wrong type exception");
            }, ArithmeticException.class);
            System.out.println("Thrown exception is of type " 
                    + re.getClass().getName());
        } catch (AssertionError ae) {
            failOccurred = true;
            String errMsg = ae.getMessage();
            assert errMsg != null : "Error message should not be null";
            assert !errMsg.isEmpty() : "Error message should not be empty";
            System.out.println("\"" + errMsg + "\"");
            String checkMsg 
                    = "Failure should report expected, unexpected exceptions";
            assert errMsg.contains("ArithmeticException") : checkMsg;
            assert errMsg.contains("RuntimeException") : checkMsg;
        }
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertThrowsSavesWrongExceptionThrownDefaultMessage() {
        boolean failOccurred = false;
        RuntimeException expected 
                = new RuntimeException("For testing purposes");
        try {
            IllegalArgumentException iae = Asserters.assertThrows(() -> {
                throw expected;
            }, IllegalArgumentException.class);
            System.out.println("Thrown exception is of type " 
                    + iae.getClass().getName());
        } catch (AssertionError ae) {
            failOccurred = true;
            Throwable actual = ae.getCause();
            String msg = "Expected " + expected.toString() + " but got " 
                    + actual;
            assert expected.equals(actual) : msg;
        }
        assert failOccurred : "Wrong type should've failed test";
    }
    
    @Test
    public void testAssertDoesNotThrowButDoes() {
        boolean failOccurred = false;
        String msg = "Assertion should fail if exception thrown";
        try {
            Asserters.assertDoesNotThrow(() -> {
                throw new RuntimeException("Sorry");
            }, msg);
        } catch (AssertionError ae) {
            failOccurred = true;
            String errMsg = ae.getMessage();
            System.out.println("\"" + errMsg + "\"");
            String checkMsg = "Failure should report unexpected exception";
            assert errMsg.contains("RuntimeException") : checkMsg;
        }
        assert failOccurred : msg;
    }

    @Test
    public void testAssertDoesNotThrowSavesThrown() {
        RuntimeException expected 
                = new RuntimeException("For testing purposes");
        try {
            Asserters.assertDoesNotThrow(() -> {
                throw expected;
            }, "Throwing exception on purpose");
        } catch (AssertionError ae) {
            Throwable actual = ae.getCause();
            String msg = "Expected " + expected.toString() + " but got " 
                    + actual;
            assert expected.equals(actual) : msg;
        }
    }

    @Test
    public void testAssertDoesNotThrow() {
        System.out.println("assertDoesNotThrow");
        boolean failOccurred = false;
        String msg = "Operation should not cause any exception";
        try {
            Asserters.assertDoesNotThrow(() -> {
                System.out.println("This should not cause any exception");
            }, msg);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDoesNotThrowButDoesDefaultMessage() {
        boolean failOccurred = false;
        String msg = "Assertion should fail if exception thrown";
        try {
            Asserters.assertDoesNotThrow(() -> {
                throw new RuntimeException("Sorry");
            });
        } catch (AssertionError ae) {
            failOccurred = true;
            String errMsg = ae.getMessage();
            assert errMsg != null : "Error message should not be null";
            assert !errMsg.isEmpty() : "Error message should not be empty";
            System.out.println("\"" + errMsg + "\"");
            String checkMsg = "Failure should report unexpected exception";
            assert errMsg.contains("RuntimeException") : checkMsg;
        }
        assert failOccurred : msg;
    }

    @Test
    public void testAssertDoesNotThrowSavesThrownDefaultMessage() {
        RuntimeException expected 
                = new RuntimeException("For testing purposes");
        try {
            Asserters.assertDoesNotThrow(() -> {
                throw expected;
            });
        } catch (AssertionError ae) {
            Throwable actual = ae.getCause();
            String msg = "Expected " + expected.toString() + " but got " 
                    + actual;
            assert expected.equals(actual) : msg;
        }
    }

    @Test
    public void testAssertDoesNotThrowDefaultMessage() {
        boolean failOccurred = false;
        String msg = "Operation should not cause any exception";
        try {
            Asserters.assertDoesNotThrow(() -> {
                System.out.println("This should not cause any exception");
            });
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        assert !failOccurred : msg;
    }
    
}
