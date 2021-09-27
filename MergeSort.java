import java.util.Arrays;
import java.util.stream.Stream;

interface SortComparator{
    boolean compare(int a, int b);
}

class Main{
    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort(args);
    }
}

public class MergeSort {
    private String sortBy;
    private int[] inputArr;
    private int[] outArr;
    private int comparison = 0;
    private int swaps = 0;
    private long execTime = 0L;

    public int getComparison() {
        return comparison;
    }

    public int getSwaps() {
        return swaps;
    }

    public MergeSort() {
    }

    public MergeSort (String[] input) {
        getInput(input);

        long startTime = System.nanoTime();

        if(sortBy.equals("desc")){
            sort(outArr,0,outArr.length-1,(int a , int b) -> a < b);
        }else {
            sort(outArr,0,outArr.length-1,(int a , int b) -> a > b);
        }
        execTime = System.nanoTime() - startTime;
        printRes();
    }

    private void printRes() {
        System.out.println("Method of sort: Merge Sort");
        System.out.println("Swap: " + swaps);
        System.out.println("Comparison: " + comparison);
        System.out.println("Execution time: " + execTime + " nano sec");
        System.out.println("Execution time: " + execTime/1000000 + " milli sec");
        System.out.println("input arr: " + Arrays.toString(inputArr));
        System.out.println("sorted arr: " + Arrays.toString(outArr));
    }

    public void getInput(String[] input) {
        try {
            if (input[0].equals("asc")) {
                sortBy = "asc";
            } else if (input[0].equals("desc")) {
                sortBy = "desc";
            }else {
                System.out.println("invalid sort by");
                System.exit(0);
            }
            makeIntArrayOfString(input[1]);
            if (input[2].equals("ms")) {
                return;
            } else {
                System.out.println("it`s only Merge Sort");
                System.exit(0);
            }
        } catch(Exception e) {
            System.out.println("Invalid input!!!");
            System.exit(0);
        }
    }

    private void makeIntArrayOfString(String input) {
        String[] arrTemp = input.split(",");
        try {
            inputArr = Stream.of(arrTemp).mapToInt(Integer::parseInt).toArray();
            outArr = Arrays.copyOf(inputArr,inputArr.length);
        } catch (NumberFormatException e){
            System.out.println("invalid numbers");
            System.exit(0);
        }
    }

    public void sort(int[] arr, int start, int end, SortComparator comparator){
        if(start < end){
            int middlePoint = (start + end) / 2;
            sort(arr, start, middlePoint, comparator);
            sort(arr, middlePoint + 1, end, comparator);
            merge(arr, start, middlePoint, end, comparator);
        }
    }

    private void merge(int[] arr, int start, int middlePoint, int end, SortComparator comparator){
        int lengthLeft = middlePoint - start + 1;
        int lengthRight = end - middlePoint;

        int[] leftTemp = new int[lengthLeft + 1];
        int[] rightTemp = new int[lengthRight + 1];

        for (int i = 0; i < lengthLeft; i++) {
            leftTemp[i] = arr[start + i];
        }
        for (int j = 0; j < lengthRight; j++) {
            rightTemp[j] = arr[middlePoint + j + 1];
        }

        int i = 0, j = 0, k = start;
        while (i < lengthLeft && j < lengthRight) {
            if (comparator.compare(leftTemp[i],rightTemp[j])) {
                arr[k] = leftTemp[i];
                i++;
                swaps++;
            } else {
                arr[k] = rightTemp[j];
                j++;
                swaps++;
            }
            comparison++;
            k++;
        }

        while (i < lengthLeft) {
            arr[k] = leftTemp[i];
            swaps++;
            i++;
            k++;
        }

        while (j < lengthRight) {
            arr[k] = rightTemp[j];
            swaps++;
            j++;
            k++;
        }

    }

}
