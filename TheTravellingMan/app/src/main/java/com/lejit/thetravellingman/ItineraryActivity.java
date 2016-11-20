package com.lejit.thetravellingman;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.lejit.thetravellingman.Attraction_Resources.DestinationMatrix_HASH;
import com.lejit.thetravellingman.Model.ItineraryRow;
import com.lejit.thetravellingman.OptimalSolver.getOptimizedSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by USER on 11/20/2016.
 */

public class ItineraryActivity extends AppCompatActivity {
    private MultiAutoCompleteTextView destinationInput;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ItineraryRecyclerAdapter mAdapter;
    // initiate lists
    public List<String> attraction_input = new ArrayList<String>(); // attraction list inputted by the user
    public List<String> attraction_list = new ArrayList<String>();
    public List<ItineraryRow> parentItineraryRowList;
    // initiate default variables
    public double budget = 0;
    public double bestTime = 9999;
    public String bestRoute = "";
    public String bestMethod = "";
    getOptimizedSolution solutionSolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);
        solutionSolver = new getOptimizedSolution();
        clearList();
        loadList();
        mRecyclerView = (RecyclerView) findViewById(R.id.itineraryRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        parentItineraryRowList = new ArrayList<ItineraryRow>();
        mAdapter = new ItineraryRecyclerAdapter(parentItineraryRowList);
        mRecyclerView.setAdapter(mAdapter);
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
    }
//
    private void setInputButtonListener() {
        Button inputButton = (Button) findViewById(R.id.inputButton);
        destinationInput = (MultiAutoCompleteTextView) findViewById(R.id.attractionInputTextView);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unprocessedData = destinationInput.getText().toString();
                RouteAsyncHelper asyncHelper = new RouteAsyncHelper(getApplicationContext(), unprocessedData);
                asyncHelper.execute();
            }
        });
    }

    private class RouteAsyncHelper extends AsyncTask<Void, Void, List<ItineraryRow>> {
        private Context mContext;
        private View rootView;
        private String unprocessedData;
        public RouteAsyncHelper(Context context, String unprocessedData) {
            this.mContext=context;
//            this.rootView=rootView;
            this.unprocessedData = unprocessedData;
        }

        @Override
        protected List<ItineraryRow> doInBackground(Void... voids) {
            List<ItineraryRow> result = null;
            try {
                if (!unprocessedData.isEmpty()) {
                    List<String> splittedData = Arrays.asList(unprocessedData.split(",\\s?"));
                    attraction_input = new ArrayList<String>();
                    attraction_input.clear();
                    attraction_input.addAll((splittedData));
                    result = solutionSolver.findOptimalPath(attraction_input, 999);

                } else {
                    Toast toast = new Toast(getApplicationContext());
                    toast.makeText(getApplicationContext(), "Please input destinations", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
        @Override
        protected void onPostExecute(List<ItineraryRow> itineraryRows) {
            parentItineraryRowList.clear();
            Log.d("ASYN", "post execute itinerary row" + itineraryRows);
            if (itineraryRows.isEmpty()) {

            } else {
                parentItineraryRowList.addAll(0, itineraryRows);
            }

            mAdapter.notifyDataSetChanged();
        }
    }
}

