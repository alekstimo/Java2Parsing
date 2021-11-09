import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPars {
    @Test
    public void testAddition() {
        Parser p = new Parser();
        try {
            assertEquals(10, p.evalute("5+5"));
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSubtraction() {
        Parser p = new Parser();
        try {
            assertEquals(5, p.evalute("10-5"));
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testMultiplication() {
        Parser p = new Parser();
        try {
            assertEquals(10, p.evalute("2*5"));
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDivision() {
        Parser p = new Parser();
        try {
            assertEquals(10, p.evalute("20/2"));
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testPower() {
        Parser p = new Parser();
        try {
            assertEquals(8, p.evalute("2^3"));
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUnaryMinus() {
        Parser p = new Parser();
        try {
            assertEquals(-10, p.evalute("-10"));
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testInBrackets() {
        Parser p = new Parser();
        try {
            assertEquals(1, p.evalute("(3+2)/5"));
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSinus() {
        Parser p = new Parser();
        try {
            assertEquals(0, p.evalute("sin(0)"));
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCosine() {
        Parser p = new Parser();
        try {
            assertEquals(1, p.evalute("cos(0)"));
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testTangent() {
        Parser p = new Parser();
        try {
            assertEquals(0, p.evalute("tg(0)"));
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testLogarithm() {
        Parser p = new Parser();
        try {
            assertEquals(0, p.evalute("ln(1)"));
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }
}
