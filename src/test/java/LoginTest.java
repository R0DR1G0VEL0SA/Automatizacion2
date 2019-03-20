import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
	WebDriver driver;

	@Before
	public void InitBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com/");

	}

	@Test
	public void testBD() throws ClassNotFoundException {
		Login pageLogin = new Login(driver);
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			Connection conx = DriverManager.getConnection(url, "sys as sysdba", "Adsi-908163*");
			System.out.println("Conected a Oracle");

			Statement smt = conx.createStatement();
			ResultSet rs = smt.executeQuery("SELECT * "
					+                       "  FROM hr.login"
					+                       " WHERE login_id = 1");

			while (rs.next()) {

				String USERNAME = rs.getString("USERNAME");
				String PASSWORD = rs.getString("PASSWORD");

				pageLogin.LoginApp(USERNAME, PASSWORD);
				Assert.assertTrue(pageLogin.validateLogin());
			}
			
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@After
	public void CloseBrowser() {
		 driver.close();

	}

}
