package test.selenium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public class ContactData {

  @JsonProperty
  private int id = Integer.MAX_VALUE;


  @Expose
  @JsonProperty
  private String firstName = "";
  @Expose
  @JsonProperty
  private String lastName = "";
  @JsonIgnore
  private String group = "";
  @JsonIgnore
  private String companyName = "";
  @JsonIgnore
  private String homeNumber = "";
  @JsonProperty
  private String mobileNumber = "";
  @JsonIgnore
  private String workNumber = "";
  @JsonIgnore
  private String allPhones = "";
  @Expose
  @JsonProperty
  private String address = "";
  @JsonIgnore
  private String emails = "";

  @JsonProperty
  private String email1 = "";
  @JsonIgnore
  private String email2 = "";
  @JsonIgnore
  private String email3 = "";
  @JsonIgnore
  private String infoPageText = "";
  @Expose
  @JsonProperty
  private File photo;

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
    if (group.equals(""))
      return "[none]";
    else return group;
        //else return "[none]";
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
  public String getAllPhones() {
    return allPhones;
  }
  public String getAddress() {
    return address;
  }
  public String getEmails() {
    return emails;
  }
  public String getEmail1() {
    return email1;
  }
  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }
  public String getInfoPageText() {
    return infoPageText;
  }
  public File getPhoto() {
    return photo;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }
  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }
  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }
  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
  public ContactData withCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }
  public ContactData withHomeNumber(String homeNumber) {
    this.homeNumber = homeNumber;
    return this;
  }
  public ContactData withMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }
  public ContactData withWorkNumber(String workNumber) {
    this.workNumber = workNumber;
    return this;
  }
  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }
  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }
  public ContactData withEmails(String emails) {
    this.emails = emails;
    return this;
  }
  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }
  public ContactData withInfoPageText(String infoPageText) {
    this.infoPageText = infoPageText;
    return this;
  }
  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", mobileNumber='" + mobileNumber + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", address='" + address + '\'' +
            ", emails='" + emails + '\'' +
            ", email1='" + email1 + '\'' +
            ", photo=" + photo +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(mobileNumber, that.mobileNumber) &&
            Objects.equals(allPhones, that.allPhones) &&
            Objects.equals(address, that.address) &&
            Objects.equals(emails, that.emails) &&
            Objects.equals(email1, that.email1) &&
            Objects.equals(photo, that.photo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, mobileNumber, allPhones, address, emails, email1, photo);
  }

}
