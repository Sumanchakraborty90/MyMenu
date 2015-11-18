package com.bitcanny.office.mymenu;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryAdapter extends ArrayAdapter {

	Context context;
	List<Map<String, String>> list ;
	Map<String, String> map;
	Typeface typeface;
	PlaceOrderSqlHelperDao dao;
	private int lastPosition = -1;


	/*public CategoryAdapter(Context context,
			 List<Map<String, String>> list) {
		super(context, R.layout.category_item, list);
		this.context = context;
		this.list = list;
	}*/

	public CategoryAdapter(Context context, int resource, List<Map<String, String>> list) {
		super(context, R.layout.category_item, list);
		this.list = list;
		this.context = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService
				(Context.LAYOUT_INFLATER_SERVICE);
		dao = new PlaceOrderSqlHelperDao(context);
		convertView = inflater.inflate(R.layout.category_item, parent, false);
		
		ImageView imgView = (ImageView) convertView.findViewById(R.id.img_cat);
		
		TextView button = (TextView) convertView.findViewById(R.id.btn_cat_name);

		TextView txt_doc_number = (TextView) convertView.findViewById(R.id.txt_doc_number);


		/*CardView card_view = (CardView)convertView.findViewById(R.id.card_view);


		card_view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});*/

		//RelativeLayout relativeLayout = (RelativeLayout)convertView.findViewById(R.id.rel2);
		
		map = list.get(position);

		//relativeLayout.setBackgroundResource(R.drawable.focusable_color);
	/*	try {

			new Thread(new Runnable() {
				public void run() {
					ResturantEntryActivity.DownloadFromUrl("/MenuApp/MenuCategory/", position + ".jpg", JsonFunctions.BASE_URL + map.get("CategoryImageURL"));
				}
			}).start();*/

		try {

			for(int index = 0;index<list.size();index++){
			if(!list.get(position).get("CategoryName").equals(Utility.getItemImageName(getFromSdcard("/MenuApp/MenuCategory/").get(index)) )) {
				Picasso.with(context)/*.load(JsonFunctions.BASE_URL+map.get("CategoryImageURL"))*/
						.load(new File(getFromSdcard("/MenuApp/MenuCategory/").get(position)))
						.placeholder(R.mipmap.ic_launcher)
						.fit()
						.transform(new RoundedCorner(4, 0))
						.error(R.mipmap.ic_launcher)
						.into(imgView);
			}
			}
		}catch (Exception e){

			e.printStackTrace();
		}

		/*}catch (Exception e){

			e.printStackTrace();
		}
		*/
		typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ufonts.com_century-gothic.ttf");

		button.setText(map.get("CategoryName"));

		button.setTypeface(typeface);

		txt_doc_number.setText(String.valueOf(dao.getMenuDetails(map.get("CategoryName")).size()) + " Items");
		txt_doc_number.setTypeface(typeface);

		try {

			/*ObjectAnimator animX = ObjectAnimator.ofFloat(convertView, "Y", -50f,50);
			animX.setDuration(3000);
			animX.start();*/

		/*	Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.down_from_top : R.anim.up_from_bottom);
			convertView.startAnimation(animation);
			lastPosition = position;*/
		}catch (Exception e){

			e.printStackTrace();
		}
		
		return convertView;
	}



	public List<String> getFromSdcard(String path)
	{
		ArrayList<String> f = new ArrayList<String>();
		File[] listFile;
		File file= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+path);

		if (file.isDirectory())
		{
			listFile = file.listFiles();


			for (int i = 0; i < listFile.length; i++)
			{

				f.add(listFile[i].getAbsolutePath());

				Log.d("val",listFile[i].getAbsolutePath());

			}
		}

		return  f;
	}

	
	

}
