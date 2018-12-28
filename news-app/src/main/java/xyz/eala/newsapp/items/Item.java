package xyz.eala.newsapp.items;

import java.time.LocalDateTime;

public class Item {
	private String headline = "no headline available";
	private String image = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/No_image_available_500_x_500.svg/500px-No_image_available_500_x_500.svg.png"; 
	private String dateTime;
	private String writer = "no writer available";
	private String text = "no text available";
	
	public Item(){

	}
	
	public Item(String headline, String image, String dateTime, String writer, String text) {
		super();
		this.headline = headline;
		this.image = image;
		this.dateTime = dateTime;
		this.writer = writer;
		this.text = text;
	}
	
	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	

}
