package com.lejit.thetravellingman;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Special thanks for andoid pit for the rss reader boilerplate
 */
public class NewsUpdateActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
//    private TextView mRssFeed;
    private RecyclerAdapter mAdapter;
    private List<String> parentRssFeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_update);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GetRSS rssGetter = new GetRSS(getApplicationContext(), viewGroup);
        // setup recycler view
        parentRssFeed = new ArrayList<String>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new RecyclerAdapter(parentRssFeed);
        mRecyclerView.setAdapter(mAdapter);
        rssGetter.execute();
    }

    private class GetRSS extends AsyncTask<Void, Void, List<String>> {
        private Context mContext;
        private View rootView;
        public GetRSS(Context context, View rootView) {
            this.mContext=context;
            this.rootView=rootView;
        }

        @Override
        protected List<String> doInBackground(Void... voids) {
            List<String> result = null;
            try {
                String feed = getRssFeed();
                result = parse(feed);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("ASYN", "ReSUlT IS =" + result);
            return result;
        }
        private List<String> parse(String rssFeed) throws XmlPullParserException, IOException {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xpp.setInput(new StringReader(rssFeed));
            xpp.nextTag();
            return readRss(xpp);
        }


        private String getRssFeed() {
            InputStream in = null;
            String rssFeed = "";
            try {
                // TODO change to Straits times singapore rss
                URL url = new URL("http://www.straitstimes.com/news/sport/rss.xml");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                in = conn.getInputStream();
//                Log.d("ASYN", "OPENING CONNECTION" + in);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];

                for (int count; (count = in.read(buffer)) != -1; ) {
                    out.write(buffer, 0, count);
                }

                byte[] response = out.toByteArray();
                rssFeed = new String(response, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
//                        Log.d("ASYN", "CLOSING CONNECTION" + in);
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return rssFeed;
        }

        private List<String> readRss(XmlPullParser parser)
                throws XmlPullParserException, IOException {
            List<String> items = new ArrayList<>();
            parser.require(XmlPullParser.START_TAG, null, "rss");
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                if (name.equals("channel")) {
                    items.addAll(readChannel(parser));
                } else {
                    skip(parser);
                }
            }
            return items;
        }

        private List<String> readChannel(XmlPullParser parser)
                throws IOException, XmlPullParserException {
            List<String> items = new ArrayList<>();
            parser.require(XmlPullParser.START_TAG, null, "channel");
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                if (name.equals("item")) {
                    items.add(readItem(parser));
                } else {
                    skip(parser);
                }
            }
            return items;
        }

        private String readItem(XmlPullParser parser) throws XmlPullParserException, IOException {
            String result = null;
            parser.require(XmlPullParser.START_TAG, null, "item");
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                if (name.equals("title")) {
                    result = readTitle(parser);
                } else {
                    skip(parser);
                }
            }
            return result;
        }

        // Processes title tags in the feed.
        private String readTitle(XmlPullParser parser)
                throws IOException, XmlPullParserException {
            parser.require(XmlPullParser.START_TAG, null, "title");
            String title = readText(parser);
            parser.require(XmlPullParser.END_TAG, null, "title");
            return title;
        }


        private String readText(XmlPullParser parser)
                throws IOException, XmlPullParserException {
            String result = "";
            if (parser.next() == XmlPullParser.TEXT) {
                result = parser.getText();
                parser.nextTag();
            }
            return result;
        }
        private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                throw new IllegalStateException();
            }
            int depth = 1;
            while (depth != 0) {
                switch (parser.next()) {
                    case XmlPullParser.END_TAG:
                        depth--;
                        break;
                    case XmlPullParser.START_TAG:
                        depth++;
                        break;
                }
            }
        }
        @Override
        protected void onPostExecute(List<String> rssFeed) {
            if (rssFeed != null) {
                parentRssFeed.clear();
                parentRssFeed.addAll(0, rssFeed);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

}

