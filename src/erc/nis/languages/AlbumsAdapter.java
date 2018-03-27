package erc.nis.languages;

import java.text.MessageFormat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumsAdapter extends BaseAdapter {
	private static int[] coverItems = new int[] { R.drawable.img_chick,
			R.drawable.img_purple, R.drawable.img_raspberry,
			R.drawable.img_salad, R.drawable.img_dolphin, R.drawable.img_lion };

	private static int[] icons = new int[] { R.drawable.c1, R.drawable.c2,
			R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6 };
	private String[] albumNames;
	private LayoutInflater inflater;
	private Animation[] animIcons;
	private int LANG_INTERFACE;

	private static int[] bkgs = new int[] { R.drawable.m1, R.drawable.m2,
			R.drawable.m3, R.drawable.m4, R.drawable.m5, R.drawable.m6 };

	// public AlbumsAdapter(Context context, String[] albumNames) {
	// this.albumNames = albumNames;
	// this.inflater = (LayoutInflater) context
	// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	// this.animIcons = new Animation[]{
	// AnimationUtils.loadAnimation(context,R.anim.chip_repeat),AnimationUtils.loadAnimation(context,R.anim.chip_repeat),AnimationUtils.loadAnimation(context,R.anim.chip_repeat)};
	//
	// }

	public AlbumsAdapter(Context context, String[] albumNames,
			int LANG_INTERFACE) {
		this.albumNames = albumNames;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.animIcons = new Animation[] {
				AnimationUtils.loadAnimation(context, R.anim.chip_repeat),
				AnimationUtils.loadAnimation(context, R.anim.chip_repeat_2),
				AnimationUtils.loadAnimation(context, R.anim.chip_repeat_3) };
		this.LANG_INTERFACE = LANG_INTERFACE;
	}

	@Override
	public int getCount() {
		return albumNames.length;
	}

	@Override
	public Object getItem(int position) {
		return albumNames[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		if (convertView != null) {
			view = (FrameLayout) convertView;
		} else {

			view = inflater.inflate(R.layout.cover_item, parent, false);
			ImageView imageView = (ImageView) view
					.findViewById(R.id.imageViewCoverItem);
			imageView.setImageResource(coverItems[position]);
			imageView.setBackgroundResource(bkgs[position]);
			imageView.setTag(position);
			TextView textView = (TextView) view
					.findViewById(R.id.textViewAlbumName);
			textView.setText(MessageFormat.format(albumNames[position],
					LANG_INTERFACE));
			// textView.setTypeface(typeface);
			ImageView iconLeft = (ImageView) view
					.findViewById(R.id.imageViewIconAnim);
			iconLeft.setImageResource(icons[position]);
			iconLeft.startAnimation(animIcons[position%3]);
		}

		return view;
	}

}
