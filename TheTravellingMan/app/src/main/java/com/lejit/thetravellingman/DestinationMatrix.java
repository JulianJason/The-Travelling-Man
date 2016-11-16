package com.lejit.thetravellingman;
import java.util.*; // <- bad idea

/**
 * Created by MY LENOVO on 10/11/2016.
 */

/*

 */
public class DestinationMatrix {
    public static final HashMap<String,Integer> destination_matrix=new HashMap<String, Integer>();
    static {
        destination_matrix.put("Marina Bay Sands",0);
        destination_matrix.put("Singapore Flyer",1);
        destination_matrix.put("VivoCity",2);
        destination_matrix.put("Resorts World Sentosa",3);
        destination_matrix.put("Buddha Tooth Relic Temple",4);
        destination_matrix.put("The Singapore Zoo",5);
    }
    public static final String[] DESTINATIONS = new String[] {
            "Marina Bay Sands", "Singapore Flyer", "VivoCity", "Resorts World Sentosa", "Buddha Tooth Relic Temple", "The Singapore Zoo"
    };
    final static int TotNum=destination_matrix.size();
    public static final double [][][] costArray=new double [3][TotNum][TotNum];
    static {
        // bus
        costArray[1][0][1]=0.83; // by bus, from marina bay to singapore flyer
        costArray[1][0][2]=1.18;
        costArray[1][0][3]=4.03;
        costArray[1][0][4]=0.88;
        costArray[1][0][5]=1.96;

        costArray[1][1][1]=0.83;
        costArray[1][1][2]=1.26;
        costArray[1][1][3]=4.03;
        costArray[1][1][4]=0.98;
        costArray[1][1][5]=1.89;


        costArray[1][2][1]=1.18;
        costArray[1][2][2]=1.26;
        costArray[1][2][3]=2.00;
        costArray[1][2][4]=0.98;
        costArray[1][2][5]=1.99;

        costArray[1][3][1]=1.18;
        costArray[1][3][2]=1.16;
        costArray[1][3][3]=0.00;
        costArray[1][3][4]=0.98;
        costArray[1][3][5]=1.99;

        costArray[1][4][1]=0.88;
        costArray[1][4][2]=0.98;
        costArray[1][4][3]=0.98;
        costArray[1][4][4]=3.98;
        costArray[1][4][5]=1.91;

        costArray[1][5][1]=1.88;
        costArray[1][5][2]=1.96;
        costArray[1][5][3]=2.11;
        costArray[1][5][4]=4.99;
        costArray[1][5][5]=1.99;

        //taxi
        costArray[2][0][1]=10.77;
        costArray[2][0][2]=10.00;
        costArray[2][0][3]=14.10;
        costArray[2][0][4]=9.15;
        costArray[2][0][5]=24.85;

        costArray[2][1][1]=10.77;
        costArray[2][1][2]=10.82;
        costArray[2][1][3]=11.38;
        costArray[2][1][4]=9.70;
        costArray[2][1][5]=23.75;

        costArray[2][2][1]=15.45;
        costArray[2][2][2]=12.18;
        costArray[2][2][3]=9.15;
        costArray[2][2][4]=9.97;
        costArray[2][2][5]=27.60;

        costArray[2][3][1]=13.82;
        costArray[2][3][2]=14.92;
        costArray[2][3][3]=9.15;
        costArray[2][3][4]=11.90;
        costArray[2][3][5]=33.62;

        costArray[2][4][1]=9.15;
        costArray[2][4][2]=9.70;
        costArray[2][4][3]=9.97;
        costArray[2][4][4]=11.62;
        costArray[2][4][5]=28.95;

        costArray[2][5][1]=24.58;
        costArray[2][5][2]=23.75;
        costArray[2][5][3]=27.60;
        costArray[2][5][4]=38.62;
        costArray[2][5][5]=28.95;

    }
    public static final double[][][] timeArray=new double[3][TotNum][TotNum];
    static {
        timeArray[0][0][1] = 14;
        timeArray[0][0][2] = 69;
        timeArray[0][0][3] = 76;
        timeArray[0][0][4] = 28;
        timeArray[0][0][5] = 269;

        timeArray[0][1][0] = 14;
        timeArray[0][1][2] = 81;
        timeArray[0][1][3] = 88;
        timeArray[0][1][4] = 39;
        timeArray[0][1][5] = 264;

        timeArray[0][2][0] = 69;
        timeArray[0][2][1] = 81;
        timeArray[0][2][3] = 12;
        timeArray[0][2][4] = 47;
        timeArray[0][2][5] = 270;

        timeArray[0][3][0] = 76;
        timeArray[0][3][1] = 88;
        timeArray[0][3][2] = 12;
        timeArray[0][3][4] = 55;
        timeArray[0][3][5] = 285;

        timeArray[0][4][0] = 28;
        timeArray[0][4][1] = 39;
        timeArray[0][4][2] = 47;
        timeArray[0][4][3] = 55;
        timeArray[0][4][5] = 264;

        timeArray[0][5][0] = 269;
        timeArray[0][5][1] = 264;
        timeArray[0][5][2] = 270;
        timeArray[0][5][3] = 285;
        timeArray[0][5][4] = 264;

        //Bus time
        timeArray[1][0][1] = 17;
        timeArray[1][0][2] = 26;
        timeArray[1][0][3] = 35;
        timeArray[1][0][4] = 19;
        timeArray[1][0][5] = 84;

        timeArray[1][1][0] = 17;
        timeArray[1][1][2] = 31;
        timeArray[1][1][3] = 38;
        timeArray[1][1][4] = 24;
        timeArray[1][1][5] = 85;

        timeArray[1][2][0] = 24;
        timeArray[1][2][1] = 29;
        timeArray[1][2][3] = 10;
        timeArray[1][2][4] = 18;
        timeArray[1][2][5] = 85;

        timeArray[1][3][0] = 33;
        timeArray[1][3][1] = 38;
        timeArray[1][3][2] = 10;
        timeArray[1][3][4] = 27;
        timeArray[1][3][5] = 92;

        timeArray[1][4][0] = 18;
        timeArray[1][4][1] = 23;
        timeArray[1][4][2] = 19;
        timeArray[1][4][3] = 28;
        timeArray[1][4][5] = 83;

        timeArray[1][5][0] = 86;
        timeArray[1][5][1] = 87;
        timeArray[1][5][2] = 86;
        timeArray[1][5][3] = 96;
        timeArray[1][5][4] = 84;

        // Taxi cost
        timeArray[2][0][1] = 3;
        timeArray[2][0][2] = 14;
        timeArray[2][0][3] = 19;
        timeArray[2][0][4] = 8;
        timeArray[2][0][5] = 30;

        timeArray[2][1][0] = 6;
        timeArray[2][1][2] = 13;
        timeArray[2][1][3] = 18;
        timeArray[2][1][4] = 8;
        timeArray[2][1][5] = 29;

        timeArray[2][2][0] = 12;
        timeArray[2][2][1] = 14;
        timeArray[2][2][3] = 9;
        timeArray[2][2][4] = 11;
        timeArray[2][2][5] = 31;

        timeArray[2][3][0] = 13;
        timeArray[2][3][1] = 14;
        timeArray[2][3][2] = 4;
        timeArray[2][3][4] = 12;
        timeArray[2][3][5] = 32;

        timeArray[2][4][0] = 7;
        timeArray[2][4][1] = 8;
        timeArray[2][4][2] = 9;
        timeArray[2][4][3] = 14;
        timeArray[2][4][5] = 30;

        timeArray[2][5][0] = 32;
        timeArray[2][5][1] = 29;
        timeArray[2][5][2] = 32;
        timeArray[2][5][3] = 36;
        timeArray[2][5][4] = 30;
    }

}
