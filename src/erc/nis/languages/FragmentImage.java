package erc.nis.languages;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FragmentImage extends Fragment{

	private static final String ATTR_IMG = "img";

	public static Fragment newInstance(Context context, int pos, int img) {
		Bundle b = new Bundle();
		b.putInt(ATTR_IMG, img);
		return Fragment.instantiate(context, FragmentImage.class.getName(), b);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int img = this.getArguments().getInt(ATTR_IMG);	
		ImageView image = new ImageView(getActivity());
		image.setImageResource(img);

		return image;
	}


}