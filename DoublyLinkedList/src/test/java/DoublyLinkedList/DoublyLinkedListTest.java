package DoublyLinkedList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListTest{

    private DoublyLinkedList<Integer> list;
    private DoublyLinkedList<String>  slist;
    private List<Integer> collection;
    private Integer[] array;

    @Before
    public void setUp() {
        list  = new DoublyLinkedList<>();
        slist = new DoublyLinkedList<>();
        collection = new ArrayList<>();
        array = new Integer[3];
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());
    }

    @Test
    public void testIsEmptyReturnsTrue() {
        assertTrue(list.isEmpty());
    }

    @Test                 // assert false, see oracle doc of add.
    public void testAdd() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.getHead().getElement().equals(1));
        assertTrue(list.getTail().getElement().equals(3));
        assertEquals("[1,2,3]", list.toString());
  }

    @Test
    public void testAddAll() {
        list.add(1);
        list.add(2);
        list.add(3);

        collection.add(4);
        collection.add(5);
        collection.add(6);

        list.addAll(collection);

        assertTrue(list.getHead().getElement().equals(1));
        assertTrue(list.getTail().getElement().equals(6));
        assertEquals("[1,2,3,4,5,6]", list.toString());
    }

    @Test
    public void testClear() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.clear();

        assertTrue(list.isEmpty());
        assertEquals("]", list.toString());                 // why is the first bracket removed??????
  }

    @Test
    public void testContains() {
        list.add(4);

        assertTrue(list.contains(4));
  }

    @Test
	public void testGet() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.get(1).equals(2));
  }

    @Test(expected = IndexOutOfBoundsException.class)   // are both cases checked seperately?
	public void testGetThrowsIndexOutOfBoundsException() {
        list.get(1);
        list.get(-1);
    }

    @Test
	public void testIndexOf() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.indexOf(2) == 1);
  }

    @Test                 // assert false, see oracle doc of add.
    public void testRemoveIndex() {
        list.add(1);
        list.add(2);

        list.remove(0);

        assertTrue(list.getHead().getElement().equals(2));
        assertTrue(list.getTail().getElement().equals(2));
        assertEquals(1, list.size());
        assertEquals("[2]", list.toString());
  }

    @Test(expected = IndexOutOfBoundsException.class)   // are both cases checked seperately?
	public void testRemoveIndexThrowsIndexOutOfBoundsException() {
        list.remove(2);
        list.remove(-1);
    }

    @Test                 // assert false, see oracle doc of add.
    public void testRemoveObject() {
        slist.add("null");
        slist.add("eins");
        slist.add("zwei");

        slist.remove("eins");
        slist.remove("null");

        assertTrue(slist.getHead().getElement().equals("zwei"));
        assertTrue(slist.getTail().getElement().equals("zwei"));
        assertEquals(1, slist.size());
        assertEquals("[zwei]", slist.toString());
  }

    @Test(expected = NoSuchElementException.class)
	public void testRemoveObjectThrowsNoSuchElementException() {
        slist.remove("test");
    }

    @Test
	public void testSet() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.set(1, 5);

        assertEquals("[1,5,3]", list.toString());
  }

    @Test(expected = IndexOutOfBoundsException.class)   // are both cases checked seperately?
	public void testSetThrowsIndexOutOfBoundsException() {
        list.set(1, 2);
        list.set(-1, 2);
    }

    @Test
    public void testToArray() {
        list.add(1);
        list.add(2);
        list.add(3);

        Object[] test     = list.toArray(array);
        Object[] expected = {1,2,3};

        assertArrayEquals(expected, test);
  }
}
