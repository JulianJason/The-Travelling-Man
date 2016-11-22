package com.lejit.thetravellingman;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.lejit.thetravellingman.Attraction_Resources.DestinationMatrix_HASH;
import com.lejit.thetravellingman.Model.ItineraryRow;
import com.lejit.thetravellingman.OptimalSolver.getOptimizedSolution;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lejit.thetravellingman.WordDistance.getWordCorrectionList;

/**
 * Created by USER on 11/20/2016.
 */

public class ItineraryActivity extends AppCompatActivity {
    private MultiAutoCompleteTextView destinationInput;
    private EditText budgetInput;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ItineraryRecyclerAdapter mAdapter;
    // initiate lists
    public List<String> attraction_input = new ArrayList<String>(); // attraction list inputted by the user
    public List<String> attraction_list = new ArrayList<String>();
    public List<ItineraryRow> parentItineraryRowList = new ArrayList<ItineraryRow>();;
    // initiate default variables

    getOptimizedSolution solutionSolver;
    @Override
    protected void onDestroy() {

        super.onDestroy();
        if (mRecyclerView != null) {
            mRecyclerView.setLayoutManager(null);
            mRecyclerView.setAdapter(null);
            mRecyclerView = null;
        }
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);
        solutionSolver = new getOptimizedSolution();
        clearList();
        loadList();
        parentItineraryRowList = new ArrayList<ItineraryRow>();
        mRecyclerView = (RecyclerView) findViewById(R.id.itineraryRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new ItineraryRecyclerAdapter(parentItineraryRowList);
        mRecyclerView.setAdapter(mAdapter);
        setInputButtonListener();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_information) {
                    Intent intent = new Intent(getApplicationContext(),AboutSG.class);
                    startActivity(intent);
                }else if(tabId == R.id.tab_food){
                    Intent intent = new Intent(getApplicationContext(),NewsUpdateActivity.class);
                    startActivity(intent);
                }else if(tabId == R.id.tab_home){

                }else if(tabId == R.id.tab_SOS){
                    Intent intent = new Intent(getApplicationContext(), MainActivity_Emergency.class);
                    startActivity(intent);
                }else if(tabId == R.id.tab_Updates){
                    Intent intent = new Intent(getApplicationContext(), NewsUpdateActivity.class);
                    startActivity(intent);
                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_information) {
                    Intent intent = new Intent(getApplicationContext(),AboutSG.class);
                    startActivity(intent);
                }else if(tabId == R.id.tab_food){
                    Intent intent = new Intent(getApplicationContext(),NewsUpdateActivity.class);
                    startActivity(intent);
                }else if(tabId == R.id.tab_home){
                    Toast.makeText(getApplicationContext(),"Home Page",Toast.LENGTH_SHORT).show();
                }else if(tabId == R.id.tab_SOS){
                    Intent intent = new Intent(getApplicationContext(), MainActivity_Emergency.class);
                    startActivity(intent);
                }else if(tabId == R.id.tab_Updates){
                    Intent intent = new Intent(getApplicationContext(), NewsUpdateActivity.class);
                    startActivity(intent);
                }
            }
        });
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

    private void setInputButtonListener() {
        Button inputButton = (Button) findViewById(R.id.inputButton);
        destinationInput = (MultiAutoCompleteTextView) findViewById(R.id.attractionInputTextView);
        budgetInput = (EditText) findViewById(R.id.budgetInput);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unprocessedData = destinationInput.getText().toString();
                double budget = 100;
                if (!budgetInput.getText().toString().isEmpty()) {
                    budget = Double.parseDouble(budgetInput.getText().toString());
                }
                RouteAsyncHelper asyncHelper = new RouteAsyncHelper(getApplicationContext(), unprocessedData, budget);
                asyncHelper.execute();
            }
        });
    }



    private class RouteAsyncHelper extends AsyncTask<Void, Void, List<ItineraryRow>> {
        private Context mContext;
        private View rootView;
        private String unprocessedData;
        private double budget;
        public RouteAsyncHelper(Context context, String unprocessedData, double budget) {
            this.mContext=context;
            this.unprocessedData = unprocessedData;
            this.budget = budget;
        }

        @Override
        protected List<ItineraryRow> doInBackground(Void... voids) {
            List<ItineraryRow> result = new ArrayList<ItineraryRow>();
            try {
                if (!unprocessedData.isEmpty()) {
                    List<String> splittedData = Arrays.asList(unprocessedData.split(",\\s?"));
                    attraction_input = new ArrayList<String>();
                    attraction_input.clear();
                    for (String s : splittedData) {
                        attraction_input.add(getWordCorrectionList(s));
                    }
//                    attraction_input.addAll((splittedData));
                    result = solutionSolver.findOptimalPath(attraction_input, budget);

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

            if (itineraryRows.isEmpty()) {
            } else {
                if (itineraryRows.get(itineraryRows.size() - 1).getCost() == null || itineraryRows.get(itineraryRows.size() - 1).getTime() == null) {
                    itineraryRows.remove(itineraryRows.size() -1);
                }
                parentItineraryRowList.addAll(0, itineraryRows);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

}

