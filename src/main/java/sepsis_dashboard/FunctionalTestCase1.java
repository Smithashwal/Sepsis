package sepsis_dashboard;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import enom.LocatorType;

public class FunctionalTestCase1 extends GeneralCalls {

	public void displayedAlertBreakdown() {
		findByXpath("//*[@id=\"alert-bd-div\"]").isDisplayed();
	}

	public void screenShotSaveFolder() throws IOException {
		takeScreenshot("functionalTestCase1");
	}

	@Override
	public void workflow() throws IOException {

		/*String tp= findByXpath("//*[@id=\"tDivTP\"]").getText();
		assertEquals(true, "True Positive");*/
		
		// ALERT BREAKDOWN
		// Patient 18+
		displayedAlertBreakdown();
		screenShotSaveFolder();// When selected
		checkSelectedLocator("//*[@id=\"patients-18\"]", LocatorType.XPATH); // When not selected
		screenShotSaveFolder();
		checkUnSelectedLocator("//*[@id=\"patients-18\"]", LocatorType.XPATH);// Again select patient 18+

		// Patient <18
		displayedAlertBreakdown();
		screenShotSaveFolder(); // When selected
		checkSelectedLocator("//*[@id=\"patients-18-plus\"]", LocatorType.XPATH); // When not selected
		screenShotSaveFolder();
		checkUnSelectedLocator("//*[@id=\"patients-18-plus\"]", LocatorType.XPATH);// Again select patient 18+

		//CONDITION POSITIVE
		// SIRS
		displayedAlertBreakdown();
		screenShotSaveFolder();
		checkUnSelectedLocator("//*[@id=\"sirs\"]", LocatorType.XPATH);
		
		screenShotSaveFolder();
		checkSelectedLocator("//*[@id=\"sirs\"]", LocatorType.XPATH);
		

		// SEPTICEMIA
		displayedAlertBreakdown();
		screenShotSaveFolder();
		checkUnSelectedLocator("//*[@id=\"septicmia\"]", LocatorType.XPATH);
		screenShotSaveFolder();
		checkSelectedLocator("//*[@id=\"septicmia\"]", LocatorType.XPATH);
		

		// SEPSIS
		displayedAlertBreakdown();
		screenShotSaveFolder();
		checkSelectedLocator("//*[@id=\"sepsis\"]", LocatorType.XPATH);
		screenShotSaveFolder();
		checkUnSelectedLocator("//*[@id=\"sepsis\"]", LocatorType.XPATH);

		// SEVERE SEPSIS
		displayedAlertBreakdown();
		screenShotSaveFolder();
		checkSelectedLocator("//*[@id=\"severe-shock\"]", LocatorType.XPATH);
		screenShotSaveFolder();
		checkUnSelectedLocator("//*[@id=\"severe-shock\"]", LocatorType.XPATH);

		// SEPTIC SHOCK
		displayedAlertBreakdown();
		screenShotSaveFolder();
		checkSelectedLocator("//*[@id=\"septic-shock\"]", LocatorType.XPATH);
		screenShotSaveFolder();
		checkUnSelectedLocator("//*[@id=\"septic-shock\"]", LocatorType.XPATH);

		// ALERT POSITIVE
		// SEPSIS DELIVERED
		displayedAlertBreakdown();
		screenShotSaveFolder();
		checkSelectedLocator("//*[@id=\"sepsis-delivered\"]", LocatorType.XPATH);
		screenShotSaveFolder();
		checkUnSelectedLocator("//*[@id=\"sepsis-delivered\"]", LocatorType.XPATH);

		// SEPSIS SUPPRESSED
		displayedAlertBreakdown();
		screenShotSaveFolder();
		checkSelectedLocator("//*[@id=\"sepsis-suppressed\"]", LocatorType.XPATH);
		screenShotSaveFolder();
		checkUnSelectedLocator("//*[@id=\"sepsis-suppressed\"]", LocatorType.XPATH);

		// SIRS DELIVERED
		displayedAlertBreakdown();
		screenShotSaveFolder();
		checkSelectedLocator("//*[@id=\"sirs-delivered\"]", LocatorType.XPATH);
		screenShotSaveFolder();
		checkUnSelectedLocator("//*[@id=\"sirs-delivered\"]", LocatorType.XPATH);

		// SIRS SUPPRESSED
		displayedAlertBreakdown();
		screenShotSaveFolder();
		checkSelectedLocator("//*[@id=\"sirs-suppressed\"]", LocatorType.XPATH);
		screenShotSaveFolder();
		checkUnSelectedLocator("//*[@id=\"sirs-suppressed\"]", LocatorType.XPATH);


	}

}
