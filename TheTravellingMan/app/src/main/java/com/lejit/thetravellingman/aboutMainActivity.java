package com.lejit.aboutsingapore;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.*;
import android.view.ViewGroup.LayoutParams;


public class aboutMainActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maim_about);

        // Get the application context
        mContext = getApplicationContext();

        // Get the activity
        mActivity = aboutMainActivity.this;

        // Get the widgets reference from XML layout
        mRelativeLayout = (ScrollView) findViewById(R.id.scrollDict);
        mButton = (Button) findViewById(R.id.dictbut);
        Bt1=(Button)findViewById(R.id.buttoncult);
        Bt2=(Button)findViewById(R.id.mustbtn) ;
        Bt3=(Button)findViewById(R.id.legbutton);
        Bt4=(Button)findViewById(R.id.histbutton);
        Bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater5=(LayoutInflater)mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customview5=inflater5.inflate(R.layout.popup_about5,null);
                window5 = new PopupWindow(
                        customview5,
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
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
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
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
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
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
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
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
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
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
    }
}



