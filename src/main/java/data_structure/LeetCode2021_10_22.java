package data_structure;

import java.util.*;

public class LeetCode2021_10_22 {

    /**
     * 有序数组中。两数之和
     * 保存减数和索引，用结果查询减数
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int result = target - numbers[i];
            if (map.containsKey(result)) {
                arr[0] = map.get(result) + 1;
                arr[1] = i + 1;
                return arr;
            }
            map.put(numbers[i], i);
        }
        return arr;
    }

    /**
     * 两数之和（有序数组）：
     * 双指针
     * 从两界开始遍历，如果和大于目标值则下移上界，如果和小于目标值则上移下界
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        for (int low = 0, high = numbers.length - 1; low < high; ) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) return new int[]{low + 1, high + 1};
            else if (sum > target) high--;
            else low++;
        }
        return null;
    }

    /**
     * 翻转一个单词内的所有字母
     *
     * @param s
     */
    public void reverseString(char[] s) {
        for (int low = 0, high = s.length - 1; low < high; ) {
            char temp;
            temp = s[low];
            s[low++] = s[high];
            s[high--] = temp;
        }
        System.out.println("Arrays.toString(s) = " + Arrays.toString(s));
    }

    /**
     * 翻转字符串： "let's do this"
     * 局部翻转字符串
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int low = 0;
        for (int i = 0; i <= chars.length; i++) {
            if (i == chars.length || chars[i] == ' ') {
                reverse(chars, low, i - 1);
                low = 1 + i;
            }
        }
        return new String(chars);
    }

    /**
     * 使用方法对于基本类型数据，传值不传引用的方式保持low和high的在主方法中的值
     *
     * @param chars
     * @param low
     * @param high
     */
    private void reverse(char[] chars, int low, int high) {
        while (low < high) {
            char temp;
            temp = chars[low];
            chars[low++] = chars[high];
            chars[high--] = temp;
        }
    }

    /**
     * 移动0的元素至队尾，且保持非零元素的相对顺序：
     * 把非零元素向前移动至头，然后补0
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
     * 数组是否包含重复元素：
     * set集合add重复元素时返回false判断
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        final HashSet<Integer> integers = new HashSet<>();
        for (int num : nums) {
            if (!integers.add(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 最大子序和：
     * 动态规划：假设sum<=0，对后续的序列的值增加没有帮助，就舍弃
     * 如果sum为正数则跟下一个遍历的数相加，跟已有序列和比较，保留较大值
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            int num = nums[0];
            return num;
        }
        int sum = 0;
        int sequence = nums[0];
        for (int num : nums) {
            if (sum <= 0) {
                sum = num;
            } else {
                sum += num;
            }
            sequence = Math.max(sequence, sum);
        }
        return sequence;
    }

    /**
     * 合并两个有序数列，且nums1长度为m+n：
     * 以n遍历 然后倒序遍历比较较大值，倒序填充nums1
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int end = m-- + --n;
        while (n >= 0) {
            nums1[end--] = m >= 0 && nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
    }

    /**
     * 两个数组的交集：
     * 转为list，第二个数组转list时，判断元素是否在list1中，
     * 如果存在就删除list1中的元素，list2做add，最后list2保留的元素就是交集
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

    /**
     * 买股票的最佳时机：
     * 动态规划
     * 假设min为今天之前买入的最小值，
     * 则计算max为今天买入的利润和历史最大利润中的最大值
     * min为今天买入价格和历史买入最低价的最小值
     * 顺序遍历
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int min = prices[0], max = 0;
        for (int i = 0; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    /**
     * 杨辉三角：
     * 使用二维数组保存历史数据
     * 遍历到i行时，就有i列数据，i列数据中 0 和 i列都为1，其他数据由上一行的前一列和本列数据相加得到
     * 1
     * 11
     * 121
     * 1331
     * 14641
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        int[][] store = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            List<Integer> sub = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    store[i][j] = 1;
                } else {
                    store[i][j] = store[i - 1][j - 1] + store[i - 1][j];
                }
                sub.add(store[i][j]);
            }
            list.add(sub);
        }
        return list;
    }

    public static void main(String[] args) {
        LeetCode2021_10_22 leetCode = new LeetCode2021_10_22();
    }
}
