package com.otaupdater;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

@SuppressWarnings("deprecation")
public class UpdaterSettings extends PreferenceActivity implements OnPreferenceClickListener {
    private Config cfg;
    
	private CheckBoxPreference showNotifPref;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		cfg = Config.getInstance(getApplicationContext());
		
		addPreferencesFromResource(R.xml.settings_main);
		
		showNotifPref = (CheckBoxPreference) findPreference("show_notif");
		showNotifPref.setChecked(cfg.getShowNotif());
		showNotifPref.setOnPreferenceClickListener(this);
	}
	
	@Override
    public boolean onPreferenceClick(Preference preference) {
	    if (preference == showNotifPref) {
	        cfg.setShowNotif(showNotifPref.isChecked());
	        return true;
	    }
	    return false;
    }
}
