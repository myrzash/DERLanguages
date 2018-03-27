package erc.nis.languages;

import java.text.MessageFormat;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDetail extends FragmentActivity implements OnClickListener {
	private static int[][][] sounds = new int[][][] {
			{ // KZ
					{ R.raw.sound_kz1_1, R.raw.sound_kz1_2, R.raw.sound_kz1_3,
							R.raw.sound_kz1_4, R.raw.sound_kz1_5,
							R.raw.sound_kz1_6, R.raw.sound_kz1_7,
							R.raw.sound_kz1_8, R.raw.sound_kz1_9,
							R.raw.sound_kz1_10, R.raw.sound_kz1_11,
							R.raw.sound_kz1_12 },
					{ R.raw.sound_kz2_1, R.raw.sound_kz2_2, R.raw.sound_kz2_3,
							R.raw.sound_kz2_4, R.raw.sound_kz2_5,
							R.raw.sound_kz2_6, R.raw.sound_kz2_7,
							R.raw.sound_kz2_8, R.raw.sound_kz2_9,
							R.raw.sound_kz2_10, R.raw.sound_kz2_11,
							R.raw.sound_kz2_12 },
					{ R.raw.sound_kz3_1, R.raw.sound_ru3_2, R.raw.sound_kz3_3,
							R.raw.sound_ru3_4, R.raw.sound_kz3_5,
							R.raw.sound_kz3_6, R.raw.sound_ru3_7,
							R.raw.sound_ru3_8, R.raw.sound_ru3_9,
							R.raw.sound_kz3_10, R.raw.sound_kz3_11,
							R.raw.sound_kz3_12 },
					{ R.raw.sound_kz4_1, R.raw.sound_kz4_2, R.raw.sound_kz4_3,
							R.raw.sound_kz4_4, R.raw.sound_kz4_5,
							R.raw.sound_kz4_6, R.raw.sound_kz4_7,
							R.raw.sound_ru4_8, R.raw.sound_kz4_9,
							R.raw.sound_kz4_10, R.raw.sound_kz4_11,
							R.raw.sound_ru4_12 },
					{ R.raw.sound_ru5_1, R.raw.sound_kz5_2, R.raw.sound_ru5_3,
							R.raw.sound_kz5_4, R.raw.sound_ru5_5,
							R.raw.sound_ru5_6, R.raw.sound_kz5_7,
							R.raw.sound_kz5_8, R.raw.sound_kz5_9,
							R.raw.sound_kz5_10, R.raw.sound_ru5_11,
							R.raw.sound_kz5_12 },
					{ R.raw.sound_kz6_1, R.raw.sound_kz6_2, R.raw.sound_kz6_3,
							R.raw.sound_kz6_4, R.raw.sound_ru6_5,
							R.raw.sound_kz6_6, R.raw.sound_ru6_7,
							R.raw.sound_kz6_8, R.raw.sound_kz6_9,
							R.raw.sound_kz6_10, R.raw.sound_kz6_11,
							R.raw.sound_kz6_12 } },
			{ // RU
					{ R.raw.sound_ru1_1, R.raw.sound_ru1_2, R.raw.sound_ru1_3,
							R.raw.sound_ru1_4, R.raw.sound_ru1_5,
							R.raw.sound_ru1_6, R.raw.sound_ru1_7,
							R.raw.sound_ru1_8, R.raw.sound_ru1_9,
							R.raw.sound_ru1_10, R.raw.sound_ru1_11,
							R.raw.sound_ru1_12 },
					{ R.raw.sound_ru2_1, R.raw.sound_ru2_2, R.raw.sound_ru2_3,
							R.raw.sound_ru2_4, R.raw.sound_ru2_5,
							R.raw.sound_ru2_6, R.raw.sound_ru2_7,
							R.raw.sound_ru2_8, R.raw.sound_ru2_9,
							R.raw.sound_ru2_10, R.raw.sound_ru2_11,
							R.raw.sound_ru2_12 },
					{ R.raw.sound_ru3_1, R.raw.sound_ru3_2, R.raw.sound_ru3_3,
							R.raw.sound_ru3_4, R.raw.sound_ru3_5,
							R.raw.sound_ru3_6, R.raw.sound_ru3_7,
							R.raw.sound_ru3_8, R.raw.sound_ru3_9,
							R.raw.sound_ru3_10, R.raw.sound_ru3_11,
							R.raw.sound_ru3_12 },
					{ R.raw.sound_ru4_1, R.raw.sound_ru4_2, R.raw.sound_ru4_3,
							R.raw.sound_ru4_4, R.raw.sound_ru4_5,
							R.raw.sound_ru4_6, R.raw.sound_ru4_7,
							R.raw.sound_ru4_8, R.raw.sound_ru4_9,
							R.raw.sound_ru4_10, R.raw.sound_ru4_11,
							R.raw.sound_ru4_12 },
					{ R.raw.sound_ru5_1, R.raw.sound_ru5_2, R.raw.sound_ru5_3,
							R.raw.sound_ru5_4, R.raw.sound_ru5_5,
							R.raw.sound_ru5_6, R.raw.sound_ru5_7,
							R.raw.sound_ru5_8, R.raw.sound_ru5_9,
							R.raw.sound_ru5_10, R.raw.sound_ru5_11,
							R.raw.sound_ru5_12 },
					{ R.raw.sound_ru6_1, R.raw.sound_ru6_2, R.raw.sound_ru6_3,
							R.raw.sound_ru6_4, R.raw.sound_ru6_5,
							R.raw.sound_ru6_6, R.raw.sound_ru6_7,
							R.raw.sound_ru6_8, R.raw.sound_ru6_9,
							R.raw.sound_ru6_10, R.raw.sound_ru6_11,
							R.raw.sound_ru6_12 } },
			{ // EN
					{ R.raw.sound_en1_1, R.raw.sound_en1_2, R.raw.sound_en1_3,
							R.raw.sound_en1_4, R.raw.sound_en1_5,
							R.raw.sound_en1_6, R.raw.sound_en1_7,
							R.raw.sound_en1_8, R.raw.sound_en1_9,
							R.raw.sound_en1_10, R.raw.sound_en1_11,
							R.raw.sound_en1_12 },
					{ R.raw.sound_en2_1, R.raw.sound_en2_2, R.raw.sound_en2_3,
							R.raw.sound_en2_4, R.raw.sound_en2_5,
							R.raw.sound_en2_6, R.raw.sound_en2_7,
							R.raw.sound_en2_8, R.raw.sound_en2_9,
							R.raw.sound_en2_10, R.raw.sound_en2_11,
							R.raw.sound_en2_12 },
					{ R.raw.sound_en3_1, R.raw.sound_en3_2, R.raw.sound_en3_3,
							R.raw.sound_en3_4, R.raw.sound_en3_5,
							R.raw.sound_en3_6, R.raw.sound_en3_7,
							R.raw.sound_en3_8, R.raw.sound_en3_9,
							R.raw.sound_en3_10, R.raw.sound_en3_11,
							R.raw.sound_en3_12 },
					{ R.raw.sound_en4_1, R.raw.sound_en4_2, R.raw.sound_en4_3,
							R.raw.sound_en4_4, R.raw.sound_en4_5,
							R.raw.sound_en4_6, R.raw.sound_en4_7,
							R.raw.sound_en4_8, R.raw.sound_en4_9,
							R.raw.sound_en4_10, R.raw.sound_en4_11,
							R.raw.sound_en4_12 },
					{ R.raw.sound_en5_1, R.raw.sound_en5_2, R.raw.sound_en5_3,
							R.raw.sound_en5_4, R.raw.sound_en5_5,
							R.raw.sound_en5_6, R.raw.sound_en5_7,
							R.raw.sound_en5_8, R.raw.sound_en5_9,
							R.raw.sound_en5_10, R.raw.sound_en5_11,
							R.raw.sound_en5_12 },
					{ R.raw.sound_en6_1, R.raw.sound_en6_2, R.raw.sound_en6_3,
							R.raw.sound_en6_4, R.raw.sound_en6_5,
							R.raw.sound_en6_6, R.raw.sound_en6_7,
							R.raw.sound_en6_8, R.raw.sound_en6_9,
							R.raw.sound_en6_10, R.raw.sound_en6_11,
							R.raw.sound_en6_12 } }, null, null, null, null,
			null, null, null, null, null, null, null, null };
	private ViewPager viewPager;
	private View prev;
	private ImageView next;
	private TextView textTitle;
	private Animation anim;

	private static final int[] flags = { R.drawable.flag_kazakhstan,
			R.drawable.flag_russia, R.drawable.flag_united_states,
			R.drawable.flag_united_kingdom, R.drawable.flag_france,
			R.drawable.flag_brazil, R.drawable.flag_italy,
			R.drawable.flag_spain, R.drawable.flag_germany,
			R.drawable.flag_denmark, R.drawable.flag_sweden,
			R.drawable.flag_turkey, R.drawable.flag_israel,
			R.drawable.flag_morocco, R.drawable.flag_china };
	private String[] words;
	private MediaPlayer media;
	private AnimationDrawable animAva;

	private int LANG_INTERFACE = 0;
	private int LANG1 = 0;
	private int LANG2 = 1;
	private boolean keySound = true;

	private void loadPreferences() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(ActivityDetail.this);
		LANG_INTERFACE = Integer.parseInt(sp.getString("interface",
				String.valueOf(LANG_INTERFACE)));
		LANG1 = Integer.parseInt(sp.getString("lang1", String.valueOf(LANG1)));
		LANG2 = Integer.parseInt(sp.getString("lang2", String.valueOf(LANG2)));
		keySound = sp.getBoolean("sound", keySound);
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		loadPreferences();
		
		
		int pos = getIntent().getExtras().getInt("pos", 0);
		Log.d(Main.LOG, "pos=" + pos);
		words = Item.getWords(getApplicationContext());
		int[] images = Item.getImages();
		
		animAva = (AnimationDrawable) findViewById(R.id.imageViewAva).getBackground();
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(
				getApplicationContext(), getSupportFragmentManager(), images);
		viewPager.setAdapter(pagerAdapter);
		viewPager.setPageTransformer(false,pagerAdapter);
		prev = (ImageView) findViewById(R.id.imagePrev);
		prev.setOnClickListener(this);
		next = (ImageView) findViewById(R.id.imageNext);
		next.setOnClickListener(this);
		if (pos == 0)
			prev.setVisibility(View.INVISIBLE);
		if (pos == 11)
			next.setVisibility(View.INVISIBLE);
		viewPager.setOnPageChangeListener(new changePage());
		viewPager.setCurrentItem(pos);
		playSound(LANG_INTERFACE);
		anim = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.word_animation);
		textTitle = (TextView) findViewById(R.id.textTitle);
