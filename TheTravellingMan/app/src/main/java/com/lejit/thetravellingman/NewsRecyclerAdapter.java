package com.lejit.thetravellingman;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lejit.thetravellingman.Model.RssData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11/18/2016.
 */

public class NewsRecyclerAdapter extends RecyclerView.Adapter {
    private List<RssData> dataset;
    public NewsRecyclerAdapter(List<RssData> data) {
        if (data != null) {
            this.dataset = data;
        } else {
            this.dataset = new ArrayList<RssData>();
        }
    }
    public static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mItemTitle;
        private TextView mItemDescription;

        private Holder(View v) {
            super(v);
            mItemTitle = (TextView) v.findViewById(R.id.item_title);
            mItemDescription = (TextView) v.findViewById(R.id.item_description);

            // TODO set proper onclick listener
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
        }

        public void bind() {
        }
    }

    @Override
    public NewsRecyclerAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_rss_row, parent, false);
        return new Holder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final RssData item = dataset.get(position);
        final Holder tempHolder = (Holder) holder;
        tempHolder.mItemTitle.setText(item.getTitle());
        tempHolder.mItemDescription.setText(item.getDesc());
        View.OnClickListener linkListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                String url = item.getLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        };

        tempHolder.mItemTitle.setOnClickListener(linkListener);
        tempHolder.mItemDescription.setOnClickListener(linkListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}

