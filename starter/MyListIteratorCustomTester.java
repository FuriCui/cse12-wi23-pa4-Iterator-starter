// DO NOT CHANGE THE METHOD NAMES

import static org.junit.Assert.*;
import org.junit.*;

public class MyListIteratorCustomTester {
    @Before
    public void setUp() throws Exception {
        MyLinkedList<Integer>x=new MyLinkedList<>();
        x.add(3);
        x.add(4);
        x.add(5);
        MyListIterator y = x.new MyListIterator();
    }
    @Test
    public void testNextEnd() {
        for(int i=0;i<2;i++){
            y.next();
        }
        assertThrows(NoSuchElementException.class,()->{y.next();});
    }
    @Test
    public void testPreviousStart() {
        assertThrows(NoSuchElementException.class,()->{y.previous();});
    }
    @Test
    public void testAddInvalid() {
        assertThrows(NullPointerException.class,()->{y.add(null);});
    }
    @Test
    public void testCantSet() {
        y.remove();
        assertThrows(IllegalStateException.class,()->{y.set(1);});
    }
    @Test
    public void testSetInvalid() {
        assertThrows(NullPointerException.class,()->{y.set(null);});
    }
    @Test
    public void testCantRemove() {
        y.remove();
        assertThrows(IllegalStateException.class,()->{y.remove();});
    }
    @Test
    public void testHasNextEnd() {
        for(int i=0;i<3;i++){
            y.next();
        }
        assertEquals(null,y.right);
    }
    @Test
    public void testHasPreviousStart() {
        assertEquals(null,y.left);
    }
    @Test
    public void testPreviousIndexStart() {
        assertEquals(0,y.previousIndex());
    }
    @Test
    public void testNextIndexEnd() {
        assertEquals(1,y.nextIndex());
    }
}

