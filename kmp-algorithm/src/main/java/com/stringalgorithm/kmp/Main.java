package com.stringalgorithm.kmp;
// In this Main class I tried to demonstrate the KMP algo with various test cases (short, medium and longs strings)
public class Main {

    public static void main(String[] args) {
        KMPAlgorithm kmp = new KMPAlgorithm();

        System.out.println("KMP STRING MATCHING ALGORITHM - DEMONSTRATION");

        testShortString(kmp); // short string test case
        testMediumString(kmp); // medium string test case
        testLongString(kmp);  // long string test case
        testEdgeCases(kmp);  // edge cases handling

        System.out.println("ALL TESTS COMPLETED SUCCESSFULLY");
    }

    private static void testShortString(KMPAlgorithm kmp) {
        System.out.println("TEST CASE 1: SHORT STRING");

        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABC";

        kmp.printSearchDetails(text, pattern);
    }

    private static void testMediumString(KMPAlgorithm kmp) {
        System.out.println("TEST CASE 2: MEDIUM STRING (DNA Sequence)");

        String text = "GATCGGAAGAGCACACGTCTGAACTCCAGTCACATCACGATCTCGTATGCCGTCTTCTGCTTG" +
                "AAAAAAAGATCGGAAGAGCACACGTCTGAACTCCAGTCACATCACGATCTCGTATGCCGTCTTCTGCTTG" +
                "GATCGGAAGAGCACACGTCTGAACTCCAGTCACATCACGATCTCGTATGCCGTCTTCTGCTTGAAA";
        String pattern = "GATCGGAAGAGCACACGTCT";

        kmp.printSearchDetails(text, pattern);
    }

    private static void testLongString(KMPAlgorithm kmp) {
        System.out.println("TEST CASE 3: LONG STRING (Literary Text)");

        String text = "In computer science, the Knuth-Morris-Pratt string-searching algorithm " +
                "(or KMP algorithm) searches for occurrences of a word W within a main text string S " +
                "by employing the observation that when a mismatch occurs, the word itself embodies " +
                "sufficient information to determine where the next match could begin, thus bypassing " +
                "re-examination of previously matched characters. The algorithm was conceived by " +
                "Donald Knuth and Vaughan Pratt and independently by James H. Morris in 1970, " +
                "but the three published it jointly in 1977. The algorithm is a foundational example " +
                "of pattern matching in strings. The KMP algorithm preprocesses the pattern to create " +
                "a failure function that enables the algorithm to skip redundant comparisons. " +
                "This preprocessing step is what gives the KMP algorithm its efficiency. " +
                "The time complexity of the KMP algorithm is O(n+m) where n is the length of the text " +
                "and m is the length of the pattern. This is a significant improvement over the naive " +
                "string matching algorithm which has a time complexity of O(n*m) in the worst case.";
        String pattern = "algorithm";

        kmp.printSearchDetails(text, pattern);
    }

    private static void testEdgeCases(KMPAlgorithm kmp) {
        System.out.println("TEST CASE 4: EDGE CASES");

        System.out.println("Edge Case 1: Pattern not found");
        String text1 = "ABCDEFGHIJKLMNOP";
        String pattern1 = "XYZ";
        kmp.printSearchDetails(text1, pattern1);

        System.out.println("\nEdge Case 2: Repeating pattern");
        String text2 = "AAAAAAAAAA";
        String pattern2 = "AAA";
        kmp.printSearchDetails(text2, pattern2);

        System.out.println("\nEdge Case 3: Single character pattern");
        String text3 = "Hello World";
        String pattern3 = "o";
        kmp.printSearchDetails(text3, pattern3);
    }
}