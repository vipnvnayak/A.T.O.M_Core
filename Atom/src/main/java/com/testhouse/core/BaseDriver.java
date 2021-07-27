package com.testhouse.core;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.testhouse.helper.Helper;

public class BaseDriver extends Helper{
	WebDriver driver;

	public BaseDriver(String browser) throws WebDriverException {
		switch (browser) {
		case "chrome":
			this.driver = new ChromeDriver();
			break;
		case "firefox":
			this.driver = new FirefoxDriver();
			break;
		case "ie":
			this.driver = new InternetExplorerDriver();
			break;
		default:
			throw new WebDriverException("No browser driver found.! please select valid browser or check the input provided");
		}
	}

	public void get(String url) {
		driver.get(url);
	}

	public void setImplicitWait(int timeInSeconds) {
		driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
	}

	public WebElement setFluentWait(final By element) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		return wait.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver driver) {
				return driver.findElement(element);
			}
		});
	}

	public void clickElement(By element) {
		setFluentWait(element).click();
	}

	public void sendText(By element, String data) {
		setFluentWait(element).clear();
		setFluentWait(element).sendKeys(data);
	}

	public void selectText(By element, String visibleText) {
		new Select(setFluentWait(element)).selectByVisibleText(visibleText);
	}

	public void selectIndex(By element, int index) {
		new Select(setFluentWait(element)).selectByIndex(index);
	}

	public void selectValue(By element, String value) {
		new Select(setFluentWait(element)).selectByValue(value);
	}

	public Boolean isSelected(By element) {
		return setFluentWait(element).isSelected();
	}

	public Boolean isDisplayed(By element) {
		return setFluentWait(element).isDisplayed();
	}

	public Boolean isEnabled(By element) {
		return setFluentWait(element).isEnabled();

	}
	
}
