## 18 . 4Sum

------

#### [力扣链接](https://leetcode-cn.com/problems/4sum/)

Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

    0 <= a, b, c, d < n
    a, b, c, and d are distinct.
    nums[a] + nums[b] + nums[c] + nums[d] == target

You may return the answer in any order.

 
Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

Example 2:

Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]

## 分析
注意 nums=[2，2，2，2，2，2]  target=8 这个输入

四数之和，和[三数之和](https://github.com/gg-dot/MyLeetcodeTrip/tree/master/0015.%203Sum)是一个思路，都是使用双指针法, 基本解法就是在[三数之和](https://github.com/gg-dot/MyLeetcodeTrip/tree/master/0015.%203Sum)的基础上再套一层for循环。

不过要注意一些细节

[三数之和](https://github.com/gg-dot/MyLeetcodeTrip/tree/master/0015.%203Sum)的双指针解法是一层for循环nums[i]为确定的数，然后循环内有left和right下表作为双指针，找到nums[i] + nums[left] + nums[right] == 0。

而四数之和是两层for循环nums[i]+nums[j]为确定值，依然是循环内有left和right下表作为双指针，找出nums[i] + nums[j] + nums[left] + nums[right] == target的情况，三数之和的时间复杂度是O(n^2)，四数之和的时间复杂度是O(n^3) 。

去重细节见下面代码

```java
 public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> result = new ArrayList<>();

        for ( int i = 0 ; i <= nums.length-4 ; i++ ) {
            if ( i > 0 && nums[i-1] == nums[i] ) continue;//去重 例如[-1,-1,-1,2,3,]
            for ( int j = i+1 ; j <= nums.length-3 ; j++ ) {
                if ( j > i+1 && nums[j] == nums[j-1] ) continue;//去重 例如[-1,-1,-1,2,3,]

                int left = j + 1;
                int right = nums.length-1;

                while ( left < right ) {
                    while ( left < right && nums[i] + nums[j] + nums[left] + nums[right] > target ) right--;
                    while ( left < right && nums[i] + nums[j] + nums[left] + nums[right] < target ) left++;

                    if ( left < right && nums[i] + nums[j] + nums[left] + nums[right] == target ) {
                        result.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        left++;
                        right--;

                        // 现在要增加 left，减小 right，但是不能重复，比如:
                        //[-2, -1, 0, 0, 3, 3], i = 0, j = 1 left = 2, right = 5, [-2, -1, 0,3] 的答案加入后，
                        // 需要排除重复的 0 和 3
                        while ( left < right && nums[left] == nums[left-1] ) left++;
                        while ( left < right && nums[right] == nums[right+1] ) right--;
                    }

                }

            }
        }

        return result;
```