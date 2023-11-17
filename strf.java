import java.util.Scanner;

public class SRTF {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes:");
        int n = sc.nextInt();
        int pid[] = new int[n];
        int at[] = new int[n];
        int bt[] = new int[n];
        int ct[] = new int[n];
        int wt[] = new int[n];
        int tat[] = new int[n];
        int f[] = new int[n];
        int k[] = new int[n];
        int st = 0;
        int tot = 0;
        float avgwt = 0, avgtat = 0;

        // Input process details
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.println("Enter process " + (i + 1) + " arrival time:");
            at[i] = sc.nextInt();
            System.out.println("Enter process " + (i + 1) + " burst time:");
            bt[i] = sc.nextInt();
            k[i] = bt[i];
            f[i] = 0;
        }

        // Implementation of Shortest Remaining Time First (SRTF) scheduling algorithm
        while (true) {
            int min = Integer.MAX_VALUE; // Initialize min to a large value
            int c = n; // Default value for c if no process is selected

            if (tot == n) {
                break;
            }

            for (int i = 0; i < n; i++) {
                // Check if the process has arrived, is not completed, and has the shortest remaining time
                if (at[i] <= st && f[i] == 0 && bt[i] < min) {
                    min = bt[i];
                    c = i;
                }
            }

            if (c == n) {
                st++;
            } else {
                bt[c]--;
                st++;

                if (bt[c] == 0) {
                    ct[c] = st;
                    f[c] = 1;
                    tot++;
                }
            }
        }

        // Calculate turnaround time and waiting time
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - k[i];
            avgwt += wt[i];
            avgtat += tat[i];
        }

        // Display results
        System.out.println("\nPID\tArrival Time\tBurst time\tCompletion time\tTurnaround time\tWaiting time\n");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + k[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        // Display average waiting time and average turnaround time
        System.out.println("Average waiting time: " + (float) (avgwt / n));
        System.out.println("Average turnaround time: " + (float) (avgtat / n));

        sc.close();
    }
}
