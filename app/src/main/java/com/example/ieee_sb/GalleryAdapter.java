package com.example.ieee_sb;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {
    private Context mContext;
    private Dialog dialog;

    public GalleryAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(370, 370));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        imageView.setTransitionName("image");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext,GalleryFullScreen.class);
                i.putExtra("image",mThumbIds[position]);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity)mContext, imageView, "image");
                mContext.startActivity(i,options.toBundle());
            }
        });
        return imageView;
    }

    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.background,R.drawable.background1,
            R.drawable.background,R.drawable.background1,
            R.drawable.background,R.drawable.background1,
            R.drawable.background,R.drawable.background1,
            R.drawable.background,R.drawable.background1,
            R.drawable.background,R.drawable.background1,
            R.drawable.background,R.drawable.background1,
            R.drawable.background,R.drawable.background1,
            R.drawable.background,R.drawable.background1
    };

//    public void showPopUp(int i){
//        dialog = new Dialog(mContext);
//        dialog.setContentView(R.layout.activity_pop_up_poster);
//        Window window = dialog.getWindow();
//        ImageView poster = dialog.findViewById(R.id.dialog_poster);
//        poster.setImageResource(mThumbIds[i]);
//        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        window.setGravity(Gravity.CENTER);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCancelable(true);
//        dialog.show();
//    }
}