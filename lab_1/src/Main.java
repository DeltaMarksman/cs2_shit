import java.nio.file.ClosedWatchServiceException;
import java.util.PriorityQueue;
import java.util.Scanner;  // Import the Scanner class


public class Main
{
    public static void main(String[] args)
    {
        // Priority Queue Min Type
        PriorityQueue<Integer> min_heap = new PriorityQueue<>();
        PriorityQueue<Integer> max_heap = new PriorityQueue<>();

        // Instantiate
        Scanner input = new Scanner(System.in);
        System.out.println("Enter input:\n");

        int num_inputs = input.nextInt();  // Read user input
        int[] number_list = new int[num_inputs];

        // Current median
        int median = 0;

        // Put into list
        for (int i = 0; i < num_inputs; i++) {
            number_list[i] = input.nextInt();

            // Insert into heap
            if (number_list[i] > median)    { max_heap.add(number_list[i]); }
            else                            { min_heap.add(number_list[i]); }

            // Get sizes
            int min_heap_size = min_heap.size();
            int max_heap_size = max_heap.size();

            // rebalance
            if (min_heap_size > max_heap_size + 1) {
                max_heap.add(min_heap.poll());
            } else if (max_heap_size > min_heap_size + 1) {
                min_heap.add(max_heap.poll());
            }

            // Find median
            if (min_heap_size == max_heap_size) {
                median = (min_heap.peek() + max_heap.peek())/2;
            } else if (min_heap_size > max_heap_size) {
                median = min_heap.peek();
            } else {
                median = max_heap.peek();
            }

            System.out.println(median);
        }
    }
}