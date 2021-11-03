package offer;

public class LeetCode2021_11_02 {

    /**
     * 二分查找某个数字出现的次数：
     * 找到首次出现数字的索引，找到最后一次出现数字的索引
     *   r-l + 1
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return rightIdx - leftIdx + 1;
        }
        return 0;
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 长度为n-1的等增数组中找到缺失的那个数字：
     * 可以将该数字看作 右子数组的首元素
     *
     * 因为单调递增，如果nums[m] = m 时，则没有缺失数字，移动下界到 l = m + 1
     * 如果 num[m] != m 时， 则已经缺失数字，移动上界 r = m-1
     * 直到 i = j 的下一次 i>j退出循环 i也即缺失元素
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums){
        int l =0, r = nums.length-1;
        while (l <= r){
            int m = (r+l)/2;
            if (nums[m] == m){
                l = m + 1;
            }else{
                r = m - 1;
            }
        }
        return l;
    }

}
