package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Locale;

public class Dashboard extends AppCompatActivity {
    int oldVaue;
    public static int langchange = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();

        //change actionbar title, if you dont change it will be accord to your system default settings
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        setContentView(R.layout.activity_dashboard);
        Button changeLang = findViewById(R.id.changeMyLang);   //Lang option button
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show alert dialog to show list of language options
                showChangeLanguageDialog();
            }
        });

        changeTextViewValueRandomlyOnButtonClick();
    }

    public void changeTextViewValueRandomlyOnButtonClick () {
        final TextView changingText = (TextView) findViewById(R.id.text_to_change);
        Button changeTextButton = (Button) findViewById(R.id.change_text_button);
            changeTextButton.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    if (langchange==1) {
                        String[] manyDifferentStrings = {"Browse info on plants", "Research your plant zone", "Try an indoor container garden", "Don't overwater", "Make sure it gets appropriate sunlight", "Inspect for pests", "Remove the weeds"};
                        int random = (int) (Math.random() * manyDifferentStrings.length);
                        if (random == oldVaue) {
                            random = (int) (Math.random() * manyDifferentStrings.length);
                        }
                        changingText.setText(manyDifferentStrings[random]);
                        oldVaue = random;
                    }
                    else if(langchange==2){
                        String[] manyDifferentStrings = {"पौधों पर जानकारी ब्राउज़ करें", "अपने प्लांट ज़ोन पर शोध करें", "इनडोर कंटेनर गार्डन आज़माएं", "बहुत सारा पानी न डालें", "सुनिश्चित करें कि इसे उपयुक्त धूप मिले", "कीटों का निरीक्षण करें","मातम दूर करो"};
                        int random = (int) (Math.random() * manyDifferentStrings.length);
                        if (random == oldVaue) {
                            random = (int) (Math.random() * manyDifferentStrings.length);
                        }
                        changingText.setText(manyDifferentStrings[random]);
                        oldVaue = random;
                    }
                    else {
                        String[] manyDifferentStrings = {"वनस्पतींवर माहिती ब्राउझ करा", "आपल्या वनस्पती झोनवर संशोधन करा", "इनडोअर कंटेनर गार्डन वापरुन पहा", "भरपूर पाणी टाकू नका", "योग्य सूर्यप्रकाश पडतो याची खात्री करा", "कीटकांची तपासणी करा","तण काढून टाका"};
                        int random = (int) (Math.random() * manyDifferentStrings.length);
                        if (random == oldVaue) {
                            random = (int) (Math.random() * manyDifferentStrings.length);
                        }
                        changingText.setText(manyDifferentStrings[random]);
                        oldVaue = random;
                    }
                }
            });
        }



    public void showChangeLanguageDialog() {
        //array of lang to display
        final String[] listItems = {"English","हिंदी","मराठी"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Dashboard.this);
        mBuilder.setTitle("Change Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0){
                    setLocale("en");
                    langchange=1;
                    //stringen();
                    recreate();
                }
                else if (i == 1){
                    setLocale("hi");
                    langchange=2;
                    //stringhi();
                    recreate();
                }
                else if (i == 2){
                    setLocale("mr");
                    langchange=3;
                    //stringmr();
                    recreate();
                }

                //dismiss alert dialog when language selected
                dialog.dismiss();

            }
        });
        AlertDialog mDialog = mBuilder.create();
        //show alert dialog
        mDialog.show();
    }
    

    public void stringen() {
        final String[] manyDifferentStrings = {"Browse info on plants", "Research your plant zone", "Try an indoor container garden", "Don't overwater", "Make sure it gets appropriate sunlight", "Inspect for pests", "Remove the weeds"};
        changeTextViewValueRandomlyOnButtonClick ();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale= locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();

    }

    //load language saved in shared preferences
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }

}