package Test;

public class TestRunner {

	public static void main(String[] args) {
		
		Automation_FrameWork_Methods FrameWorkObj = new Automation_FrameWork_Methods();
		FrameWorkObj.invokeDriver("chrome", "");
		String myurl = "https://psnaveenrk.github.io/Resume";
		FrameWorkObj.launchWebApp(myurl);
		String myurl2 = "https://google.com";
		FrameWorkObj.launchAppInNewTab(myurl2);
		FrameWorkObj.quitWebApp();
	}

}
