package test.selenium.model;

import java.util.Objects;

public class GroupData {

  private int groupId = Integer.MAX_VALUE;
  private String groupName;
  private String groupHeader;
  private String groupFooter;

  public int getGroupId() {
    return groupId;
  }
  public String getGroupName() {
    return groupName;
  }
  public String getGroupHeader() {
    return groupHeader;
  }
  public String getGroupFooter() {
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
