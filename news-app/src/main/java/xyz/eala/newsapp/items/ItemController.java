package xyz.eala.newsapp.items;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
    
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {				
        try {
        	url = new URL(ItemControllerHelper.getAPIURL("Ireland"));
	        urlConnection = url.openConnection();
	        itemList = ItemControllerHelper.getNewsItemList(urlConnection);
		 
        } catch(IOException e){
            e.printStackTrace();
        }
        
        ldtString = ItemControllerHelper.getDate(); 
        topStory = itemList.get(0);
        itemList.remove(0);
        
		model.addAttribute("ldtString", ldtString);
        model.addAttribute("topStory", topStory);
        model.addAttribute("itemList", itemList);
	
		return "index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String getNewsByCountry(HttpServletRequest request, Model model) {
		String country = request.getParameter("country");
		 try {
	        	url = new URL(ItemControllerHelper.getAPIURL(country));
		        urlConnection = url.openConnection();
		        itemList = ItemControllerHelper.getNewsItemList(urlConnection);
			 
	        } catch(IOException e){
	            e.printStackTrace();
	        }
	        
	        ldtString = ItemControllerHelper.getDate(); 
	        topStory = itemList.get(0);
	        itemList.remove(0);
	        
			model.addAttribute("ldtString", ldtString);
	        model.addAttribute("topStory", topStory);
	        model.addAttribute("itemList", itemList);
		
			return "index";
	}
}

