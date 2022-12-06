package crece.ma.algorithm.sort;

public class CountSorter {

    public void sort(Integer[] array) {
        int max = maxValue(array);
        int[] temp = new int[max + 1];
        for (int i : array) {
            temp[i] += 1;
        }
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] > 0) {
                for (int j = 0; j < temp[i]; j++) {
                    array[index++] = i;
                }
            }
        }
    }

    private int maxValue(Integer[] array) {
        int max = 0;
        for (int i : array) {
            if (i < 0) {
                throw new IllegalArgumentException();
            }
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

}
