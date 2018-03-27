package erc.nis.languages;

import java.text.MessageFormat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener {

	public static final String LOG = "Languages";
	private GridView gvMain;
	private SharedPreferences sp;
	private String[] albumNames;
	private Animation anim;
	private Animation anim2;
	private Animation animShape;
	private ImageView imageDialogBoy;
	private ImageView imageDialogShape;
	private static int LANG_INTERFACE = 0;
//	public static int LANG1 = 0;
//	public static int LANG2 = 1;
	private boolean keySound = true;
	private static final int[] shapes = new int[] { R.drawable.header_main_kz,
			R.drawable.header_main_ru, R.drawable.header_main_en };

	private static final int[] goes = new int[] { R.drawable.go_kz,
			R.drawable.go_ru, R.drawable.go_en };
	private static final int[] raws = new int[] { R.raw.go_kz, R.raw.go_ru,
			R.raw.go_en };

	private static final int[] dialogs = new int[] { R.drawable.p1,
			R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5,
			R.drawable.p6, R.drawable.p7 };
	private int currentDialog = 0;
	private MediaPlayer media;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		loadPreferences();
	
		anim = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.word_animation);
		anim2 = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.word_animation);
		animShape = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_shape);
		
		ImageView imageShape = (ImageView) findViewById(R.id.imageViewShape);
		imageShape.setImageResource(shapes[LANG_INTERFACE]);
		imageShape.setOnClickListener(this);

		ImageView imageSettings = (ImageView) findViewById(R.id.imageViewSettings);
		imageSettings.setOnClickListener(this);
		ImageView imageBoy = (ImageView) findViewById(R.id.imageViewBoy);
		imageBoy.setOnClickListener(this);

		gvMain = (GridView) findViewById(R.id.gridViewMain);
		albumNames = getResources().getStringArray(R.array.TitlesCoverItem);
		AlbumsAdapter albumAdapter = new AlbumsAdapter(Main.this, albumNames,LANG_INTERFACE);
		gvMain.setAdapter(albumAdapter);
		imageDialogBoy = (ImageView) findViewById(R.id.imageDialogBoy);
		imageDialogBoy.setImageResource(goes[LANG_INTERFACE]);
		imageDialogShape = (ImageView) findViewById(R.id.imageDialogShape);
	}

	private void loadPreferences() {
		sp = PreferenceManager.getDefaultSharedPreferences(Main.this);
		LANG_INTERFACE = Integer.parseInt(sp.getString("interface",
				String.valueOf(LANG_INTERFACE)));
//		LANG1 = Integer.parseInt(sp.getString("lang1", String.valueOf(LANG1)));
//		LANG2 = Integer.parseInt(sp.getString("lang2", String.valueOf(LANG2)));
		keySound = sp.getBoolean("sound", keySound);
	}

	private void playSound(int rawId) {
		if (keySound) {
			media = MediaPlayer.create(Main.this, rawId);
			media.start();
		}
	}

	public void onClickItem(View v) {
		int pos = (Integer) v.getTag();
		Intent intent = new Intent(Main.this, ActivityIcons.class);
		intent.putExtra("part", pos);
		intent.putExtra("title", albumNames[pos]);
		startActivity(intent);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
	}

	private static long back_pressed;

	@Override
	public void onBackPressed() {
		if (back_pressed + 2000 > System.currentTimeMillis()) {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		} else
			Toast.makeText(
					getBaseContext(),
					MessageFormat.format(getString(R.string.toast_exit),
							LANG_INTERFACE), Toast.LENGTH_SHORT).show();
		back_pressed = System.currentTimeMillis();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageViewSettings:
			startActivityForResult(new Intent(Main.this, SettingsActivity.class),1);
			overridePendingTransition(android.R.anim.fade_in,
					android.R.anim.fade_out);
			break;
		case R.id.imageViewBoy:

			if (back_pressed + 2000 > System.currentTimeMillis()) {

			} else {
				imageDialogBoy.startAnimation(anim);
				playSound(raws[LANG_INTERFACE]);
				AnimationDrawable animDraw = (AnimationDrawable) v.getBackground();
				animDraw.start();
			}

			back_pressed = System.currentTimeMillis();
			break;
		case R.id.imageViewShape:
			playSound(R.raw.pirate);
			currentDialog = currentDialog % dialogs.length;
			imageDialogShape.setImageResource(dialogs[currentDialog++]);
			imageDialogShape.startAnimation(anim2);
			v.startAnimation(animShape);
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	if(data==null) return;
		if(data.getAction().equals("restart")){
		Log.d(Main.LOG,"restart");	
		finish();
		startActivity(getIntent());
		overridePendingTransition(0, 0);
		}
	}
}
