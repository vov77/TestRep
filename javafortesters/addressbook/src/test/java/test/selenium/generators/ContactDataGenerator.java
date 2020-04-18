package test.selenium.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
    save(contacts, new File(file));
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstName(String.format("First Name %s", i))
              .withLastName(String.format("Last Name %s", i)).withAddress(String.format("address %s", i))
      .withMobileNumber(String.format("123456%s", i)).withPhoto(new File("src/test/resources/s.jpg")));

    }
    return contacts;
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(),
              contact.getAddress(), contact.getMobileNumber(), contact.getPhoto()));
    }
    writer.close();
  }
}
