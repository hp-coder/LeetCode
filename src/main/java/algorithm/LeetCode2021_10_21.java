package algorithm;

import java.util.*;

public class LeetCode2021_10_21 {


    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        char[] chars = s1.toCharArray();
        char[] chars1 = s2.toCharArray();
        Arrays.sort(chars);
        Arrays.sort(chars1);
        return Arrays.toString(chars).equals(Arrays.toString(chars1));
    }


    /**
     * 顺序序列移动0至数组尾部：
     * 非零元素前移，剩余位置补0
     *
     * @param nums
     */
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

    /**
     * 两数之和：
     * 双指针版本
     * 如果目标值大于两界之和，则增大下界
     * 如果目标值小于两界之和，则减小下界
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        for (int low = 0, high = numbers.length - 1; low < high; ) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) return new int[]{low + 1, high + 1};
            else if (sum > target) high--;
            else low++;
        }
        return null;
    }

    /**
     * 二分查找数组中元素位置：
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int l = 0;
        int h = nums.length - 1;
        while (l <= h) {
            int mid = (h - l) / 2 + l;
            if (nums[mid] > target) {
                h = mid;
            } else if (nums[mid] < target) {
                l = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 两个数组的交集：
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list1 = new ArrayList<>();
        for (int i : nums1) {
            list1.add(i);
        }
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i : nums2) {
            if (list1.contains(i)) {
                list2.add(i);
                list1.remove(Integer.valueOf(i));
            }
        }
        int[] ints = new int[list2.size()];
        int i = 0;
        for (Integer integer : list2) {
            ints[i++] = integer;
        }
        return ints;
    }

}
