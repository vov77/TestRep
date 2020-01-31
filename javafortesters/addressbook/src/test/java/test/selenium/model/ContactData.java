package test.selenium.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String companyName;
  private final String homeNumber;
  private String group;

  public ContactData(String firstName, String lastName, String companyName, String homeNumber, String group) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.companyName = companyName;
    this.homeNumber = homeNumber;
    this.group = group;
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

  public String getGroup() {
    return group;
  }
}
