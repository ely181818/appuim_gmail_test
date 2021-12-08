package androidTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import com.mobile.MobileTest.MobileDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Scrolling {
	
	public void scrollDown() {
		Dimension dimension = MobileDriver.getDriver().manage().window().getSize();
		
		Double scrollHeightStart = dimension.getHeight() * 0.5;
		int scrollStart = scrollHeightStart.intValue();
		
		Double scrollHeightEnd = dimension.getHeight() * 0.2;
		int scrollEnd = scrollHeightEnd.intValue();
		
		new TouchAction((PerformsTouchActions) MobileDriver.getDriver())
		.press(PointOption.point(0, scrollStart))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
		.moveTo(PointOption.point(0, scrollEnd))
		.release().perform();
	}
	
	
	public void open_gmail() throws InterruptedException {
		MobileDriver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 Thread.sleep(5000);
		 MobileDriver.getDriver().findElement(By.id("com.google.android.gm:id/welcome_tour_got_it")).click();
		 Thread.sleep(3000);
		 MobileDriver.getDriver().findElement(By.id("com.google.android.gm:id/action_done")).click();

		Thread.sleep(3000);
		MobileDriver.getDriver().findElement(By.id("com.google.android.gm:id/dismiss_button")).click();
		
	}
	public WebElement getItemViews() {
		
		return MobileDriver.getDriver().findElement(By.id("com.google.android.gm:id/viewified_conversation_item_view"));
		
	}
	
	public List<WebElement> getItemWebView(){
		return MobileDriver.getDriver().findElements(By.className("android.view.ViewGroup"));
		
	}
	
	//Go through a list of emails until the end of the scrolling capability and make an archive transfer
	public void scrollTillWebView() throws InterruptedException {

	
		
		MobileDriver.getDriver().findElements(By.id("om.google.android.gm:id/senders"));
		
		List<String> myList_1 = new ArrayList<String>();
		List<String> myList_2 = new ArrayList<String>();
		
		List<WebElement> list_WebaElement_1 = new ArrayList<WebElement>();
		List<WebElement> list_WebaElement_2 = new ArrayList<WebElement>();
		
		
		
		while(getItemWebView().size() > 0) {
			
		List<WebElement> elments =getItemWebView();
		if(myList_1.size()>0) { myList_1.clear(); }
		if(myList_2.size()>0) { myList_2.clear(); }
		if(list_WebaElement_1.size()>0) { list_WebaElement_1.clear(); }
		if(list_WebaElement_2.size()>0) { list_WebaElement_2.clear(); }
		
		//*************************************//
		//Create a list of first scan emails
		
		System.out.println("list --1--> " + elments.size());
		
			for(int i=0;i<elments.size()-1;i++) {	
				if((elments.get(i).getAttribute("text").toString()!="")&& (elments.get(i).getAttribute("text").toString().length()>2))
				{
				myList_1.add(elments.get(i).getAttribute("text").toString());
				list_WebaElement_1.add(elments.get(i));
			
				}	
			}
			
			
			
			for (int i = 0; i < myList_1.size(); i++) {
				System.out.println("myList_1");
				System.out.println(i + " -->" + myList_1.get(i).toString());
			   
			}
			
			Thread.sleep(1000);	
			scrollDown();
			
	    //*************************************//	
		//Create a second email scan list
			elments =getItemWebView();
			System.out.println("list --2--->  " + elments.size());
			for(int i=0;i<elments.size()-1;i++) {
				
				if((elments.get(i).getAttribute("text").toString()!="")&& (elments.get(i).getAttribute("text").toString().length()>2))
				{
				myList_2.add(elments.get(i).getAttribute("text").toString());
				list_WebaElement_2.add(elments.get(i));
				
					System.out.println(i + " -->" + elments.get(i).getAttribute("text").toString());
				}
			
				
			}
			for (int i = 0; i < myList_2.size(); i++) {
				System.out.println("myList_2");
				System.out.println(i + " -->" + myList_2.get(i).toString());
			   
			}
			
			
			
			
			//*************************************//	
			//Checking the first email list scan versus the second email list
			
			if((list_WebaElement_1.size() == list_WebaElement_2.size()) && (list_WebaElement_1.size()>0) ) {		
			int size= list_WebaElement_1.size();
			boolean isEqual	 =equalLists(myList_1,myList_2); 
	        System.out.println(  "list_string_elment_data is = " + isEqual);
	        
	        //If the mailing lists are the same, ie we have reached the end of the scroll, the archive is transferred
	        if(isEqual) {
	        	WebElement Panel = list_WebaElement_1.get(size-1);
	    		int x=0;
	    		int y=0;
	    		x=Panel.getLocation().getX();
	    		y=Panel.getLocation().getY();
	    		SwipeScreen(x+20,y,x+200,y);
	        	return;
	        	};
	        
		}
		
		     
		     
		
			Thread.sleep(1000);	
			scrollDown();
			
		}
		
	  
		
	}
	
	public  boolean equalLists(List<String> a, List<String> b){     
	    // Check for sizes and nulls

	    if (a == null && b == null) return true;


	    if ((a == null && b!= null) || (a != null && b== null) || (a.size() != b.size()))
	    {
	        return false;
	    }

	    // Sort and compare the two lists          
	    Collections.sort(a);
	    Collections.sort(b);      
	    return a.equals(b);
	}
	

	public static void SwipeScreen(int start_x,int start_y,int end_x,int end_y) throws InterruptedException {
		try {
			
		
		Dimension dimension=MobileDriver.getDriver().manage().window().getSize();
		
		new TouchAction((PerformsTouchActions) MobileDriver.getDriver())
		.press(PointOption.point(start_x, start_y))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(end_x, end_y))
		.release().perform();
		Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
