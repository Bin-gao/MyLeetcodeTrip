package com.lbgao.leetcode.binary_search;

import java.util.Arrays;

/**
 * @Auther: lbgao
 * @Date: 2021/10/27 17:10
 */


//class Solution34 {
//    public int[] searchRange(int[] nums, int target) {
//        int[] result = new int[]{-1,-1};
//        if ( nums.length == 0 ) return result;
//        int leftIndex = 0;
//        int rightIndex = nums.length-1;
//
//        while ( nums[leftIndex] != nums[rightIndex] && leftIndex <= rightIndex ){
//
//
//            if ( nums[leftIndex] != target ){
//                leftIndex++;
//            }
//            if ( nums[rightIndex] != target ){
//                rightIndex--;
//            }
//        }
//
//        if ( nums[rightIndex] == target) {
//            result[0] = leftIndex;
//            result[1] = rightIndex;
//        }
//
//        return result;
//    }
//}

class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);//即为在数组中寻找第一个大于等于target的下标
        int rightIdx = binarySearch(nums, target, false) - 1;//即为在数组中寻找第一个大于target 的下标


        return nums;
    }

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
}
public class Sample34 {
    public static void main(String[] args) {
        int[] nums = new int[]{5,8,9,9,9,9,9,9,10};
        int target = 8;

        Arrays.stream(new Solution34().searchRange(nums,target)).forEach(s -> System.out.print(s + " "));
    }
}
