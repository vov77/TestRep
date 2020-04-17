package test.selenium.generators;

import test.selenium.model.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  public static void main(String[] args) throws IOException {
    //data generated
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    // store data in file
    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> groups = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      groups.add(new ContactData().withFirstName(String.format("First Name %s", i))
              .withLastName(String.format("Last Name %s", i)).withAddress(String.format("address %s", i))
      .withMobileNumber(String.format("number 123456%s", i)));
    }
    return groups;
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(),
              contact.getAddress(), contact.getMobileNumber()));
    }
    writer.close();
  }
}
