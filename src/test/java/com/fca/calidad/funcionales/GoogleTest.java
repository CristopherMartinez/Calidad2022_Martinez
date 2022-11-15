package com.fca.calidad.funcionales;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;

public class GoogleTest {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  JavascriptExecutor js;
	  @Before
	  public void setUp() throws Exception {
	    //System.setProperty("webdriver.chrome.driver", "");
	    WebDriverManager.chromedriver().setup();
	    
	    driver = new ChromeDriver();
	    baseUrl = "https://www.google.com/";
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	    js = (JavascriptExecutor) driver;
	  }

	  @Test
	  public void testGoogle() throws Exception {
	    driver.get("https://www.google.com/search?q=goo&rlz=1C1VDKB_esMX1027MX1027&oq=goo&aqs=chrome..69i57j46i67i131i199i433i465j0i67i131i433j0i67l2j0i67i131i433j69i60l2.543j0j4&sourceid=chrome&ie=UTF-8");
	    driver.findElement(By.id("cnt")).click();
	    driver.get("https://www.google.com/");
	    driver.findElement(By.name("q")).sendKeys(Keys.DOWN);
	    driver.findElement(By.name("q")).clear();
	    driver.findElement(By.name("q")).sendKeys("Yucatáni6");
	    driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("//div[@id='rso']/div/div/div/div/div/div/div/div/a/div/cite")).click();
	    driver.get("https://siies.yucatan.gob.mx/yucatani6/");
	    assertEquals("Yucatáni6", driver.getTitle());
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	
	
	
	
}
