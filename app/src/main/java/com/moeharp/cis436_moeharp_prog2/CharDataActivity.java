/*
* Name: Mohamed Harp
* Created On: 2/23/18
* Last Modified: 3/1/18
* Desc: This app allows the user to choose a character, and customize their settings. It will save
* the settings and restore them when the user reenters the screen
*/

package com.moeharp.cis436_moeharp_prog2;

import android.app.Activity;
import android.os.Bundle;

public class CharDataActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the view for the activity using XML
        setContentView(R.layout.chardata);
    }
}
