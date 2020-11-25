// Comparable vs Comparator

// Comparable:

// Comparable is in java.lang
public class HDTV implements Comparable<HDTV> { 
    private int size;
    private String brand;
    public int getSize() {
        return size;
    }
    public String getBrand() {
        return brand;
    }

    // When you implements comparable, compareTo must be override
    public int compareTo(HDTV tv) {
        /* The stupid way:
        if (size < tv.size) {
            return -1;
        } else if (size > tv.size) {
            return 1;
        } else {
            return 0;
        }
        */
        return size - tv.size;
    }

}

////////////////////////////////////////////////////////////////////////

// Comparator:

// it is in java.util
import java.util.Comparator;
public class HDTV { // comparable is in java.lang
    private int size;
    private String brand;
    public int getSize() {
        return size;
    }
    public String getBrand() {
        return brand;
    }
}

// This requires to create a SizeComparator class that implements Comparator
class SizeComparator implements Comparator<HDTV> {
    public int compare(HDTV tv1, HDTV tv2) {
        return tv1.getSize() - tv2.getSize();
    }
}