package erc.nis.languages;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private int[] images;
	private int sizeItem;

	public ImageAdapter(Context context, int[] images) {
		this.context = context;
		this.images = images;		
		this.sizeItem = (int) context.getResources().getDimension(R.dimen.size_icon);
	}

	@Override
	public int getCount() {
		return images.length;
	}

	@Override
	public Integer getItem(int position) {
		return images[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ImageView iv;
		if (convertView != null) {
			iv = (ImageView) convertView;
		} else {
			iv = new ImageView(context);
			iv.setLayoutParams(new GridView.LayoutParams(sizeItem, sizeItem));
			iv.setScaleType(ScaleType.CENTER_INSIDE);
			iv.setPadding(10, 10, 10, 10);
			iv.setBackgroundResource(R.drawable.bkg_icon);
		}

		iv.setImageResource(getItem(position));
		return iv;
	}

}
