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
 * This class creates a hash table map with a generic key type and value type implements the MapADT
 * interface.
 * 
 * @author revakumthekar
 *
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  // instance variables
  private int capacity; // capacity of array
  private LinkedList<Node>[] pairs; // array of LinkedLists for the Hash Table

  /**
   * Constructor for a new HashTableMap object and initializes the array full of LinkedList objects
   * 
   * @param capacity is the capacity that the hash table can hold
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    pairs = (LinkedList<Node>[]) new LinkedList[capacity];
    for (int i = 0; i < capacity; i++) {
      pairs[i] = new LinkedList<Node>();
    }
  }

  /**
   * Default Constructor for a new HashTableMap object and initializes the array full of LinkedList
   * objects
   *
   */
  public HashTableMap() {
    this.capacity = 10;
    pairs = (LinkedList<Node>[]) new LinkedList[capacity];
    for (int i = 0; i < capacity; i++) {
      pairs[i] = new LinkedList<Node>();
    }
  }

  /**
   * Resize method that updates the Hash Table array when the load capacity is greater than or equal
   * to .85 by doubling the capacity and rehashing the keys
   */
  private void resize() {
    int newCap = 2 * capacity;
    capacity = newCap;
    // creating a temporary array for the old array
    LinkedList<Node>[] oldPairs = pairs;
    pairs = (LinkedList<Node>[]) new LinkedList[capacity];
    for (int i = 0; i < capacity; i++) {
      pairs[i] = new LinkedList<Node>();
    }
    for (int i = 0; i < oldPairs.length; i++) {
      if (oldPairs[i] != null) {
        for (int j = 0; j < oldPairs[i].size(); j++) {
          // rehashing the old keys
          int hCode = Math.abs(oldPairs[i].get(j).key.hashCode());
          int index = hCode % capacity;
          pairs[index].add(oldPairs[i].get(j));
        }

      }
    }
  }

  /**
   * This method calculates the loadFactor of the array by dividing the number of items in the array
   * and its capacity
   * 
   * @return the loadFactor of the array
   */
  public double loadFactor() {
    double lFactor = (double) size() / capacity;
    return lFactor;
  }

  /**
   * Private method to calculate the hash code of each key and where its index will be in the hash
   * table array
   * 
   * @param k the key passed to the method
   * @return the index of where the key will be stored in the array
   */
  private int indexFinder(KeyType k) {

    int hCode = Math.abs(k.hashCode());
    int index = hCode % capacity;
    return index;

  }

  /**
   * This method adds the key and value into the hash table array properly and calls the resize
   * method if necessary
   * 
   * @param key   is the key of the key-value pair
   * @param value is the value of the key-value pair
   * @return true if the method successfully adds the key-value pair to the array and false
   *         otherwise
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    if (key == null) {
      return false;
    }
    // If the key is already in there then it does not add it
    if (containsKey(key)) {
      return false;
    }


    Node newPair = new Node(key, value);
    pairs[indexFinder(key)].add(newPair);

    // checking if load factor is greater than or equal to 85%
    if (loadFactor() >= 0.85) {
      resize();
    }
    return true;

  }

  /**
   * This method finds and retrieves the value correlated to the key-value pair
   * 
   * @param key is the key of the key-value pair
   * @throws a NoSuchElementException if the key is not in the hash table
   * @return the value of the key-value pair that is found
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {

    for (int i = 0; i < pairs[indexFinder(key)].size(); i++) {
      if (pairs[indexFinder(key)].get(i).key.equals(key)) {
        return pairs[indexFinder(key)].get(i).value;
      }
    }

    throw new NoSuchElementException("Key is not in the hash table");
  }

  /**
   * This method calculates the size of the array by using a counter variable to count each
   * key-value pair in the array
   * 
   * @return the size, which is the number of key-value pairs
   */
  @Override
  public int size() {
    int counter = 0;
    for (int i = 0; i < pairs.length; i++) {
      if (pairs[i] != null) {
        counter = counter + pairs[i].size();
      }
    }

    return counter;
  }

  /**
   * This method searches the hash table array for the key that the method is looking for
   * 
   * @param key is the key of the key-value pair
   * @return true if the array contains the key that the method is looking for, false otherwise
   */
  @Override
  public boolean containsKey(KeyType key) {
    if (pairs[indexFinder(key)] == null) {
      return false;
    }

    for (int i = 0; i < pairs[indexFinder(key)].size(); i++) {
      if (pairs[indexFinder(key)].get(i).key.equals(key)) {
        return true;
      }
    }

    return false;
  }

  /**
   * This method finds and removes the value correlated to the key-value pair
   * 
   * @param key is the key of the key-value pair
   * @return the value of the key-value pair that is removed
   */
  @Override
  public ValueType remove(KeyType key) {

    for (int i = 0; i < pairs[indexFinder(key)].size(); i++) {
      if (pairs[indexFinder(key)].get(i).key.equals(key)) {
        return pairs[indexFinder(key)].remove(i).value;

      }
    }

    return null;
  }

  /**
   * This method clears the hash table array
   */
  @Override
  public void clear() {
    for (int i = 0; i < pairs.length; i++) {
      pairs[i].clear();
    }

  }

  /**
   * This class creates Node objects that are stored within the LinkedList objects and also holds
   * the information for the key-value pair
   * 
   * @author revakumthekar
   */
  class Node {
    KeyType key;
    ValueType value;

    public Node(KeyType key, ValueType value) 
    {
      this.key = key;
      this.value = value;
    }

  }


}


