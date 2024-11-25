import java.util.Arrays;

public class SortedList {
    private String[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public SortedList() {
        elements = new String[DEFAULT_CAPACITY];
        size = 0;
    }

    // Custom binary search implementation
    public int binarySearch(String target) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int comparison = target.compareTo(elements[mid]);

            if (comparison == 0) {
                return mid; // Element found
            } else if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // Returns the index where the element would be inserted
        return -(left + 1);
    }

    public void add(String element) {
        // Ensure capacity
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }

        // Find insertion point using binary search
        int insertIndex = binarySearch(element);

        // If negative, convert to actual insertion index
        if (insertIndex < 0) {
            insertIndex = -(insertIndex + 1);
        }

        // Shift elements to make room
        System.arraycopy(elements, insertIndex, elements, insertIndex + 1, size - insertIndex);
        elements[insertIndex] = element;
        size++;
    }

    public String[] getElements() {
        return Arrays.copyOf(elements, size);
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]).append("\n");
        }
        return sb.toString();
    }
}