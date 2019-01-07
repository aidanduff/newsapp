package xyz.eala.newsapp.items;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ItemController {
	private List<Item> itemList = new ArrayList<>();
	private URL url;
    private URLConnection urlConnection;
    private Item topStory;
    private String ldtString;
    
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {				
        try {
        	url = new URL("https://newsapi.org/v2/top-headlines?apiKey=e55fb6eb42a945238aa4946a145d17d0&country=us");
	        urlConnection = url.openConnection();
		 
	        try(BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))){         
	            StringBuilder temp = new StringBuilder();
	            String inputLine;
	            while ((inputLine = in.readLine()) != null)
	                temp.append(inputLine);
	
	            JSONObject obj = new JSONObject(temp.toString());
	            JSONArray articles = obj.getJSONArray("articles");
	            
	            itemList.clear();
	            
	            for (int i = 0; i < articles.length(); i++) {            	 
	 	            String headline = articles.getJSONObject(i).optString("title", "");
	 	            String description = articles.getJSONObject(i).optString("description", "");
	 	            String image = articles.getJSONObject(i).optString("urlToImage", "https://vignette.wikia.nocookie.net/bokunoheroacademia/images/d/d5/NoPicAvailable.png/revision/latest?cb=20160326222204");
	 	            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.parse(articles.getJSONObject(i).getString("publishedAt")), ZoneOffset.UTC);
	 	            String dateTime = localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
	 	            String writer = articles.getJSONObject(i).getJSONObject("source").optString("name", "");
	 	            String text = articles.getJSONObject(i).optString("content", "");
	 	            itemList.add(new Item(headline, description, image, dateTime, writer, text));
	            }
	           ldtString = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(LocalDate.now()); 
	           topStory = itemList.get(0);
	           itemList.remove(0);
	        }
        } catch(IOException e){
            e.printStackTrace();
        }
		model.addAttribute("ldtString", ldtString);
        model.addAttribute("topStory", topStory);
        model.addAttribute("itemList", itemList);
	
		return "index";
	}

}
