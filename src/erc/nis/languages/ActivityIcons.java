package erc.nis.languages;

import java.text.MessageFormat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityIcons extends Activity implements OnItemClickListener {

	public static int part = 0;
	private static int[] icons = new int[] { R.drawable.c1, R.drawable.c2,
			R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6 };
	private ImageView imageViewBack;

	private int[] imgs;
	private int LANG_INTERFACE = 0;
	private GridView gv;

	private void loadPreferences() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(ActivityIcons.this);
		LANG_INTERFACE = Integer.parseInt(sp.getString("interface",
				String.valueOf(LANG_INTERFACE)));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icons);
		loadPreferences();
		
		part = getIntent().getIntExtra("part", part);
		String title = getIntent().getStringExtra("title");
		TextView textViewTitle = (TextView) findViewById(R.id.textTitle);
		textViewTitle.setText(MessageFormat.format(title, LANG_INTERFACE));
		// textViewTitle.setTypeface(FontFactory.getFont1(getApplicationContext()));
		ImageView imageIcon = (ImageView) findViewById(R.id.imageViewIcon);
		imageIcon.setImageResource(icons[part]);
		gv = (GridView) findViewById(R.id.gridViewList);

		imgs = Item.getImages();
		ImageAdapter imageAdapter = new ImageAdapter(getApplicationContext(),
				imgs);
		gv.setAdapter(imageAdapter);
		gv.setOnItemClickListener(this);

		mShortAnimationDuration = getResources().getInteger(
				android.R.integer.config_longAnimTime);

		imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
		imageViewBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		findViewById(R.id.imageViewSettings).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivityForResult(new Intent(ActivityIcons.this, SettingsActivity.class),2);
						overridePendingTransition(android.R.anim.fade_in,
								android.R.anim.fade_out);
					}
				});

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int pos, long arg3) {
		zoomImageFromThumb(v, pos);

	}

	private void startIcon(int pos) {
		Intent i = new Intent(getApplicationContext(), ActivityDetail.class);
		i.putExtra("pos", pos);
		startActivity(i);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		// overridePendingTransition(0, 0);
	}

	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(ActivityIcons.this, Main.class));
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
	}

	private Animator mCurrentAnimator;
	private int mShortAnimationDuration;

	@SuppressLint("NewApi")
	public void zoomImageFromThumb(final View thumbView, final int pos) {
		if (mCurrentAnimator != null) {
			mCurrentAnimator.cancel();
		}
		// final LinearLayout containerImage = (LinearLayout)
		// findViewById(R.id.containerImage);
		final ImageView expandedTextTitle = (ImageView) findViewById(R.id.imageViewItem);
		expandedTextTitle.setVisibility(View.VISIBLE);
		expandedTextTitle.setBackgroundResource(R.drawable.bkg_icon);
		expandedTextTitle.setImageResource(imgs[pos]);
		final Rect startBounds = new Rect();
		final Rect finalBounds = new Rect();
		final Point globalOffset = new Point();

		thumbView.getGlobalVisibleRect(startBounds);
		findViewById(R.id.container).getGlobalVisibleRect(finalBounds,
				globalOffset);
		startBounds.offset(-globalOffset.x, -globalOffset.y);
		finalBounds.offset(-globalOffset.x, -globalOffset.y);

//		Log.w(Main.LOG, "startBounds=>" + startBounds.top + ","
//				+ startBounds.right + "," + startBounds.bottom + ","
//				+ startBounds.left);
//		Log.w(Main.LOG, "finalBounds=>" + finalBounds.top + ","
//				+ finalBounds.right + "," + finalBounds.bottom + ","
//				+ finalBounds.left);
//		Log.w(Main.LOG, "expandedTextTitle w=" + expandedTextTitle.getWidth());
//		Log.w(Main.LOG, "expandedTextTitle h=" + expandedTextTitle.getHeight());

		float startScale;
		if ((float) finalBounds.width() / finalBounds.height() > (float) startBounds
				.width() / startBounds.height()) {
			// Extend start bounds horizontally
			startScale = (float) startBounds.height() / finalBounds.height();
			float startWidth = startScale * finalBounds.width();
			float deltaWidth = (startWidth - startBounds.width()) / 2;
			startBounds.left -= deltaWidth;
			startBounds.right += deltaWidth;
		} else {
			// Extend start bounds vertically
			startScale = (float) startBounds.width() / finalBounds.width();
			float startHeight = startScale * finalBounds.height();
			float deltaHeight = (startHeight - startBounds.height()) / 2;
			startBounds.top -= deltaHeight;
			startBounds.bottom += deltaHeight;
		}

		thumbView.setAlpha(0f);
		expandedTextTitle.setPivotX(0f);
		expandedTextTitle.setPivotY(0f);

		AnimatorSet set = new AnimatorSet();
		set.play(
				ObjectAnimator.ofFloat(expandedTextTitle, View.X,
						startBounds.left, finalBounds.right / 2
								- expandedTextTitle.getWidth() / 2))
				.with(ObjectAnimator.ofFloat(expandedTextTitle, View.Y,
						startBounds.top, finalBounds.bottom / 2
								- expandedTextTitle.getHeight() / 2))
				.with(ObjectAnimator.ofFloat(expandedTextTitle, View.SCALE_X,
						startScale, 1f))
				.with(ObjectAnimator.ofFloat(expandedTextTitle, View.SCALE_Y,
						startScale, 1f));
		set.setDuration(mShortAnimationDuration);
		set.setInterpolator(new DecelerateInterpolator());
		set.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationStart(Animator animation) {
				gv.setEnabled(false);
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				mCurrentAnimator = null;
				expandedTextTitle.setVisibility(View.INVISIBLE);
				thumbView.setAlpha(1f);
				startIcon(pos);
				gv.setEnabled(true);
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				mCurrentAnimator = null;
				gv.setEnabled(true);
			}
		});
		set.start();
		mCurrentAnimator = set;

		// final float startScaleFinal = startScale;
		// imageViewBack.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// if (mCurrentAnimator != null) {
		// mCurrentAnimator.cancel();
		// }
		//
		// AnimatorSet set = new AnimatorSet();
		// set.play(
		// ObjectAnimator.ofFloat(expandedTextTitle, View.X,
		// startBounds.left))
		// .with(ObjectAnimator.ofFloat(expandedTextTitle, View.Y,
		// startBounds.top))
		// .with(ObjectAnimator.ofFloat(expandedTextTitle,
		// View.SCALE_X, startScaleFinal))
		// .with(ObjectAnimator.ofFloat(expandedTextTitle,
		// View.SCALE_Y, startScaleFinal));
		// set.setDuration(mShortAnimationDuration);
		// set.setInterpolator(new DecelerateInterpolator());
		// set.addListener(new AnimatorListenerAdapter() {
		// @Override
		// public void onAnimationStart(Animator animation) {
		// // containerRow.setVisibility(View.INVISIBLE);
		// super.onAnimationStart(animation);
		// }
		//
		// @Override
		// public void onAnimationEnd(Animator animation) {
		// expandedTextTitle.setVisibility(View.INVISIBLE);
		// thumbView.setAlpha(1f);
		// mCurrentAnimator = null;
		// }
		//
		// @Override
		// public void onAnimationCancel(Animator animation) {
		// thumbView.setAlpha(1f);
		// mCurrentAnimator = null;
		// }
		// });
		// set.start();
		// mCurrentAnimator = set;
		// }
		// });
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
