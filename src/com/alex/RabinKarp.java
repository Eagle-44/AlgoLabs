package com.alex;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp {
    private final int asciiChars = 256;
    private final int prime = Integer.MAX_VALUE;
    private String text;
    private String pattern;
    private int lenOfPattern;
    private int lenOfText;
    private int hashOfPattern;
    private int hashOfText;
    private int valueOfHashFunc;
    private List<Integer> matches;

    public static void main(String[] args) {
        RabinKarp r = new RabinKarp();
        r.search("AABAACAADAAABAACAADAAACAADAAABAABAABAABAABA", "AAD");
        System.out.println("Matches at indexes: " + r.getMatches());
    }

    public List<Integer> getMatches() {
        return matches;
    }

    public void search(String text, String pattern) {
        this.text = text;
        this.pattern = pattern;
        lenOfText = text.length();
        lenOfPattern = pattern.length();
        hashOfText = 0;
        hashOfPattern = 0;
        valueOfHashFunc = 1;
        matches = new ArrayList<>();
        hashFunc();
        findHashes();
        for (int i = 0; i <= lenOfText - lenOfPattern; i++) {
            if (hashOfPattern == hashOfText) {
                int amountOfElem;
                for (amountOfElem = 0; amountOfElem < lenOfPattern; amountOfElem++) {
                    if (text.charAt(i + amountOfElem) != pattern.charAt(amountOfElem)) {
                        break;
                    }
                }
                if (amountOfElem == lenOfPattern) {
                    matches.add(i);
                }
            }

            if (i < lenOfText - lenOfPattern) {
                hashOfText = (asciiChars * (hashOfText - text.charAt(i) * valueOfHashFunc) + text.charAt(i + lenOfPattern)) % prime;
                if (hashOfText < 0) {
                    hashOfText = (hashOfText + prime);
                }
            }
        }
    }

    private void findHashes() {
        for (int i = 0; i < lenOfPattern; i++) {
            hashOfPattern = (asciiChars * hashOfPattern + pattern.charAt(i)) % prime;
            hashOfText = (asciiChars * hashOfText + text.charAt(i)) % prime;
        }
    }

    private void hashFunc() {
        valueOfHashFunc = ((int) Math.pow(asciiChars, lenOfPattern - 1)) % prime;
    }
}