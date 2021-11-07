package algorithm;

public class LeetCode2021_11_04 {

    public void reverseString(char[] s) {
        int l = 0, h = s.length - 1;
        while (l < h) {
            char temp = s[l];
            s[l++] = s[h];
            s[h--] = temp;
        }
    }

    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int l = 0;
        for (int i = 0; i <= arr.length; i++) {
            if (i == arr.length || arr[i] == ' '){
                reverseString(arr,l,i-1);
                l = i+1;
            }
        }
        return String.valueOf(arr);
    }

    private void reverseString(char[]s , int l, int h){
        while (l < h) {
            char temp = s[l];
            s[l++] = s[h];
            s[h--] = temp;
        }
    }

    public static void main(String[] args) {
        LeetCode2021_11_04 code = new LeetCode2021_11_04();
        System.out.println("code.reverseWords(\"let's do this\") = " + code.reverseWords("let's do this"));
    }
}
