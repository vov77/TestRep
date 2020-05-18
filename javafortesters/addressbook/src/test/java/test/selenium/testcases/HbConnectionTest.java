package test.selenium.testcases;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.selenium.model.ContactData;
import test.selenium.model.GroupData;

import java.util.List;

public class HbConnectionTest {
  private SessionFactory sessionFactory;

  @BeforeClass

  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy( registry );
    }
  }
/*
  @Test
  public void testHbConnectionGroups(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from test.selenium.model.GroupData").list();
    for ( GroupData group : result ) {
      System.out.println(group);
    }
    session.getTransaction().commit();
    session.close();
  }*/

  @Test
  public void testHbConnectionContacts(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    @SuppressWarnings("JpaQlInspection") List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00 00:00:00'").list();
    for ( ContactData contact : result ) {
      System.out.println(contact);
    }
    session.getTransaction().commit();
    session.close();
  }
}
