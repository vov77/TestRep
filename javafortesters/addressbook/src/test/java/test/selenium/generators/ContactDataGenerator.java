package test.selenium.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import test.selenium.model.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  //Jcommander parameters (generated data)
  @Parameter (names = "-c", description = "Contact count")
  public int count;
  @Parameter (names = "-f", description = "Target file")
  public String file;
  @Parameter (names = "-d", description = "Data format")
  public String format;


  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
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
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")){
      saveAsCSV(contacts, new File(file));
    }
    else if (format.equals("xml")){
      saveAsXML(contacts, new File(file));
    }
    else if (format.equals("json")){
      saveAsJSon2(contacts, new File(file));
    }
    else System.out.println("Unrecognized format" + format);
  }


  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstName(String.format("First Name %s", i))
              .withLastName(String.format("Last Name %s", i)).withAddress(String.format("address %s", i))
      .withMobileNumber(String.format("123456%s", i)).withEmail1(String.format("%s@mail.ru", i))
              .withPhoto(new File("src/test/resources/s.jpg")));

    }
    return contacts;
  }

  private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(),
              contact.getAddress(), contact.getMobileNumber(), contact.getEmails(), contact.getPhoto()));
    }
    writer.close();
  }

  //generate json with Gson
  private void saveAsJSon(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  //generate json with Jackson
  private void saveAsJSon2(List<ContactData> contacts, File file) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    String xml = mapper.writeValueAsString(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
    ObjectMapper mapper = new XmlMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    String xml = mapper.writeValueAsString(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }
}
