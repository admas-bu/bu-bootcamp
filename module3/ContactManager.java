import java.util.*; 
 
public class ContactManager { 

    public static void lookupContact(HashMap<String, Contact> contacts, String nameToLookup) {
        Contact contact = contacts.get(nameToLookup);
        if (contact == null) {
            System.out.println(nameToLookup + " not found in contacts.");
        } else {
            System.out.println(contact);
        }
    }
 
    public static void main(String[] args) { 
 
        HashMap<String, Contact> contacts = new HashMap<>(); 
 
        // Step 4: add contacts here 
        contacts.put("Ada Lovelace", new Contact("Ada Lovelace", "+1-617-555-0101"));
        contacts.put("Grace Hopper", new Contact("Grace Hopper", "+1-781-0102"));
        contacts.put("Henry Ford", new Contact("Henry Ford", "+1-774-555-0103"));
        contacts.put("Iris Johnson", new Contact("Iris Johnson", "+1-508-555-0104"));
        contacts.put("Jackie Robinson", new Contact("Jackie Robinson", "+1-345-555-0105"));

        // Step 5: look up a contact
        lookupContact(contacts, "Ada Lovelace");
        lookupContact(contacts, "Unknown Person");
        
        // Step 6: print sorted list
        ArrayList<Contact> sorted = new ArrayList<>(contacts.values());
        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));
        
        System.out.println("=== All Contacts ===");
        for (Contact c : sorted) {
            System.out.println(c);
        }
    } 
}