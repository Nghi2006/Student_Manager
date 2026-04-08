import java.util.List;

public class Sort {

    public static void bubbleSort(List<Student> list, int criteria) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                float m1 = list.get(j).getMarks();
                float m2 = list.get(j + 1).getMarks();

                boolean shouldSwap = (criteria == 1) ? (m1 > m2) : (m1 < m2);

                if (shouldSwap) {
                    swap(list, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void insertionSort(List<Student> list, int criteria) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            Student key = list.get(i);
            int j = i - 1;

            while (j >= 0) {
                float currentMark = list.get(j).getMarks();
                boolean condition = (criteria == 1) ? (currentMark > key.getMarks()) : (currentMark < key.getMarks());

                if (condition) {
                    list.set(j + 1, list.get(j));
                    j--;
                } else {
                    break;
                }
            }
            list.set(j + 1, key);
        }
    }

    public static void quickSort(List<Student> list, int low, int high, int criteria) {
        if (low < high) {
            int pi = partition(list, low, high, criteria);
            quickSort(list, low, pi - 1, criteria);
            quickSort(list, pi + 1, high, criteria);
        }
    }

    private static int partition(List<Student> list, int low, int high, int criteria) {
        float pivot = list.get(high).getMarks();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            boolean condition = (criteria == 1) ? (list.get(j).getMarks() < pivot) : (list.get(j).getMarks() > pivot);

            if (condition) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private static void swap(List<Student> list, int i, int j) {
        Student temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}