package com.gdg.miagegi.mondial2014.models;

public class Data {
	
		private String  mLink;
        private String  mTitle;  
        private String  mImage;
      
       
       public Data(){
    	   
       }
        public Data(String link, String title, String image){
        	this.mLink = link;
        	this.mTitle = title;
        	this.mImage = image;
        }


	public String getLink() {
		return mLink;
	}


	public void setLink(String mLink) {
		this.mLink = mLink;
	}


	public String getTitle() {
		return mTitle;
	}


	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}


	public String getImage() {
		return mImage;
	}


	public void setImage(String mImage) {
		this.mImage = mImage;
	}

       
        

}
