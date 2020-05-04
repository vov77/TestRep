package test.selenium.testcases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;
import test.selenium.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewGroup extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsFromCsv() throws IOException {
    //reading data from csv file using JCommander
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
    String line = reader.readLine();
    while (line != null) {
      String split[] = line.split(";");
      list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    //reading data from xml using XStream - doesn't work!!! XStream security error for Java 9 and higher!!
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null){
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();

    List<GroupData> groups = (List<GroupData>)xStream.fromXML(xml);
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml2() throws IOException {
    //reading data from xml using Jackson FastXML
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null){
      xml += line;
      line = reader.readLine();
    }
    XmlMapper xmlMapper = new XmlMapper();
    List<GroupData> groups = xmlMapper.readValue(xml, new TypeReference<List<GroupData>>() {
      @Override
      public Type getType() {
        return super.getType();
      }
    });
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    //reading data from json using Gson
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validGroupsFromXml2")
  public void createNewGroup(GroupData group){
    app.goTo().groups();
    Groups before = app.group().set();
    app.group().create(group);
    app.goTo().groups();
    assertThat(app.group().count(), equalTo(before.size()+1));
    Groups after = app.group().set();
    // Assert.assertEquals(after.size(), before.size()+1 );
    assertThat(after.size(), equalTo(before.size()+1));
    group.withId(after.stream().mapToInt(GroupData::getGroupId).max().getAsInt());
   // Assert.assertEquals(before, after);
  }

  @Test
  public void createBadGroup(){
    app.goTo().groups();
    Groups before = app.group().set();
    GroupData group = new GroupData().withName("'").withFooter("Test110").withHeader("Test111");
    app.group().create(group);
    app.goTo().groups();
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().set();
    assertThat(after, equalTo(before));
  }
}


