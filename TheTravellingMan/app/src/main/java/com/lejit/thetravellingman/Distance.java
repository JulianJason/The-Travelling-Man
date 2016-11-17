package com.lejit.thetravellingman;

import java.util.ArrayList;

/**
 * Created by Matthew on 10/11/2016.
 */

/**
 * How to use
 *
 */
public class Distance {

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
        int d[][]; // matrix
        int n; // length of s
        int m; // length of t
        int i; // iterates through s
        int j; // iterates through t
        char s_i; // ith character of s
        char t_j; // jth character of t
        int cost; // cost

        // Step 1

        n = s.length ();
        m = t.length ();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n+1][m+1];

        // Step 2

        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        // Step 3

        for (i = 1; i <= n; i++) {

            s_i = s.charAt (i - 1);

            // Step 4

            for (j = 1; j <= m; j++) {

                t_j = t.charAt (j - 1);

                // Step 5

                if (s_i == t_j) {
                    cost = 0;
                }
                else {
                    cost = 1;
                }

                // Step 6

                d[i][j] = Minimum (d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1] + cost);

            }

        }

        // Step 7

        return d[n][m];

    }

    public static ArrayList<String> getWordCorrectionList(String input)    {
        String[] languages = {"Abdul Gaffoor Mosque", "ArtScience Museum", "Buddha Tooth Relic Temple", "Bukit Timah Nature Reserve", "Central Sikh Temple", "Changi Prison Chapel and Museum", "Chijmes", "Chinatown Heritage Centre", "East Coast Park", "Esplanade - Theatres on the Bay", "Fort Canning Park", "Gardens by the Bay", "Geylang Serai Market", "Haw Par Villa", "Istana", "Jurong Bird Park", "Katong", "Kranji War Memorial", "Kusu Island", "Lau Pa Sat", "Malay Village", "Marina Barrage", "Marina Bay Sands", "Maxwell Road Hawker Centre", "Merlion Park", "Mount Faber", "NEWater Vsitor Centre", "Old Parliament House", "Pulau Ubin", "Raffles Place", "Resort World Sentosa", "Singapore Art Museum", "Singapore Flyer", "Singapore Night Safari", "Singapore River", "Science Centre Singapore", "Singapore Zoo", "Singapore Mint Coin Gallery", "Sri Srinivasa Perumal Temple", "St Andrew's Cathedral", "St John's Island", "Sungei Buloh Nature Park", "Supreme Court and City Hall", "Temple of 1000 lights", "The Padang", "Treetop Walk at MacRitchie Reservoir", "Underwater World", "Universal Studios Singapore"};
        ArrayList<String> dropdownlist = new ArrayList<>();
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
        return dropdownlist;
    }

    public static void main(String[] args) {
        System.out.println(getWordCorrectionList("sentoza"));
    }
}