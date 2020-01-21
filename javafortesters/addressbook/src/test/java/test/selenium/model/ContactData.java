package test.selenium.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String companyName;
  private final String homeNumber;

  public ContactData(String firstName, String lastName, String companyName, String homeNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.companyName = companyName;
    this.homeNumber = homeNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getHomeNumber() {
    return homeNumber;
  }
}
