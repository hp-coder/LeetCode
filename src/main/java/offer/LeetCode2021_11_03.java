package offer;

public class LeetCode2021_11_03 {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int m = 0, n = matrix[0].length - 1;
        while (n >= 0 && m < matrix.length) {
            System.out.println(matrix[m][n]);
            if (matrix[m][n] > target) {
                n--;
            } else if (matrix[m][n] < target) {
                m++;
            } else {
                return true;
            }
        }
        return false;
    }

    public int minArray(int[] numbers) {
        int min = numbers[0], l = 1, r = numbers.length - 1;
        while (l <= r) {
            if (numbers[l] < numbers[r]) {
                min = Math.min(numbers[l++], min);
            } else {
                min = Math.min(numbers[r--], min);
            }
        }
        return min;
    }

    public char firstUniqChar(String s){
        if (s.length() == 1) return s.charAt(0);
        char[] arr = new char[26];
        for (int i = 0,j = s.length()-1; i <= j; i++,j--) {
            arr[s.charAt(i) - 'a']++;
            arr[s.charAt(j) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (arr[s.charAt(i)-'a'] == 1){
                return s.charAt(i);
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 2};
        String s = "z";
        LeetCode2021_11_03 code = new LeetCode2021_11_03();
        System.out.println("code.firstUniqChar(s) = " + code.firstUniqChar(s));
    }

}
