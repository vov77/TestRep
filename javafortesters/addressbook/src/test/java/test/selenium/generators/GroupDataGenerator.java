package test.selenium.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import test.selenium.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

  //Jcommander parameters (generated data)
  @Parameter(names = "-c", description = "Group count")
  public int count;
  @Parameter (names = "-f", description = "Target file")
  public String file;
  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main (String [] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
    JCommander commander = new JCommander(generator);
    try {
      commander.parse(args);
    }
    catch (ParameterException e){
      commander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    // store data in file
    List<GroupData> groups = generateGroups(count);
    if (format.equals("csv")){
      saveAsCSV(groups, new File(file));
    }
    else if (format.equals("xml")){
      saveAsXML(groups, new File(file));
    }
    else if (format.equals("json")){
      saveAsJSon(groups, new File(file));
    }
    else System.out.println("Unrecognized format" + format);

  }

  private void saveAsJSon(List<GroupData> groups, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(groups);
    try (Writer writer = new FileWriter(file);) { // using try to close writer
      writer.write(json);
    }
  }

  /*private void saveAsXML(List<GroupData> groups, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.alias("group", GroupData.class);
    xstream.omitField(GroupData.class, "groupId");
    String xml = xstream.toXML(groups);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }*/

  private void saveAsXML(List<GroupData> groups, File file) throws IOException {
    ObjectMapper mapper = new XmlMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    //mapper.alias("group", GroupData.class);
    //mapper.omitField(GroupData.class, "groupId");
    String xml = mapper.writeValueAsString(groups);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close(); // closing writer
  }


  private void saveAsCSV(List<GroupData> groups, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (GroupData group :groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
    }
    writer.close();
  }


  private List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<>();
    for (int i = 0; i<count; i++){
      groups.add(new GroupData().withName(String.format("test %s", i))
              .withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
    }
    return groups;
  }

}
