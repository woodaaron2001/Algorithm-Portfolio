import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/******************************************************************************
 *  Compilation:  javac Huffman.java
 *
 *  Compress or expand a binary input stream using the Huffman algorithm.
 *
 *  To run program first naviagate to compressionAssignment folder and compile program
 *
 *  NOTE MAKESURE that console is wide enough when displaying to show all results
 *  Arguments are also not caseSensitve 
 *  
 *  Compression - java Huffman compress inputFile outputFile
 *
 *  Decompression - java Huffman decompress inputFile outputFile
 *
 *  EXAMPLE - java Huffman compress mobyDick.txt mobyDickCompressed
 *
 ******************************************************************************/

//Non helper code uses ==

/**
 *  Node - Prviate inner class which represents node in huffman trie
 *
 *  Node - isLeaf() - Checks if node has no children - end of prefixCode
 *  
 *  compress() == Compresses the inputFile given from user - sends Trie, length of text and compressed text to file
 *
 *  decompress() == deCompresses inputFile from user - we read through the trie and output to file
 *  
 *  buildTrie() - builds the Huffman trie based on the frequency of characters given
 *
 *  buildCode() - Writes the values of traversing the huffman trie to a table
 *
 *  writeTrie() - Writes the encoding of the trie to the outputFile
 *
 *  readTrie() - reads in the encoding of a trie from the inputFile
 *
 *  bitCounter() == Similar to binaryDump prints out the total number of bits in given file
 *
 *  printCompression() == Writes information to stdout - inputFileName , OuputFileName, bitCounts, time, compressionRatio
 *
 *  checkValidInput() == Parses user input for correct argLength,and arg names - also does error checking on files
 *
 *  @author Aaron Wood
 */
public class Huffman {

    //Constants for alphabet size and size of character
    private static final int ASCII_SIZE = 256;
    private static final int BITOUT = 8;

    //variables allowing for ease of writing and reading from files 
    private static  BinaryIn in;
    private static  BinaryOut out;

    // Do not instantiate.
    private Huffman() { }

 

    /**
     * Reads a sequence of 8-bit bytes from standard input; compresses them
     * using Huffman codes with an 8-bit alphabet; and writes the results
     * to standard output.
     */
    public static void compress() {

        System.out.println("Compressing - Make sure console is wide enough to display properly");
       
        //1. read the input
    	String input = in.readString();
 
        //2. tabulate the frequency counts
        //CharacterIterators are used to move throug the string rather than charAt()

    	CharacterIterator inputIt = new StringCharacterIterator(input);
    	
        //occurences hold the number of occurences of each character in alphabet
        int[] occurences = new int[ASCII_SIZE];

    	while(inputIt.current() != inputIt.DONE) {
    		occurences[inputIt.current()]++;
    		inputIt.next();
    	}

        //3. build Huffman trie
        //Note stopwatches are used here to check only the speed of trie building for analysis 

        //Stopwatch trieBuildTime = new Stopwatch();
        Node root = buildTrie(occurences);
        //System.out.println("Time to build trie(S)" + trieBuildTime.elapsedTime()/1000000000);

        //4. build code table
        String[] codeTable = new String[ASCII_SIZE];
        
        buildCode(codeTable, root, "");


        //5. print trie for decoder
        writeTrie(root);

        //6. print number of bytes in original uncompressed message
        out.write(input.length());

        // use Huffman code to encode input
        inputIt.setIndex(0);

        while(inputIt.current() != inputIt.DONE) {
            //forEach character in the text we print the subsequent value of the codeTable given
        	CharacterIterator tableIt = new StringCharacterIterator(codeTable[inputIt.current()]);
        	
        	while(tableIt.current() != tableIt.DONE) {
                //write a 1 to file if true else write a 0
        		out.write(tableIt.current() == '1');
        		tableIt.next();
        	}
        	inputIt.next();
        }

        //closing file for security
        out.close();

    }


    /**
     * Reads a sequence of bits that represents a Huffman-compressed message from
     * standard input; expands them; and writes the results to standard output.
     */
    public static void decompress() {
     System.out.println("DeCompressing - Make sure console is wide enough to display properly");
        // read in Huffman trie from input stream
        Node root = readTrie();  
           
        // number of bytes to write - Note works assuming that the value for textLength ALWAYS occurs after last trie node
        int byteCount = in.readInt();

        // decode using the Huffman trie
        for(int letter = 0; letter < byteCount; letter++){
            Node traversalNode  = root;

            //while we're not at the end of a code
            while(traversalNode.left() != null && traversalNode.right() != null){
                //1 is right 0 is left
                if(in.readBoolean()){
                    traversalNode = traversalNode.right();
                }
                else{
                    traversalNode = traversalNode.left();
                    }
            }
        //write the character found at final node
        //alternatively we could use out.writeBoolean() as we moved through the loop
        out.write(traversalNode.getChar(),BITOUT);
        }

        //closing file for security
        out.close();
    }




