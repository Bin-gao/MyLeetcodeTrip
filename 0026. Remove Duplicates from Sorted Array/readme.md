## 26 . Remove Duplicates from Sorted Array

------

#### [力扣链接](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)
Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}

If all assertions pass, then your solution will be accepted.

 

Example 1:

Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

Example 2:

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

## 分析
和[27 . Remove Element](https://leetcode-cn.com/problems/remove-element/)不同
虽然在双指针的使用上, 两者的思想是一致的, 但是具体的使用过程还是有点区别的.

在 [27 . Remove Element](https://leetcode-cn.com/problems/remove-element/) 中, 我们 需要比较的对象 是题目中的给定值, 而且是唯一固定的, 从头到尾都是没有任何变化的.

但是在本题中, 我们 需要比较的对象 不再是某个固定的元素了, 而是 快指针指向位置的前一个元素和当前元素的比较, 因为这样比较, 才能确定两个相邻的元素是否为 重复元素, 从而决定是否要保留当前元素, 这是两题最大的不同点.

还有一个小细节注意下, 因为 移除元素 中被移除的元素可能是任意一个位置的元素, 所以两个指针的下标都是 从0开始 的. 但是在本题中, 数组的第一个元素一定是被保留下来的元素, 所以我们直接从 第二个元素 开始遍历就可以了, 也就是 双指针的下标都是从1开始的.

```java
	// 删除有序数组的重复项
//时间复杂度O(n)
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1; // 删除重复元素之后也至少剩下一个元素
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) { // 说明nums[fast] 和之前的元素都不同
                nums[slow] = nums[fast];        // nums[fast] 的值复制到 nums[slow]
                ++slow;
            }
            ++fast;
        }
        return slow; // 从nums[0]到nums[slow−1]的每个元素都不相同
    }
}

```
这里可以看下用双指针解决移除元素类题目的总结[移除元素总结](https://github.com/gg-dot/MyLeetcodeTrip/tree/master/attainment/%E7%A7%BB%E9%99%A4%E5%85%83%E7%B4%A0%E6%80%BB%E7%BB%93)。

