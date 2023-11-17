import java.util.Scanner;

public class Test {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int x, n, p[], pp[], wt[], ta[], bt[], awt = 0, ata = 0;
        p = new int[10];
        pp = new int[10];
        bt = new int[10];
        wt = new int[10];
        ta = new int[10];

        System.out.println("Enter the number of processes: ");
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter burst time of process " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            System.out.println("Enter the priority: ");
            pp[i] = sc.nextInt();
            p[i] = i + 1;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (pp[j] < pp[j + 1]) {
                    // Swap priority
                    x = pp[j];
                    pp[j] = pp[j + 1];
                    pp[j + 1] = x;

                    // Swap burst time
                    x = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = x;

                    // Swap process ID
                    x = p[j];
                    p[j] = p[j + 1];
                    p[j + 1] = x;
                }
            }
        }

        wt[0] = 0;
        ta[0] = bt[0];
        ata += ta[0];

        for (int i = 1; i < n; i++) {
            wt[i] = ta[i - 1];
            awt += wt[i];
            ta[i] = wt[i] + bt[i];
            ata += ta[i];
        }

        System.out.println("\n Process  Priority  Burst time  Wait time  Turnaround time  ");
        for (int i = 0; i < n; i++) {
            System.out.println(" " + p[i] + "  \t  " + pp[i] + "  \t  " + bt[i] + "  \t  " + wt[i] + "  \t  " + ta[i]);
        }

        awt /= n;
        ata /= n;

        System.out.println("Average wait time is: " + awt);
        System.out.println("Average turnaround time is: " + ata);
    }
}
