// --== CS400 File Header Information ==--
// Name: Reva Kumthekar
// Email: rkumthekar@wisc.edu
// Team: Red
// Group: BG
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: NA
import java.util.NoSuchElementException;

public interface MapADT<KeyType, ValueType> {

    public boolean put(KeyType key, ValueType value);
    public ValueType get(KeyType key) throws NoSuchElementException;
    public int size();
    public boolean containsKey(KeyType key);
    public ValueType remove(KeyType key);
    public void clear();
    
}