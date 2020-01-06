import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Verifications extends Functions {
    void makeTransaction(String no) {
        System.out.println("make trans "+no);
    }

    boolean assertTransCount() {
        System.out.println("Validating transaction count");
        return false;
    }

    public void editTransaction(){
        System.out.println("from veri");
    }

    public static void main(String[] args) {
        new Verifications().makeTransaction("tran1");
        new Verifications().assertTransCount();
        new Verifications().editTransaction();
    }
}
