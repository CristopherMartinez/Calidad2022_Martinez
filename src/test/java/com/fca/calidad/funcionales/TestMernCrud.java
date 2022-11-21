package com.fca.calidad.funcionales;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.runner.OrderWith;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runners.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;

<<<<<<< HEAD
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
=======
/*import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)*/


>>>>>>> ac888d8b55524192bd33c431e3af7cc0e5c5416f

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
<<<<<<< HEAD
  public void firstTestMernCrudAgregar() throws Exception {
    driver.get("https://mern-crud.herokuapp.com/");
=======
  public void FirsttestMernCrudAgregar() throws Exception {
    //ERROR: Caught exception [unknown command []]
    driver.get("http://mern-crud.herokuapp.com/");
    pause(30);
>>>>>>> ac888d8b55524192bd33c431e3af7cc0e5c5416f
    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Cristopher Martinez Bahena");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("martinezcristopher11@gmail.com");
    driver.findElement(By.name("age")).click();
    driver.findElement(By.name("age")).clear();
    driver.findElement(By.name("age")).sendKeys("22");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[2]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::div[2]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
  }
  
<<<<<<< HEAD
  //Test Editar
  @Test
  public void secondtestMernCrudEditar() throws Exception {
	  driver.get("https://mern-crud.herokuapp.com/");
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button")).click();
	    driver.findElement(By.name("name")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("Cristopher Martinez Perez");
	    driver.findElement(By.name("email")).click();
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("martinezcristopher22@gmail.com");
	    driver.findElement(By.name("age")).click();
	    driver.findElement(By.name("age")).clear();
	    driver.findElement(By.name("age")).sendKeys("24");
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::div[2]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
	    driver.findElement(By.xpath("//i")).click();
  }
  public void fourTestMernCrudBuscar() throws Exception {

	  
  }
=======
  /*@Test
  public void SecondtestMernCrudEditar() throws Exception {

	  System.out.println("Second test");
	  
	  
  }*/
  
  @Test
  //public void ThirdtestMernCrudEliminar() throws Exception {

	  
	//  System.out.println("third Test");
	  
  //}
  
 
  
>>>>>>> ac888d8b55524192bd33c431e3af7cc0e5c5416f
  
  
  @Test
  public void thirdTestMernCrudEliminar() throws Exception {

	  driver.get("https://mern-crud.herokuapp.com/");
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button[2]")).click();
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