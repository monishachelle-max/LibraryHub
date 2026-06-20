package CO5_Library;

public class MergeSortLibrary {

    void merge(int arr[], int l, int m, int r) {

        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[l + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j])
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }

        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }

    void sort(int arr[], int l, int r) {
        if (l < r) {

            int m = (l + r) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    public static void main(String[] args) {

        int arr[] = { 45, 12, 89, 34, 23, 67, 10, 55 };

        MergeSortLibrary ob = new MergeSortLibrary();

        System.out.println("LibraryHub - Book Record Sorting using Merge Sort");
        System.out.println("\nLibrary Book IDs Before Sorting:");

        for (int num : arr)
            System.out.print(num + " ");

        ob.sort(arr, 0, arr.length - 1);

        System.out.println("\n\nLibrary Book IDs After Merge Sort:");

        for (int num : arr)
            System.out.print(num + " ");

        System.out.println("\n\nSorting Completed Successfully");
    }
}
