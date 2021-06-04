package com.checkinglinks.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckingLinksPage {
private WebDriver driver;
	
	public CheckingLinksPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean checkingPageLinks() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		String url = "";
		List<String> brokenLinks = new ArrayList<String>();
		List<String> oklinks = new ArrayList<String>();
		
		HttpURLConnection httpConection = null;
		int responseCode = 200;
		Iterator<WebElement> it = links.iterator();
		
		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			if (url==null || url.isEmpty()) {
				System.out.println(url + "url is not configured or it is empty");
				continue;
			}
			try {
				httpConection = (HttpURLConnection)(new URL(url).openConnection());
				httpConection.setRequestMethod("HEAD");
				httpConection.connect();
				responseCode = httpConection.getResponseCode();
				
				if (responseCode>400) {
					System.out.println("ERROR BROKEN LINK: --" + url);
					brokenLinks.add(url);
				}else {
					System.out.println("VALID LINK: -- " + url);
					oklinks.add(url);
				}
			}catch(Exception e) {
				//TODO: handle exception
				e.printStackTrace();
			}
			
		} 
		
		System.out.println("Valid Links: " + oklinks.size());
		System.out.println("Invalid Links: " + brokenLinks.size());
		
		if (brokenLinks.size()>0) {
			System.out.println("***** ERROR ------------ Broken Links");
			for(int i = 0; i < brokenLinks.size(); i++) {
				System.out.println(brokenLinks.get(i));
			}
			return false;
			
		} else {
			return false;
		}
		
		
	}

}
