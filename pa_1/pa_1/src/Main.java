/* COP 3503C Assignment 1
This program is written by: Daniel Vu */

import java.util.Scanner;


void main() {
    // Take user input
    Scanner scanner = new Scanner(System.in);

    // Loop through cases
    int cases = scanner.nextInt();
    for (int case_index = 0; case_index < cases; case_index++) {
        int sorted = scanner.nextInt();


        // Loop through each case's game and store the number in an array
        int num_games = scanner.nextInt();
        int[] games = new int[num_games];
        for (int game = 0; game < num_games; game++) {
            games[game] = scanner.nextInt();
        }


        // Get the target
        int target = scanner.nextInt();


        // Loop through the games array if the array is unsorted
        if (sorted == 0) {
            HashSet<Integer> set = new HashSet<>();
            boolean found = false;

            for (int i = 0; i < num_games; i++) {
                int couple = target - games[i];

                if (set.contains(couple)) {
                    found = true;
                    System.out.println("Test case #" + case_index + ": Spend " + target + " points by playing games with " + couple + " points and " + games[i] + " points.");
                    break;
                }

                set.add(games[i]);
            }

            if (!found) {
                System.out.println("Test case #" + case_index + ": No way you can spend exactly " + target + " points.");
            }
        } else {
            int left = 0;
            int right = num_games - 1;
            boolean found = false;

            while (left < right) {
                int sum = games[left] + games[right];

                if (sum == target) {
                    System.out.println("Test case #" + case_index + ": Spend " + target + " points by playing games with " + games[left] + " points and " + games[right] + " points.");
                    found = true;
                    break;
                }
                else if (sum < target) {
                    left++;
                }
                else {
                    right--;
                }
            }

            if (!found) {
                System.out.println("Test case #" + case_index + ": No way you can spend exactly " + target + " points.");
            }
        }
    }
}
