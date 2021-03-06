package Tests;

import Pages.CustomerServicePage;
import Pages.HomePage;
import base.CommonAPI;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerServicePageTest extends CommonAPI {

    CustomerServicePage customerServicePage;
    @BeforeMethod
    public void createTestObj(){
        this.customerServicePage = new CustomerServicePage();
    }

  @Test
  public void validateLabelText(){
        String ans = customerServicePage.CSLabelText();
      Assert.assertEquals(ans,"We're here to help.");
  }

  @Test
    public void validatePhoneFieldTest() throws InterruptedException {
        customerServicePage.verifyPhoneTextLabel();
  }

  @Test
  public void AddPropertyTest() throws InterruptedException {
        customerServicePage.verifyPropertyPageBannerText();
    }

    @Test
    public void SearchResultsCount() throws InterruptedException {
        customerServicePage.getSearchResultCount();
    }
}
