import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Test cases for Glossary project.
 *
 * @author Sean Kelley
 *
 */
public class GlossaryTests {

    /**
     * Tests nextWordOrSeparator with two words
     *
     * Expects the first word.
     */
    @Test
    public void testNextWordOrSeparatorTwoWordsExpectsFirst() {
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('.');
        separators.add(',');
        separators.add('/');
        separators.add(':');
        separators.add(';');
        separators.add('\'');
        separators.add('?');
        separators.add('!');

        String expected = "test";

        String actual = Glossary.nextWordOrSeparator("test case", 0,
                separators);

        assertEquals(expected, actual);
    }

    /**
     * Tests nextWordOrSeparator with 3 words.
     *
     * Expects the first word.
     */
    @Test
    public void testNextWordOrSeparatorThreeWordsExpectsFirst() {
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('.');
        separators.add(',');
        separators.add('/');
        separators.add(':');
        separators.add(';');
        separators.add('\'');
        separators.add('?');
        separators.add('!');

        String expected = "I";

        String actual = Glossary.nextWordOrSeparator("I like tests", 0,
                separators);

        assertEquals(expected, actual);
    }

    /**
     * Tests nextWordOrSeparator with 3 words.
     *
     * Expects the second word.
     */
    @Test
    public void testNextWordOrSeparatorExpectsSecondWord() {
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('.');
        separators.add(',');
        separators.add('/');
        separators.add(':');
        separators.add(';');
        separators.add('\'');
        separators.add('?');
        separators.add('!');

        String expected = "like";

        String actual = Glossary.nextWordOrSeparator("I like tests", 2,
                separators);

        assertEquals(expected, actual);
    }

    /**
     * Tests nextWordOrSeparator with 3 words.
     *
     * Expects the first space.
     */
    @Test
    public void testNextWordOrSeparatorExpectsSpace() {
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('.');
        separators.add(',');
        separators.add('/');
        separators.add(':');
        separators.add(';');
        separators.add('\'');
        separators.add('?');
        separators.add('!');

        String expected = " ";

        String actual = Glossary.nextWordOrSeparator("I like tests", 1,
                separators);

        assertEquals(expected, actual);
    }

    /**
     * Tests findTermsAndDefinitions.
     *
     * Expects queue of terms and map of terms and definitions use
     * testterms.txt.
     */

    @Test
    public void testFindTermsAndDefintions() {
        Map<String, String> expectedMap = new Map1L<>();
        Map<String, String> actualMap = new Map1L<>();
        Queue<String> expected = new Queue1L<>();

        expectedMap.add("test",
                "a procedure intended to establish the quality, performance, or "
                        + "reliability of something, especially before it is "
                        + "taken into widespread use.");
        expectedMap.add("definitionTest",
                "a statement of the meaning of a word or word group or a sign "
                        + "or symbol test");

        expected.enqueue("test");
        expected.enqueue("definitionTest");

        SimpleReader in = new SimpleReader1L("test/testterms.txt");
        Queue<String> actual = new Queue1L<>();
        actual.append(Glossary.findTermsAndDefinitions(actualMap, in));

        assertEquals(expected, actual);
        assertEquals(expectedMap, actualMap);

        in.close();
    }

    /**
     * Tests addLinkToDefinition by comparing expectedMap to the map altered by
     * addLinkToDefiniions.
     *
     * Expects expectedMap to equal actualMap created by the addLinkToDefinition
     * method.
     */
    @Test
    public void testAddLinkToDefintion() {
        Map<String, String> expectedMap = new Map1L<>();
        Map<String, String> actualMap = new Map1L<>();
        Queue<String> expectedTerms = new Queue1L<>();

        expectedMap.add("test",
                "a procedure intended to establish the quality, "
                        + "performance, or reliability of "
                        + "something, especially before it is taken into "
                        + "widespread use.");
        expectedMap.add("definitionTest",
                "a statement of the meaning of a word or word group or a sign "
                        + "or symbol <a href=\"test.html\">test</a>");

        SimpleReader in = new SimpleReader1L("test/testterms.txt");
        expectedTerms.append(Glossary.findTermsAndDefinitions(actualMap, in));

        Glossary.addLinkToDefinition(actualMap, expectedTerms, "test");

        expectedTerms.enqueue("test");
        expectedTerms.enqueue("definitionTest");

        assertEquals(expectedMap, actualMap);

        in.close();
    }

    /**
     * Tests createTermPages using testterms.txt.
     *
     * expects defintionTest.html to equal testTermPage.html.
     */
    @Test
    public void testCreateTermPages() {
        Map<String, String> expectedMap = new Map1L<>();
        Queue<String> expectedTerms = new Queue1L<>();

        SimpleReader in = new SimpleReader1L("test/testterms.txt");
        expectedTerms.append(Glossary.findTermsAndDefinitions(expectedMap, in));

        expectedTerms.enqueue("test");
        expectedTerms.enqueue("definitionTest");

        Glossary.createTermPages(expectedMap, expectedTerms, "test");

        SimpleReader expected = new SimpleReader1L("test/testTermPage.html");
        SimpleReader actual = new SimpleReader1L("test/definitionTest.html");

        String expectedStr = "";
        String actualStr = "";
        while (!expected.atEOS()) {
            expectedStr = expectedStr + expected.nextLine();
            actualStr = actualStr + actual.nextLine();

        }
        assertEquals(expectedStr, actualStr);

        expected.close();
        actual.close();
    }

    /**
     * Tests createIndex method by create an testCreateIndex.html to compare to
     * the file created by createIndexPage method.
     *
     * Expects testCreateIndex.html to be the same as testIndex.html.
     */
    @Test
    public void testCreateIndexPage() {
        Queue<String> expectedTerms = new Queue1L<>();

        expectedTerms.enqueue("test");
        expectedTerms.enqueue("definitionTest");

        SimpleWriter out = new SimpleWriter1L("test/testIndex.html");
        Glossary.createIndexPage(expectedTerms, out);

        SimpleReader expected = new SimpleReader1L("test/testCreateIndex.html");
        SimpleReader actual = new SimpleReader1L("test/testIndex.html");

        String expectedStr = "";
        String actualStr = "";
        while (!expected.atEOS()) {
            expectedStr = expectedStr + expected.nextLine();
            actualStr = actualStr + actual.nextLine();

        }
        assertEquals(expectedStr, actualStr);

        expected.close();
        actual.close();
    }

}
