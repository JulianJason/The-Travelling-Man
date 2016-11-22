package com.lejit.thetravellingman;

import android.content.Intent;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class MainActivityFragment extends Fragment
        implements ItenaryPlanner.OnFragmentInteractionListener,AttractionsNearby.OnFragmentInteractionListener,
        FoodNearby.OnFragmentInteractionListener{
    public static String language = "English";

    List<Address> matchedList;
    List<Integer> buttonList = Arrays.asList(R.id.first,R.id.map1,R.id.second,R.id.map2);
    List<Integer> webviewList = Arrays.asList(R.id.name1,R.id.r1,R.id.name2,R.id.r2,R.id.name3,R.id.r3,R.id.name4,R.id.r4,R.id.name5,R.id.r5,R.id.name6,R.id.r6);
    HashMap<Integer,String> hash = new HashMap<>();
    EditText LocationString;
    RelativeLayout rel;
    String attr;

    private ViewPager mViewPager;

    private CoordinatorLayout coordinatorLayout;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    TextView mSectionView;
    TextView mWelcomeMessage;

    View view;

    public MainActivityFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_main_activity, container, false);
        LocaleHelper.onCreate(view.getContext(), "en");

        //Setting urls to name of attractions
        hash.put(R.id.name1,"http://www.yoursingapore.com/see-do-singapore/recreation-leisure/resorts/marina-bay-sands.html");
        hash.put(R.id.name2,"http://www.yoursingapore.com/see-do-singapore/recreation-leisure/viewpoints/singapore-flyer.html");
        hash.put(R.id.name3,"http://www.vivocity.com.sg/leisure");
        hash.put(R.id.name4,"http://www.yoursingapore.com/see-do-singapore/recreation-leisure/resorts/resorts-world-sentosa.html");
        hash.put(R.id.name5,"http://www.yoursingapore.com/see-do-singapore/culture-heritage/places-of-worship/buddha-tooth-relic-temple-museum.html");
        hash.put(R.id.name6,"http://www.yoursingapore.com/see-do-singapore/nature-wildlife/fun-with-animals/singapore-zoo.html");

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {   //set listener for Toast
            @Override
            public void onPageSelected(int position) {
                if (position==2) {
                    Toast toast = Toast.makeText(view.getContext(),"Press to find out more!",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,350);//adjust position of toast upwards
                    toast.show();
                }
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.main_content);

//        BottomBar bottomBar = (BottomBar) view.findViewById(R.id.bottomBar);
//        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelected(@IdRes int tabId) {
//                if (tabId == R.id.tab_information) {
////                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////                    transaction.replace(R.id.container, new AboutSG_fragment());
////                    transaction.addToBackStack(null);
////                    transaction.commit();
////                    Intent intent = new Intent(getApplicationContext(),AboutSG.class);
////                    startActivity(intent);
//                }else if(tabId == R.id.tab_food){
//
//                }else if(tabId == R.id.tab_home){
//
//                }else if(tabId == R.id.tab_SOS){
//                    Intent intent = new Intent(view.getContext(), MainActivity_Emergency.class);
//                    startActivity(intent);
//                }else if(tabId == R.id.tab_Updates){
//                    Intent intent = new Intent(view.getContext(), NewsUpdateActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });
//
//        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
//            @Override
//            public void onTabReSelected(@IdRes int tabId) {
//                if (tabId == R.id.tab_information) {
////                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////                    transaction.replace(R.id.container, new AboutSG_fragment());
////                    transaction.addToBackStack(null);
////                    transaction.commit();
////                    Intent intent = new Intent(getApplicationContext(),AboutSG.class);
////                    startActivity(intent);
//                }else if(tabId == R.id.tab_food){
//
//                }else if(tabId == R.id.tab_home){
//                    Toast.makeText(view.getContext(),"Home Page",Toast.LENGTH_SHORT).show();
//                }else if(tabId == R.id.tab_SOS){
//                    Intent intent = new Intent(view.getContext(), MainActivity_Emergency.class);
//                    startActivity(intent);
//                }else if(tabId == R.id.tab_Updates){
//                    Intent intent = new Intent(view.getContext(), NewsUpdateActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });

        mSectionView = (TextView) view.findViewById(R.id.section_label);
        mWelcomeMessage = (TextView) view.findViewById(R.id.welcomeMessage);

        return view;

    }

    public void changeToEnglish(View view){
        language = "English";
        LocaleHelper.setLocale(view.getContext(), "en");
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TextView interactiveGuide = (TextView) view.findViewById(R.id.textView2);
        TextView welcomeMessage = (TextView) view.findViewById(R.id.welcomeMessage);

        interactiveGuide.setText(getResources().getString(R.string.InteractiveGuide));
        welcomeMessage.setText(getString(R.string.welcome_message));

    }

    public void changeToChinese(View view){
        language = "Chinese";
        LocaleHelper.setLocale(view.getContext(), "zh");
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TextView interactiveGuide = (TextView) view.findViewById(R.id.textView2);
        TextView welcomeMessage = (TextView) view.findViewById(R.id.welcomeMessage);

        interactiveGuide.setText(getResources().getString(R.string.InteractiveGuide));
        welcomeMessage.setText(getString(R.string.welcome_message));
    }

    public void changeToMalay(View view){

        Toast.makeText(view.getContext(),"Sorry, Malay is not supported yet! > <",Toast.LENGTH_SHORT).show();
        updateViews();

    }

    public void changeToTamil(View view){

        Toast.makeText(view.getContext(),"Sorry, Tamil is not supported yet! > <",Toast.LENGTH_SHORT).show();
        updateViews();

    }

    private void updateViews() {
        Resources resources = getResources();

        TextView interactiveGuide = (TextView) view.findViewById(R.id.textView2);
        TextView welcomeMessage = (TextView) view.findViewById(R.id.welcomeMessage);

        interactiveGuide.setText(getResources().getString(R.string.InteractiveGuide));
        welcomeMessage.setText(getString(R.string.welcome_message));

}

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


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
        public static MainActivityFragment.PlaceholderFragment newInstance(int sectionNumber) {
            MainActivityFragment.PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            View.OnClickListener tempListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ItineraryActivity.class);
                    startActivity(intent);
                }
            };
            Button tempButton = (Button) rootView.findViewById(R.id.tempButton);
            tempButton.setOnClickListener(tempListener);
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
                case 0: return MainActivityFragment.PlaceholderFragment.newInstance(position+1);     // Home Page
                case 1 : return ItenaryPlanner.newInstance();                   // This is the fragment for the itenary planner
                case 2: return AttractionsNearby.newInstance();                    // This should house the attractions nearby
                default: return  MainActivityFragment.PlaceholderFragment.newInstance(position+1);
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
                    return getString(R.string.homePage);
                case 1:
                    return getString(R.string.itineraryPlanner);

                case 2:
                    return getString(R.string.AttractionsNearby);

            }
            return null;
        }
    }
    //map function
    public void map(View v) {
        for(int i=0;i<buttonList.size();i++){
            if(buttonList.get(i)==v.getId()){
                LocationString = (EditText)view.findViewById(buttonList.get(i-1));
            }

        }

        Geocoder myGcdr = new Geocoder(view.getContext(), Locale.getDefault());

        try {
            matchedList=myGcdr.getFromLocationName(LocationString.getText().toString(),1);
        }
        catch(IOException e) {
            Toast.makeText(view.getContext(),"Not able to find location: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
        Intent myIntent = new Intent(Intent.ACTION_VIEW);
        String lat="";
        String lon="";

        try {
            lat=String.valueOf(matchedList.get(0).getLatitude());
            lon=String.valueOf(matchedList.get(0).getLongitude());
        }
        catch (Exception e) {
            Toast.makeText(view.getContext(), "A problem occurred in retrieving location", Toast.LENGTH_LONG).show();
        }
        String query = LocationString.getText().toString();
        String encoded = Uri.encode(query);
        myIntent.setData(Uri.parse("geo:"+lat+","+lon+"?q="+encoded));
        Intent chooser=Intent.createChooser(myIntent,"Launch Maps");
        startActivity(chooser);
    }

    //popup window -- details of attractions: using webview
    public void popup(View v) {

        for(int i=0;i<webviewList.size();i++){
            if(webviewList.get(i)==v.getId()){
                rel = (RelativeLayout) view.findViewById(webviewList.get(i+1));
                attr = hash.get(v.getId());
            }
        }
        LayoutInflater layInf = (LayoutInflater)view.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        ViewGroup window = (ViewGroup)layInf.inflate(R.layout.fragment_pop,null);
        LinearLayout ll = (LinearLayout) window.findViewById(R.id.linlay);
        final WebView webview = (WebView)ll.findViewById(R.id.webview);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(attr);

        final PopupWindow popupWindow = new PopupWindow(window,670,470,true);
        popupWindow.showAtLocation(rel, Gravity.NO_GRAVITY,30,485);
        window.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent m){
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
