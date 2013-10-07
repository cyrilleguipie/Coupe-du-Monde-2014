package com.gdg.miagegi.mondial2014.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.gdg.miagegi.mondial2014.models.Data;

public class FeedParser extends AsyncTask<String[], Long, ArrayList<Data>> {
private String urlString;
private Context ct;
ProgressDialog dialog;

public FeedParser(Context context, String stringURL) {
    urlString = stringURL;
    ct = context;
    dialog = ProgressDialog.show(ct, "Loading in Progress", "Please wait...");
}

ArrayList<Data> ParseXML() throws ParserConfigurationException,
        SAXException, IOException {
    ArrayList<Data> collection = new ArrayList<Data>();
    Data data = new Data();
    URL url = new URL(urlString);
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(new InputSource(url.openStream()));
    doc.getDocumentElement().normalize();
    int j;
    NodeList entries = doc.getElementsByTagName("entry");
    for (int i = 0; i < entries.getLength(); i++) {
        j = 1;
        Element entry = (Element) entries.item(i);
        NodeList children = entry.getChildNodes();
        for (int k = 0; k < children.getLength(); k++) {
            Node child = children.item(k);
            if ((child.getNodeName().equalsIgnoreCase("id"))
                    || (child.getNodeName().equalsIgnoreCase("title"))
                    || ((child.getNodeName().equalsIgnoreCase("im:image")) && (((Element) child)
                            .getAttribute("height"))
                            .equalsIgnoreCase("170"))
                    ) {
                Log.d(child.getNodeName(), child.getTextContent());

                switch (j) {
                case 1:
                    data.setLink(child.getTextContent());
                    break;
                case 2:
                    data.setTitle(child.getTextContent());
                    break;
                case 3:
                    data.setImage(child.getTextContent());
                    break;
                
                }
                j++;
                if (j == 4) {
                    j = 1;
                    collection.add(data);
                    data = new Data();

                }

            }

        }

    }
    return collection;
}

@Override
protected ArrayList<Data> doInBackground(String[]... arg0) {
	 
    ArrayList<Data> Collection = null;
    try {

        Collection = ParseXML();
    } catch (ParserConfigurationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (SAXException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    return Collection;
}

@Override
protected void onPostExecute(ArrayList<Data> result) {
    // TODO Auto-generated method stub
    super.onPostExecute(result);
    for (Data data : result) {
        Log.i("Each", data.getTitle());
      
    	 	    
           	
 	  
    }
    dialog.dismiss();
}
}

