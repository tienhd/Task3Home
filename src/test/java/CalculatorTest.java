import com.qsoft.Calculator;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 5/17/13
 * Time: 8:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class CalculatorTest {

    @Test
    public void shouldBeZeroOnEmptyString() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(0, calculator.add(""));
    }

    @Test
    public void shouldBeThree() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(3,calculator.add("1,2"));
    }

    @Test
    public void shouldBeFive() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(5,calculator.add("1,\n2,2"));
    }

    @Test
    public void testDifferentDelimiter() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(5,calculator.add("//;1,*\n2,2"));
    }

    public void testAdvanceDelimiter() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(9,calculator.add("//;\n2;4;3"));
    }

    @Rule
    public ExpectedException myException = ExpectedException.none();

    @Test
    public void testException() {
        Calculator calculator = new Calculator();
        myException.expect(NumberFormatException.class);
        myException.expectMessage("Negative is not allowed");
        calculator.add("-1,-2");

    }

    @Test
    public void testWithNumberGreaterThan1000 () {
        Calculator calculator = new Calculator();
        Assert.assertEquals(5,calculator.add("//2,3,1001"));
    }

    @Test
    public void testWithDefineDelimiter() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(6,calculator.add("//[**6*]\n1**6*2**6*3"));
        Assert.assertEquals(6,calculator.add("//[**[]*]\n1**[]*2**[]*3"));
    }

    @Test
    public void testAdvanceDefineDelimiter() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(6,calculator.add("//[[]*][?]\n1[]*2?3"));
    }

    @Test
    public void testQuynhTQ() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(6,calculator.add("//[,,]\n1,,3,,2"));
    }


}
