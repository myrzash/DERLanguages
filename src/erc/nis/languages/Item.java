package erc.nis.languages;

import android.content.Context;

public class Item {

	private static int[][] images = new int[][] {
			{ R.drawable.img_duck, R.drawable.img_horse, R.drawable.img_chick,
					R.drawable.img_hen, R.drawable.img_rabbit,
					R.drawable.img_donkey, R.drawable.img_goat,
					R.drawable.img_pig, R.drawable.img_sheep,
					R.drawable.img_cat, R.drawable.img_cow, R.drawable.img_dog },
			{ R.drawable.img_white, R.drawable.img_yellow,
					R.drawable.img_beige, R.drawable.img_green,
					R.drawable.img_brown, R.drawable.img_blue,
					R.drawable.img_pink, R.drawable.img_red,
					R.drawable.img_black, R.drawable.img_gray,
					R.drawable.img_orange_color, R.drawable.img_purple },
			{ R.drawable.img_pear, R.drawable.img_banana,
					R.drawable.img_strawberry, R.drawable.img_pineapple,
					R.drawable.img_apple, R.drawable.img_raspberry,
					R.drawable.img_coconut, R.drawable.img_passionfruit,
					R.drawable.img_orange, R.drawable.img_watermelon,
					R.drawable.img_cherry, R.drawable.img_melon },
			{ R.drawable.img_zucchini, R.drawable.img_tomato,
					R.drawable.img_cauliflower, R.drawable.img_pepper,
					R.drawable.img_carrot, R.drawable.img_eggplant,
					R.drawable.img_greenpeas, R.drawable.img_broccoli,
					R.drawable.img_potato, R.drawable.img_pumpkin,
					R.drawable.img_onion, R.drawable.img_salad },
			{ R.drawable.img_jellyfish, R.drawable.img_shell,
					R.drawable.img_shark, R.drawable.img_starfish,
					R.drawable.img_killerwhale, R.drawable.img_whale,
					R.drawable.img_octopus, R.drawable.img_crab,
					R.drawable.img_turtle, R.drawable.img_fish,
					R.drawable.img_dolphin, R.drawable.img_seahorse },
			{ R.drawable.img_rhinoceros, R.drawable.img_monkey,
					R.drawable.img_zebra, R.drawable.img_hippopotamus,
					R.drawable.img_leopard, R.drawable.img_lion,
					R.drawable.img_gnu, R.drawable.img_antelope,
					R.drawable.img_giraffe, R.drawable.img_cheetah,
					R.drawable.img_hyena, R.drawable.img_elephant } };

	

	public static int[] getImages() {
		// TODO Auto-generated method stub
		return images[ActivityIcons.part];
	}
	
	public static String[] getWords(Context context) {	
		int resId = 0;
		switch(ActivityIcons.part){
		case 0: resId = R.array.FarmAnimals; break;
		case 1: resId = R.array.Colors; break;
		case 2: resId = R.array.Fruits; break;
		case 3: resId = R.array.Vegetables; break;
		case 4: resId = R.array.AnimalsOcean; break;
		case 5: resId = R.array.AnimalsSavanna; break;
		}		
		String[] words = context.getResources().getStringArray(resId);
		return words;
	}
}
