package test.selenium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//mapping BD name to be used by Hibernate
@Entity
@Table(name = "group_list")
public class GroupData {
  //mapping BD fields to be used by Hibernate
  @Id
  @Column(name = "group_id")
  //ignore fields using FasterXML Jackson
  @JsonIgnore
  private int groupId = Integer.MAX_VALUE;

  //mark fields to be included in json using Gson
  @Expose
    //rename fields to correctly map with xml using FasterXML Jackson
  @JsonProperty("name")
  @Column(name = "group_name")
  private String groupName;

  @Expose
  @JsonProperty("header")
  @Column(name = "group_header")
  @Type(type = "text")
  private String groupHeader;

  @Expose
  @JsonProperty("footer")
  @Column(name = "group_footer")
  @Type(type = "text")
  private String groupFooter;

  public int getGroupId() {
    return groupId;
  }
  public String getName() {
    return groupName;
  }
  public String getHeader() {
    return groupHeader;
  }
  public String getFooter() {
    return groupFooter;
  }



  @ManyToMany(mappedBy = "groups")
  private Set<ContactData> contacts = new HashSet<ContactData>();

  public Contacts getContacts() {
    return new Contacts(contacts);
  }

  //Setters updated to return current object, thus all constructors can be deleted
  public GroupData withId(int groupId) {
    this.groupId = groupId;
    return this;
  }
  public GroupData withName(String groupName) {
    this.groupName = groupName;
    return this;
  }
  public GroupData withHeader(String groupHeader) {
    this.groupHeader = groupHeader;
    return this;
  }
  public GroupData withFooter(String groupFooter) {
    this.groupFooter = groupFooter;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return groupId == groupData.groupId &&
            Objects.equals(groupName, groupData.groupName) &&
            Objects.equals(groupHeader, groupData.groupHeader) &&
            Objects.equals(groupFooter, groupData.groupFooter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupId, groupName, groupHeader, groupFooter);
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "groupId='" + groupId + '\'' +
            ", groupName='" + groupName + '\'' +
            '}';
  }
}
