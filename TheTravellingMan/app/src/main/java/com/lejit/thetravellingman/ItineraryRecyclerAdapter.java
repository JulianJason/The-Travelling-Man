package com.lejit.thetravellingman;

import android.support.v7.widget.RecyclerView;
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
            tempHolder.mTo.setText(dataset.get(dataset.size() - 1).getTo());
            tempHolder.mFrom.setText("Estimated total \ncost to ");
        } else {
            tempHolder.mFrom.setText(dataset.get(position - 1).getTo());
            tempHolder.mTo.setText(item.getTo());
        }

        tempHolder.mCost.setText("SGD " + item.getCost());
        tempHolder.mTime.setText(item.getTime());
        tempHolder.mMethodOfTransport.setText(item.getMethod());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
