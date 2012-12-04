package com.pulllist;

import com.pulllist.imageutils.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleMenuItemActivity  extends Activity {
	
	// XML node keys
	static final String KEY_TITLE = "title";
	static final String KEY_PUB = "publisher";
	static final String KEY_IMG = "img";
	static final String KEY_DESC = "description";
	static final String KEY_PUBDATE = "pubDate";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        // getting intent data
        Intent in = getIntent();
        ImageLoader imageLoader=new ImageLoader(getApplicationContext());
        
        // Get XML values from previous intent
        String title = in.getStringExtra(KEY_TITLE);
        String pub = in.getStringExtra(KEY_PUB);
        String description = in.getStringExtra(KEY_DESC);
        String pubDate = in.getStringExtra(KEY_PUBDATE);
        String imgURL = in.getStringExtra(KEY_IMG);
        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.title_label);
        TextView lblCost = (TextView) findViewById(R.id.pub_label);
        TextView lblDesc = (TextView) findViewById(R.id.description_label); 
        TextView lblPubDate = (TextView) findViewById(R.id.pubDate_label);
        ImageView lblImg=(ImageView) findViewById(R.id.comicImage_label);
        
        lblName.setText(title);
        lblCost.setText(pub);
        lblDesc.setText(description);
        lblPubDate.setText(pubDate);
        imageLoader.DisplayImage(imgURL, lblImg);
    }
}
