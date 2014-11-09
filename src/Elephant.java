/** An instance (i.e. object) maintains info about an elephant. 
 * @author Victor Chen
 * 
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Elephant {

	/** Instance Variables */
	private String e_name;		// Name given to this Elephant, a String of length > 0.
	private int e_birthYear; 	// year of birth (an int). Must be >= 2000.
	private int e_birthMonth;	// month of birth (an int). In range 1..12 with 1 being January, etc.
	private char e_gender;		// gender of this Elephant (a char). ÕMÕ means male and ÔFÕ means female.
	private Elephant e_Mother;	// mother (an Elephant). The object of class Elephant that is the mother of this objectÑnull if unknown
	private Elephant e_Father;	// father (an Elephant). The object of class Elephant that is the father of this objectÑnull if unknown.
	private int e_numOfChildren;// number of children of this Elephant.
	
	/** Constructor0: a no-arg constructor yields a system error message
	 *  Caller will be advised of proper contructor usage.
	 */
	public Elephant () {
		System.err.println("Usage: Elephant(String n, char g, int y, int m) \n"
				+ "Precondition: n has at least 1 character, y >= 2000, m is in 1..12, and g is 'M' for"
				+ "male or 'F' for female.");
	}
 	
	/** Constructor1: an instance with nickname n, gender g, birth year y, and birth month m.
	Its parents are unknown, and it has no children.
	Precondition: n has at least 1 character, y >= 2000, m is in 1..12, and g is 'M' for
	male or 'F' for female. */
	public Elephant(String n, char g, int y, int m)
	{
		assert (n.length() >= 1 && y >= 2000 && (m >= 1 && m <= 12) && (g == 'F' || g == 'M') );
		e_name = n;
		e_gender = g;
		e_birthYear = y;
		e_birthMonth = m;
		e_Mother = null;
		e_Father = null;
		e_numOfChildren = 0;
	}
	
	/** Constructor2: an elephant with nickname n, gender g, birth year y, birth month m, 
	 * mother ma, and father pa.
	 * Precondition: n has at least 1 character, y >= 2000, g is ÔFÕ for female or ÔMÕ
	 * for male, m is in 1..12, ma is a female, and pa is a male.
	 * 
	 */
	public Elephant(String n, char g, int y, int m, Elephant ma, Elephant pa) {
		this(n, g, y, m);
		//assert (ma.isFemale() && ma.getMom() == null && !pa.isFemale() && pa.getDad() == null );
		addDad(pa);
		addMom(ma);
	}
	
	/** Group A: Getter Functions */
	/** getName()	Return this elephant's nickname	*/
	public String getName() {
		return e_name;
	}
	/** getYear()	Return the year this elephant was born	*/
	public int getYear() {
		return e_birthYear;
	}
	/** getMonth 	Return the month this elephant was born */
	public int getMonth() {
		return e_birthMonth;
	}
	/** isFemale() 	Return the value of "this elephant is a female" */
	public boolean isFemale() {
		return (e_gender == 'F');
	}
	/** getMom() 	Return this elephant's mother (null if unknown) */
	public Elephant getMom() {
		return e_Mother;
	}
	/** getDad() 	Return this elephant's father (null if unknown) */
	public Elephant getDad() {
		return e_Father;
	}
	/** getChildren() Return the number of children of this elephant */
	public int getChildren () {
		return e_numOfChildren;
	}

	/** Group B: Setter Functions */
	private void addChildren() {
		e_numOfChildren++;
	}
	/** addMom(Elephant e)	Add e as this elephant's mother.
	Precondition: this elephantÕs mother is unknown and e is female. */
	public void addMom(Elephant ma) {
		assert(ma.isFemale());
		assert(this.getMom() == null);
		ma.addChildren();
		e_Mother = ma;
	}
	/** addDad(Elephant e) 	Add e as this elephant's father.
	Precondition: This elephant's father is unknown and e is male. */
	public void addDad(Elephant pa) {
		assert(!pa.isFemale());
		assert(this.getDad() == null);
		pa.addChildren();
		e_Father = pa;
	}
	/** isOlder(Elephant e)	Return value of "this elephant is older than e.Ó
	* Precondition: e is not null. 
	* 
	* Thought Process: 
	* 
	* 	Year Older?	-(no)-> Same Year? -(no)-> Return False
	* 	    |					|
	* 	   (yes)			  (yes)
	* 		|					|
	* 	 Return True		Month Older? -(no)-> Return False
	* 							| (yes)
	* 						Return True
	*/
	public boolean isOlder(Elephant e) {
		assert(e != null);
		return ( (getYear() < e.getYear() ? true : 
			(getYear() == e.getYear() ? (getMonth() < e.getMonth() ) : false ) ) ) ;		
	}
	/** isSibling(Elephant e)	Return value of "this Elephant and e are siblings.Ó 
	 *  (note: if e is null they can't be siblings, so false should be returned).
	 *  
	 *  Thought Process:
	 *  Questions:
	 *  A. Father Elephant is Null? 
	 *  B. Elephants have same Father?
	 *  C. Mother Elephant is Null?
	 *  D. Elephants have same Mother?
	 *  	________________________________
	 *  	|								|
	 *  	|  A ---(no)--> B ---(no)------>|
	 *		|  |			 \(yes)-> Return True
	 *		| (yes) 
	 *		|  |
	 *		|--C ---(no)--> D ---(yes)--> Return True
	 *		   |			|
	 *		  (yes)		   (no)
	 *		   |			|
	 *		Return False	Return False
	 *
	 */
	public boolean isSibling(Elephant e) {
						
		return ( (getDad() == null ?  ((getMom() == null) ? false 
				: (getMom() == e.getMom() ? true : false)) : 
			(getDad() == e.getDad() ? true 
			: (getMom() == null) ? false 
			: (getMom() == e.getMom() ? true : false) ) ) );
	}		
	
	/** Main Function */
	public static void main(String [] args) {
		Result result = JUnitCore.runClasses(ElephantTester.class);
		for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(result.wasSuccessful());
	}
}
