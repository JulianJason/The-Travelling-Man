package com.lejit.thetravellingman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;


import com.lejit.thetravellingman.Attraction_Resources.DestinationMatrix_HASH;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class itineraryActivity extends AppCompatActivity {
    MultiAutoCompleteTextView destinationInput;
    ListView scroll;
    // initiate lists
    public List<String> attraction_input = new ArrayList<String>(); // attraction list inputted by the user
    public List<String> attraction_list = new ArrayList<String>();


    // initiate default variables
    public double budget = 0;
    public double bestTime = 9999;
    public String bestRoute = "";
    public String bestMethod = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        clearList();
        loadList();
        setInputButtonListener();
    }

    private void clearList() {
        attraction_input.clear();
        attraction_list.clear();
    }
    private void loadList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DestinationMatrix_HASH.DESTINATIONS);
        MultiAutoCompleteTextView destinationInput = (MultiAutoCompleteTextView) findViewById(R.id.attractionInputTextView);
        destinationInput.setAdapter(adapter);
        destinationInput.setThreshold(2);
        destinationInput.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        ListView scroll = (ListView) findViewById(R.id.DestinationListView);
        ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, attraction_input);
        scroll.setAdapter(listAdapter);
    }

    private void setInputButtonListener() {
        Button inputButton = (Button) findViewById(R.id.inputButton);
        destinationInput = (MultiAutoCompleteTextView) findViewById(R.id.attractionInputTextView);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unprocessedData = destinationInput.getText().toString();
                Log.d("WORD", "unprocessed data" + unprocessedData);
                String[] processedData = unprocessedData.split(",");
                Log.d("WORD", "processed data" + java.util.Arrays.toString(processedData));
                // TODO a more robust version where a user does not need to input from scratch
                attraction_input.clear();
                attraction_input.addAll(Arrays.asList(processedData));
                Log.d("TIME", "time taken: ");
            }
        });
    }
    private boolean isInsideIgnoreCase(String destination, List<String> inputs){
        for (String string : inputs){
            if (string.equalsIgnoreCase(destination)){
                return true;
            }
        }
        return false;
    }
}
