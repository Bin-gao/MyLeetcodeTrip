## 34.Find First and Last Position of Element in Sorted Array

#### [力扣链接](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

进阶：

你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？

```
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
```

二分法解决这道题，可以发挥数组是升序排列这个特点。
注意区间，这里是左闭右开。

同时，目标值在数组中的开始的位置就是在数组中寻找第一个大于等于target的下标，lower为true时开启
            目标值在数组中的结束的位置就是在数组中寻找第一个大于target的下标，lower为false时开启

```java
private int binarySearch(int[] nums , int target,boolean lower) {
    int left = 0;
    int right = nums.length;

    while ( left < right ) {
        int mid = left + (right - left)/2;

		
        if ( nums[mid] > target || (lower && nums[mid] >= target) ) {
            right = mid;
        }else{
            left = mid+1;
        }
    }
    return right;
}
```