    /** 
    * Parses user input and checks for errors while opening files
    * @param args The string of inputs given from the user
    * @return Boolean depending on if user inputted arguments correctly
    */
    private static boolean checkValidInput(String[] args) {
        //checks if the first arguments is atleast one of the 2 options and that we have enough arguments
    	if(!args[0].equalsIgnoreCase("compress") && !args[0].equalsIgnoreCase("decompress") && args.length != 3) {
    		System.out.println("Error with arguments given to program");
    		System.out.println("Example use: java Huffman compress inputFile outputFile");
            System.out.println("Example use: java Huffman decompress inputFile outputFile");
    		return false;
    	}

       
        //we catch any errors thrown by binaryIn and Out and return false if there are
        try{
        in = new BinaryIn(args[1]);
        out = new BinaryOut(args[2]);
        }
        catch(Exception e){
             System.out.println("Error with file");
             System.out.println("Please try again");
            return false;
        }


    	return true;
    }
 /**
     * Similar to binaryDump bitCounter counts each individual bit and returns the total
     *
     * @param text One of the files give in command line
     * @return Number of bits in total in the given text/file
     */
    private static int bitCounter(String text){

        BinaryIn binaryText = new BinaryIn(text);
        int count = 0;
        while(!binaryText.isEmpty()){
            binaryText.readBoolean();
            count +=1;
        }
    return count;
    }
    
 /**
     * Prints information about the files, number of bits in each file, time for compression and compression ratio
     * NOTE Make sure that console is wide enough to display all parts correctly
     *
     * @param in Input file given in command line
     * @param out Output file given in commmand line
     * @param time Time taken for compression from main
     * @param compress Tells us wheter the user ran compress() or decompress() 
     */
    private static void printCompression(String in, String out,double time,boolean compress) {
    
        double inFileBits = bitCounter(in);
        double outFileBits = bitCounter(out);
    	
        if(compress) {
            System.out.format("%25s%25s%25s%20s%20s%10s\n","InputFile(Original)","OutputFile(Compressed)","Time Elapsed","Original bits","CompressedBits","Ratio");

            double compression =  outFileBits *100 / inFileBits;
            System.out.format("%25s%25s%25f%20.0f%20.0f%10.2f%1s\n",in,out,time,inFileBits,outFileBits,compression,"%");
    	}
    	
        else {
    		System.out.format("%25s%25s%20s%20s%20s\n","InputFile(Compressed)","OuputFile(Decompressed)","Time Elapsed","Original bits","DecompressedBits");

            System.out.format("%25s%25s%20f%20.0f%20.0f\n",in,out,time,inFileBits,outFileBits);
    	}
    }


    /**
     * Sample client that calls {@code compress()} if the command-line
     * argument is "compress" an {@code decompress()} if it is "decompress".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
    	if(checkValidInput(args)) {
    		if(args[0].equals("compress")) {
    			
    	    	Stopwatch CompTimer = new Stopwatch();
    	    	compress();
    	    	printCompression(args[1],args[2],CompTimer.elapsedTime()/1000000000,true);
    		}
    		else {
    	    	Stopwatch deCompTimer = new Stopwatch();
    	    	decompress();
    	    	printCompression(args[1],args[2],deCompTimer.elapsedTime()/1000000000,false);
                }
    		}
    }


//Helper Code:

   // Huffman trie node
    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }

        public int getChar(){
            return this.ch;
        }

        public Node left(){
            return this.left;
        }
        public Node right(){
            return this.right;
        }
    }


    
    
    // build the Huffman trie given frequencies
    private static Node buildTrie(int[] freq) {

        // initialze priority queue with singleton trees
    	MinPQ<Node> pq = new MinPQ<Node>();
        for (char i = 0; i < ASCII_SIZE; i++)
            if (freq[i] > 0)
                pq.insert(new Node(i, freq[i], null, null));

        // special case in case there is only one character with a nonzero frequency
        if (pq.size() == 1) {
            if (freq['\0'] == 0) pq.insert(new Node('\0', 0, null, null));
            else                 pq.insert(new Node('\1', 0, null, null));
        }

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left  = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        return pq.delMin();
    }


    // write bitstring-encoded trie to standard output
    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            out.write(true);
            out.write(x.ch, BITOUT);
            return;
        }
        out.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }


    // make a lookup table from symbols and their encodings
    private static void buildCode(String[] st, Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(st, x.left,  s + '0');
            buildCode(st, x.right, s + '1');
        }
        else {
            st[x.ch] = s;
        }
    }



    private static Node readTrie() {
        boolean isLeaf = in.readBoolean();
        if (isLeaf) {
            return new Node(in.readChar(), -1, null, null);
        }
        else {
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }

}