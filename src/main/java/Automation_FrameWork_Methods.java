package Test;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Automation_FrameWork_Methods {
	WebDriver driver = null;
	 
	/* Invoke Web Driver
	 * Description - This method will Invoke/return the appropriate driver based on user input 
	 * driverName - This variable denotes the name of the browser/driver to be invoked 
	 * driverPath - This variable hold the path where driver.exe file is stored
	 */
	public WebDriver invokeDriver(String driverName, String driverPath) {		
		try {
			//Chrome
			if(driverName.equalsIgnoreCase("Chrome")) {
				if(!driverPath.isEmpty()) {
					System.setProperty("webdriver.chrome.driver", driverPath);
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					//options.addArguments("--headless"); //Uncomment to run in headless mode
					driver = new ChromeDriver(options);
					System.out.println("Invoking Chrome Driver, from path: " + driverPath);
				}else {
					driver = new ChromeDriver();
					System.out.println("Invoking available system Chrome Driver using Selenium Manager...");
				}
			}
			//Firefox
			else if(driverName.equalsIgnoreCase("firefox")){
				if(!driverPath.isEmpty()) {
					System.setProperty("webdriver.gecko.driver", driverPath);
					FirefoxOptions options = new FirefoxOptions();
                    options.addPreference("dom.webnotifications.enabled", false);
                    // options.setHeadless(true);  // Uncomment to run in headless mode
                    System.out.println("Invoking Firefox Driver, from path: " + driverPath);
                    driver = new FirefoxDriver(options);
                } else {
                    System.out.println("Invoking available system Firefox Driver using Selenium Manager...");
                    driver = new FirefoxDriver();
                }
			//Edge
			} else if (driverName.equalsIgnoreCase("Edge")) {
	            if (!driverPath.isEmpty()) {
	                System.setProperty("webdriver.edge.driver", driverPath);
	                EdgeOptions options = new EdgeOptions();
                    options.addArguments("--disable-notifications");
                    // options.addArguments("--headless");  // Uncomment to run in headless mode
                    System.out.println("Invoking Edge Driver, from path: " + driverPath);
                    driver = new EdgeDriver(options);
                } else {
                    System.out.println("Invoking available system Edge Driver using Selenium Manager...");
                    driver = new EdgeDriver();
                }
	        }
	        else {
	            System.out.println("Unsupported browser: " + driverName);
	        }		
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while setting up the WebDriver: " + e.getMessage());
        }
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		return driver;
	}
	
	
	/*Launch Web Application
	 * Description: To launch/open the web application in driver
	 * url - Variable that holds the web page URL that needs to be Launched
	 */	
	public void launchWebApp(String url) {
		try {
			if (!url.isEmpty()) {
				driver.get(url);
				System.out.println("Launched Application '" + url + "' in Browser...");
			} else {
				System.out.println("Please enter a valid URL to launch the web application...");
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred launching the URL: " + e.getMessage());
        }
	}
	
	/*
	 * Description: Launch application in new tab of existing driver session
	 * url - Variable that holds the web page URL that needs to be Launched
	*/
	public void launchAppInNewTab(String url) {
		try {
			if (!url.isEmpty()) {
				((JavascriptExecutor) driver).executeScript("window.open('" + url + "');");
				System.out.println("Launched Application '" + url + "' in new tab of existing driver session...");
			} else {
				System.out.println("Please enter a valid URL to open the web application...");
			}		
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred launching the URL: " + e.getMessage());
        }
	}
	
	
	/*Wait Until Element is visible and return the element
	 * Description: To Wait until the element is located
	 * eltName - Variable that holds the locator details to find the element
	 * timeOut - Time in seconds that the driver should wait before throwing Element Not Found error
	 */	
	public WebElement waitAndGetElement(By eltName, int timeOut) {
		WebElement elt=null;
		try {
			elt = new WebDriverWait(driver, Duration.ofSeconds(timeOut))
                    .until(ExpectedConditions.visibilityOfElementLocated(eltName));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while waiting and finding the element: " + e.getMessage());
        }
		return elt;
	}
	
	/*Wait Until Element is visible (element is NOT returned)
	 * Description: To Wait until the element is located
	 * eltName - Variable that holds the locator details to find the element
	 * timeOut - Time in seconds that the driver should wait before throwing Element Not Found error
	 */	
	public void waitForElement(By eltName, int timeOut) {
		WebElement elt=null;
		try {
			elt = new WebDriverWait(driver, Duration.ofSeconds(timeOut))
                    .until(ExpectedConditions.visibilityOfElementLocated(eltName));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while waiting and finding the element: " + e.getMessage());
        }
	}
	
	/*Wait Until Element is element exists in the DOM, not necessarily visible
	 * Description: To Wait until the element is available in DOM
	 * eltName - Variable that holds the locator details to find the element
	 * timeOut - Time in seconds that the driver should wait before throwing Element Not Found error
	 */	
	public void waitUntilElementAvailableInDOM(By eltName, int timeOut) {
		WebElement elt=null;
		try {
			elt = new WebDriverWait(driver, Duration.ofSeconds(timeOut))
                    .until(ExpectedConditions.presenceOfElementLocated(eltName));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while waiting and finding the element in DOM: " + e.getMessage());
        }
	}
	
	
	/*Wait Until Element is Clickable
	 * Description: To Wait until the element is clickable
	 * eltName - Variable that holds the locator details to find the element
	 * timeOut - Time in seconds that the driver should wait before throwing Element Not Found error
	 */	
	public void waitUntilElementIsClickable(By eltName, int timeOut) {
		WebElement elt=null;
		try {
			elt = new WebDriverWait(driver, Duration.ofSeconds(timeOut))
                    .until(ExpectedConditions.elementToBeClickable(eltName));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while waiting for the element to be clickable: " + e.getMessage());
        }
	}
	
	/*getCurrentUrl
	 * Description: To get the current URL
	 */	
	public String getURL() {
		String currentURL="";
		try {
			currentURL = driver.getCurrentUrl();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while getting current URL: " + e.getMessage());
        }
		return currentURL;
	}

	/*Navigate Forward/Backward
	 * Description: To navigate forward or backward in browser
	 */	
	public void navigateForwardOrBackward(String FwdOrBwd) {
		try {
			if(FwdOrBwd.equalsIgnoreCase("Forward")||FwdOrBwd.equalsIgnoreCase("Fwd")) {
				driver.navigate().forward();
			}else if(FwdOrBwd.equalsIgnoreCase("Backward")||FwdOrBwd.equalsIgnoreCase("Bwd")) {
					driver.navigate().back();
			}else {
				System.out.println("Invalid input: Please 'Forward/Fwd' to navigate Foward Or enter 'Backward/Bwd to navigate backward");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while navigating '"+FwdOrBwd+"' in browser: " + e.getMessage());
        }
	}
	
	/*getElement
	 * Description: To Find an element
	 * eltName - Variable that holds the locator details to find the element
	 */	
	public WebElement getElement(By eltName) {
		WebElement elt=null;
		try {
			elt = driver.findElement(eltName);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred locatoing the element: " + e.getMessage());
        }
		return elt;
	}


	/*getElements
	 * Description: Returns a list of WebElements
	 * eltName - Variable that holds the locator details to find the elements
	 */
	public List<WebElement> getElements(By eltName) {
		List<WebElement> elts = null;
		try {
			elts = driver.findElements(eltName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred locating the elements: " + e.getMessage());
		}
		return elts;
	}
	
	/*getElement Size
	 * Description: Returns the number of element persent
	 * eltName - Variable that holds the locator details to find the elements
	 */
	public int getElementSize(By eltName) {
		int eltSize=0;
		List<WebElement> elts = null;
		try {
			elts = driver.findElements(eltName);
			eltSize = elts.size();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred getting the size of the element: " + e.getMessage());
		}
		return eltSize;
	}

	/*Click on the Element
	 * Description: Click on the WebElements 
	 * eltName - Variable that holds the locator details to find the elements
	 */
	public void clickOnElt(By eltName) {
		try {
			WebElement elt = waitAndGetElement(eltName, 20);
			if (elt.isDisplayed()&& elt.isEnabled()) {
				elt.click();
			} else {
				System.out.println("The element is NOT enabled/displayed and cannot be clicked...");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred clicking the element: " + e.getMessage());
		}
	}
	
	/*Enter text
	 * Description: Click on the WebElements 
	 * eltName - Variable that holds the locator details to find the elements
	 * inputTxt - text to be entered in the tex box
	 */
	public void enterText(By eltName, String inputTxt) {
		try {
			WebElement elt = waitAndGetElement(eltName, 20);
			if (elt.isDisplayed()&& elt.isEnabled()) {
				elt.click();
				elt.sendKeys(inputTxt);
			} else {
				System.out.println("The element is NOT enabled/displayed and cannot enter the text...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred enter the text: " + e.getMessage());
		}
	}
	
	/*Enter Get Inner Text
	 * Description: Return the Element Inner Text
	 * eltName - Variable that holds the locator details to find the elements
	 */
	public String getInnerText(By eltName) {
		String eltInnerText="";
		try {
			WebElement elt = waitAndGetElement(eltName, 20);
			if (elt.isDisplayed()&& elt.isEnabled()) {
				eltInnerText = elt.getText();
			} else {
				System.out.println("The element is NOT enabled/displayed and cannot get the inner text...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred getting the inner text of the element: " + e.getMessage());
		}
		return eltInnerText;
	}
	
	/*Check Box/Radio Button Selection Status
	 * Description: To check whether the check box or Radio button is enabled or NOT
	 * eltName - Variable that holds the locator details to find the elements
	 */
	public boolean elementtSelectionStatus(By eltName) {
		boolean eltSelectionStatus=false;
		try {
			WebElement elt = waitAndGetElement(eltName, 20);
			if (elt.isDisplayed()&& elt.isEnabled()) {
				eltSelectionStatus = elt.isSelected();
			} else {
				System.out.println("The element is NOT enabled/displayed so NOT able to check its Selection status...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred check box/Radio button Selection status: " + e.getMessage());
		}
		return eltSelectionStatus;
	}
	
	
	/*Check Element Visibility
	 * Description: To check whether the element is visible in the UI
	 * eltName - Variable that holds the locator details to find the element
	 */
	public boolean elementVisibilityStatus(By eltName) {
	    boolean eltVisibilityStatus = false;
	    try {
	        WebElement elt = waitAndGetElement(eltName, 20);  // Assuming this method waits and fetches the element
	        if (elt.isDisplayed()) {
	            eltVisibilityStatus = true;  // Element is visible on the UI
	            System.out.println("The element is visible on the UI.");
	        } else {
	            System.out.println("The element is NOT visible on the UI.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error occurred while checking element visibility: " + e.getMessage());
	    }
	    return eltVisibilityStatus;
	}

	
	
	/*Select option in Dropdown using value
	 * Description: Select the required option in Dropdown element using Value
	 * eltName - Variable that holds the locator details to find the elements
	 * optionText - value of the option to be selected in dropdown element
	 */
	public void selectDropDownValueByText(By eltName, String optionText) {
		try {
			WebElement drpDwnElt = waitAndGetElement(eltName, 20);			
			if (drpDwnElt.isDisplayed()&& drpDwnElt.isEnabled()) {
				Select options = new Select(drpDwnElt);
				options.selectByValue(optionText);
			} else {
				System.out.println("The element is NOT enabled/displayed and cannot select the required option...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred selecting the value in dropdown element: " + e.getMessage());
		}
	}
	
	/*Select option in Dropdown using index
	 * Description: Select the required option in Dropdown element using index
	 * eltName - Variable that holds the locator details to find the elements
	 * optionIndex - Index of the option to be selected
	 */
	public void selectDropDownValueByIndex(By eltName, int optionIndex) {
		try {
			WebElement drpDwnElt = waitAndGetElement(eltName, 20);			
			if (drpDwnElt.isDisplayed()&& drpDwnElt.isEnabled()) {
				Select options = new Select(drpDwnElt);
				options.selectByIndex(optionIndex);
			} else {
				System.out.println("The element is NOT enabled/displayed and cannot select the required option...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred selecting the value in dropdown element: " + e.getMessage());
		}
	}
	
	
	/*Select option in Auto Suggestive Text Box
	 * Description: Select the required option in Auto Suggestive Text Box
	 * eltName - Variable that holds the locator details to find the elements (parent Text box details)
	 * optionText - Index of the option to be selected
	 * optionTagName - TagName of the option to selected once listed
	 * preText - PreText to be entered in Auto Suggestive Text Box
	 */
	public void selectOptionInAutosuggestTextBox(By eltName, String preText, String optionTagName, String optionText) {
		try {
			WebElement drpDwnElt = waitAndGetElement(eltName, 20);			
			if (drpDwnElt.isDisplayed()&& drpDwnElt.isEnabled()) {
				List<WebElement> options = drpDwnElt.findElements(By.tagName(optionTagName));
				for(WebElement e:options) {
					if(e.getText().equals(optionText)) {
						e.click();
						break;
					}else {
						System.out.println("The provided Text is NOT displayed under the Auto Suggestive text box...");
					}
				}
			} else {
				System.out.println("The element is NOT enabled/displayed and cannot select the required option...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred selecting the value in dropdown element: " + e.getMessage());
		}
	}
	
	
	
	/*Get Alert Text
	 * Description: To get the text displayed in the alert
	 */	
	public String getAlertText() {
		String alertText = "";
		try {
			Alert alert = new WebDriverWait(driver, Duration.ofSeconds(40))
					.until(ExpectedConditions.alertIsPresent());
			alertText = driver.switchTo().alert().getText();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while getting Alert Text: " + e.getMessage());
        }
		return alertText;
	}
	
	/*Accept Or Dismiss Alert popup
	 * Description: To Accept or Dismiss the alert
	 */	
	public void acceptOrDismissAlert(String alertAction) {
		try {
			Alert alert = new WebDriverWait(driver, Duration.ofSeconds(40))
					.until(ExpectedConditions.alertIsPresent());
			if(alertAction.equalsIgnoreCase("Accept")) {
				driver.switchTo().alert().accept();
			}else if(alertAction.equalsIgnoreCase("Dismiss")) {
				driver.switchTo().alert().dismiss();
			}else {
				System.out.println("Invalid input: Please enter 'Accept/Dismiss' to perform Action/Dismiss action on Alert");
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while taking Accept/Dismiss action on Alert: " + e.getMessage());
        }
	}
	
	
	/*Switching between windows
	 * Description: To switch between window
	 */	
	public void switchBetweenWindows(int childWindowindex) {
		String childId ="1";
		try {
			Set<String> windows = driver.getWindowHandles();
			Iterator <String> it = windows.iterator();
			String parentId = it.next();
			for(int i=1; i<=windows.size();i++) {
				childId = it.next();
				if(i==childWindowindex) {
					driver.switchTo().window(childId);
					System.out.println("Navigated to Child window of index :'"+i+"'.");
					break;
				}
			}
		}		
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error closing the Browser: " + e.getMessage());
		}
	}
	
	
	/*Get Title of All Tabs
	 * Description: Returns the title of all the available tabs
	 */	
	public List<String> getTitleOfAllTabs() {
		List<String> titles = null;
		try {
			Set<String> windows = driver.getWindowHandles();
			Iterator <String> it = windows.iterator();
			while (it.hasNext()) {
				driver.switchTo().window(it.next());
				titles.add(driver.getTitle());
				}
		}		
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while getting the titles of all the opened tabs: " + e.getMessage());
		}
		return titles;
	}
	
	/*Switch to Frame by Frame index
	 * Description: To Switch to Frame using Frame Index
	 * eltIndex - Frame Index number
	 */
	public void switchToFrameUsingIndex(int eltIndex) {
		try {
			driver.switchTo().frame(eltIndex);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred switching to Frame: " + e.getMessage());
		}
	}
	
	/*Switch to Frame by Frame Name/Id attribute value
	 * Description: To Switch to Frame using Name/Id attribute value
	 * nameOrIdVal -  name attribute value
	 */
	public void switchToFrameUsingNameVal(String nameOrIdVal) {
		try {
			driver.switchTo().frame(nameOrIdVal);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred switching to Frame: " + e.getMessage());
		}
	}
	
	/*Switch to Frame by Frame Element
	 * Description: To Switch to Frame using Frame element detils
	 * eltName - Variable that holds the locator details to find the elements
	 */
	public void switchToFrameUsingByLocator(By eltName) {
		
		try {
			WebElement elt = waitAndGetElement(eltName, 20);
			if (elt.isDisplayed()&& elt.isEnabled()) {
				driver.switchTo().frame(elt);
			} else {
				System.out.println("The Frame element is NOT enabled/displayed so NOT able to switch...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred switching to Frame: " + e.getMessage());
		}
	}
	
	/*Drag and Drop
	 * Description: To Drag and Drop an element
	 * sourceElt - Source Element locator which needs to be dragged
	 * targetElt - Target Element locator where it needs to be dropped
	 */
	public void dragAndDrop(By sourceElt, By targetElt) {
		try {
			Actions a = new Actions(driver);
			WebElement srcElt = waitAndGetElement(sourceElt, 20);
			WebElement tgtElt = waitAndGetElement(targetElt, 20);
			if (srcElt.isDisplayed() && srcElt.isEnabled() &&tgtElt.isDisplayed() && tgtElt.isEnabled()) {
				a.dragAndDrop(srcElt, tgtElt);
			} else {
				System.out.println("The Source/Taget element is NOT enabled/displayed so NOT able to drap and drop...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred performing drag and drop: " + e.getMessage());
		}
	}
	
	/*Scroll Element Into View
	 * Description: To scroll an element into the visible portion of the web page
	 * eltName - By Element - having locator details of element to be scrolled into view
	 */  
	public void scrollToElement(By eltName) {
	    try {
	    	WebElement elt = waitAndGetElement(eltName, 20);
	        if (elt != null) {	       
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elt);
	            System.out.println("Scrolled to the element");
	        } else {
	            System.out.println("Element is null. Cannot scroll to the element...");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error occurred while scrolling to the element: " + e.getMessage());
	    }
	}

	/*Scroll Element Into View using Actions class
	 * Description: To scroll to an element using the keyboard "Page Down" or arrow keys simulation
	 * eltName - By Element - having locator details of element to be scrolled into view
	 */  
	public void scrollToElementUsingActions(By eltName) {
	    try {
	    	WebElement elt = waitAndGetElement(eltName, 20);
	        if (elt != null) {
	            Actions actions = new Actions(driver);
	            actions.moveToElement(elt).perform(); 
	            System.out.println("Scrolled to the element using Actions class: " + elt);
	        } else {
	            System.out.println("Element is null. Cannot scroll to the element...");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error occurred while scrolling to the element using Actions: " + e.getMessage());
	    }
	}
	
	/*General Scroll Down using Keys.PAGE_DOWN
	 * Description: Scrolls down the page using the PAGE_DOWN key to make the element visible
	 */  
	public void scrollDownWithPageDown() {
	    try {
	        Actions actions = new Actions(driver);
	        actions.sendKeys(Keys.PAGE_DOWN).perform();
	        System.out.println("Scrolled down using PAGE_DOWN key.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error occurred while scrolling down with PAGE_DOWN key: " + e.getMessage());
	    }
	}

	/*Scroll Element Into View using Mouse Scroll (Wheel)
	 * Description: To simulate mouse scroll wheel action to scroll to the element
	 * eltName - By Element - having locator details of element to be scrolled into view
	 */  
	public void scrollToElementWithMouseWheel(By eltName) {
	    try {
	    	WebElement elt = waitAndGetElement(eltName, 20);
	        if (elt != null) {
	            Actions actions = new Actions(driver);
	            actions.moveToElement(elt).perform();  
	            actions.sendKeys(Keys.PAGE_DOWN).perform();
	            System.out.println("Scrolled to the element using Mouse Wheel: " + elt);
	        } else {
	            System.out.println("Element is null. Cannot scroll to the element...");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error occurred while scrolling to the element with Mouse Wheel: " + e.getMessage());
	    }
	}

	/* General Zoom Out using Keys.CONTROL and Keys.SUBTRACT
	 * Description: Zooms out the page using CTRL + '-' keys to decrease the zoom level
	 */  
	public void zoomOutUsingKeys() {
	    try {
	        Actions actions = new Actions(driver);
	        actions.sendKeys(Keys.CONTROL, Keys.SUBTRACT).perform();
	        System.out.println("Zoomed out using CTRL + '-' keys.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error occurred while zooming out using CTRL + '-' keys: " + e.getMessage());
	    }
	}
	
	/* Zoom Out using Javascript Executor
	 * Description: Zooms out by setting the document's zoom level using JavaScript.
	 */
	public void zoomOutWithJavascriptExecutor() {
	    try {
	        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	        jsExecutor.executeScript("document.body.style.zoom='80%'");  
	        System.out.println("Zoomed out using JavaScript Executor.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error occurred while zooming out with JavaScript Executor: " + e.getMessage());
	    }
	}


	
	/*Close Web Application
	 * Description: To Close the current window of the web driver
	 */	
	public void closeWebApp() {
		try {
			driver.close();
			System.out.println("Driver has been Closed...");
		}		
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error closing the Browser: " + e.getMessage());
		}
	}
	
	
	/*Quit Web Application
	 * Description: To Close all the windows of web drivers that are opened
	 */	
	public void quitWebApp() {
		try {
			driver.quit();
			System.out.println("Driver has been Closed...");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error closing the Browser: " + e.getMessage());
		}
	}
	
	/*Close all Web Application
	 * Description: To Close all the windows opened one by one
	 */	
	public void closeAllWebApp() {
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			for (String handle : windowHandles) {
				driver.switchTo().window(handle);
				driver.close();
			}
			driver.quit();
			System.out.println("Driver has been Closed...");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error closing the Browser: " + e.getMessage());
		}
	}
	
	/*Take Screenshot
	 * Description: To take screenshot and save it in provided location
	 */	
	public void takeScreenshotAndSave(String fileLocation) {
		String ssFilePath = fileLocation+"//Screenshot.png";
		try {
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src,new File(ssFilePath));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while taking and saving screenshot: " + e.getMessage());
		}
	}
	
}
