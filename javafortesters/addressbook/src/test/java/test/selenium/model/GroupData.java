package test.selenium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

import java.util.Objects;

public class GroupData {
  //ignore fields using FasterXML Jackson
  @JsonIgnore
  private int groupId = Integer.MAX_VALUE;
  //mark fields to be included in json using Gson
  @Expose
  //rename fields to correctly map with xml using FasterXML Jackson
  @JsonProperty("name")
  private String groupName;
  @Expose
  @JsonProperty("header")
  private String groupHeader;
  @Expose
  @JsonProperty("footer")
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
            Objects.equals(groupName, groupData.groupName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupId, groupName);
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "groupId='" + groupId + '\'' +
            ", groupName='" + groupName + '\'' +
            '}';
  }
}
