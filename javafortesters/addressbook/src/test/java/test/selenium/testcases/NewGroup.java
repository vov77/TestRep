package test.selenium.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.selenium.model.GroupData;
import test.selenium.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewGroup extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
    String line = reader.readLine();
    while (line != null){
      String split[] = line.split(";");
      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test (dataProvider = "validGroups")
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


