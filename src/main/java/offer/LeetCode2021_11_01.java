package offer;

public class LeetCode2021_11_01 {

    /**
     * 替换空格
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                sb.append("%20");
            } else {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    public String reverseLeftWords(String s, int n) {
        char[] arr = s.toCharArray();
        int low = 0, high = arr.length - 1;
        while (low < high) {
            char temp = arr[low];
            arr[low++] = arr[high];
            arr[high--] = temp;
        }
        low = arr.length-n; high = arr.length-1;
        while(low < high) {
            char temp = arr[low];
            arr[low++] = arr[high];
            arr[high--] = temp;
        }
        low = 0 ; high = arr.length-n-1;
        while(low < high) {
            char temp = arr[low];
            arr[low++] = arr[high];
            arr[high--] = temp;
        }
        return String.valueOf(arr);
    }

}
