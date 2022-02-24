import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class CatalogTest
{
    @Test
    public void testAdd()
    {   
        boolean result = false;
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        catalog.add(book);
        ArrayList<String> items = catalog.search("The Hunger Games");
        for (String i: items){
           LibraryItem item = catalog.getLibraryItem(i);
           if(item.equals(book)){
               result = true;
           }
           else{
               result = false;
           }
        }
        assertEquals("failure: test did not add ", true, result);
    }

    @Test
    public void testAddTwice()
    {   
        boolean result = true;
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        String book1 = catalog.add(book);
        String book2 = catalog.add(book);

        if(book1.equals(book2)){
            result = false;
        }
        assertEquals("failure: cannot add same book twice ", true, result);
    }

    @Test 
    public void testCheckin(){
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        catalog.add(book);
        ArrayList<String> find = catalog.search("The Hunger Games");

        boolean checkinSuccess = catalog.checkin(find.get(0));
        assertEquals("failure: checkin method does not work", true, checkinSuccess);
    }

    @Test
    public void testCheckinToEmptyCatalog(){
        Catalog catalog = new Catalog();
        boolean checkinSuccess = catalog.checkin("1");
        assertEquals("failure: should not be able to check in to empty catalog", false, checkinSuccess);
    }

    @Test
    public void testCheckoutToEmptyCatalog(){
        Catalog catalog = new Catalog();
        boolean checkinSuccess = catalog.checkout("1");
        assertEquals("failure: should not be able to check out empty catalog", false, checkinSuccess);
    }

    @Test
    public void testCheckout(){
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        catalog.add(book);
        ArrayList<String> find = catalog.search("The Hunger Games");

        boolean checkoutSuccess = catalog.checkout(find.get(0));
        assertEquals("failure: checkout method does not work", true, checkoutSuccess);
    }

    @Test
    public void testCheckoutTwice(){
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        catalog.add(book);

        ArrayList<String> find = catalog.search("The Hunger Games");
        boolean checkoutSuccess = catalog.checkout(find.get(0));
        checkoutSuccess = catalog.checkout(find.get(0));
        assertEquals("failure: cannot checkout twice of the same book", false, checkoutSuccess);
    }

    @Test
    public void testSearchTitle(){
        boolean searchSuccess = false;
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        catalog.add(book);

        ArrayList<String> find = catalog.search("The Hunger Games");
        if(catalog.getLibraryItem(find.get(0)).getTitle().equals(book.getTitle())){
            searchSuccess = true;
        }
        assertEquals("failure: searching title does not work", true, searchSuccess);
    }

    @Test
    public void testSearchAuthorsFirstName(){
        boolean searchSuccess = false;
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        catalog.add(book);

        ArrayList<String> find = catalog.search("Suzanne");
        if(catalog.getLibraryItem(find.get(0)).getTitle().equals(book.getTitle())){
            searchSuccess = true;
        }
        assertEquals("failure: seaching authors first name does not work", true, searchSuccess);
    }

    @Test
    public void testSearchAuthorsLastName(){
        boolean searchSuccess = false;
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        catalog.add(book);

        ArrayList<String> find = catalog.search("Collins");
        if(catalog.getLibraryItem(find.get(0)).getTitle().equals(book.getTitle())){
            searchSuccess = true;
        }
        assertEquals("failure: seaching authors last name does not work", true, searchSuccess);
    }

    @Test
    public void testSearchPublisher(){
        boolean searchSuccess = false;
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        catalog.add(book);

        ArrayList<String> find = catalog.search("Scholastic Press");
        if(catalog.getLibraryItem(find.get(0)).getTitle().equals(book.getTitle())){
            searchSuccess = true;
        }
        assertEquals("failure: seaching publisher does not work", true, searchSuccess);
    }

    @Test
    public void testSearchISBN(){
        boolean searchSuccess = false;
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        catalog.add(book);

        ArrayList<String> find = catalog.search("9780439023481");
        if(catalog.getLibraryItem(find.get(0)).getTitle().equals(book.getTitle())){
            searchSuccess = true;
        }
        assertEquals("failure: seaching ISBN does not work", true, searchSuccess);
    }

    @Test
    public void testSearchManyMatches(){
        boolean success = true;
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Bob", "Smith", "Scholastic Press", "9780439023481");
        catalog.add(book);

        book = new Book("The Hunger Games", 2012, "Sally", "Jane", "Scholastic Press", "9780439023481");
        catalog.add(book);

        book = new Book("The Hunger Games", 2012, "Nick", "Colletti", "Scholastic Press", "9780439023481");
        catalog.add(book);

        ArrayList<String> find = catalog.search("The Hunger Games");
        if(find.size() != 3){
            success = false;
        }
        assertEquals("failure: the size of catalog should be 3", true, success);
    }

    @Test 
    public void testRemove(){
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        catalog.add(book);
        catalog.add(book);

        ArrayList<String> find = catalog.search("Suzanne");
        boolean removeSuccess = catalog.remove(find.get(0));
        assertEquals("failure: remove function does not work", true, removeSuccess);
    }

    @Test 
    public void testRemoveTwice(){
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        catalog.add(book);

        ArrayList<String> find = catalog.search("Suzanne");
        boolean removeSuccess = catalog.remove(find.get(0));
        removeSuccess = catalog.remove(find.get(0));
        assertEquals("failure: book shouldn't be able to be removed twice", false, removeSuccess);
    }

    @Test 
    public void testRemoveAfterCheckout(){
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        catalog.add(book);
        catalog.checkout("0");

        ArrayList<String> find = catalog.search("Suzanne");
        boolean removeSuccess = catalog.remove(find.get(0));
        assertEquals("failure: should not be able to remove after checkout", false, removeSuccess);
    }

    @Test 
    public void testGetLibraryItem(){
        boolean success = false;
        Catalog catalog = new Catalog();
        Book book = new Book("The Hunger Games", 2012, "Suzanne", "Collins", "Scholastic Press", "9780439023481");
        catalog.add(book);

        ArrayList<String> find = catalog.search("Suzanne");
        if (catalog.getLibraryItem(find.get(0)).equals(book)){
            success = true;
        }
        assertEquals("failure: cannot get library item", true, success);
    }
}
