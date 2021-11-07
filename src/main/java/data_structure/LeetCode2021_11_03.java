package data_structure;

import com.sun.source.tree.Scope;
import support.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LeetCode2021_11_03 {

    /**
     * 非空整数数组，又且仅有一个数是不重复的：
     * 位运算：异或
     * 异或特点：
     * 1、任何数和0异或，结果为原来的数
     * 2、任何数和自身异或，结果为0
     * 3、异或满足交换律结合律，也即 a^b^a = a^a^b  = b^(a^a) = b^0 = b;
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int i : nums) {
            single ^= i;
        }
        return single;
    }

    /**
     * 找众数：
     * 摩尔投票方法：
     * 将数组中的众数看做1，非众数部分数字看作-1；
     * 所有数的和必然>0
     * <p>
     * 例子：一对一，剩下的就是众数
     * 初始化候选元素为首元素，计数器为0
     * 当计数器为0时，切换当前候选元素为当前遍历元素，
     * 如果当前候选元素 == 当前遍历元素时， 计数器+1
     * 反之 计数器-1；
     * 最后计数器必然是大于0的，且最后的候选元素必然是众数
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 0;
        for (int i : nums) {
            if (count == 0) {
                candidate = i;
            }
            if (i == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        //顺序枚举每一个元素
        for (int first = 0; first < n; first++) {
            //每次枚举的值要和上一次不同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            //对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            //枚举b
            for (int second = first + 1; second < n; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

}


