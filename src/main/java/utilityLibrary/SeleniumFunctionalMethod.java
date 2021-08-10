package utilityLibrary;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumFunctionalMethod {

	WebDriver driver = null;
	final boolean Demo = false;

	public SeleniumFunctionalMethod(WebDriver passedDriver) {
		this.driver = passedDriver;

	}

	public void openUrl(String url) {
		driver.get(url);
	}

	public void clickElement(By locator) {
		driver.findElement(locator).click();
	}

	public void fillTextBox(By locator, String text) {
		driver.findElement(locator).sendKeys(text);
	}

	public String getElementText(By locator) {
		return findElm(locator).getText();
	}

	public String getElementTextByIndex(By locator, int index) {
		return findElems(locator).get(index).getText();
	}

	public String getElementColor(By locator) {
		String color = findElm(locator).getCssValue("background-color");
		return color;
	}

	public String getElementAttributeByIndex(By locator, int index, String attributeType) {
		return findElems(locator).get(index).getAttribute(attributeType);
	}

	/* Select & Action class */

	public void selectDynamicDropDown(WebDriver driver, By locator, String optionValue) {
		List<WebElement> options = findElems(locator);
		options.forEach(option -> {
			if (option.getAttribute("value").equalsIgnoreCase(optionValue)) {
				option.click();
			}
		});
	}

	public void selectDropdownByValue(By locator, String value) {
		Select dropdown = new Select(findElm(locator));
		dropdown.selectByValue(value);
	}
	/* Verification method */

	public boolean elemExist(By locator) {

		boolean result = findElems(locator).size() > 0;
		if (result) {
			highlightElement(locator);
		}
		return result;
	}

	public boolean elemVisible(By locator) {
		boolean result = findElems(locator).size() > 0;
		if (result) {
			return driver.findElement(locator).isDisplayed();
		}
		return false;
	}

	public boolean isTextFieldEmpty(By locator) {
		boolean isEmpty = findElm(locator).getText().isEmpty();

		return isEmpty;
	}

	/* Time method */
	public void hardCodeWaitTime(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void waitByMillisSecond(int millis) {

		try {
			Thread.sleep(millis * 100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Private method */
	private List<WebElement> findElems(By locator) {
		return driver.findElements(locator);
	}

	private WebElement findElm(By locator) {
		WebElement result = null;
		try {
			highlightElement(locator);
			result = driver.findElement(locator);
		} catch (NoSuchElementException e) {
			System.out.println("Unable to find Element by " + locator.toString());
		}
		return result;
	}

	/***
	 * This method highlights web-elements using javascript executor
	 * 
	 * @param by
	 */
	public void highlightElement(By by) {
		if (Demo) {
			if (elemVisible(by)) {
				try {
					WebElement temp = driver.findElement(by);
					WrapsDriver wrappedElement = (WrapsDriver) temp;
					JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
					for (int i = 0; i < 3; i++) {
						waitByMillisSecond(5);
						js.executeScript("arguments[0].setAttribute('style', arguments[1]);", temp,
								"color: red; border: 2px solid yellow;");
						waitByMillisSecond(5);
						js.executeScript("arguments[0].setAttribute('style', arguments[1]);", temp, "");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void JSclickElement(By locator) {
		try {
			waitByMillisSecond(5);
			WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (NoSuchElementException e) {
			System.out.println("*********Element is not exist in the DOM");
			e.printStackTrace();
		}
	}

	public boolean compareTime(String time) {
		boolean result = false;
		try {
			SimpleDateFormat parser = new SimpleDateFormat("MMM dd, HH:mm");
			Date currentTime = new Date(System.currentTimeMillis());
			Date before30 = new Date(System.currentTimeMillis() - 30 * 60 * 1000); // 30
																					// mintues

			Date UITime = parser.parse(time);

			if (UITime.before(currentTime) && UITime.after(before30))
				result = true;

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void takeScreenShot(String filePrefix) {
		if (filePrefix == null)
			filePrefix = "";
		try {
			File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(screenShot, new File("target/screenShots/" + filePrefix + getTimeStamp() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTimeStamp() {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
		Date date = new Date();
		String timeStamp = simpleFormat.format(date);
		return timeStamp;
	}

}
