package com.fca.calidad.funcionales;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class TestMernCrud {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
	WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;
  }

  //TestCrear
  	@Test
    public void aTestMernCrudAgregar() throws Exception {
    //ERROR: Caught exception [unknown command []]
    driver.get("http://mern-crud.herokuapp.com/");
    pause(1500);
    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Cristopher Martinez Bahena");
    driver.findElement(By.name("email")).click();
    pause(1500);
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("martinezcristopher11@gmail.com");
    driver.findElement(By.name("age")).click();
    pause(1500);
    driver.findElement(By.name("age")).clear();
    driver.findElement(By.name("age")).sendKeys("22");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[2]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::div[2]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
  }
  
  //Test Editar
  @Test
  public void btestMernCrudEditar() throws Exception {
	  driver.get("https://mern-crud.herokuapp.com/");
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button")).click();
	    pause(1500);
	    driver.findElement(By.name("name")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("Cristopher Martinez Perez");
	    driver.findElement(By.name("email")).click();
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("martinezcristopher22@gmail.com");
	    driver.findElement(By.name("age")).click();
	    pause(1500);
	    driver.findElement(By.name("age")).clear();
	    driver.findElement(By.name("age")).sendKeys("24");
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::div[2]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
	    driver.findElement(By.xpath("//i")).click();
  }
  @Test
  public void dTestMernCrudBuscar() throws Exception {
	  /*
	  driver.get("https://mern-crud.herokuapp.com/");
	  pause(30);
	  assertTrue(driver.findElement(By.xpath("//*[text()='martinezcristopher22@gmail.com']")).isDisplayed());
	  */
  }

  

  @Test
  public void cTestMernCrudEliminar() throws Exception {

	  driver.get("https://mern-crud.herokuapp.com/");
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button[2]")).click();
	    pause(1500);
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cristopher Martinez Perez'])[2]/following::button[1]")).click();
	    
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
  
  
  private void pause(long mils) {
	  try {
		  Thread.sleep(mils);
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
  }
  
}