import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Put your name here
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_49_7() {
        NaturalNumber n = new NaturalNumber2(49);
        NaturalNumber nExpected = new NaturalNumber2(7);
        NaturalNumber m = new NaturalNumber2(7);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_137_9() {
        NaturalNumber n = new NaturalNumber2(137);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(9);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_2() {
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(8);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_3() {
        NaturalNumber n = new NaturalNumber2("4981479459234");
        NaturalNumber nExpected = new NaturalNumber2("4981479459234");
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_4() {
        NaturalNumber n = new NaturalNumber2(137);
        NaturalNumber nExpected = new NaturalNumber2(137);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_9_10_12() {
        NaturalNumber n = new NaturalNumber2(9);
        NaturalNumber nExpected = new NaturalNumber2(9);
        NaturalNumber p = new NaturalNumber2(10);
        NaturalNumber pExpected = new NaturalNumber2(10);
        NaturalNumber m = new NaturalNumber2(12);
        NaturalNumber mExpected = new NaturalNumber2(12);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }
    /*
     * Testing isWitnessToCompositeness
     */

    @Test
    public void testIsWitnessToCompositness_0() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        NaturalNumber w = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsWitnessToCompositness_1() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(17);
        NaturalNumber w = new NaturalNumber2(6);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsWitnessToCompositness_2() {
        NaturalNumber n = new NaturalNumber2(49);
        NaturalNumber nExpected = new NaturalNumber2(49);
        NaturalNumber w = new NaturalNumber2(35);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsWitnessToCompositness_3() {
        NaturalNumber n = new NaturalNumber2(137);
        NaturalNumber nExpected = new NaturalNumber2(137);
        NaturalNumber w = new NaturalNumber2(46);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsWitnessToCompositness_4() {
        NaturalNumber n = new NaturalNumber2("584357938442");
        NaturalNumber nExpected = new NaturalNumber2("584357938442");
        NaturalNumber w = new NaturalNumber2("7943843");
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Testing isPrime2.
     */
    @Test
    public void testIsPrime2_0() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsPrime2_1() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(3);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsPrime2_2() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsPrime2_3() {
        NaturalNumber n = new NaturalNumber2(137);
        NaturalNumber nExpected = new NaturalNumber2(137);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsPrime2_4() {
        NaturalNumber n = new NaturalNumber2("100000000");
        NaturalNumber nExpected = new NaturalNumber2("100000000");
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

}
