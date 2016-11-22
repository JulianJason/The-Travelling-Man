package com.lejit.thetravellingman;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.lejit.thetravellingman.Attraction_Resources.DestinationMatrix_HASH;
import com.lejit.thetravellingman.Model.ItineraryRow;
import com.lejit.thetravellingman.OptimalSolver.getOptimizedSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lejit.thetravellingman.WordDistance.getWordCorrectionList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItenaryPlanner.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItenaryPlanner#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItenaryPlanner extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    private OnFragmentInteractionListener mListener;

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

    public ItenaryPlanner() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //     * @param param1 Parameter 1.
     //     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePage.
     */
    // TODO: Rename and change types and number of parameters
    public static ItenaryPlanner newInstance(){ //String param1, String param2) {
        ItenaryPlanner fragment = new ItenaryPlanner();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_itinerary, container, false);  //fragment_itenary_planner
        solutionSolver = new getOptimizedSolution();
        clearList();
        loadList(view);
        parentItineraryRowList = new ArrayList<>();
        mRecyclerView = (RecyclerView)view.findViewById(R.id.itineraryRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new ItineraryRecyclerAdapter(parentItineraryRowList);
        mRecyclerView.setAdapter(mAdapter);
        setInputButtonListener(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void clearList() {
        attraction_input.clear();
        attraction_list.clear();
    }
    private void loadList(View view) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.custom_dropdown, DestinationMatrix_HASH.DESTINATIONS); //android.R.simple_dropdown_item_1line
        destinationInput = (MultiAutoCompleteTextView)view.findViewById(R.id.attractionInputTextView);
        destinationInput.setAdapter(adapter);
        destinationInput.setThreshold(2);
        destinationInput.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private void setInputButtonListener(View view) {
        Button inputButton = (Button)view.findViewById(R.id.inputButton);
        destinationInput = (MultiAutoCompleteTextView)view.findViewById(R.id.attractionInputTextView);
        budgetInput = (EditText)view.findViewById(R.id.budgetInput);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unprocessedData = destinationInput.getText().toString();
                double budget = 100;
                if (!budgetInput.getText().toString().isEmpty()) {
                    budget = Double.parseDouble(budgetInput.getText().toString());
                }
                RouteAsyncHelper asyncHelper = new RouteAsyncHelper(getActivity().getApplicationContext(), unprocessedData, budget);
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
//            this.rootView=rootView;
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
                    Toast toast = new Toast(getActivity().getApplicationContext());
                    toast.makeText(getActivity().getApplicationContext(), "Please input destinations", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
        @Override
        protected void onPostExecute(List<ItineraryRow> itineraryRows) {
            parentItineraryRowList.clear();

            Log.d("ASYN", "post execute itinerary row" + itineraryRows.toString());
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
