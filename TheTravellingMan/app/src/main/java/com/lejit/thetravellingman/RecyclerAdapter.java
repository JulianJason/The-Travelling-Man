package com.lejit.thetravellingman;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11/18/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {
    private List<String> dataset;
    public RecyclerAdapter(List<String> data) {
        Log.d("ASYN", "" + data);
        if (data != null) {
            this.dataset = data;
            Log.d("ASYN", "DATASET =" + this.dataset.toString());
        } else {
            Log.d("ASYN", "CLEARED DATASET");
            this.dataset = new ArrayList<String>();
        }
    }
    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //2
        private TextView mItemTitle;
        private TextView mItemDescription;
        //4
        private Holder(View v) {
            super(v);
            mItemTitle = (TextView) v.findViewById(R.id.item_title);
            mItemDescription = (TextView) v.findViewById(R.id.item_description);

            // TODO set proper onclick listener
            v.setOnClickListener(this);
        }

        //5
        @Override
        public void onClick(View v) {
            Context context = itemView.getContext();
            Intent linkIntent = new Intent(Intent.ACTION_VIEW);
//            linkIntent.setData(Uri.parse(link));
            context.startActivity(linkIntent);
        }

        public void bind() {
//            mItemDate.setText(photo.getHumanDate());
//            mItemDescription.setText(photo.getExplanation());
        }
    }

    @Override
    public RecyclerAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_rss_row, parent, false);
        return new Holder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final String item = dataset.get(position);
        final Holder tempHolder = (Holder) holder;
        tempHolder.mItemTitle.setText("aaa");
        tempHolder.mItemDescription.setText("descriptionHere");
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}

