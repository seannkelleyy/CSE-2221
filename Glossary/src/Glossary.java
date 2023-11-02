import java.util.Comparator;

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
 *
 * This program takes input from a text file and creates an html document that
 * is in the form of a glossary for the terms in definitions found in the text
 * file.
 *
 * @author Sean Kelley
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class TermSorter implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Makes a queue of terms and a map of terms and definitions by reading the
     * file the user inputs.
     *
     * @param termsAndDefinitions
     *            map of strings <term, definition>
     * @param fileIn
     *            the file that the user inputs containing terms and
     *            definitions.
     * @return queue that contains all of the terms
     */
    public static Queue<String> findTermsAndDefinitions(
            Map<String, String> termsAndDefinitions, SimpleReader fileIn) {

        Queue<String> terms = new Queue1L<>();
        //reads input file and finds the terms and definitions.
        while (!fileIn.atEOS()) {
            String term = fileIn.nextLine();
            String definition = fileIn.nextLine();
            String lastLine = " ";
            //checking to see if the definition is longer than one line
            while (!fileIn.atEOS() && lastLine.length() > 0) {
                lastLine = fileIn.nextLine();
                if (lastLine.length() > 0) {
                    definition += lastLine;
                }
            }
            //adds terms to queue as well as terms and definition to map
            termsAndDefinitions.add(term, definition);
            terms.enqueue(term);
        }
        return terms;
    }

    /**
     * Creates html pages for each term. Each page includes the term and
     * definition as well as a link back to the index page.
     *
     * @param termsAndDefinitions
     *            map of strings <term,definition>
     * @param terms
     *            queue containing all of the terms
     * @param out
     *            file that is returned to the user
     */
    public static void createTermPages(Map<String, String> termsAndDefinitions,
            Queue<String> terms, String out) {

        Queue<String> temp = new Queue1L<>();
        //goes through every term's definitions
        while (terms.length() > 0) {
            String term = terms.dequeue();
            String definition = termsAndDefinitions.value(term);
            temp.enqueue(term);
            //creating link
            SimpleWriter pageLink = new SimpleWriter1L(
                    out + "/" + term + ".html");
            pageLink.println(
                    "<html><head><title>" + term + "</title></head><body>");
            pageLink.println("<h2><b><i><font color=\"red\">" + term
                    + "</font></i></b></h2><hr />");
            pageLink.println(
                    "<blockquote>" + definition + "</blockquote><hr />");
            pageLink.println(
                    "<p>Return to <a href=\"index.html\">index</a>.</p>"
                            + "</body></html>");
            pageLink.close();
        }
        //transferring terms back from temp.

        terms.transferFrom(temp);
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        char first = text.charAt(position);
        String nextWordOrSeparator = "";
        nextWordOrSeparator = nextWordOrSeparator + first;
        if (!separators.contains(first)) {
            for (int i = position + 1; i < text.length()
                    && !separators.contains(first); i++) {
                first = text.charAt(i);
                if (!separators.contains(first)) {
                    String concat = "" + first;
                    nextWordOrSeparator = nextWordOrSeparator.concat(concat);
                }
            }
        } else {
            for (int i = position + 1; i < text.length()
                    && separators.contains(first); i++) {
                first = text.charAt(i);
                if (separators.contains(first)) {
                    String concat = "" + first;
                    nextWordOrSeparator = nextWordOrSeparator.concat(concat);
                }
            }
        }
        return nextWordOrSeparator;
    }

    /**
     * Adds links to a word if it is a term. The link takes the user to the
     * definition page.
     *
     * @param termsAndDefinitions
     *            map of strings <term,definition>
     * @param terms
     *            queue that contains all of the terms
     * @param out
     *            file that is returned to the user
     */
    public static void addLinkToDefinition(
            Map<String, String> termsAndDefinitions, Queue<String> terms,
            String out) {
        Queue<String> temp = new Queue1L<>();
        //creates set filled with separators
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

        // iterates through all terms
        int position = 0;
        while (terms.length() > 0) {
            String term = terms.dequeue();
            temp.enqueue(term);
            String definition = termsAndDefinitions.value(term);
            String actualDefinition = " ";
            //loops while position is shorter than the definition
            while (position < definition.length()) {
                String word = nextWordOrSeparator(definition, position,
                        separators);
                //links to page of a word if it is a term.
                if (termsAndDefinitions.hasKey(word)) {
                    actualDefinition += "<a href=\"" + word + ".html\">" + word
                            + "</a>";
                } else {
                    actualDefinition += word;
                }
                //changes positions to after the word
                position = position + word.length();
            }
            //updates definition with new value
            termsAndDefinitions.replaceValue(term,
                    actualDefinition.substring(1));
            //resets position to 0
            position = 0;
        }
        //restores terms back to its original contents
        terms.transferFrom(temp);
    }

    /**
     * Creates the index page for the glossary.
     *
     * @param terms
     *            queue that contains all of the terms.
     * @param out
     *            file that is returned to the user
     */
    public static void createIndexPage(Queue<String> terms, SimpleWriter out) {
        //creates index file header
        out.println("<html><head><title>Glossary</title></head><body><h2>"
                + "Glossary</h2><hr /><h3>Index</h3><ul>");
        //creates an unordered list of terms
        while (terms.length() > 0) {
            String term = terms.dequeue();
            out.println(
                    "<li><a href=\"" + term + ".html\">" + term + "</a></li>");
        }
        out.println("</ul></body></html>");
    }

    /**
     * Asks the user for a file to input as well as where the want the file
     * returned. Then calls the corresponding methods that create the index page
     * as well as the pages that contain the terms and definitions.
     *
     * @param args
     *            Command-line arguments: not used
     */
    public static void main(String[] args) {
        // creates a new map for terms and definitions and a new queue for terms
        Map<String, String> termsAndDefinitions = new Map1L<>();
        Queue<String> terms = new Queue1L<>();

        //asking user for input and folder to save output
        SimpleReader userInput = new SimpleReader1L();
        SimpleWriter userOutput = new SimpleWriter1L();

        userOutput.print("Enter the input file: ");
        String userIn = userInput.nextLine();
        userOutput.print(
                "Enter the folder where you want to save the output file: ");
        String userOut = userInput.nextLine();

        SimpleReader in = new SimpleReader1L(userIn);
        SimpleWriter out = new SimpleWriter1L(userOut + "/index.html");

        //creates term list and adds terms and definitions to map
        terms.append(findTermsAndDefinitions(termsAndDefinitions, in));

        //puts terms in alphabetical order
        Comparator<String> termSort = new TermSorter();
        terms.sort(termSort);

        //adds links to definitions
        addLinkToDefinition(termsAndDefinitions, terms, userOut);

        //creates the pages for the terms
        createTermPages(termsAndDefinitions, terms, userOut);

        //creates index page
        createIndexPage(terms, out);

        userInput.close();
        userOutput.close();
        in.close();
        out.close();
    }
}
