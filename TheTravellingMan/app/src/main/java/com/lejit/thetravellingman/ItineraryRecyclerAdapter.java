package com.lejit.thetravellingman;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lejit.thetravellingman.Model.ItineraryRow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by USER on 11/20/2016.
 */

public class ItineraryRecyclerAdapter extends RecyclerView.Adapter {
    private List<ItineraryRow> dataset;
    List<Address> matchedList;

    public ItineraryRecyclerAdapter(List<ItineraryRow> data) {
        if (data != null) {
            this.dataset = data;
        } else {
            this.dataset = new ArrayList<ItineraryRow>();
        }
    }
    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mFrom;
        private TextView mTo;
        private TextView mCost;
        private TextView mTime;
        private TextView mMethodOfTransport;
        private ImageView location;


        private Holder(View v) {
            super(v);
            mFrom = (TextView) v.findViewById(R.id.fromResult);
            mTo = (TextView) v.findViewById(R.id.toResult);
            mCost = (TextView) v.findViewById(R.id.costValue);
            mTime = (TextView) v.findViewById(R.id.timeValue);
            mMethodOfTransport = (TextView) v.findViewById(R.id.methodOfTransportText);
            location = (ImageView)v.findViewById(R.id.location);

            // TODO set proper onclick listener
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            // do nothing
        }
    }

    @Override
    public ItineraryRecyclerAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_itinerary_row, parent, false);
        return new ItineraryRecyclerAdapter.Holder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItineraryRow item = dataset.get(position);
        final ItineraryRecyclerAdapter.Holder tempHolder = (ItineraryRecyclerAdapter.Holder) holder;
        if (position == 0) {
            tempHolder.mTo.setText(dataset.get(0).getTo());  //dataset.size() - 1
            tempHolder.mFrom.setText("Estimated total \ncost from ");
            tempHolder.mTime.setText(item.getTime()+" min");
        } else {
            tempHolder.mFrom.setText(dataset.get(position - 1).getTo());
            tempHolder.mTo.setText(item.getTo());
            tempHolder.mTime.setText(item.getTime());
        }

        tempHolder.mCost.setText("SGD " + item.getCost());

        tempHolder.mMethodOfTransport.setText(item.getMethod());

        tempHolder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Geocoder myGcdr = new Geocoder(v.getContext(), Locale.getDefault());
                TextView LocationString = tempHolder.mTo;
                try {
                    matchedList=myGcdr.getFromLocationName(LocationString.getText().toString(),1);
                }
                catch(IOException e) {
                    Toast.makeText(v.getContext(),"Not able to find location: "+e.getMessage(),Toast.LENGTH_LONG).show();
                }
                Intent myIntent = new Intent(Intent.ACTION_VIEW);
                String lat="";
                String lon="";

                try {
                    lat=String.valueOf(matchedList.get(0).getLatitude());
                    lon=String.valueOf(matchedList.get(0).getLongitude());
                }
                catch (Exception e) {
                    Toast.makeText(v.getContext(), "A problem occurred in retrieving location", Toast.LENGTH_LONG).show();
                }
                String query = LocationString.getText().toString();
                String encoded = Uri.encode(query);
                myIntent.setData(Uri.parse("geo:"+lat+","+lon+"?q="+encoded));
                Intent chooser=Intent.createChooser(myIntent,"Launch Maps");
                v.getContext().startActivity(chooser);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
