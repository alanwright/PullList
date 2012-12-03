package com.pulllist;

import java.util.List;

import com.pulllist.data.RssItem;
import com.pulllist.listeners.ListListener;
import com.pulllist.util.RssReader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
public class MainActivity extends Activity {
	
	private ProgressDialog m_ProgressDialog = null; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Read RSS Feed
        new RSSRead(this).execute();
    }
    
    private class RSSRead extends AsyncTask<Void, Integer, Void>{
        Activity parent;
        ListView itcItems;
        List<RssItem> rssItems;
        ArrayAdapter<RssItem> adapter;
        
    	RSSRead(MainActivity parent){
    		this.parent = parent;
    	}
        
        // A callback method executed on UI thread on starting the task
        @Override
        protected void onPreExecute() {
            // Getting reference to the TextView tv_counter of the layout activity_main
        	m_ProgressDialog = ProgressDialog.show(MainActivity.this,    
                    "Please wait...", "Retrieving data ...", true);
        }
 
        // A callback method executed on non UI thread, invoked after
        // onPreExecute method if exists
        @Override
        protected Void doInBackground(Void... params) {
        	try {
    			// Create RSS reader
    			//RssReader rssReader = new RssReader(parent.getString(R.string.xmlFeed));
        		RssReader rssReader = new RssReader("http://feeds.feedburner.com/ComixologyDigitalComics?fmt=xml");
    			
    			// Get a ListView from main view
    			itcItems = (ListView) findViewById(R.id.listMainView);

    			rssItems = rssReader.getItems();
    			
    			// Create a list adapter
    			adapter = new ArrayAdapter<RssItem>(parent,android.R.layout.simple_list_item_1, rssItems);
    			
    		} catch (Exception e) {
    			Log.e("ITCRssReader", e.getMessage());
    		}
          
          return null;
        }
 
        // A callback method executed on UI thread, invoked by the publishProgress()
        // from doInBackground() method
        @Override
        protected void onProgressUpdate(Integer... values) {
        		int val = values[0];
        		switch(val){
        		case(1):
        			m_ProgressDialog.setMessage("Turning on the Bat Signal...");
        			break;
        		case(2):
        			m_ProgressDialog.setMessage("Assembling the Avengers...");
        			break;
        		case(3):
        			m_ProgressDialog.setMessage("Calming down the Hulk...");
        			break;
        		default:
        			break;
        		}
        }
 
        // A callback method executed on UI thread, invoked after the completion of the task
        @Override
        protected void onPostExecute(Void result) {
        	// Set list adapter for the ListView
			itcItems.setAdapter(adapter);
			
			// Set list view item click listener
			itcItems.setOnItemClickListener(new ListListener(rssItems, parent));
			m_ProgressDialog.dismiss();
        }
       
    }
}

//package com.pulllist;
//
//import android.app.Activity;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.util.Log;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import com.pulllist.data.RssItem;
//import com.pulllist.listeners.ListListener;
//import com.pulllist.util.RssReader;
//
///**
// * Main application activity.
// * 
// * @author ITCuties
// *
// */
//public class MainActivity extends Activity {
//	/** 
//	 * This method creates main application view
//	 */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		// Set view
//		setContentView(R.layout.activity_main);
//		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//		StrictMode.setThreadPolicy(policy);
//		
//		try {
//			// Create RSS reader
//			RssReader rssReader = new RssReader("http://feeds.feedburner.com/ComixologyDigitalComics");
//			// Get a ListView from main view
//			ListView itcItems = (ListView) findViewById(R.id.listMainView);
//			
//			// Create a list adapter
//			ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(this,android.R.layout.simple_list_item_1, rssReader.getItems());
//			// Set list adapter for the ListView
//			itcItems.setAdapter(adapter);
//			
//			// Set list view item click listener
//			itcItems.setOnItemClickListener(new ListListener(rssReader.getItems(), this));
//			
//		} catch (Exception e) {
//			Log.e("ITCRssReader", e.getMessage());
//		}
//		
//	}
//	
//	
//}