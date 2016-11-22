package com.lejit.thetravellingman;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ScrollView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AboutSG_fragment extends Fragment {

    private Context mContext;

    private ScrollView mRelativeLayout;
    private Button mButton;
    private Button Bt1;
    private Button Bt2;
    private Button Bt3;
    private Button Bt4;

    private PopupWindow mPopupWindow;
    private PopupWindow window2;
    private PopupWindow window3;
    private PopupWindow window4;
    private PopupWindow window5;

    public AboutSG_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_about_sg_fragment, container, false);

        // Get the application context
        mContext = view.getContext();

        // Get the activity

        // Get the widgets reference from XML layout
        mRelativeLayout = (ScrollView) view.findViewById(R.id.scrollDict);
        mButton = (Button) view.findViewById(R.id.dictbut);
        Bt1=(Button)view.findViewById(R.id.buttoncult);
        Bt2=(Button)view.findViewById(R.id.mustbtn) ;
        Bt3=(Button)view.findViewById(R.id.legbutton);
        Bt4=(Button)view.findViewById(R.id.histbutton);
        Bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater5=(LayoutInflater)mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customview5=inflater5.inflate(R.layout.popup_about5,null);
                window5 = new PopupWindow(
                        customview5,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                Button closeButton5 = (Button) customview5.findViewById(R.id.closeButton5);
                closeButton5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Dismiss the popup window
                        window5.dismiss();
                    }
                });
                window5.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);
            }
        });
        Bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater4=(LayoutInflater)mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customview4=inflater4.inflate(R.layout.popup_about4,null);
                window4 = new PopupWindow(
                        customview4,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                Button closeButton4 = (Button) customview4.findViewById(R.id.closeButton4);
                closeButton4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Dismiss the popup window
                        window4.dismiss();
                    }
                });
                window4.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);
            }
        });
        Bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater3=(LayoutInflater)mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customview3=inflater3.inflate(R.layout.popup_about3,null);
                window3 = new PopupWindow(
                        customview3,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                Button closeButton2 = (Button) customview3.findViewById(R.id.closeButton2);
                closeButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Dismiss the popup window
                        window3.dismiss();
                    }
                });
                window3.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);
            }
        });

        Bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater2=(LayoutInflater)mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customview2=inflater2.inflate(R.layout.popup_about2,null);
                window2 = new PopupWindow(
                        customview2,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                Button closeButton1 = (Button) customview2.findViewById(R.id.closeButton1);
                closeButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        window2.dismiss();
                    }
                });
                window2.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);
            }
        });

        // Set a click listener for the text view
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize a new instance of LayoutInflater service
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                // Inflate the custom layout/view
                View customView = inflater.inflate(R.layout.popup_about,null);

                /*
                    public PopupWindow (View contentView, int width, int height)
                        Create a new non focusable popup window which can display the contentView.
                        The dimension of the window must be passed to this constructor.

                        The popup does not provide any background. This should be handled by
                        the content view.

                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
                // Initialize a new instance of popup window
                mPopupWindow = new PopupWindow(
                        customView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                // Set an elevation value for popup window
                // Call requires API level 21

                // Get a reference for the custom view close button
                Button closeButton = (Button) customView.findViewById(R.id.closeButton);

                // Set a click listener for the popup window close button
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });

                /*
                    public void showAtLocation (View parent, int gravity, int x, int y)
                        Display the content view in a popup window at the specified location. If the
                        popup window cannot fit on screen, it will be clipped.
                        Learn WindowManager.LayoutParams for more information on how gravity and the x
                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
                        to specifying Gravity.LEFT | Gravity.TOP.

                    Parameters
                        parent : a parent view to get the getWindowToken() token from
                        gravity : the gravity which controls the placement of the popup window
                        x : the popup's x location offset
                        y : the popup's y location offset
                */
                // Finally, show the popup window at the center location of root relative layout
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);
            }
        });


//        BottomBar bottomBar = (BottomBar) view.findViewById(R.id.bottomBar);
//        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelected(@IdRes int tabId) {
//                if (tabId == R.id.tab_information) {
//
//                }else if(tabId == R.id.tab_food){
//
//                }else if(tabId == R.id.tab_home){
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.AboutSGFragment, new MainActivityFragment());
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//
//                }else if(tabId == R.id.tab_SOS){
//                    Intent intent = new Intent(view.getContext(),MainActivity_Emergency.class);
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
//                    Toast.makeText(view.getContext(),"More About Singapore",Toast.LENGTH_SHORT).show();
//                }else if(tabId == R.id.tab_food){
//
//                }else if(tabId == R.id.tab_home){
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.AboutSGFragment, new MainActivityFragment());
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//                }else if(tabId == R.id.tab_SOS){
//                    Intent intent = new Intent(view.getContext(), MainActivity_Emergency.class);
//                    startActivity(intent);
//                }else if(tabId == R.id.tab_Updates){
//                    Intent intent = new Intent(view.getContext(), NewsUpdateActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });

        return view;

    }
}
