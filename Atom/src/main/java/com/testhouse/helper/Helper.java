package com.testhouse.helper;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Helper {

	public void assertValue(String actualValue, String expectedValue) {
		Assert.assertEquals(actualValue, expectedValue);
	}
	public void getScreenShot(WebDriver driver,String fileName) {
		if(!new File("Screenshots").exists()) {
			new File("Screenshots").mkdir();
		}
		Screenshot screenshot= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);

		try {
			ImageIO.write(screenshot.getImage(), "jpg", new File("Screenshots\\"+fileName+".jpg"));
		} catch (IOException e) {
			System.out.println("File could not be saved.!" +  e.getMessage());
		}
	}
}
