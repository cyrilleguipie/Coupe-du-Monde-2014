package com.gdg.miagegi.mondial2014.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class ImageLoader {
	private static String tempDocsFolderName;
	
	 private Map<String, Bitmap> drawableMap = new HashMap<String, Bitmap>();;
	  private static ImageLoader instance;
	  private Context activity;
	  int clearPoint = 15;

	  private ImageLoader(Context activity) {
	    this.activity = activity;
	  }

	  public Bitmap fetchDrawable(String urlString) {
	   
	    if (drawableMap.containsKey(urlString)) {
	      return drawableMap.get(urlString);
	    }

	    try {
	      URL url = new URL(urlString);
	      InputStream in = url.openConnection().getInputStream();
	      BitmapFactory.Options o = new BitmapFactory.Options();
	      o.inPurgeable = true;
	      o.inInputShareable = true;
	      Bitmap b = BitmapFactory.decodeStream(in, null, o);
	     
	      if (b != null) {
	        drawableMap.put(urlString, b);

	      }
	      return b;
	    } catch (Throwable e) {
	      if (e instanceof OutOfMemoryError) {
	        clear();
	      }
	      Log.e(this.getClass().getSimpleName(), "fetchDrawable failed", e);
	      return null;
	    }
	  }

	 

	  public synchronized static ImageLoader getDrawableManager(Context activity) {
	    if (instance == null) {
	      instance = new ImageLoader(activity);
	    } else {
	    }
	    return instance;
	  }

	  public void clear() {
	    drawableMap = new HashMap<String, Bitmap>();
	    System.gc();
	  }
	
	public static String saveBitMapToGallary(Bitmap newImg, boolean permanent, Context activity) {

	    String root = getCacheDir(activity).toString();
	    String fname = "Image-" + System.currentTimeMillis() + ".jpg";
	    try {

	      File myDir = new File(root + "/test_saved");
	      if (permanent) {
	        root = getExternalStorageDirectory();
	        myDir = new File(root + "/test");
	      }
	      myDir.mkdirs();

	      File file = new File(myDir, fname);
	      if (file.exists())
	        file.delete();
	      try {
	        FileOutputStream out = new FileOutputStream(file);
	        newImg.compress(Bitmap.CompressFormat.JPEG, 100, out);
	        out.flush();
	        out.close();

	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return root + "/test_saved/" + fname;
	  }
	
	 public static String getExternalStorageDirectory() {
		    return Environment.getExternalStorageDirectory().toString();
		  }
	 
	 public static File getCacheDir(Context context) {
		    File cacheDir;
		    if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
		      cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "Android/data/" + "test" + "/" + getTempDocsFolderName() + "/");
		      
		    } else {
		      cacheDir = context.getCacheDir();
		    }
		    if (!cacheDir.exists())
		      cacheDir.mkdirs();
		    return cacheDir;
		  }
	 
	 public static String getTempDocsFolderName() {
		    return tempDocsFolderName;
		  }

		
}
