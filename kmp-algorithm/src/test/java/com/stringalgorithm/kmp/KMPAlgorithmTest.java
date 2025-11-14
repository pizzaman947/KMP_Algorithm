package com.stringalgorithm.kmp;


import com.stringalgorithm.kmp.KMPAlgorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Comprehensive jUnit tests for KMP (use mvn test after mvn compile)

class KMPAlgorithmTest {

    private KMPAlgorithm kmp;

    @BeforeEach
    void setUp() {
        kmp = new KMPAlgorithm();
    }

    @Test
    @DisplayName("Test basic pattern matching")
    void testBasicPatternMatching() {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABC";

        List<Integer> matches = kmp.search(text, pattern);

        assertEquals(1, matches.size());
        assertEquals(10, matches.get(0));
    }

    @Test
    @DisplayName("Test multiple occurrences")
    void testMultipleOccurrences() {
        String text = "AABAACAADAABAABA";
        String pattern = "AABA";

        List<Integer> matches = kmp.search(text, pattern);

        assertEquals(3, matches.size());
        assertEquals(Arrays.asList(0, 9, 12), matches);
    }

    @Test
    @DisplayName("Test pattern not found")
    void testPatternNotFound() {
        String text = "ABCDEFGHIJKLMNOP";
        String pattern = "XYZ";

        List<Integer> matches = kmp.search(text, pattern);

        assertTrue(matches.isEmpty());
        assertEquals(-1, kmp.searchFirst(text, pattern));
    }

    @Test
    @DisplayName("Test overlapping patterns")
    void testOverlappingPatterns() {
        String text = "AAAAAAA";
        String pattern = "AAA";

        List<Integer> matches = kmp.search(text, pattern);

        assertEquals(5, matches.size());
        assertEquals(Arrays.asList(0, 1, 2, 3, 4), matches);
    }

    @Test
    @DisplayName("Test single character pattern")
    void testSingleCharacterPattern() {
        String text = "Hello World";
        String pattern = "o";

        List<Integer> matches = kmp.search(text, pattern);

        assertEquals(2, matches.size());
        assertEquals(Arrays.asList(4, 7), matches);
    }

    @Test
    @DisplayName("Test pattern equals text")
    void testPatternEqualsText() {
        String text = "ABCDEF";
        String pattern = "ABCDEF";

        List<Integer> matches = kmp.search(text, pattern);

        assertEquals(1, matches.size());
        assertEquals(0, matches.get(0));
    }

    @Test
    @DisplayName("Test pattern longer than text")
    void testPatternLongerThanText() {
        String text = "ABC";
        String pattern = "ABCDEF";

        List<Integer> matches = kmp.search(text, pattern);

        assertTrue(matches.isEmpty());
    }

    @Test
    @DisplayName("Test empty pattern")
    void testEmptyPattern() {
        String text = "ABCDEF";
        String pattern = "";

        List<Integer> matches = kmp.search(text, pattern);

        assertTrue(matches.isEmpty());
    }

    @Test
    @DisplayName("Test null inputs")
    void testNullInputs() {
        List<Integer> matches1 = kmp.search(null, "ABC");
        List<Integer> matches2 = kmp.search("ABC", null);
        List<Integer> matches3 = kmp.search(null, null);

        assertTrue(matches1.isEmpty());
        assertTrue(matches2.isEmpty());
        assertTrue(matches3.isEmpty());
    }

    @Test
    @DisplayName("Test LPS array computation")
    void testLPSArrayComputation() {
        String pattern = "ABABC";
        int[] lps = kmp.getLPSArray(pattern);

        assertArrayEquals(new int[]{0, 0, 1, 2, 0}, lps);
    }

    @Test
    @DisplayName("Test LPS array for repeating pattern")
    void testLPSArrayRepeating() {
        String pattern = "AAAA";
        int[] lps = kmp.getLPSArray(pattern);

        assertArrayEquals(new int[]{0, 1, 2, 3}, lps);
    }

    @Test
    @DisplayName("Test LPS array for complex pattern")
    void testLPSArrayComplex() {
        String pattern = "AABAACAABAA";
        int[] lps = kmp.getLPSArray(pattern);

        assertArrayEquals(new int[]{0, 1, 0, 1, 2, 0, 1, 2, 3, 4, 5}, lps);
    }

    @Test
    @DisplayName("Test count occurrences")
    void testCountOccurrences() {
        String text = "ABABABABAB";
        String pattern = "ABA";

        int count = kmp.countOccurrences(text, pattern);

        assertEquals(4, count);
    }

    @ParameterizedTest
    @CsvSource({
            "ABCABC, ABC, 2",
            "AAAAA, AA, 4",
            "HELLO, LL, 1",
            "WORLD, Z, 0",
            "MISSISSIPPI, ISSI, 2"
    })
    @DisplayName("Test various pattern matching scenarios")
    void testVariousScenarios(String text, String pattern, int expectedCount) {
        int count = kmp.countOccurrences(text, pattern);
        assertEquals(expectedCount, count);
    }

    @Test
    @DisplayName("Test case sensitivity")
    void testCaseSensitivity() {
        String text = "Hello hello HELLO";
        String pattern = "hello";

        List<Integer> matches = kmp.search(text, pattern);

        assertEquals(1, matches.size());
        assertEquals(6, matches.get(0));
    }

    @Test
    @DisplayName("Test DNA sequence matching")
    void testDNASequence() {
        String text = "GATCGGAAGAGCACACGTCTGAACTCCAGTCAC";
        String pattern = "CACACGTCT";

        List<Integer> matches = kmp.search(text, pattern);

        assertEquals(1, matches.size());
        assertEquals(11, matches.get(0));
    }

    @Test
    @DisplayName("Test performance with long text")
    void testPerformanceWithLongText() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("ABCDEFGH");
        }
        String text = sb.toString();
        String pattern = "DEFG";

        long startTime = System.nanoTime();
        List<Integer> matches = kmp.search(text, pattern);
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0;

        assertEquals(10000, matches.size());
        assertTrue(executionTime < 100);

        System.out.println("Performance test - Text length: " + text.length() +
                ", Matches: " + matches.size() +
                ", Time: " + String.format("%.2f", executionTime) + "ms");
    }

    @Test
    @DisplayName("Test searchFirst method")
    void testSearchFirst() {
        String text = "ABCABCABC";
        String pattern = "ABC";

        int firstMatch = kmp.searchFirst(text, pattern);

        assertEquals(0, firstMatch);
    }

    @Test
    @DisplayName("Test searchFirst when not found")
    void testSearchFirstNotFound() {
        String text = "ABCDEF";
        String pattern = "XYZ";

        int firstMatch = kmp.searchFirst(text, pattern);

        assertEquals(-1, firstMatch);
    }

    @Test
    @DisplayName("Test with special characters")
    void testWithSpecialCharacters() {
        String text = "Hello, World! Hello, Java!";
        String pattern = "Hello,";

        List<Integer> matches = kmp.search(text, pattern);

        assertEquals(2, matches.size());
        assertEquals(Arrays.asList(0, 14), matches);
    }
}