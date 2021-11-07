package data_structure;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class LeetCode2021_11_04 {

    /**
     * 荷兰国旗问题
     * 单指针方式： 维护一个头部长度，第一次遍历0 到头部， 然后从头部开始 将1 移到0后面
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int ptr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int temp = nums[ptr];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < nums.length; i++) {
            if (nums[i] == 1) {
                int temp = nums[ptr];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }

    public void sortColor(int[] nums) {
        int num0 = 0, num1 = 0, num2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[num2++] = 2;
                nums[num1++] = 1;
                nums[num0++] = 0;
            } else if(nums[i]== 1){
                nums[num2++] = 2;
                nums[num1++] = 1;
            }else{
                nums[num2++] = 2;
            }
        }
    }

}
