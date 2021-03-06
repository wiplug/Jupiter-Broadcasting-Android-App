package jupiter.broadcasting.live.tv;

import java.util.Hashtable;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
/*
 * Copyright (c) 2012 Shane Quigley
 *
 * This software is MIT licensed see link for details
 * http://www.opensource.org/licenses/MIT
 * 
 * @author Shane Quigley
 */
public class RssListActivity extends Activity {
    /** Called when the activity is first created. */
    public Hashtable<String,String> showToFeedTable;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView showsListView = (ListView) findViewById(R.id.showList);
        final String[] shows = new String[]{"All Shows",
        		"Coder Radio",
        		"Faux Show",
        		"Linux Action Show",
        		"SciByte",
        		"Techsnap",
        		"Unfilter"};
        showToFeedTable = new Hashtable<String,String>();
        showToFeedTable.put("Youtube Feed", "http://www.youtube.com/rss/user/JupiterBroadcasting/videos.rss");
        showToFeedTable.put("All Shows", "http://feeds.feedburner.com/JupiterBroadcasting");
        showToFeedTable.put("Coder Radio", "http://feeds.feedburner.com/coderradiomp3");
        showToFeedTable.put("Faux Show", "http://www.jupiterbroadcasting.com/feeds/FauxShowMP3.xml");
        showToFeedTable.put("Linux Action Show", "http://feeds.feedburner.com/TheLinuxActionShow");
        showToFeedTable.put("SciByte", "http://feeds.feedburner.com/scibyteaudio");
        showToFeedTable.put("Techsnap", "http://feeds.feedburner.com/techsnapmp3");
        showToFeedTable.put("Unfilter", "http://www.jupiterbroadcasting.com/feeds/unfilterMP3.xml");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, shows);
        showsListView.setAdapter(adapter);
        showsListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int pos,long id) {				
				Toast.makeText(getApplicationContext(),
						"Loading : " + shows[pos], Toast.LENGTH_LONG)
						.show();
				String feed = showToFeedTable.get(shows[pos]);
				Intent episodeIntent = new Intent(view.getContext(), EpisodeListActivity.class);
				episodeIntent.putExtra("SHOW_NAME", shows[pos]);
				episodeIntent.putExtra("SHOW_FEED", feed);
                startActivityForResult(episodeIntent, 0);
			}
		});
    }
}