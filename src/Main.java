// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
// Binary Search Algorithm

public class Main {
    public static void main(String[] args) {
        int[] arr=  {1,2,3,4,5,6,8,9};
        int target = 2;
        int result = BinarySearch(arr,target);
        System.out.println(result);
    }

    static int BinarySearch(int[] arr,int target) {
        int start= 0;
        int end= arr.length-1;

        while (start <= end) {
            int mid  = start + (end-start)/2;

            if (target < arr[mid]) {
                end  = mid-1;
            }
            else if (target > arr[mid]) {
                mid = start + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }
}