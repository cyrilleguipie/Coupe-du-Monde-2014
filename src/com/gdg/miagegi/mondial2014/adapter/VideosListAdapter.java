package com.gdg.miagegi.mondial2014.adapter;

import java.util.List;
import java.util.concurrent.ExecutionException;


import com.gdg.miagegi.mondial2014.R;
import com.gdg.miagegi.mondial2014.models.Videos;
import com.gdg.miagegi.mondial2014.utils.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VideosListAdapter extends BaseAdapter{
	private Context mContext;
    private List<Videos> mVideos;
    private LayoutInflater mInflater;
    
    public VideosListAdapter(Context context, List<Videos> videos){
    	this.mContext = context;
    	this.mVideos = videos;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mVideos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mVideos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		Bitmap image = null;
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = mInflater.inflate(R.layout.videos_list_item, parent,
                false);
        TextView videosTitle = (TextView)itemView.findViewById(R.id.videos_title);
        TextView videosDescription = (TextView)itemView.findViewById(R.id.videos_description);
        ImageView videosIv = (ImageView)itemView.findViewById(R.id.videos_iv);
        videosTitle.setText(mVideos.get(position).getTitle());
        videosDescription.setText(mVideos.get(position).getDescription());
        final ImageLoader drawableManager = ImageLoader.getDrawableManager(mContext);
        
		
		AsyncTask<Void, Bitmap, Bitmap> bitmapTask = new AsyncTask<Void, Bitmap, Bitmap>(){

			@Override
			protected Bitmap doInBackground(Void... params) {
				// TODO Auto-generated method stub
				Bitmap bmp = drawableManager.fetchDrawable(mVideos.get(position).getPicture());
				return bmp;
			}
			@Override
			protected void onPostExecute(Bitmap result){
				Log.i("BitmapTask RESULT", ""+result.getHeight());
			}
			 
		  };
	  
	try {
		image = bitmapTask.execute().get();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	videosIv.setImageBitmap(image);        
      
 
        return itemView;
	}

}
