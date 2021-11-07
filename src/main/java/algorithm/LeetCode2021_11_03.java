package algorithm;

import java.util.Arrays;

public class LeetCode2021_11_03 {
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        while (j < nums.length) {
            nums[j++] = 0;
        }
    }

    public int[] twoSumII(int[] numbers, int target) {
        int l = 0 , r = numbers.length-1;
        while (l < r){
            if (numbers[l] + numbers[r] > target){
                r--;
            }else if(numbers[l]+numbers[r] < target){
                l++;
            }else{
                return new int[]{l+1,r+1};
            }
        }
        return null;
    }
}
