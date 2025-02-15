import java.util.*;

public class NGE {
    public static void main(String[] args) {
        int arr[] = {4, 5, 2, 10, 8};
        int result[] = nextGreaterElement(arr);
        System.out.println(Arrays.toString(result));
    }

    public static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        return result;
    }
}
