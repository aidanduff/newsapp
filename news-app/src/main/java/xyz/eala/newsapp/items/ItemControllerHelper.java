package xyz.eala.newsapp.items;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

class ItemControllerHelper {
	
	static String getDate() {
		return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(LocalDateTime.now()) + " " + LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).toLocalTime();
	}
	
	static String getDay() {
		String day1 = LocalDate.now().getDayOfWeek().toString().toLowerCase();
        char c = day1.toUpperCase().charAt(0);
        String day2 = day1.substring(1);
        return c + day2;
	}
	
	static String getAPIURL(String country) {
		switch(country) {
			case "Ireland" : country = "https://newsapi.org/v2/top-headlines?apiKey=e55fb6eb42a945238aa4946a145d17d0&country=ie"; break;						
			case "UK"      : country = "https://newsapi.org/v2/top-headlines?apiKey=e55fb6eb42a945238aa4946a145d17d0&country=gb"; break;
			case "US"      : country =  "https://newsapi.org/v2/top-headlines?apiKey=e55fb6eb42a945238aa4946a145d17d0&country=us"; break;
			default        : country =  "https://newsapi.org/v2/top-headlines?apiKey=e55fb6eb42a945238aa4946a145d17d0&country=ie";
		}
		return country;
	}
	
	static List<Item> getNewsItemList(URLConnection urlConnection) throws IOException{
		List<Item> itemList = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))){         
	        StringBuilder temp = new StringBuilder();
	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	            temp.append(inputLine);
	
	        JSONObject obj = new JSONObject(temp.toString());
	        JSONArray articles = obj.getJSONArray("articles");
	        
	        for (int i = 0; i < articles.length(); i++) {            	 
	            String headline = articles.getJSONObject(i).optString("title", "");
	            String description = articles.getJSONObject(i).optString("description", "");
	            String image = articles.getJSONObject(i).optString("urlToImage", "https://vignette.wikia.nocookie.net/bokunoheroacademia/images/d/d5/NoPicAvailable.png/revision/latest?cb=20160326222204");
	            //LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.parse(articles.getJSONObject(i).getString("publishedAt")), ZoneOffset.UTC);
	            LocalDateTime ldt = LocalDateTime.ofInstant(Instant.parse(articles.getJSONObject(i).getString("publishedAt")), ZoneOffset.UTC);
	            //String dateTime = localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
	            String dateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(ldt) + " " + ldt.truncatedTo(ChronoUnit.MINUTES).toLocalTime();
	            String writer = articles.getJSONObject(i).getJSONObject("source").optString("name", "");
	            String text = articles.getJSONObject(i).optString("content", "");
	            itemList.add(new Item(headline, description, image, dateTime, writer, text));
	        }
        }
        return itemList;

	}
}
