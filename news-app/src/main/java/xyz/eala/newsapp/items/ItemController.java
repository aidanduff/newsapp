package xyz.eala.newsapp.items;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
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
    private String day;
    
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
        day = ItemControllerHelper.getDay();
        
        topStory = itemList.get(0);
        itemList.remove(0);
        
        model.addAttribute("day", day);
		model.addAttribute("ldtString", ldtString);
        model.addAttribute("topStory", topStory);
        model.addAttribute("itemList", itemList);
	
		return "index";
	}
	
	@RequestMapping(value = "/ireland", method = RequestMethod.GET)
	public String getIreland(HttpServletRequest request, Model model) {				
        try {
        	url = new URL(ItemControllerHelper.getAPIURL("Ireland"));
	        urlConnection = url.openConnection();
	        itemList = ItemControllerHelper.getNewsItemList(urlConnection);
        } catch(IOException e){
            e.printStackTrace();
        }
        
        ldtString = ItemControllerHelper.getDate();
        day = ItemControllerHelper.getDay();
        topStory = itemList.get(0);
        itemList.remove(0);
        
		model.addAttribute("day", day);
		model.addAttribute("ldtString", ldtString);
        model.addAttribute("topStory", topStory);
        model.addAttribute("itemList", itemList);
	
		return "index";
	}
	
	@RequestMapping(value = "/uk", method = RequestMethod.GET)
	public String getUK(HttpServletRequest request, Model model) {				
        try {
        	url = new URL(ItemControllerHelper.getAPIURL("UK"));
	        urlConnection = url.openConnection();
	        itemList = ItemControllerHelper.getNewsItemList(urlConnection);
        } catch(IOException e){
            e.printStackTrace();
        }
        
        ldtString = ItemControllerHelper.getDate();
        day = ItemControllerHelper.getDay();
        topStory = itemList.get(0);
        itemList.remove(0);
        
		model.addAttribute("day", day);
		model.addAttribute("ldtString", ldtString);
        model.addAttribute("topStory", topStory);
        model.addAttribute("itemList", itemList);
	
		return "index";
	}
	
	@RequestMapping(value = "/us", method = RequestMethod.GET)
	public String getUS(HttpServletRequest request, Model model) {				
        try {
        	url = new URL(ItemControllerHelper.getAPIURL("US"));
	        urlConnection = url.openConnection();
	        itemList = ItemControllerHelper.getNewsItemList(urlConnection);
        } catch(IOException e){
            e.printStackTrace();
        }
        
        ldtString = ItemControllerHelper.getDate();
        day = ItemControllerHelper.getDay();
        
        topStory = itemList.get(0);
        itemList.remove(0);
        
		model.addAttribute("day", day);
		model.addAttribute("ldtString", ldtString);
        model.addAttribute("topStory", topStory);
        model.addAttribute("itemList", itemList);
	
		return "index";
	}
}

