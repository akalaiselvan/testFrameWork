package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class InstallSteps {


    @Given("Clear Registry {string} and {string}")
    public void clear_Registry(String regname, String local) {
        System.out.println("Clear all dependent registries"+regname+" and "+local);
    }

    @When("Start Web Installer and switch to labtest")
    public void start_Web_Installer_and_switch_to_labtest() {
        System.out.println("Start web installer setup and switch the same into labtest mode");
    }

    @Then("Enter Username and Password")
    public void enter_Username_and_Password() {
        System.out.println("Enter user name and Password");
    }

    @Given("Enter the data in Table")
    public void enterTheDataInTable(DataTable table) {
        for (Map<String,String> data:table.asMaps()){
            System.out.println(data.get("itemname"));
            System.out.println(data.get("Tax"));
        }
    }

    @Given("Enter the data in Table using List")
    public void enterTheDataInTableUsingList(DataTable table) {
        List<List<String>> data = table.asLists();
        System.out.println(data.get(1).get(0));
        System.out.println(data.get(1).get(1));
    }
}