//		textTitle.setTypeface(FontFactory.getFont1(getApplicationContext()));
		ImageView flag1 = (ImageView) findViewById(R.id.imageFlagOne);
		flag1.setImageResource(flags[LANG1]);
		flag1.setOnClickListener(this);
		ImageView flag2 = (ImageView) findViewById(R.id.imageFlagTwo);
		flag2.setImageResource(flags[LANG2]);
		flag2.setOnClickListener(this);
		ImageView back = (ImageView) findViewById(R.id.imageViewBack);
		back.setOnClickListener(this);
		

		
		
	}

	@Override
	public void onClick(View v) {
		int curr = 0;
		switch (v.getId()) {
		case R.id.imageFlagOne:
			// MessageFormat.format( words[Main.LANG1],ActivityIcons.part);
			textAnimation(LANG1);
			break;
		case R.id.imageFlagTwo:
			textAnimation(LANG2);
			break;
		case R.id.imageViewBack:
			onBackPressed();
			break;
		case R.id.imagePrev:
			curr = viewPager.getCurrentItem() - 1;
			viewPager.setCurrentItem(curr);
			break;
		case R.id.imageNext:
			curr = viewPager.getCurrentItem() + 1;
			viewPager.setCurrentItem(curr);
			break;
		}
	}

	private void textAnimation(int lang) {
		textTitle.setText(MessageFormat.format(words[lang],
				viewPager.getCurrentItem()));
		textTitle.startAnimation(anim);
		playSound(lang);
	}

	private class changePage implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int pos) {
			if (pos == 0)
				prev.setVisibility(View.INVISIBLE);
			if (pos == 10)
				next.setVisibility(View.VISIBLE);
			if (pos == 1)
				prev.setVisibility(View.VISIBLE);
			if (pos == 11)
				next.setVisibility(View.INVISIBLE);
			
			playSound(LANG_INTERFACE);
		}
		
	}

	private void playSound(int lang) {
		if ((sounds[lang] != null) && (keySound)) {
			int sound = sounds[lang][ActivityIcons.part][viewPager
					.getCurrentItem()];
			media = MediaPlayer.create(ActivityDetail.this, sound);
			media.start();
			animAva.stop();
			animAva.start();
		}
	}
	
	//
	// public class onClickListener implements OnClickListener {
	//
	// @Override
	// public void onClick(View v) {
	// switch (v.getId()) {
	// case R.id.imageButtonPrevious:
	// textViewTitle.setAnimation(null);
	// if (position + 1 == ImageAdapter.icons.length)
	// buttonNext.setVisibility(Button.VISIBLE);
	// position -= 1;
	//
	// if (position == 0)
	// buttonPrevious.setVisibility(Button.INVISIBLE);
	//
	// imageAnimation(imageViewIcon);
	//
	// break;
	// case R.id.imageButtonNext:
	// textViewTitle.setAnimation(null);
	// if (position == 0)
	// buttonPrevious.setVisibility(Button.VISIBLE);
	// position += 1;
	//
	// if (position + 1 == ImageAdapter.icons.length)
	// buttonNext.setVisibility(Button.INVISIBLE);
	//
	// imageAnimation(imageViewIcon);
	//
	// break;
	//
	// case R.id.buttonFlagOne:
	//
	// textViewTitle.setText(MessageFormat.format(words[Main.LANG1],
	// position));
	// titleAnimation(textViewTitle);
	// // playSound(SoundAdapter.getSounds1(), position);
	// break;
	// case R.id.buttonFlagTwo:
	// textViewTitle.setText(MessageFormat.format(words[Main.LANG2],
	// position));
	// // playSound(SoundAdapter.getSounds2(), position);
	// titleAnimation(textViewTitle);
	// break;
	//
	// }
	//
	// }
	//
	// }
	//

	//
	// private void imageAnimation(View v) {
	// // imageViewIcon.setImageResource(ImageAdapter.getIcon2x(pos));
	// Animation anim = (Animation)
	// AnimationUtils.loadAnimation(getApplicationContext(),
	// android.R.anim.fade_in);
	// anim.setDuration(1500);
	// v.setAnimation(anim);
	// }
	//
	// public void onClickBackList(View v) {
	// Intent intent = new Intent();
	// intent.putExtra("position", position);
	// setResult(RESULT_OK, intent);
	// finish();
	// overridePendingTransition(android.R.anim.fade_in,
	// android.R.anim.fade_out);
	// }

	// }
	// @Override
	// public void onBackPressed() {
	// onBackPressed();
	// // Intent intent = new Intent(ActivityDetail.this, ActivityIcons.class);
	// // startActivity(intent);
	// //
	// overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
	// }
}
