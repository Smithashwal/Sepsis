package sepsis_dashboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import enom.LocatorType;

public abstract  class GeneralCalls {

	private WebDriver driver;
	
	public abstract void workflow() throws Exception;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public GeneralCalls() {
	}
	
	@Test
	public void runTest() throws Exception {
		initializeDriver();
		startDriver();
		workflow();
		stopDriver();
		
	}

	public WebDriver initializeDriver() {
		if (null == driver) {
			System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--no-sandbox");

			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(cap);
		}
		return driver;
	}

	public void startDriver() {

		driver.manage().window().maximize();

		driver.get(
				"http://cernwpdevapp01:4001?personContributorSystemId=661f130b-a565-4dfb-b200-4d1250f3bb3a&orgName=The%20Healthe%20Exchange%20(ssa-sepsis)");

	}

	// TO READ TEST DATA FROM EXCEL
	public XSSFSheet readExcel() throws IOException {
		File srcDate = new File("Excel/General.xlsx");

		// load file
		FileInputStream fis = new FileInputStream(srcDate);

		// Load workbook
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		// Load sheet- Here we are loading first sheet only

		return wb.getSheetAt(0);
	}

	public void takeScreenshot(String folderName) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		String fileName = folderName + "/" + "Screenshot" + new Date().toString() + ".png";
		fileName = fileName.replaceAll(" ", "_");
		fileName = fileName.replaceAll(":", "_");
		File destFile = new File(fileName);

		// Call getScreenshotAs method to create image file

		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination which is passed as parameter
		// Copy file at destination

		FileUtils.copyFile(srcFile, destFile);
	}

	public void checkSelectedLocator(String locator, LocatorType locatorType) {
		WebElement element;
		if (locatorType.equals(LocatorType.XPATH)) {
			element = findByXpath(locator);
			if (element.isSelected()) {
				element.click();
			}
		} else if (locatorType.equals(LocatorType.CssSELECTOR)) {
			element = findByCssSelector(locator);
			if (element.isSelected()) {
				element.click();
			}
		} else if (locatorType.equals(LocatorType.ID)) {
			element = findById(locator);
			if (element.isSelected()) {
				element.click();
			}
		} else {
			element = findByClassName(locator);
			if (element.isSelected()) {
				element.click();
			}
		}

	}

	public void checkUnSelectedLocator(String locator, LocatorType locatorType) {
		WebElement element;
		if (locatorType.equals(LocatorType.XPATH)) {
			element=findByXpath(locator);
			if (!element.isSelected()) {
				element.click();
			}
		}else if (locatorType.equals(LocatorType.CssSELECTOR)) {
			element=findByCssSelector(locator);
			if (!element.isSelected()) {
				element.click();
			}
		}else if (locatorType.equals(LocatorType.ID)) {
			element=findById(locator);
			if (!element.isSelected()) {
				element.click();
			}
		}else {
			element=findByClassName(locator);
			if (!element.isSelected()) {
				element.click();
			}
		}

	}

	public WebElement findByXpath(String xpath) {
		WebElement xp = driver.findElement(By.xpath(xpath));
		return xp;
	}

	public WebElement findByCssSelector(String cssSelector) {
		WebElement cs = driver.findElement(By.cssSelector(cssSelector));
		return cs;
	}

	public WebElement findById(String id) {
		WebElement newId = driver.findElement(By.id(id));
		return newId;
	}
	
	public WebElement findByClassName(String classLocator) {
		WebElement cn = driver.findElement(By.className(classLocator));
		return cn;
	}
	
	public List <WebElement> findByClassNames(String classLocator) {
		List <WebElement> cn = driver.findElements(By.className(classLocator));
		return cn;
	}

	public WebElement mouseHover(String locator, LocatorType locatorType) {
		Actions action = new Actions(driver);
		
		WebElement e ;
		if(locatorType.equals(LocatorType.XPATH)) {
			e= findByXpath(locator);
		}else if(locatorType.equals(LocatorType.CssSELECTOR)) {
			e= findByCssSelector(locator);
		}else if(locatorType.equals(LocatorType.ID)) {
			e= findById(locator);
			
		}else {
			e= findByClassName(locator);
		}
		
		action.moveToElement(e).perform();
		return e;
	}
	
	public void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	

	public void stopDriver() {
		// driver.close();
	}

}
