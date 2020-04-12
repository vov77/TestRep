package test.selenium.model;

import java.util.Objects;

public class ContactData {

  private int contactId;
  private final String firstName;
  private final String lastName;
  private final String companyName;
  private final String homeNumber;
  private String group;


  public ContactData(int contactId, String firstName, String lastName, String companyName, String homeNumber, String group) {
    this.contactId = contactId;
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
    contactId = Integer.MAX_VALUE;
  }

  public void setContactId(int contactId) {
    this.contactId = contactId;
  }

  public int getContactId() {
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
    if (group != null)
      return group;
    else return "[none]";
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "contactId=" + contactId +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", homeNumber='" + homeNumber + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return contactId == that.contactId &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(homeNumber, that.homeNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactId, firstName, lastName, homeNumber);
  }
}
