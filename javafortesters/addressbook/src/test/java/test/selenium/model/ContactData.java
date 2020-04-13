package test.selenium.model;

import java.util.Objects;

public class ContactData {

  private int id;
  private String firstName;
  private String lastName;
  private String group;
  private String companyName;
  private String homeNumber;
  private String mobileNumber;
  private String workNumber;



  public ContactData(int id, String firstName, String lastName, String group, String homeNumber, String mobileNumber, String workNumber) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.group = group;
    this.homeNumber = homeNumber;
    this.mobileNumber = mobileNumber;
    this.workNumber = workNumber;
  }

  public ContactData(int id, String firstName, String lastName, String companyName, String homeNumber, String group) {
    this.id = id;
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
    id = Integer.MAX_VALUE;
  }


  public int getId() {
    return id;
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
  public String getGroup() {
    if (group != null)
      return group;
    else return "[none]";
  }
  public String getHomeNumber() {
    return homeNumber;
  }
  public String getMobileNumber() {
    return mobileNumber;
  }
  public String getWorkNumber() {
    return workNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(homeNumber, that.homeNumber) &&
            Objects.equals(mobileNumber, that.mobileNumber) &&
            Objects.equals(workNumber, that.workNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, homeNumber, mobileNumber, workNumber);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", homeNumber='" + homeNumber + '\'' +
            ", mobileNumber='" + mobileNumber + '\'' +
            ", workNumber='" + workNumber + '\'' +
            '}';
  }


}
