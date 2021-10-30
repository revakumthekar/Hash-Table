// --== CS400 File Header Information ==--
// Name: Reva Kumthekar
// Email: rkumthekar@wisc.edu
// Team: Red
// Group: BG
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: NA
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods defined in the classes
 * HashTableMap and the interface.
 * 
 * @author revakumthekar
 *
 */
public class TestHashTable {

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {

    System.out.println("test1: " + test1());
    System.out.println("test2: " + test2());
    System.out.println("test3: " + test3());
    System.out.println("test4: " + test4());
    System.out.println("test5: " + test5());
  }

  /**
   * Checks the correctness of the put method implemented in the HashTableMap class
   * 
   * @return true if the put method works as it should, false otherwise.
   */
  public static boolean test1() {

    HashTableMap<Integer, Integer> map1 = new HashTableMap<Integer, Integer>();
    // checking if a null key returns false like it should
    if (map1.put(null, 10) == true) {
      return false;
    }
    // checking if adding a key that is already in the hash table will return false like it should
    map1.put(5, 10);
    if (map1.put(5, 10) == true) {
      return false;
    }

    // checking if adding a key and integer is successfully added
    if (map1.put(4, 3) == false) {
      return false;
    }

    map1.put(7, 10);
    map1.put(2, 3);
    map1.put(8, 12);
    map1.put(9, 11);
    map1.put(12, 8);
    map1.put(10, 5);
    // checking if the loadFactor method works as it should
    if (map1.loadFactor() != .80) {
      return false;
    }
    map1.put(11, 3);
    // checking the resize method
    if (map1.loadFactor() != .45) {
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the get method implemented in the HashTableMap class
   * 
   * @return true if the get method works as it should, false otherwise.
   */
  public static boolean test2() {

    HashTableMap<Integer, Integer> map1 = new HashTableMap<Integer, Integer>();
    map1.put(5, 10);
    map1.put(4, 3);
    map1.put(6, 12);

    if (map1.get(4) != 3) {
      return false;
    }
    if (map1.get(5) != 10) {
      return false;
    }
    if (map1.get(6) != 12) {
      return false;
    }

    // checking if trying to get a key that does not exist throws an exception
    try {
      map1.get(7);
      return false;
    } catch (NoSuchElementException e) {

    }

    return true;
  }

  /**
   * Checks the correctness of the size method implemented in the HashTableMap class
   * 
   * @return true if the size method works as it should, false otherwise.
   */
  public static boolean test3() {

    HashTableMap<Integer, Integer> map1 = new HashTableMap<Integer, Integer>();
    map1.put(5, 10);
    map1.put(4, 3);
    map1.put(6, 12);
    map1.put(3, 11);

    if (map1.size() != 4) {
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the containsKey method implemented in the HashTableMap class
   * 
   * @return true if the containsKey method works as it should, false otherwise.
   */
  public static boolean test4() {

    HashTableMap<Integer, Integer> map1 = new HashTableMap<Integer, Integer>();
    map1.put(5, 10);
    map1.put(4, 3);
    map1.put(6, 12);
    map1.put(3, 11);


    if (map1.containsKey(3) == false) {
      return false;
    }
    if (map1.containsKey(2) == true) {
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of the remove method implemented in the HashTableMap class
   * 
   * @return true if the remove method works as it should, false otherwise.
   */
  public static boolean test5() {

    HashTableMap<Integer, Integer> map1 = new HashTableMap<Integer, Integer>();
    map1.put(5, 10);
    map1.put(4, 3);
    map1.put(6, 12);
    map1.put(3, 11);

    if (map1.remove(2) != null) {
      return false;
    }
    if (map1.remove(4) != 3) {
      return false;
    }

    return true;

  }


}
