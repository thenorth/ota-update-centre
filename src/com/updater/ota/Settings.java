package com.updater.ota;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class Settings extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getFragmentManager().beginTransaction().replace(android.R.id.content,
                new SettingsFrag()).commit();
	}
	
	public static class SettingsFrag extends PreferenceFragment {
        
		@Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.settings_main);
        }
	}
}
