import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    /*
     * tests of overlap
     */
    @Test
    public void testOverlap1() {
        String str1 = "OhioSta";
        String str2 = "ioState";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(5, overlap);
    }

    @Test
    public void testOverlap2() {
        String str1 = "Lexu";
        String str2 = "xus";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(2, overlap);
    }

    /*
     * tests of combination
     */
    @Test
    public void testCombination1() {
        String str1 = "Bucke";
        String str2 = "ckeyes";
        int overlap = 3;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("Buckeyes", combine);
    }

    @Test
    public void testCombination2() {
        String str1 = "JuliusCa";
        String str2 = "usCaesar";
        int overlap = 4;
        String combine = StringReassembly.combination(str1, str2, overlap);
        assertEquals("JuliusCaesar", combine);
    }

    /*
     * tests of addToSetAvoidingSubstrings
     */
    @Test
    public void testAddToSetAvoidingSubstrings1() {
        Set<String> strSet = new Set1L<>();
        strSet.add("Sean");
        strSet.add("Michael");
        strSet.add("Kelley");
        String str = "an";
        Set<String> expect = new Set1L<>();
        expect.add("Sean");
        expect.add("Michael");
        expect.add("Kelley");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expect, strSet);
    }

    @Test
    public void testAddToSetAvoidingSubstrings2() {
        Set<String> strSet = new Set1L<>();
        strSet.add("Buckeyes");
        strSet.add("Are");
        strSet.add("Great");
        String str = "eye";
        Set<String> expect = new Set1L<>();
        expect.add("Buckeyes");
        expect.add("Are");
        expect.add("Great");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(expect, strSet);
    }

    /*
     * tests of printWithLineSeparators
     */
    @Test
    public void testPrintWithLineSeparators1() {
        SimpleWriter out = new SimpleWriter1L("test.txt");
        SimpleReader in = new SimpleReader1L("test.txt");
        String text = "Sean~1 2~Kelley";
        String expect = "Sean" + "\n" + "1 2" + "\n" + "Kelley";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        String test3 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2 + "\n" + test3);
    }

    @Test
    public void testPrintWithLineSeparators2() {
        SimpleWriter out = new SimpleWriter1L("test.txt");
        SimpleReader in = new SimpleReader1L("test.txt");
        String text = "Buckeyes~Football";
        String expect = "Buckeyes" + "\n" + "Football";
        StringReassembly.printWithLineSeparators(text, out);
        String test = in.nextLine();
        String test2 = in.nextLine();
        in.close();
        out.close();
        assertEquals(expect, test + "\n" + test2);
    }

    /*
     * tests of assemble
     */
    @Test
    public void testAssemble1() {
        Set<String> strSet = new Set1L<>();
        strSet.add("CJ Str");
        strSet.add("troud 4");
        strSet.add("oud 4 Heis");
        strSet.add("Heisman");
        Set<String> expect = new Set1L<>();
        expect.add("CJ Stroud 4 Heisman");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

    @Test
    public void testAssemble2() {
        Set<String> strSet = new Set1L<>();
        strSet.add("CJ Str");
        strSet.add("troud 4");
        strSet.add("oud 4 Heis");
        strSet.add("Heisman");
        strSet.add("Buckeyes");
        strSet.add("OSU");
        Set<String> expect = new Set1L<>();
        expect.add("CJ Stroud 4 Heisman");
        expect.add("Buckeyes");
        expect.add("OSU");
        StringReassembly.assemble(strSet);
        assertEquals(expect, strSet);
    }

}