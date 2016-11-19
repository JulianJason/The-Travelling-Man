package com.lejit.thetravellingman;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;


public class MainActivity extends AppCompatActivity
        implements HomePage.OnFragmentInteractionListener{

    List<Address> matchedList;
    List<Integer> buttonList = Arrays.asList(R.id.first,R.id.map1,R.id.second,R.id.map2);
    EditText LocationString;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_information) {
                    Intent intent = new Intent(getApplicationContext(),itineraryActivity.class);
                    startActivity(intent);
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                }else if(tabId == R.id.tab_food){
                    Intent intent = new Intent(getApplicationContext(),itineraryActivity.class);
                    startActivity(intent);
                }else if(tabId == R.id.tab_home){
                    Intent intent = new Intent(getApplicationContext(),itineraryActivity.class);
                    startActivity(intent);
                }else if(tabId == R.id.tab_SOS){
                    Intent intent = new Intent(getApplicationContext(),itineraryActivity.class);
                    startActivity(intent);
                }else if(tabId == R.id.tab_Updates){
                    Intent intent = new Intent(getApplicationContext(),itineraryActivity.class);
                    startActivity(intent);
                }
            }
        });

//        final TabHost host = (TabHost) findViewById(R.id.tabHost);
//        host.setup();
//        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String tabId) {
//                for (int i = 0; i < host.getTabWidget().getChildCount(); i++) {
//                    host.getTabWidget().getChildAt(i)
//                            .setBackgroundResource(R.drawable.tab_selected); // unselected
//                }
//                host.getTabWidget().getChildAt(host.getCurrentTab())
//                        .setBackgroundResource(R.drawable.tab_unselected); // selected
//            }
//        });
//
//        //Tab 1
//        TabHost.TabSpec spec = host.newTabSpec("Tab One");
//        spec.setContent(R.id.tab1);
//        spec.setIndicator("Tab One");
//
//        host.addTab(spec);
//
//        //Tab 2
//        spec = host.newTabSpec("Tab Two");
//        spec.setContent(R.id.tab2);
//        spec.setIndicator("Tab Two");
//        host.addTab(spec);
//
//        //Tab 3
//        spec = host.newTabSpec("Tab Three");
//        spec.setContent(R.id.tab3);
//        spec.setIndicator("Tab Three");
//        host.addTab(spec);
//
//        for(int i=0;i<host.getTabWidget().getChildCount();i++){
//            host.getTabWidget().getChildAt(i)
//                    .setBackgroundResource(R.drawable.tab_unselected); // unselected
//        }
//        host.getTabWidget().setCurrentTab(1);
//        host.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#C35817"));


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }

//    public void tabOne(View view){
//        Intent intent = new Intent(this,itineraryActivity.class);
//        startActivity(intent);
//        //host.setCurrentTab(tab);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.language_array, android.R.layout.simple_spinner_item);

            // Specify the layout to use when the list of choices appears

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0: return PlaceholderFragment.newInstance(position+1);
                case 1 : return HomePage.newInstance();
                default: return  PlaceholderFragment.newInstance(position+1);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
    //map function
    public void map(View v) {
        for(int i=0;i<buttonList.size();i++){
            if(buttonList.get(i)==v.getId()){
                LocationString = (EditText)findViewById(buttonList.get(i-1));
            }

        }

        Geocoder myGcdr = new Geocoder(this, Locale.getDefault());

        try {
            matchedList=myGcdr.getFromLocationName(LocationString.getText().toString(),1);
        }
        catch(IOException e) {
            Toast.makeText(this,"Not able to find location: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
        Intent myIntent = new Intent(Intent.ACTION_VIEW);
        String lat="";
        String lon="";

        try {
            lat=String.valueOf(matchedList.get(0).getLatitude());
            lon=String.valueOf(matchedList.get(0).getLongitude());
        }
        catch (Exception e) {
            Toast.makeText(this, "A problem occurred in retrieving location", Toast.LENGTH_LONG).show();
        }
        String query = LocationString.getText().toString();
        String encoded = Uri.encode(query);
        myIntent.setData(Uri.parse("geo:"+lat+","+lon+"?q="+encoded));
        Intent chooser=Intent.createChooser(myIntent,"Launch Maps");
        startActivity(chooser);
    }
}
