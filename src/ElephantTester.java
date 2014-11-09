import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Ignore;
import org.junit.Test;

public class ElephantTester {

	
	@Test
	public void test() {
		testContructor1();
		testConstructor2();
		testMutators();
		testAge();
		testRelationship();
	}
	
	private void testContructor1() {
		
		Elephant e = new Elephant("Bob", 'M', 2013, 11);
		Elephant e2 = new Elephant("Bob", 'F', 2013, 11);
		
		assertEquals("Bob", e.getName());
		assertEquals(2013, e.getYear());
		assertEquals(11, e.getMonth());
		assertEquals(false, e.isFemale());
		assertEquals(true, e2.isFemale());
		assertEquals(null, e.getMom());
		assertEquals(null, e.getDad());
		assertEquals(0, e.getChildren());
	}
	
	private void testMutators() {
		Elephant e1 = new Elephant("Bob Jr", 'M', 2013, 1);
		Elephant e2 = new Elephant("Bob Sr", 'M', 2001, 7);
		Elephant e3 = new Elephant("Mary", 'F', 2002, 12);
		Elephant e4 = new Elephant("Granny", 'F', 2000, 2);
		Elephant e5 = new Elephant("Grandpa", 'M', 2000, 8);
		Elephant e6 = new Elephant("Emily", 'F', 2003, 11);
		Elephant e7 = new Elephant("George", 'M', 2002, 12);
		Elephant e8 = new Elephant("Noah", 'M', 2000, 2);
		Elephant e9 = new Elephant("George Jr", 'M', 2014, 7);
		
		/** 			Family Tree 
		 * 							
		 * 		Noah			Granny			Grandpa
		 * 		/	\				\			/
		 * Bob Sr	George			   Mary, Emily
		 * 		\		  \			    /		|
		 * 	  	  \		    \          /		|
		 * 			\	      \       /			|
		 * 			 Bob Jr    George Jr		|
		 * 				|						|
		 * 				|_______________________|
		 */
		
		e1.addDad(e2); e1.addMom(e6);
		e9.addDad(e7); e9.addMom(e3);
		e2.addDad(e8);
		e7.addDad(e8);
		e6.addDad(e5); e6.addMom(e4);
		e3.addDad(e5); e3.addMom(e4);
		
		assertEquals(2,e8.getChildren());
		assertEquals(null,e8.getMom()); assertEquals(null,e8.getDad());
		assertEquals(2,e4.getChildren());
		assertEquals(null,e4.getMom()); assertEquals(null,e4.getDad());
		assertEquals(2,e5.getChildren());
		assertEquals(null,e5.getMom()); assertEquals(null,e5.getDad());
		assertEquals(1, e2.getChildren()); 
		assertEquals(e8, e2.getDad()); assertEquals(null, e2.getMom());
		assertEquals(1, e7.getChildren());
		assertEquals(e8, e7.getDad()); assertEquals(null, e7.getMom()); 
		assertEquals(1, e3.getChildren());
		assertEquals(e5, e3.getDad()); assertEquals(e4, e3.getMom());
		assertEquals(1, e6.getChildren());
		assertEquals(e5, e6.getDad()); assertEquals(e4, e6.getMom());
		assertEquals(0, e1.getChildren());
		assertEquals(e2, e1.getDad()); assertEquals(e6, e1.getMom());
		assertEquals(0, e9.getChildren());
		assertEquals(e7, e9.getDad()); assertEquals(e3, e9.getMom());
	}

	private void testConstructor2() {
		
		Elephant ma = new Elephant("Tom", 'M', 2000, 4);
		Elephant pa = new Elephant("Jerry", 'F', 2000, 2);
		Elephant e = new Elephant("T&J Jr", 'M', 2014, 3, ma, pa);
		
		assertEquals("T&J Jr", e.getName());
		assertEquals(2014, e.getYear());
		assertEquals(3, e.getMonth());
		assertEquals(false, e.isFemale());
		assertEquals("Tom", e.getMom().getName());
		assertEquals("Jerry", e.getDad().getName());
		assertEquals(1, e.getMom().getChildren());
		assertEquals(1, e.getDad().getChildren());
		assertEquals(0, e.getChildren());
	}
	
	private void testAge() {
		Elephant e1 = new Elephant("Bob Jr", 'M', 2013, 1);
		Elephant e2 = new Elephant("Bob Sr", 'M', 2001, 7);
		Elephant e3 = new Elephant("Mary", 'F', 2002, 12);
		Elephant e4 = new Elephant("Granny", 'F', 2001, 2);
		
		assertTrue(e2.isOlder(e1));
		assertTrue(e3.isOlder(e1));
		assertTrue(e4.isOlder(e1));
		assertTrue(e4.isOlder(e2));
		assertTrue(e2.isOlder(e3));
		assertTrue(e4.isOlder(e3));
		
		assertFalse(e2.isOlder(e4));
	}
	
	private void testRelationship() {
		Elephant e1 = new Elephant("Bob Jr", 'M', 2013, 1);
		Elephant e2 = new Elephant("Bob Sr", 'M', 2001, 7);
		Elephant e3 = new Elephant("Mary", 'F', 2002, 12);
		Elephant e4 = new Elephant("Granny", 'F', 2000, 2);
		Elephant e5 = new Elephant("Grandpa", 'M', 2000, 8);
		Elephant e6 = new Elephant("Emily", 'F', 2003, 11);
		Elephant e7 = new Elephant("George", 'M', 2002, 12);
		Elephant e8 = new Elephant("Noah", 'M', 2000, 2);
		Elephant e9 = new Elephant("George Jr", 'M', 2014, 7);
		
		e1.addDad(e2); e1.addMom(e6);
		e9.addDad(e7); e9.addMom(e3);
		e2.addDad(e8);
		e7.addDad(e8);
		e6.addDad(e5); e6.addMom(e4);
		e3.addDad(e5); e3.addMom(e4);
		
		/** 			Family Tree 
		 * 							
		 * 		Noah			Granny			Grandpa
		 * 		/	\				\			/
		 * Bob Sr	George			   Mary, Emily
		 * 		\		  \			    /		|
		 * 	  	  \		    \          /		|
		 * 			\	      \       /			|
		 * 			 Bob Jr    George Jr		|
		 * 				|						|
		 * 				|_______________________|
		 */
		
		assertTrue(e2.isSibling(e7));
		assertTrue(e3.isSibling(e6));
		
		assertFalse(e8.isSibling(e1));
		assertFalse(e8.isSibling(e2));
		assertFalse(e8.isSibling(e3));
		assertFalse(e8.isSibling(e4));
		assertFalse(e8.isSibling(e5));
		assertFalse(e8.isSibling(e6));
		assertFalse(e8.isSibling(e7));
		assertFalse(e8.isSibling(e9));
		
		assertFalse(e4.isSibling(e1));
		assertFalse(e4.isSibling(e2));
		assertFalse(e4.isSibling(e3));
		assertFalse(e4.isSibling(e5));
		assertFalse(e4.isSibling(e6));
		assertFalse(e4.isSibling(e7));
		assertFalse(e4.isSibling(e8));
		assertFalse(e4.isSibling(e9));	
		
	}
	
	@Ignore
	public void ignoreTest() {
		
	}
}
