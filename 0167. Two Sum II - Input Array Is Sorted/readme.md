## 167 . Two Sum II - Input Array Is Sorted

------

#### [力扣链接](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)

给定一个已按照 非递减顺序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。

函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。

你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。


示例 1：

输入：numbers = [2,7,11,15], target = 9
输出：[1,2]
解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。

示例 2：

输入：numbers = [2,3,4], target = 6
输出：[1,3]

示例 3：

输入：numbers = [-1,0], target = -1
输出：[1,2]

## 分析
#### 双指针
  数组是有序的优先考虑双指针，left指向数组第一个元素，right指向数组最后一个元素。  则在迭代的过程中只有3种可能:
   	  1.  如果num[left]+num[right]==target,则找到了返回对应的结果。
   	  2.  如果num[left]+num[right]>target,则说明num[left]+num[right]偏大，需要减小，由于数组是有序的，所以右指针左移使num[left]+num[right]减小，即right--
   	  3.  如果num[left]+num[right]>target,则说明num[left]+num[right]偏小，需要增大，由于数组是有序的，所以左指针右移使num[left]+num[right]增大，即left++
  代码如下：
  ```java
   public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length-1;

        while ( left < right && numbers[left] + numbers[right] != target ) {
            while ( left < right && numbers[left] + numbers[right] > target ) right--;
            while ( left < right && numbers[left] + numbers[right] < target ) left++;
        }
        return new int[]{left+1,right+1};
    }
  ```

  时间复杂度O(n)
  空间复杂度O(1)

------

#### 二分法+双指针
  由于数组是有序的，右指针没必要从最后一个元素开始，可以利用二分法缩短查找范围。
  假设num1 + num2 == target 则num2 == target-num1,又num1 >= num[0],则num2 <= target - num[0],即我们可以找出第一个大于等于target-num[0]的数所在的位置作为右指针开始的位置。

 ```java
   public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int pos = binarySearch(numbers, target - numbers[0]);
        int p1 = 0, p2 = pos;
        while (p1 < p2 && numbers[p1] + numbers[p2] != target) {
            while (p1 < p2 && numbers[p1] + numbers[p2] < target)
                ++p1;
            while (p2 > p1 && numbers[p1] + numbers[p2] > target)
                --p2;
        }
        res[0] = p1 + 1;
        res[1] = p2 + 1;
        return res;
    }
 ```

 时间复杂度O(logn)
 空间复杂度O(1)

  

