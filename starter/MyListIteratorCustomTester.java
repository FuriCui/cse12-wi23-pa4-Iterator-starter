import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
public class MyListIteratorCustomTester{
    MyLinkedList<Integer>x=new MyLinkedList<>();
    MyLinkedList<Integer>.MyListIterator y;
    @Before
    public void setUp() throws Exception{
        x.add(3);
        x.add(4);
        x.add(5);
        y=x.new MyListIterator();
    }
    @Test
    public void testNextEnd(){
        for(int i=0;i<3;i++){
            y.next();
        }
        assertThrows(NoSuchElementException.class,()->{y.next();});
    }
    @Test
    public void testPreviousStart(){
        assertThrows(NoSuchElementException.class,()->{y.previous();});
    }
    @Test
    public void testAddInvalid(){
        for(int i=0;i<3;i++){
            y.next();
        }
        assertThrows(NullPointerException.class,()->{y.add(null);});
    }
    @Test
    public void testCantSet(){
        y.next();
        y.add(1);
        assertThrows(IllegalStateException.class,()->{y.set(2);});
    }
    @Test
    public void testSetInvalid(){
        assertThrows(NullPointerException.class,()->{y.set(null);});
    }
    @Test
    public void testCantRemove(){
        y.add(1);
        assertThrows(IllegalStateException.class,()->{y.remove();});
    }
    @Test
    public void testHasNextEnd(){
        for(int i=0;i<3;i++){
            y.next();
        }
        assertEquals(false,y.hasNext());
    }
    @Test
    public void testHasPreviousStart(){
        assertEquals(false,y.hasPrevious());
    }
    @Test
    public void testPreviousIndexStart(){
        assertEquals(-1,y.previousIndex());
    }
    @Test
    public void testNextIndexEnd(){
        for(int i=0;i<3;i++){
            y.next();
        }
        assertEquals(3,y.nextIndex());
    }
}
