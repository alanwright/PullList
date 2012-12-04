package com.pulllist.listeners;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
//import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.pulllist.SingleMenuItemActivity;
import com.pulllist.data.RssItem;

/**
 * Class implements a list listener
 * 
 * @author ITCuties
 *
 */
public class ListListener implements OnItemClickListener {

	// List item's reference
	List<RssItem> listItems;
	// Calling activity reference
	Activity activity;
	
	// XML node keys
	static final String KEY_TITLE = "title";
	static final String KEY_IMG = "img";
	static final String KEY_PUB = "publisher";
	static final String KEY_DESC = "description";
	static final String KEY_PUBDATE = "pubDate";
	
	public ListListener(List<RssItem> aListItems, Activity parseRSS) {
		listItems = aListItems;
		activity  = parseRSS;
	}
	
	/**
	 * Start a browser with url from the rss item.
	 */
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		//Links to website
		//Intent i = new Intent(Intent.ACTION_VIEW);
		//i.setData(Uri.parse(listItems.get(pos).getLink()));
	
		//activity.startActivity(i);
		
		 // getting values from selected ListItem
        String title = listItems.get(pos).getTitle();
        String category = listItems.get(pos).getCategory();
        String description = listItems.get(pos).getDescription();
        String pubDate = listItems.get(pos).getPubDate();
        String imgURL = listItems.get(pos).getImgURL();

        // Starting new intent
        Intent in = new Intent(activity.getApplicationContext(), SingleMenuItemActivity.class);
        in.putExtra(KEY_TITLE, title);
        in.putExtra(KEY_IMG, imgURL);
        in.putExtra(KEY_PUB, category);
        in.putExtra(KEY_DESC, description);
        in.putExtra(KEY_PUBDATE, pubDate);
        activity.startActivity(in);
	}
	
}
