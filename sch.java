import java.util.Scanner;

public class SCH {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int ct[] = new int[10];     // completion times
        int ta[] = new int[10];     // turn around times
        int wt[] = new int[10];     // waiting times
        int temp, count = 0, i;
        float avgwt = 0, avgta = 0;
        int pid[] = new int[10];   // process ids
        int ar[] = new int[10];     // arrival times
        int bt[] = new int[10];
        int rem_bt[] = new int[10];
        int qt, sq = 0;

        float awt = 0, atat = 0;

        int ch;
        System.out.println("1.FCFS");
        System.out.println("2.Round Robin");
        System.out.println("Enter the Choice:");
        ch = sc.nextInt();
        switch (ch) {
            case 1:
                System.out.println("Enter the number of processes: ");
                int n = sc.nextInt();
                for (i = 0; i < n; i++) {
                    System.out.println("Enter process P" + (i + 1) + " arrival time: ");
                    ar[i] = sc.nextInt();
                    System.out.println("Enter process P" + (i + 1) + " burst time: ");
                    bt[i] = sc.nextInt();
                    pid[i] = i + 1;
                }

                for (i = 0; i < n; i++) {
                    for (int j = 0; j < n - (i + 1); j++) {
                        if (ar[j] > ar[j + 1]) {
                            temp = ar[j];
                            ar[j] = ar[j + 1];
                            ar[j + 1] = temp;
                            temp = bt[j];
                            bt[j] = bt[j + 1];
                            bt[j + 1] = temp;
                            temp = pid[j];
                            pid[j] = pid[j + 1];
                            pid[j + 1] = temp;
                        }
                    }
                }

                // Finding completion times
                for (i = 0; i < n; i++) {
                    if (i == 0) {
                        ct[i] = ar[i] + bt[i];
                    } else {
                        if (ar[i] > ct[i - 1]) {
                            ct[i] = ar[i] + bt[i];
                        } else {
                            ct[i] = ct[i - 1] + bt[i];
                        }
                    }
                    ta[i] = ct[i] - ar[i];          // turnaround time= completion time- arrival time
                    wt[i] = ta[i] - bt[i];          // waiting time= turnaround time- burst time
                    avgwt += wt[i];               // total waiting time
                    avgta += ta[i];               // total turnaround time
                }
                System.out.println("\npid  arrival  burst  complete turn waiting");
                for (i = 0; i < n; i++) {
                    System.out.println(pid[i] + "  \t " + ar[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
                }
                sc.close();
                System.out.println("\nAverage waiting time: " + (avgwt / n));     // printing average waiting time.
                System.out.println("Average turnaround time:" + (avgta / n));    // printing average turnaround time

                break;
            case 2:
                System.out.print("Enter the number of processes= ");
                n = sc.nextInt();
                System.out.print("Enter the burst time of the process\n");
                for (i = 0; i < n; i++) {
                    System.out.print("Process" + i + " = ");
                    bt[i] = sc.nextInt();
                    rem_bt[i] = bt[i];
                }
                System.out.print("Enter the quantum time: ");
                qt = sc.nextInt();
                while (true) {
                    for (i = 0, count = 0; i < n; i++) {
                        temp = qt;
                        if (rem_bt[i] == 0) {
                            count++;
                            continue;
                        }
                        if (rem_bt[i] > qt) {
                            rem_bt[i] = rem_bt[i] - qt;
                        } else if (rem_bt[i] >= 0) {
                            temp = rem_bt[i];
                            rem_bt[i] = 0;
                        }
                        sq = sq + temp;
                        tat[i] = sq;
                    }
                    if (n == count)
                        break;
                }
                System.out.print("--------------------------------------------------------------------------------");
                System.out.print("\nProcess\t      Burst Time\t       Turnaround Time\t          Waiting Time\n");
                System.out.print("--------------------------------------------------------------------------------");
                for (i = 0; i < n; i++) {
                    wt[i] = tat[i] - bt[i];
                    awt = awt + wt[i];
                    atat = atat + tat[i];
                    System.out.print("\n " + (i + 1) + "\t " + bt[i] + "\t\t " + tat[i] + "\t\t " + wt[i] + "\n");
                }
                awt = awt / n;
                atat = atat / n;
                System.out.println("\nAverage waiting Time = " + awt + "\n");
                System.out.println("Average turnaround time = " + atat);
                break;
        }
    }
}
