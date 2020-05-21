package test.selenium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {

  @JsonProperty
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;


  @Expose
  @JsonProperty
  @Column(name = "firstname")
  private String firstName = "";
  @Expose
  @JsonProperty
  @Column(name = "lastname")
  private String lastName = "";

  @JsonIgnore
  @ManyToMany (fetch = FetchType.EAGER)
  @JoinTable (name = "address_in_groups", joinColumns = @JoinColumn(name = "id")
          , inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<>();

  public Groups getGroups() {
    return new Groups(groups);
  }


  @JsonIgnore
  @Transient
  private String companyName = "";
  @JsonIgnore
  @Column(name = "home")
  @Type(type = "text")
  private String homeNumber = "";
  @JsonProperty
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobileNumber = "";
  @JsonIgnore
  @Column(name = "work")
  @Type(type = "text")
  private String workNumber = "";

  @JsonIgnore
  @Transient
  private String allPhones = "";
  @Expose
  @JsonProperty
  @Column(name = "address")
  @Type(type = "text")
  private String address = "";
  @JsonIgnore
  @Transient
  private String emails = "";

  @JsonProperty
  @Column(name = "email")
  @Type(type = "text")
  private String email1 = "";
  @JsonIgnore
  @Column(name = "email2")
  @Type(type = "text")
  private String email2 = "";
  @JsonIgnore
  @Column(name = "email3")
  @Type(type = "text")
  private String email3 = "";
  @JsonIgnore
  @Transient
  private String infoPageText = "";

  @Expose
  @JsonProperty
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

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
    return new File(photo);
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
    this.photo = photo.getPath();
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(companyName, that.companyName) &&
            Objects.equals(homeNumber, that.homeNumber) &&
            Objects.equals(mobileNumber, that.mobileNumber) &&
            Objects.equals(workNumber, that.workNumber) &&
            Objects.equals(address, that.address) &&
            Objects.equals(email1, that.email1) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, companyName, homeNumber, mobileNumber, workNumber, address, email1, email2, email3);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", companyName='" + companyName + '\'' +
            ", homeNumber='" + homeNumber + '\'' +
            ", mobileNumber='" + mobileNumber + '\'' +
            ", workNumber='" + workNumber + '\'' +
            ", address='" + address + '\'' +
            ", email1='" + email1 + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            '}';
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
