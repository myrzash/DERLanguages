package erc.nis.languages;

import java.text.MessageFormat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager.LayoutParams;

public class SettingsActivity extends PreferenceActivity implements
		OnPreferenceChangeListener {

	private static final int[] flags = { R.drawable.flag_kazakhstan,
			R.drawable.flag_russia, R.drawable.flag_united_states,
			R.drawable.flag_united_kingdom, R.drawable.flag_france,
			R.drawable.flag_brazil, R.drawable.flag_italy,
			R.drawable.flag_spain, R.drawable.flag_germany,
			R.drawable.flag_denmark, R.drawable.flag_sweden,
			R.drawable.flag_turkey, R.drawable.flag_israel,
			R.drawable.flag_morocco, R.drawable.flag_china };
	// private ListPreference listLang;

	private String[] langs;
	
	private int LANG_INTERFACE = 0;
	private int LANG1 = 0;
	private int LANG2 = 1;

	private void loadPreferences() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(SettingsActivity.this);
		LANG_INTERFACE = Integer.parseInt(sp.getString("interface",
				String.valueOf(LANG_INTERFACE)));
		LANG1 = Integer.parseInt(sp.getString("lang1", String.valueOf(LANG1)));
		LANG2 = Integer.parseInt(sp.getString("lang2", String.valueOf(LANG2)));
	}
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref);
		loadPreferences();
		langs = getResources().getStringArray(R.array.Languages);
		((PreferenceCategory) findPreference("ctglangs"))
				.setTitle(translate(R.string.languages));
		ListPreference listLang = (ListPreference) findPreference("interface");
		listLang.setDialogTitle(translate(R.string.interfacelang));
		listLang.setTitle(translate(R.string.interfacelang));
		listLang.setNegativeButtonText(translate(R.string.cancel));
		listLang.setSummary(langs[LANG_INTERFACE]);
		listLang.setOnPreferenceChangeListener(this);

		ListPreference lang1 = (ListPreference) findPreference("lang1");
		lang1.setDialogTitle(translate(R.string.first_language));
		lang1.setTitle(translate(R.string.first_language));
		lang1.setNegativeButtonText(translate(R.string.cancel));
		lang1.setIcon(flags[LANG1]);
		lang1.setSummary(langs[LANG1]);
		lang1.setOnPreferenceChangeListener(this);
		ListPreference lang2 = (ListPreference) findPreference("lang2");
		lang2.setDialogTitle(translate(R.string.second_language));
		lang2.setTitle(translate(R.string.second_language));
		lang2.setNegativeButtonText(translate(R.string.cancel));
		lang2.setIcon(flags[LANG2]);
		lang2.setOnPreferenceChangeListener(this);
		lang2.setSummary(langs[LANG2]);

		((PreferenceCategory) findPreference("ctgextra"))
				.setTitle(translate(R.string.extra));
		((SwitchPreference) findPreference("sound"))
				.setTitle(translate(R.string.display_name_while_speaking));
		((PreferenceCategory) findPreference("ctginfo"))
				.setTitle(translate(R.string.interfacelang));
		((PreferenceScreen) findPreference("cred"))
				.setTitle(translate(R.string.credits));
	}

	@Override
	public void onBackPressed() {
		setResult(RESULT_OK, new Intent().setAction("restart"));
		finish();
		overridePendingTransition(0, 0);
	}

	private String translate(int resId) {
		return MessageFormat.format(getString(resId), LANG_INTERFACE);
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		String key = preference.getKey().toString();
		int value = Integer.parseInt(newValue.toString());
		Log.d(Main.LOG, "value=" + value);
		if (key.equals("interface")) {
//			Log.d(Main.LOG, "interface");
//			LANG_INTERFACE = value;
			onBackPressed();
			startActivity(getIntent());
			overridePendingTransition(0, 0);

		} else {
			preference.setSummary(langs[value]);
			preference.setIcon(flags[value]);
		}
		return true;
	}

}
