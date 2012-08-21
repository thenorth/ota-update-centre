package com.updater.ota;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

@SuppressWarnings("deprecation")
public class UpdaterSettings extends PreferenceActivity {
	
	private CheckBoxPreference show_notif;
	public static SharedPreferences prefs;
	private String SHOW_NOTIF_KEY = "show_notifications";
	private SharedPreferences.Editor editor;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings_main);
		prefs = getPreferences(MODE_PRIVATE);
		
		show_notif = (CheckBoxPreference) findPreference("show_notif");
		
		editor = prefs.edit();
		
		prefs.getBoolean(SHOW_NOTIF_KEY, true);
		if (SHOW_NOTIF_KEY == null) {
			editor.putBoolean(SHOW_NOTIF_KEY, true);
		} 
		
		
		show_notif.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				if (show_notif.isChecked()) {
					editor.putBoolean(SHOW_NOTIF_KEY, true);
				} else {
					editor.putBoolean(SHOW_NOTIF_KEY, false);
				}
				return false;
			}
			
		});
	}
	


}
