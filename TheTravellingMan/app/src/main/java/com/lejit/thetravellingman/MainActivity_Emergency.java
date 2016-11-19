package com.lejit.thetravellingman;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity_Emergency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
    }

    public void call_police(View view) {
        Intent phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 96564650));
        startActivity(phone);
    }

    public void call_amb(View view) {
        Intent phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 96564650));
        startActivity(phone);
    }

    public void call_aus(View view) {
        Intent phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 68364100));
        startActivity(phone);
    }

    public void call_fra(View view) {
        Intent phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 68807800));
        startActivity(phone);
    }

    public void call_ger(View view) {
        Intent phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 65336002));
        startActivity(phone);
    }

    public void call_ita(View view) {
        Intent phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 62506022));
        startActivity(phone);
    }

    public void call_jpn(View view) {
        Intent phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 62538855));
        startActivity(phone);
    }

    public void call_kor(View view) {
        Intent phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 62561188));
        startActivity(phone);
    }

    public void call_uk(View view) {
        Intent phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 64244200));
        startActivity(phone);
    }

    public void call_us(View view) {
        Intent phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 64769100));
        startActivity(phone);
    }

    public void see_embassy(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String text = spinner.getSelectedItem().toString();
        LayoutInflater layoutInflater= (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        ViewGroup container;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_emergency);
        final PopupWindow popupWindow;
        switch(text) {
            case "Australia":
                container = (ViewGroup) layoutInflater.inflate(R.layout.aus_emb_details, null);
                popupWindow = new PopupWindow(container, 1000, 600, true);
                popupWindow.showAtLocation(linearLayout, Gravity.NO_GRAVITY, 0, 900);
                container.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            popupWindow.dismiss();
                        }
                        return Boolean.TRUE;
                    }
                });
                break;
            case "France":
                container = (ViewGroup) layoutInflater.inflate(R.layout.fra_emb_details, null);
                popupWindow = new PopupWindow(container, 1000, 600, true);
                popupWindow.showAtLocation(linearLayout, Gravity.NO_GRAVITY, 0, 900);
                container.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            popupWindow.dismiss();
                        }
                        return Boolean.TRUE;
                    }
                });
                break;
            case "Germany":
                container = (ViewGroup) layoutInflater.inflate(R.layout.ger_emb_details, null);
                popupWindow = new PopupWindow(container, 1000, 600, true);
                popupWindow.showAtLocation(linearLayout, Gravity.NO_GRAVITY, 0, 900);
                container.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            popupWindow.dismiss();
                        }
                        return Boolean.TRUE;
                    }
                });
                break;
            case "Italy":
                container = (ViewGroup) layoutInflater.inflate(R.layout.ita_emb_details, null);
                popupWindow = new PopupWindow(container, 1000, 600, true);
                popupWindow.showAtLocation(linearLayout, Gravity.NO_GRAVITY, 0, 900);
                container.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            popupWindow.dismiss();
                        }
                        return Boolean.TRUE;
                    }
                });
                break;
            case "Japan":
                container = (ViewGroup) layoutInflater.inflate(R.layout.jpn_emb_details, null);
                popupWindow = new PopupWindow(container, 1000, 600, true);
                popupWindow.showAtLocation(linearLayout, Gravity.NO_GRAVITY, 0, 900);
                container.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            popupWindow.dismiss();
                        }
                        return Boolean.TRUE;
                    }
                });
                break;
            case "South Korea":
                container = (ViewGroup) layoutInflater.inflate(R.layout.kor_emb_details, null);
                popupWindow = new PopupWindow(container, 1000, 600, true);
                popupWindow.showAtLocation(linearLayout, Gravity.NO_GRAVITY, 0, 900);
                container.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            popupWindow.dismiss();
                        }
                        return Boolean.TRUE;
                    }
                });
                break;
            case "United Kingdom":
                container = (ViewGroup) layoutInflater.inflate(R.layout.uk_emb_details, null);
                popupWindow = new PopupWindow(container, 1000, 600, true);
                popupWindow.showAtLocation(linearLayout, Gravity.NO_GRAVITY, 0, 900);
                container.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            popupWindow.dismiss();
                        }
                        return Boolean.TRUE;
                    }
                });
                break;
            case "United States of America":
                container = (ViewGroup) layoutInflater.inflate(R.layout.us_emb_details, null);
                popupWindow = new PopupWindow(container, 1000, 600, true);
                popupWindow.showAtLocation(linearLayout, Gravity.NO_GRAVITY, 0, 900);
                container.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            popupWindow.dismiss();
                        }
                        return Boolean.TRUE;
                    }
                });
                break;
            default:
                Toast.makeText(getApplicationContext(), "Please choose an embassy.", Toast.LENGTH_SHORT).show();
        }
    }

    public void call_embassy(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String text = spinner.getSelectedItem().toString();
        switch(text) {
            case "Australia":
                call_aus(view);
                break;
            case "France":
                call_fra(view);
                break;
            case "Germany":
                call_ger(view);
                break;
            case "Italy":
                call_ita(view);
                break;
            case "Japan":
                call_jpn(view);
                break;
            case "South Korea":
                call_kor(view);
                break;
            case "United Kingdom":
                call_uk(view);
                break;
            case "United States of America":
                call_us(view);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Please choose an embassy.", Toast.LENGTH_SHORT).show();
        }
    }
}
