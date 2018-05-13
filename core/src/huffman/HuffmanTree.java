package huffman;

import java.io.PrintStream;
import java.util.*;

/**
 * HuffmanTree.java
 * Implementation of a Huffman Tree.
 *
 * @author Ryan Strauss
 */
public class HuffmanTree {

    private HuffmanNode myRoot;
    private Queue<HuffmanNode> myNodes;
    private Map<Integer, String> myHuffCodes;

    /**
     * Constructor for the class HuffmanTree.java.
     * Constructs a Huffman coding tree using the given array of frequencies,
     * where count[i] is the number of occurrences of the character with the ASCII value i.
     *
     * @param count frequencies of ASCII characters
     */
    public HuffmanTree(int[] count) {
        myNodes = new PriorityQueue<HuffmanNode>();
        myHuffCodes = new TreeMap<Integer, String>();
        importArray(count);
        buildTree();
        setHuffCodes();
    }

    /**
     * Constructor for the class HuffmanTree.java.
     * Constructs a Huffman tree from a file that contains the description of a tree.
     *
     * @param input
     */
    public HuffmanTree(Scanner input) {
        importScanner(input);
    }

    /**
     * Helper method for the constructor which parses through a .code file to build the tree.
     *
     * @param input
     */
    private void importScanner(Scanner input) {
        myRoot = new HuffmanNode();
        while (input.hasNext()) {
            int ASCII = Integer.parseInt(input.nextLine());
            String huffCode = input.nextLine();
            importScannerHelper(ASCII, huffCode);
        }
    }

    /**
     * Helper method for importScanner which uses iteration to place a new node with the given parameters.
     *
     * @param ASCII
     * @param huffCode
     */
    private void importScannerHelper(int ASCII, String huffCode) {
        HuffmanNode cur = myRoot;
        for (int i = 0; i < huffCode.length(); i++) {
            char direction = huffCode.charAt(i);
            if (direction == '0') {
                if (cur.myLeft == null)
                    cur.myLeft = new HuffmanNode();
                cur = cur.myLeft;
            } else if (direction == '1') {
                if (cur.myRight == null)
                    cur.myRight = new HuffmanNode();
                cur = cur.myRight;
            }
        }
        cur.myChar = ASCII;
    }

    /**
     * Writes the Huffman tree to the supplied output stream.
     *
     * @param output
     */
    public void write(PrintStream output) {
        String toBeWritten = writeHelper(myRoot, "");
        output.print(toBeWritten);
    }

    /**
     * Reads the individual bits from the input stream and writes the corresponding characters to the supplied output stream.
     * It stops reading when a character with a value equal to the eof parameter is encountered.
     *
     * @param input
     * @param output
     * @param eof
     */
    public void decode(BitInputStream input, PrintStream output, int eof) {
        HuffmanNode cur = myRoot;
        while (true) {
            int direction = input.readBit();
            if (cur.myChar != 0) {
                if (cur.myChar == eof)
                    return;
                output.write(cur.myChar);
                cur = myRoot;
            }
            if (direction == 0)
                cur = cur.myLeft;
            if (direction == 1)
                cur = cur.myRight;
            if (direction == -1) {
                System.out.println("Bit -1 reached");
                return;
            }
        }
    }

    /**
     * Helper method for write which recursively builds the string to be written
     * to the file.
     *
     * @param cur
     * @param curHuffValue
     * @return
     */
    private String writeHelper(HuffmanNode cur, String curHuffValue) {
        if (cur == null)
            return "";
        String temp = "";
        if (cur.myChar != 0)
            temp += cur.myChar + "\n" + curHuffValue + "\n";
        return writeHelper(cur.myLeft, curHuffValue + "0") + temp + writeHelper(cur.myRight, curHuffValue + "1");
    }

    /**
     * Imports the given array into a priority queue of HuffmanNodes.
     *
     * @param count
     */
    private void importArray(int[] count) {
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                HuffmanNode temp = new HuffmanNode(count[i], i, null, null);
                myNodes.add(temp);
            }
        }
        HuffmanNode temp = new HuffmanNode(1, count.length, null, null);
        myNodes.add(temp);
    }

    /**
     * Builds a Huffman Tree from a priority queue.
     */
    private void buildTree() {
        while (myNodes.size() > 1) {
            HuffmanNode temp1 = myNodes.remove();
            HuffmanNode temp2 = myNodes.remove();
            HuffmanNode combined = new HuffmanNode(temp1.myFreq + temp2.myFreq, 0, temp1, temp2);
            myNodes.add(combined);
        }
        myRoot = myNodes.peek();
    }

    /**
     * Finds the Huffman Codes for all elements in the tree, and saves them to a map.
     */
    private void setHuffCodes() {
        setHuffCodes(myRoot, "");
        myHuffCodes.remove(0);
    }

    /**
     * Helper method for setHuffCodes which recursivley finds the code for each character.
     *
     * @param cur
     * @param curHuffValue
     */
    private void setHuffCodes(HuffmanNode cur, String curHuffValue) {
        if (cur == null)
            return;
        myHuffCodes.put(cur.myChar, curHuffValue);
        setHuffCodes(cur.myLeft, curHuffValue + "0");
        setHuffCodes(cur.myRight, curHuffValue + "1");
    }

    /**
     * Private class which represents the Huffman Nodes.
     * Implements the Comparable interface.
     */
    private class HuffmanNode implements Comparable<HuffmanNode> {

        HuffmanNode myLeft, myRight;
        int myFreq, myChar;

        /**
         * Default constructor for the HuffmanNode class.
         */
        HuffmanNode() {
            myFreq = 0;
            myChar = 0;
            myLeft = null;
            myRight = null;
        }

        /**
         * Parameterized constructor for the HuffmanNode class.
         *
         * @param f
         * @param c
         * @param left
         * @param right
         */
        HuffmanNode(int f, int c, HuffmanNode left, HuffmanNode right) {
            myFreq = f;
            myChar = c;
            myLeft = left;
            myRight = right;
        }

        /**
         * Compares a HuffmanNode against another HuffmanNode.
         */
        public int compareTo(HuffmanNode o) {
            return myFreq - o.myFreq;
        }

    }

}
