package com.stringalgorithm.kmp;

import java.util.ArrayList;
import java.util.List;

public class KMPAlgorithm {

    private int[] computeLPSArray(String pattern) {
        // Related to the example from lecture 9
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        lps[0] = 0;
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
    // Searches for all occurrences of a pattern in the given text using KMP algorithm

    public List<Integer> search(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();


        // Handle edge cases
        if (text == null || pattern == null || pattern.isEmpty() || text.length() < pattern.length()) {
            return matches;
        }

        int n = text.length();
        int m = pattern.length();
        int[] lps = computeLPSArray(pattern);
        int i = 0;
        int j = 0;

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                matches.add(i - j);
                // Continue searching for next occurrence
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    // No previous match, just advance text pointer
                    i++;
                }
            }
        }

        return matches;
    }

    // Searches for the first occurrence of a pattern in the given text
    public int searchFirst(String text, String pattern) {
        List<Integer> matches = search(text, pattern);
        return matches.isEmpty() ? -1 : matches.get(0);
    }
    // Counts the number of occurrences of a pattern in the text
    public int countOccurrences(String text, String pattern) {
        return search(text, pattern).size();
    }
    // Returns the LPS array for visualization and debugging purposes
    public int[] getLPSArray(String pattern) {
        return computeLPSArray(pattern);
    }
    // Prints detailed information about the search process
    public void printSearchDetails(String text, String pattern) {
        System.out.println("=".repeat(80));
        System.out.println("KMP String Matching Algorithm - Detailed Analysis");
        System.out.println("=".repeat(80));
        System.out.println("Text: \"" + text + "\"");
        System.out.println("Text Length: " + text.length());
        System.out.println("Pattern: \"" + pattern + "\"");
        System.out.println("Pattern Length: " + pattern.length());
        System.out.println("-".repeat(80));

        int[] lps = computeLPSArray(pattern);
        System.out.println("LPS Array (Longest Proper Prefix which is also Suffix):");
        System.out.print("Index:   ");
        for (int i = 0; i < pattern.length(); i++) {
            System.out.printf("%3d ", i);
        }
        System.out.println();

        System.out.print("Pattern: ");
        for (int i = 0; i < pattern.length(); i++) {
            System.out.printf("%3c ", pattern.charAt(i));
        }
        System.out.println();

        System.out.print("LPS:     ");
        for (int i = 0; i < lps.length; i++) {
            System.out.printf("%3d ", lps[i]);
        }
        System.out.println();
        System.out.println("-".repeat(80));

        long startTime = System.nanoTime();
        List<Integer> matches = search(text, pattern);
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0;

        System.out.println("Search Results:");
        System.out.println("Number of matches found: " + matches.size());

        if (!matches.isEmpty()) {
            System.out.println("Match positions: " + matches);
            System.out.println("\nMatches in context:");
            for (int pos : matches) {
                int start = Math.max(0, pos - 10);
                int end = Math.min(text.length(), pos + pattern.length() + 10);
                String context = text.substring(start, end);
                String marker = " ".repeat(Math.min(10, pos - start)) + "^".repeat(pattern.length());
                System.out.println("  Position " + pos + ": \"" + context + "\"");
                System.out.println("  " + " ".repeat(15 + Math.min(10, pos - start)) + marker);
            }
        } else {
            System.out.println("No matches found.");
        }

        System.out.println("-".repeat(80));
        System.out.printf("Execution Time: %.4f ms%n", executionTime);
        System.out.println("Time Complexity: O(n + m) = O(" + text.length() + " + " + pattern.length() + ")");
        System.out.println("Space Complexity: O(m) = O(" + pattern.length() + ")");
        System.out.println("=".repeat(80));
        System.out.println();
    }
}