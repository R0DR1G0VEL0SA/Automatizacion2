import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
	// Crear variables tipo Web
	WebDriver miDriver;
	WebElement inicio;
	WebElement txtName;
	WebElement txtPassword;
	WebElement btnIngresar;
	WebElement validacion;

	public Login(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.miDriver = driver;
	}

	public void LoginApp(String user, String password) {
		// TODO Auto-generated method stub
		inicio = miDriver.findElement(By.xpath("//*[@id=\"gh-ug\"]/a"));
		inicio.click();

		txtName = miDriver.findElement(By.id("userid"));
		txtName.sendKeys(user);
		txtPassword = miDriver.findElement(By.id("pass"));
		txtPassword.sendKeys(password);
		btnIngresar = miDriver.findElement(By.xpath("//*[@id=\"sgnBt\"]"));
		btnIngresar.click();

	}

	public boolean validateLogin() {
		boolean isValidateLogin = true;
		// TODO Auto-generated method stub
		try {
			WebElement lbsMensaje = miDriver.findElement(By.id("errf"));
			if (lbsMensaje.isDisplayed()) {
				isValidateLogin = false;

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());

		}

		return isValidateLogin;

	}

}
