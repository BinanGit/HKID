package org.test.stepsdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.test.methods.HKIDchecking_Methods;

import static org.test.methods.HKIDchecking_Methods.HKIDgenerated;

public class HKIDchecking_Steps {
    @Given("The user generate a HKID")
    public void theUserGenerateAHKID() {
        HKIDchecking_Methods.getHKIDgenerated();
    }

    @Then("The HKID generated should be verified")
    public void theHKIDGeneratedShouldBeVerified() {
        HKIDchecking_Methods.verifyHKID(HKIDgenerated);
    }

    @Given("The user provide a HKID of {string} with a check digit")
    public void theUserProvideAHKIDOfWithACheckDigit(String hkid) {
        HKIDchecking_Methods.verifyHKID(hkid);
    }

    @When("The user input the HKID into the checking formula")
    public void theUserInputTheHKIDIntoTheCheckingFormula() {
    }

    @Then("The user shall verify if the HKID is valid")
    public void theUserShallVerifyIfTheHKIDIsValid() {
    }

}
