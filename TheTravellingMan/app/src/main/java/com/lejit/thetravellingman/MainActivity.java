package com.lejit.thetravellingman;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity
        implements ItenaryPlanner.OnFragmentInteractionListener{

    public static String language = "English";

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
                    Intent intent = new Intent(getApplicationContext(),NewsUpdateActivity.class);
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

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_information) {
                    Intent intent = new Intent(getApplicationContext(),NewsUpdateActivity.class);
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

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


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

    public void changeToEnglish(){
        language = "English";
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TextView interactiveGuide = (TextView) findViewById(R.id.textView2);
        TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);

        interactiveGuide.setText(getResources().getString(R.string.InteractiveGuide_English));
        welcomeMessage.setText(getString(R.string.welcome_message_english));

    }

    public void changeToChinese(){
        language = "Chinese";
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TextView interactiveGuide = (TextView) findViewById(R.id.textView2);
        TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);

        interactiveGuide.setText(getResources().getString(R.string.InteractiveGuide_Chinese));
        welcomeMessage.setText(getString(R.string.welcome_message_chinese));
    }

    public void changeToEnglish(View view){
        language = "English";
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Button englishButton = (Button) findViewById(R.id.English);
        englishButton.setBackgroundColor(Color.rgb(192,192,192)); // Silver
        Button chineseButton = (Button) findViewById(R.id.Chinese);
        chineseButton.setBackgroundColor(Color.rgb(220,220,220)); // gainsboro

        TextView interactiveGuide = (TextView) findViewById(R.id.textView2);
        TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);

        interactiveGuide.setText(getResources().getString(R.string.InteractiveGuide_English));
        welcomeMessage.setText(getString(R.string.welcome_message_english));

    }

    public void changeToChinese(View view){
        language = "Chinese";
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Button englishButton = (Button) findViewById(R.id.English);
        Button chineseButton = (Button) findViewById(R.id.Chinese);
        chineseButton.setBackgroundColor(Color.rgb(192,192,192)); // Silver
        englishButton.setBackgroundColor(Color.rgb(220,220,220)); // gainsboro

        TextView interactiveGuide = (TextView) findViewById(R.id.textView2);
        TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);

        interactiveGuide.setText(getResources().getString(R.string.InteractiveGuide_Chinese));
        welcomeMessage.setText(getString(R.string.welcome_message_chinese));
    }

    public void changeToMalay(View view){

    }

    public void changeToTamil(View view){

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
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//            Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
//            // Create an ArrayAdapter using the string array and a default spinner layout
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
//                    R.array.language_array, android.R.layout.simple_spinner_item);
//
//            // Specify the layout to use when the list of choices appears
//
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            // Apply the adapter to the spinner
//            spinner.setAdapter(adapter);
//            Button rssButton = (Button) rootView.findViewById(R.id.toRSS);
//            rssButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), NewsUpdateActivity.class);
//                    startActivity(intent);
//                }
//            });
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
                case 0: return PlaceholderFragment.newInstance(position+1);     // Home Page
                case 1 : return ItenaryPlanner.newInstance();                   // This is the fragment for the itenary planner
                case 2: return ItenaryPlanner.newInstance();                    // This should house the attractions nearby
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
                    if(language.equals("English")){
                        return getString(R.string.homePage_english);
                    }else if(language.equals("Chinese")){
                        return getString(R.string.homePage_chinese);
                    }
                case 1:
                    if(language.equals("English")){
                        return getString(R.string.itineraryPlanner_english);
                    }else if(language.equals("Chinese")){
                        return getString(R.string.itineraryPlanner_chinese);
                    }
                case 2:
                    if(language.equals("English")){
                        return getString(R.string.AttractionsNearby_english);
                    }else if(language.equals("Chinese")){
                        return getString(R.string.AttractionsNearby_chinese);
                    }
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
