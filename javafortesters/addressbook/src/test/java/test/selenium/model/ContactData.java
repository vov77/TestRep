package test.selenium.model;

import java.util.Objects;

public class ContactData {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(contactId, that.contactId) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(homeNumber, that.homeNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactId, firstName, lastName, homeNumber);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "contactId='" + contactId + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", homeNumber='" + homeNumber + '\'' +
            '}';
  }

  private final String contactId;
  private final String firstName;
  private final String lastName;
  private final String companyName;
  private final String homeNumber;
  private String group;


  public ContactData(String id, String firstName, String lastName, String companyName, String homeNumber, String group) {
    this.contactId = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.companyName = companyName;
    this.homeNumber = homeNumber;
    this.group = group;
  }

  public ContactData(String firstName, String lastName, String companyName, String homeNumber, String group) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.companyName = companyName;
    this.homeNumber = homeNumber;
    this.group = group;
    contactId = null;
  }

  public String getContactId() {
    return contactId;
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
