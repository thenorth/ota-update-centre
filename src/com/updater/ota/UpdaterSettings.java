package com.updater.ota;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

@SuppressWarnings("deprecation")
public class UpdaterSettings extends PreferenceActivity {
	
	private CheckBoxPreference show_notif;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings_main);
		
		show_notif = (CheckBoxPreference) findPreference("show_notif");
		show_notif.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
	}

}
