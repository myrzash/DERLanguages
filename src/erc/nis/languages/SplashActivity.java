package erc.nis.languages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	private static final int SPLASH_TIME_OUT = 1500;// 7000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
//		String[] langs = new String[] { "қазақ тілі", "русский"};
//		int[] images = new int[] { R.drawable.splash_kz,
//				R.drawable.splash_ru};
//		String lang = Locale.getDefault().getDisplayLanguage();
//
//		ImageView splash = (ImageView) findViewById(R.id.imageViewSplash);
//		for (int i = 0; i < langs.length; i++)
//			if (lang.equals(langs[i]))
//				splash.setImageResource(images[i]);
////		Toast.makeText(getApplicationContext(), lang, Toast.LENGTH_SHORT)
////				.show();
		new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

			@Override
			public void run() {
				finish();
				Intent i = new Intent(SplashActivity.this, Main.class);
				startActivity(i);
				overridePendingTransition(android.R.anim.slide_in_left,
						android.R.anim.slide_out_right);
			}
		}, SPLASH_TIME_OUT);
	}
}