## 15 . 3Sum

------

#### [力扣链接](https://leetcode-cn.com/problems/3sum/)

Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]

Example 2:

Input: nums = []
Output: []

Example 3:

Input: nums = [0]
Output: []

## 分析
#### 排序+双指针

先将数组排序，之后有一层for循环，遍历数组，下标i从0开始，左指针从i+1开始，右指针从数组最后一个元素开始。这就相当于先选好一个元素，再用双指针的方法从其他元素中找出符合条件的元素，也就是直接变成两数之和了。

接下来如何移动lefe和right呢，如果nums[i]+nums[left]+nums[right]>0,说明三数之和大了，又数组是排过序1，则右指针向左移动，使三数之和变小。

如果 nums[i] + nums[left] + nums[right] < 0 说明 此时 三数之和小了，left 就向右移动，才能让三数之和大一些，直到left与right相遇为止。

去重见代码注释。

时间复杂度：O(n^2)。

```java
 public List<List<Integer>> threeSum(int[] nums) {// 总时间复杂度：O(n^2)
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);// O(nlogn)

        for ( int i = 0 ; i < nums.length ; i++ ) {
            int left = i+1;
            int right = nums.length-1;

            if (nums[i] > 0) return result;// 第一个数大于 0，后面的数都比它大，肯定不成立了
            if ( i > 0 && nums[i] == nums[i-1] ) continue;// 去掉重复情况 例如[-1,-1,1,2] 

            while ( left < right ) {
                while ( left < right && nums[left] + nums[i] + nums[right] < 0 ) left++;
                while ( left < right && nums[left] + nums[i] + nums[right] > 0 ) right--;

                if ( left < right && nums[left] + nums[i] + nums[right] == 0 ) {
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    left++;
                    right--;
                    // 现在要增加 left，减小 right，但是不能重复，比如: 
                    //[-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，
                    // 需要排除重复的 -1 和 3
                    while ( left < right && nums[left] == nums[left-1]) left++;
                    while ( left < right && nums[right] == nums[right+1]) right--;

                }
            }
        }
        return result;
    }
```
