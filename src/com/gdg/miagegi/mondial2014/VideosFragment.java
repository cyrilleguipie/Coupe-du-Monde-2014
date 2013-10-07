package com.gdg.miagegi.mondial2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;
import com.gdg.miagegi.mondial2014.adapter.VideosListAdapter;
import com.gdg.miagegi.mondial2014.models.Videos;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.OnGestureListener;
import android.widget.ListView;

public class VideosFragment extends SherlockFragment implements OnGestureListener{
	private ProgressDialog mDialog;
	private ListView mVideosLv;
	private VideosListAdapter mVideosAdapter;
	private static final String URL = "http://gdata.youtube.com/feeds/api/videos?q=FIFA+World+Cup+Bresil+2014&v=2&alt=jsonc";
	//private static final String URL = "http://gdata.youtube.com/feeds/api/playlists/PLC6A454DD385AAB9F?v=2&alt=jsonc";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_videos, container, false);
		mDialog = ProgressDialog.show(getActivity(), "Chargement en cours", "Veuillez patienter SVP...");
		
		mVideosLv = (ListView)rootView.findViewById(R.id.videos_lv);
		getVideos();
		return rootView;
	}
	
	public String postRequest(String url)

	  {

	    HttpClient httpclient = new DefaultHttpClient() ;
	    String result = "";
	    HttpGet httpget = new HttpGet(url);
	    HttpResponse response = null;

	    try {
	      
	      response = httpclient.execute(httpget);
	      
	      Log.i("response", response.getStatusLine().toString());
	      HttpEntity entity = response.getEntity();

	      if (entity != null) {
	        InputStream instream = entity.getContent();
	        result = convertStreamToString(instream);
	        
	          Log.i("", " result : " + result);
	        instream.close();
	      }

	    } catch (Exception e) {
	      
	        Log.i("Exception : ", e.getMessage());
	    }
	    if (isNull(result)) {
	      result = response.getStatusLine().toString();
	    }
	    return result;
	  }
	
	 public static  String convertStreamToString(InputStream is) {
		    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		    StringBuilder sb = new StringBuilder();

		    String line = null;
		    try {
		      while ((line = reader.readLine()) != null) {
		        sb.append(line + "\n");
		      }
		    } catch (IOException e) {
		      e.printStackTrace();
		    } finally {
		      try {
		        is.close();
		      } catch (IOException e) {
		        e.printStackTrace();
		      }
		    }
		    return sb.toString();
		  }
	 public void getVideos() {
		    new AsyncTask<String, String, String>() {
		      @Override
		      protected String doInBackground(String... params) {
		    	  
		          return postRequest(URL);
		      }

		      @Override
		      protected void onPostExecute(String res) {
		    	  Log.i("PING", res);
		    	  JSONObject json;
		    	  JSONObject dataObject;
		    	  JSONArray items;
		    	  JSONObject videoObject;
		    	  List<Videos> videos = new ArrayList<Videos>();
				try {
					json = new JSONObject(res);
					dataObject = json.getJSONObject("data");
					items = dataObject.getJSONArray("items");
					
					for (int i = 0; i < items.length(); i++) {
					    videoObject = items.getJSONObject(i); 
					    Videos video = new Videos(videoObject.getString("title"), videoObject.getString("description"),
					   videoObject.getJSONObject("player").getString("default"), videoObject.getJSONObject("thumbnail").getString("sqDefault"));
					    videos.add(video);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	  
		    	mVideosAdapter = new VideosListAdapter(getActivity(), videos);  	    	 
		        mVideosLv.setAdapter(mVideosAdapter);
		        mDialog.dismiss();
		      }

		    }.execute();

		  }

	 public static boolean isNull(String s) {
		    if ((s == null) || s.equalsIgnoreCase("null") || s.trim().length() == 0)
		      return true;
		    else
		      return false;
		  }

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
