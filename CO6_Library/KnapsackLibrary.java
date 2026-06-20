package CO6_Library;

public class KnapsackLibrary {

    static int knapsack(int W, int wt[], int val[], int n) {

        int dp[][] = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {

            for (int w = 0; w <= W; w++) {

                if (i == 0 || w == 0)
                    dp[i][w] = 0;

                else if (wt[i - 1] <= w)

                    dp[i][w] = Math.max(
                            val[i - 1] + dp[i - 1][w - wt[i - 1]],
                            dp[i - 1][w]);

                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        return dp[n][W];
    }

    public static void main(String[] args) {

        int val[] = { 60, 100, 120 };
        int wt[] = { 10, 20, 30 };

        int capacity = 50;

        int result = knapsack(capacity, wt, val, val.length);

        System.out.println("LibraryHub - Resource Allocation using 0/1 Knapsack");

        System.out.println("\nAvailable Resources:");
        System.out.println("AI Journal      Value = 60   Cost = 10");
        System.out.println("DBMS Package    Value = 100  Cost = 20");
        System.out.println("DSA Package     Value = 120  Cost = 30");

        System.out.println("\nBudget Capacity = 50");

        System.out.println("\nMaximum Value Obtained = " + result);

        System.out.println("\nSelected Resources:");
        System.out.println("DBMS Package");
        System.out.println("DSA Package");

        System.out.println("\nResource Allocation Completed Successfully");
    }
}