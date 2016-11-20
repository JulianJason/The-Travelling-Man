package com.lejit.thetravellingman;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lejit.thetravellingman.Model.ItineraryRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11/20/2016.
 */

public class ItineraryRecyclerAdapter extends RecyclerView.Adapter {
    private List<ItineraryRow> dataset;
    public ItineraryRecyclerAdapter(List<ItineraryRow> data) {
        Log.d("ASYN","INITIATED!");
        if (data != null) {
            for (ItineraryRow row : data) {
            }
            this.dataset = data;
            Log.d("DATA", "datasize" + dataset.size());
        } else {
            this.dataset = new ArrayList<ItineraryRow>();
        }
    }
    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //2
        private TextView mFrom;
        private TextView mTo;
        private TextView mCost;
        private TextView mTime;
        private TextView mMethodOfTransport;
        //4
        private Holder(View v) {
            super(v);
            mFrom = (TextView) v.findViewById(R.id.fromResult);
            mTo = (TextView) v.findViewById(R.id.toResult);
            mCost = (TextView) v.findViewById(R.id.costValue);
            mTime = (TextView) v.findViewById(R.id.timeValue);
            mMethodOfTransport = (TextView) v.findViewById(R.id.methodOfTransportText);

            // TODO set proper onclick listener
            v.setOnClickListener(this);
        }

        //5
        @Override
        public void onClick(View v) {
        }

        public void bind() {
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
        if (position != 0) {
            tempHolder.mFrom.setText(dataset.get(position - 1).getTo());
        } else {
            tempHolder.mFrom.setText("START");
        }
        Log.d("DATA", "ITEM " + position + "to" + item.getTo());
        tempHolder.mTo.setText(item.getTo());
        tempHolder.mCost.setText(item.getCost());
        tempHolder.mTime.setText(item.getTime());
        tempHolder.mMethodOfTransport.setText(item.getMethod());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
