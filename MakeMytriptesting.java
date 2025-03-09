package makemytrip;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMytriptesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	       // Setup ChromeDriver
			 System.setProperty("webdriver.chrome.driver","./Driver/chromedriver.exe"); 
			 WebDriver driver = new ChromeDriver();
		        WebDriverWait wait = new WebDriverWait(driver, 30);
		        driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		        String phoneNumber = "8925771744"; // Replace with your phone number
		        String password = "Mahesh27112@"; // Replace with your password
		        String fromCity = "Delhi"; // Replace with your departure city
		        String toCity = "Mumbai"; // Replace with your destination city
		        LocalDate departureDate = LocalDate.of(2025, 3, 15); // Replace with your departure date

		        try {
		            driver.get("https://www.makemytrip.com/");
		            closePopups(driver, wait);
		            login(driver, wait, phoneNumber, password);
		            bookFlight(driver, wait, fromCity, toCity, departureDate);
		            fillPassengerDetails(driver, wait);
		            proceedToPayment(driver, wait);
		            System.out.println("Booking process started. Please complete payment.");

		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            // driver.quit(); //Uncomment when finished.
		        }
		    }

		    private static void closePopups(WebDriver driver, WebDriverWait wait) {
		        try {
		            WebElement langPopupClose = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='langCardClose']")));
		            langPopupClose.click();
		        } catch (Exception e) {
		            System.out.println("Language popup not found/closed.");
		        }
		        try {
		            WebElement loginPopupClose = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='overlayModal__close']")));
		            loginPopupClose.click();
		        } catch (Exception e) {
		            System.out.println("Login popup not found/closed.");
		        }
		    }

		    private static void login(WebDriver driver, WebDriverWait wait, String phoneNumber, String password) {
		        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-cy='account']")));
		        loginButton.click();
		        WebElement phoneNumberLogin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-cy='LoginEmail']")));
		        phoneNumberLogin.click();
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(phoneNumber);
		        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-cy='continueBtn']"))).click();
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);
		        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-cy='login']"))).click();
		    }

		    private static void bookFlight(WebDriver driver, WebDriverWait wait, String fromCity, String toCity, LocalDate departureDate) {
		        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-cy='menu_Flights']"))).click();
		        selectCity(driver, wait, "fromCity", fromCity);
		        selectCity(driver, wait, "toCity", toCity);
		        selectDate(driver, wait, "departure", departureDate);
		        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-cy='submit']"))).click();
		        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Book Now')])[1]"))).click();
		        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue as Guest')]"))).click();
		    }

		    private static void selectCity(WebDriver driver, WebDriverWait wait, String cityFieldId, String cityName) {
		        wait.until(ExpectedConditions.elementToBeClickable(By.id(cityFieldId))).click();
		        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + cityName + "']"))).click();
		    }

		    private static void selectDate(WebDriver driver, WebDriverWait wait, String dateFieldId, LocalDate departureDate) {
		        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='" + dateFieldId + "']"))).click();
		        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
		        String ariaLabel = departureDate.format(dateFormatter);
		        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='" + ariaLabel + "']"))).click();
		    }

		    private static void fillPassengerDetails(WebDriver driver, WebDriverWait wait) {
		        // Implement passenger details filling here (complex, site-specific)
		        System.out.println("Fill passenger details");
		    }

		    private static void proceedToPayment(WebDriver driver, WebDriverWait wait) {
		        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]"))).click();
		        // Implement payment selection and input here (very complex, site-specific)
		        System.out.println("Proceed to payment");
		    }
	}
