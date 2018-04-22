/*
* Name: Mohamed Harp
* Created On: 2/23/18
* Last Modified: 3/1/18
* Desc: This app allows the user to choose a character, and customize their settings. It will save
* the settings and restore them when the user re-enters the screen
*
* This file handles character selections (user clicks 1 of 5 possible buttons)
*/

package com.moeharp.cis436_moeharp_prog2;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CharacterFrag extends Fragment implements View.OnClickListener {
    private Button btnWarrior, btnMage, btnHealer, btnHunter, btnPaladin;
    private SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get the default SharedPreferences object
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character,
                container, false);

        // get references to the widgets
        btnWarrior = view.findViewById(R.id.btnWarrior);
        btnMage = view.findViewById(R.id.btnMage);
        btnHealer = view.findViewById(R.id.btnHealer);
        btnHunter = view.findViewById(R.id.btnHunter);
        btnPaladin = view.findViewById(R.id.btnPaladin);

        // set the listeners
        btnWarrior.setOnClickListener(this);
        btnMage.setOnClickListener(this);
        btnHealer.setOnClickListener(this);
        btnHunter.setOnClickListener(this);
        btnPaladin.setOnClickListener(this);

        // return the View for the layout
        return view;
    }

    @Override
    public void onClick(View v) {
        // save the button click as a name
        SharedPreferences.Editor editor = prefs.edit();

        switch (v.getId()) {
            case R.id.btnWarrior:
                // TODO: Bridge Button To next fragment
                editor.putString("name", "Warrior");
                break;
            case R.id.btnMage:
                // TODO: Bridge Button To next fragment
                editor.putString("name", "Mage");
                break;
            case R.id.btnHealer:
                // TODO: Bridge Button To next fragment
                editor.putString("name", "Healer");
                break;
            case R.id.btnHunter:
                // TODO: Bridge Button To next fragment
                editor.putString("name", "Hunter");
                break;
            case R.id.btnPaladin:
                // TODO: Bridge Button To next fragment
                editor.putString("name", "Paladin");
                break;
        }

        editor.apply();
        if(isPhone()) // For Phone View
        {
            startActivity(new Intent(getActivity(), CharDataActivity.class));
        }
        else // For Tablet View
        {
            startActivity(new Intent(getActivity(), CharacterActivity.class));
        }
    }

    private boolean isPhone()
    {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);

        return (diagonalInches < 6.5);
    }
}

/*
* Helpful Links:
* http://www.cs.dartmouth.edu/~campbell/cs65/lecture09/lecture09.html
* https://developer.android.com/training/data-storage/shared-preferences.html#java
* https://stackoverflow.com/questions/10354400/onpause-onresume-and-ondestroy
* https://stackoverflow.com/questions/44854187/get-value-of-multiple-rating-bars-and-set-a-limit-on-how-many-stars-can-be-used
* https://www.youtube.com/watch?v=xv_JJbjDQ3M
* */
