package com.lejit.thetravellingman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivityJason extends AppCompatActivity {
    private Button itineraryButton;
    private Button layoutButton;
    private Button mapsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jason);
        setButtonListeners();
    }

    private void setButtonListeners() {
        itineraryButton = (Button) findViewById(R.id.itinerary);
        layoutButton = (Button) findViewById(R.id.layout);
        mapsButton = (Button) findViewById(R.id.maps);

        View.OnClickListener intentListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.itinerary:
                        Intent itineraryIntent = new Intent(MainActivityJason.this, itineraryActivity.class);
                        startActivity(itineraryIntent);
                        break;
                    case R.id.layout:
                        Intent layoutIntent = new Intent(MainActivityJason.this, layoutActivity.class);
                        startActivity(layoutIntent);
                        break;
                    case R.id.maps:
                        Intent mapsIntent = new Intent(MainActivityJason.this, layoutActivity.class);
                        startActivity(mapsIntent);
                        break;
                }

            }
        };

    }
}
