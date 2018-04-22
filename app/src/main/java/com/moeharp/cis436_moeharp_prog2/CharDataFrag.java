/*
* Name: Mohamed Harp
* Created On: 2/23/18
* Last Modified: 3/1/18
* Desc: This app allows the user to choose a character, and customize their settings. It will save
* the settings and restore them when the user reenters the screen
*
* This file handles allowing a user to tweak settings using the rating bar widget for strength,
* wisdom, intelligence, and dexterity, user cannot exceed 10 stars total.
*/

package com.moeharp.cis436_moeharp_prog2;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class CharDataFrag extends Fragment {
    private RatingBar ratStrgth, ratIntel, ratWsdm, ratDex;
    private TextView txtChar, txtPointsLeft;
    private SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_data,
                container, false);

        // get references to the widgets
        ratStrgth = view.findViewById(R.id.ratStrgth);
        ratIntel = view.findViewById(R.id.ratIntel);
        ratWsdm = view.findViewById(R.id.ratWsdm);
        ratDex = view.findViewById(R.id.ratDex);
        txtChar = view.findViewById(R.id.txtChar);
        txtPointsLeft = view.findViewById(R.id.txtPointsLeft);

        // set star increment to 1
        ratStrgth.setStepSize(1.0f);
        ratIntel.setStepSize(1.0f);
        ratWsdm.setStepSize(1.0f);
        ratDex.setStepSize(1.0f);

        // If a preference exists, then change the [CHARACTER] placeholder to an actual character
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if(prefs != null)
        {
            RestoreSettings();
        }

        // on change listeners for each individual rating bar
        ratStrgth.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ChangeRating(ratingBar);
            }
        });

        ratIntel.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ChangeRating(ratingBar);
            }
        });

        ratWsdm.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ChangeRating(ratingBar);
            }
        });

        ratDex.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ChangeRating(ratingBar);
            }
        });

        return view;
    }

    /* This method handles all of the rating bar adjustments, to make sure that the selected values
    *  are only in the range of 0 - 10, and if they go over it stops allowing higher adjustments
    *  without locking the user from making other adjustments.*/
    private void ChangeRating(RatingBar ratingBar)
    {
        String value;
        int pntsLeft = 10 - ((int)ratStrgth.getRating() + (int)ratIntel.getRating()
                + (int)ratWsdm.getRating() + (int)ratDex.getRating());
        if(pntsLeft < 0)
        {
            ratingBar.setRating(Integer.parseInt(txtPointsLeft.getText().toString()));
            pntsLeft = (int)ratStrgth.getRating() + (int)ratIntel.getRating()
                    + (int)ratWsdm.getRating() + (int)ratDex.getRating();
            if(pntsLeft != 10)
            {
                ratingBar.setRating(10 - pntsLeft);
            }
            pntsLeft = 10 - ((int)ratStrgth.getRating() + (int)ratIntel.getRating()
                    + (int)ratWsdm.getRating() + (int)ratDex.getRating());
            if(pntsLeft >= 0)
            {
                value = String.valueOf(pntsLeft);
            }
            else
            {
                value = String.valueOf(0);
            }
        }
        else
        {
            value = String.valueOf(pntsLeft);
        }
        txtPointsLeft.setText(value);
        SaveSettings();
    }

    // Finds which character is being manipulated and calls helper method
    private void SaveSettings()
    {
        String name = txtChar.getText().toString();
        // save the instance variables

        if(name.equals("Warrior"))
        {
            save("War");
        }
        else if(name.equals("Mage"))
        {
            save("Mg");
        }
        else if(name.equals("Healer"))
        {
            save("Heal");
        }
        else if(name.equals("Hunter"))
        {
            save("Hunt");
        }
        else if(name.equals("Paladin"))
        {
            save("Pal");
        }
    }

    // Handles saving to shared preferences accordingly
    private void save(String prefix)
    {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(prefix + "PntsLeft", txtPointsLeft.getText().toString());
        editor.putFloat(prefix + "Strength", ratStrgth.getRating());
        editor.putFloat(prefix + "Intel", ratIntel.getRating());
        editor.putFloat(prefix + "Wsdm", ratWsdm.getRating());
        editor.putFloat(prefix + "Dex", ratDex.getRating());
        editor.apply();
    }

    // finds which character is being handled and calls helper function
    public void RestoreSettings()
    {
        String name = prefs.getString("name", "");
        txtChar.setText(name);
        // save the instance variables
        if(name.equals("Warrior"))
        {
            restore("War");
        }
        else if(name.equals("Mage"))
        {
            restore("Mg");
        }
        else if(name.equals("Healer"))
        {
            restore("Heal");
        }
        else if(name.equals("Hunter"))
        {
            restore("Hunt");
        }
        else if(name.equals("Paladin"))
        {
            restore("Pal");
        }
    }

    // handles restoring data accordingly
    private void restore(String prefix)
    {
        txtPointsLeft.setText(prefs.getString(prefix + "PntsLeft", "10").toString());
        ratStrgth.setRating(prefs.getFloat(prefix + "Strength", 0));
        ratIntel.setRating(prefs.getFloat(prefix + "Intel", 0));
        ratWsdm.setRating(prefs.getFloat(prefix + "Wsdm", 0));
        ratDex.setRating(prefs.getFloat(prefix + "Dex", 0));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // Bundle will be passed to onCreate if the process is killed / restarted.
        savedInstanceState.putInt("Strength", (int)ratStrgth.getRating());
        savedInstanceState.putInt("Intel", (int)ratIntel.getRating());
        savedInstanceState.putInt("Wisdom", (int)ratWsdm.getRating());
        savedInstanceState.putInt("Dex", (int)ratDex.getRating());
    }

    @Override
    public void onPause(){
        super.onPause();
        onSaveInstanceState(new Bundle());
        SaveSettings();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        onSaveInstanceState(new Bundle());
        SaveSettings();
    }

    @Override
    public void onStop(){
        super.onStop();
        onSaveInstanceState(new Bundle());
        SaveSettings();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null)
        {
            ratStrgth.setRating(savedInstanceState.getInt("Strength"));
            ratIntel.setRating(savedInstanceState.getInt("Intel"));
            ratWsdm.setRating(savedInstanceState.getInt("Wisdom"));
            ratDex.setRating(savedInstanceState.getInt("Dex"));
        }
    }
}
