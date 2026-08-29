import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 
 
public class ContactTest { 
 
  @Test 
  void constructor_setsNameCorrectly() { 
    Contact c = new Contact("Ada Lovelace", "+1 617 555 0101"); 
    assertEquals("Ada Lovelace", c.getName()); 
  } 
 
  @Test
  void constructor_setsPhoneCorrectly() { 
    Contact c = new Contact("Ada Lovelace", "+1 617 555 0101"); 
    assertEquals("+1 617 555 0101", c.getPhone()); 
  } 
 
  @Test
  void getName_returnsExactString_notTransformed() { 
    Contact c = new Contact("Grace Hopper", "555-0000"); 
    assertEquals("Grace Hopper", c.getName());
  } 
 
  @Test
  void toString_containsName() { 
    Contact c = new Contact("Alan Turing", "555-0001"); 
    assertTrue(c.toString().contains("Alan Turing"));
  } 
 
  @Test
  void toString_containsPhone() {
    Contact c = new Contact("Alan Turing", "555-0001");
    assertTrue(c.toString().contains("555-0001"));
  }

  @Test
  void testTwoContactsWithSameNameAreIndependent() {
    // Create two Contact objects with the same name
    Contact contact1 = new Contact("Grace Hopper", "555-1001");
    Contact contact2 = new Contact("Grace Hopper", "555-1002");
    
    // Modify contact1's phone number
    contact1.setPhone("555-9999");
    
    // Verify that contact2's phone is unchanged
    assertEquals("555-1002", contact2.getPhone());
    assertEquals("555-9999", contact1.getPhone());
  }

  @Test
  void testSetPhoneUpdatesPhoneNumber() {
    Contact c = new Contact("Blaise Pascal", "555-0002");
    assertEquals("555-0002", c.getPhone());
    
    // Update the phone number
    c.setPhone("555-0003");
    assertEquals("555-0003", c.getPhone());
    
    // Verify toString reflects the updated phone
    assertTrue(c.toString().contains("555-0003"));
  }
} 