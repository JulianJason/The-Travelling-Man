package com.lejit.thetravellingman;

import java.util.ArrayList;

/**
 * Created by USER on 11/20/2016.
 */

public class WordDistanceJava {

    //****************************
    // Get minimum of three values
    //****************************

    private static int Minimum (int a, int b, int c) {
        int mi;

        mi = a;
        if (b < mi) {
            mi = b;
        }
        if (c < mi) {
            mi = c;
        }
        return mi;

    }

    //*****************************
    // Compute Levenshtein distance
    //*****************************

    public static int LD (String s, String t) {
        int cost;
        s = s.toLowerCase();
        t = t.toLowerCase();
        int m = s.length();
        int n = t.length();

        // prerequisite for some optimized operation
        if (m < n) {
            // swap
            String temp = s;
            s = t;
            t = temp;

            int temp2 = m;
            m = n;
            n = temp2;
        }

        int[][] out = new int[m + 1][n + 1];

        for (int x = 0; x < n + 1; x++) {
            out[0][x] = x;
        }
        for (int i = 1; i < m + 1; i++) {
            out[i][0] = i;
            for (int j = 1; j < n + 1; j++) {
                cost = s.charAt(i - 1) == t.charAt(j - 1) ? 0 : 1;
                out[i][j] = Minimum(out[i - 1][j] + 1, out[i][j - 1] + 1, out[i - 1][j-1] + cost);

            }
        }
        return out[m][n];
    }

    public static ArrayList<String> getWordCorrectionList(String input)    {
        String[] languages = {"Abdul Gaffoor Mosque", "ArtScience Museum", "Buddha Tooth Relic Temple", "Bukit Timah Nature Reserve", "Central Sikh Temple", "Changi Prison Chapel and Museum", "Chijmes", "Chinatown Heritage Centre", "East Coast Park", "Esplanade - Theatres on the Bay", "Fort Canning Park", "Gardens by the Bay", "Geylang Serai Market", "Haw Par Villa", "Istana", "Jurong Bird Park", "Katong", "Kranji War Memorial", "Kusu Island", "Lau Pa Sat", "Malay Village", "Marina Barrage", "Marina Bay Sands", "Maxwell Road Hawker Centre", "Merlion Park", "Mount Faber", "NEWater Vsitor Centre", "Old Parliament House", "Pulau Ubin", "Raffles Place", "Resort World Sentosa", "Singapore Art Museum", "Singapore Flyer", "Singapore Night Safari", "Singapore River", "Science Centre Singapore", "Singapore Zoo", "Singapore Mint Coin Gallery", "Sri Srinivasa Perumal Temple", "St Andrew's Cathedral", "St John's Island", "Sungei Buloh Nature Park", "Supreme Court and City Hall", "Temple of 1000 lights", "The Padang", "Treetop Walk at MacRitchie Reservoir", "Underwater World", "Universal Studios Singapore"};
//        String[] languages = {"Park"};
        ArrayList<String> dropdownlist = new ArrayList<>();
        String[] inputList = input.split("\\s+");
        // if there are more than 2 words, there is a high change
        // that someone is looking at something specific, use Matthew's algo
        if (inputList.length > 2) {
            for (String s : languages) {
                if (input.charAt(0) == s.charAt(0)) {
                    if (s.length() < 15) {
                        if (LD(input, s) <= 4) {
                            dropdownlist.add(s);
                        }
                    } else if (LD(input, s) <= 11) {
                        dropdownlist.add(s);
                    }
                }
                String regex = ".{1,}?" + input;
                if (s.matches(regex)) {
                    dropdownlist.add(s);
                }
            }
        } else {
            for (String attraction : languages) {
                String attractionList[] = attraction.split("\\s+");
                innerloop:
                for (String attractionWord : attractionList) {
                    for (String word : inputList) {

                        int len = attractionWord.length();
                        int wordLen = word.length();
                        int biggerLen = len > wordLen ? len : wordLen;
                        double bound = Math.ceil(biggerLen / 2);
                        int a = LD(word, attractionWord);
                        if (a < bound) {
                            dropdownlist.add(attraction);
                            break innerloop;
                        }
                    }
                }
            }
        }
        return dropdownlist;
    }

    public static void main(String[] args) {
//        System.out.println(getWordCorrectionList("Park"));
//        System.out.println(getWordCorrectionList("Gardens by the"));
    }

}
