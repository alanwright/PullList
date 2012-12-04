package com.pulllist;

import java.util.List;

import com.pulllist.data.RssItem;
import com.pulllist.imageutils.ImageLoader;
 
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class LazyAdapter extends BaseAdapter {
 
	private Activity activity;
    private List<RssItem> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public LazyAdapter(Activity parent, List<RssItem> items) {
        activity = parent;
        data=items;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }
    public int getCount() {
        return data.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);
 
        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.desc); // artist name
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
 
        RssItem comic = data.get(position);
 
        // Setting all values in listview
        title.setText(comic.getTitle());
        artist.setText(comic.getDescription());
        imageLoader.DisplayImage(comic.getImgURL(), thumb_image);
        return vi;
    }
}