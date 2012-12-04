package com.pulllist;

import java.util.List;

import com.pulllist.data.RssItem;
import com.pulllist.listeners.ListListener;
import com.pulllist.util.RssReader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
 
public class MainActivity extends Activity {
	
	private ProgressDialog m_ProgressDialog = null; 
    private ListView itcItems;
    private List<RssItem> rssItems;
    private LazyAdapter adapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        //Read RSS Feed
        new RSSRead(this).execute();
    }
    
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
    }
    
    private class RSSRead extends AsyncTask<Void, Integer, Void>{
        Activity parent;
        
    	RSSRead(Activity parent){
    		this.parent = parent;
    	}
        
        // A callback method executed on UI thread on starting the task
        @Override
        protected void onPreExecute() {
            // Getting reference to the TextView tv_counter of the layout activity_main
        	m_ProgressDialog = ProgressDialog.show(parent,    
                    "Please wait...", "Retrieving data ...", true);
        }
 
        // A callback method executed on non UI thread, invoked after
        // onPreExecute method if exists
        @Override
        protected Void doInBackground(Void... params) {
        	try {
    			// Create RSS reader
    			RssReader rssReader = new RssReader(parent.getString(R.string.xmlFeed));
        		//RssReader rssReader = new RssReader("http://feeds.feedburner.com/ComixologyDigitalComics?fmt=xml");
    			
    			// Get a ListView from main view
    			itcItems = (ListView) findViewById(R.id.listMainView);

    			rssItems = rssReader.getItems();
    			
    			// Create a list adapter
    			//adapter = new ArrayAdapter<RssItem>(parent,android.R.layout.simple_list_item_1, rssItems);
    			adapter = new LazyAdapter(parent, rssItems);
    			
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