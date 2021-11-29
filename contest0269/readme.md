## 第 269 场周赛

[5938 . Find Target Indices After Sorting Array](https://leetcode-cn.com/problems/find-target-indices-after-sorting-array/)

[2090 . K Radius Subarray Averages](https://leetcode-cn.com/problems/k-radius-subarray-averages/)

[2091 . Removing Minimum and Maximum From Array](https://leetcode-cn.com/problems/removing-minimum-and-maximum-from-array/)

[2092 . Find All People With Secret](https://leetcode-cn.com/problems/find-all-people-with-secret/)

## 分析

[5938 . Find Target Indices After Sorting Array](https://leetcode-cn.com/problems/find-target-indices-after-sorting-array/)
我们首先的对数组排序，之后再遍历数组中的所有元素，并按顺序记录等于target的元素的下标。这样可以保证下标元素是递增的。
最后，如果下标数组存在，我们作为答案返回，反之，返回空数组即可。

```java
//时间复杂度度O(nlogn)
//空间复杂度O(logn)即为排序的栈空间开销。
public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);

        for ( int i = 0 ; i < n ; i++ ) {
            if ( target == nums[i] ) {
                res.add(i);
            }
        }

        return res;
    }
```
还有一个思路，可以不对原始数组排序来构造下标数组。

在排序后数组中，这些数值等于 target 的元素的下标（如果存在）一定是连续的。因此，我们可以通过寻找目标下标的左边界（即最小值，如果存在，下同）和目标下标的数量来构造目标下标数组。

由于数组是升序排序的，数值等于 target 的元素一定在数值小于 target 的元素的右侧，因此目标下标的左边界即为数组中数值小于 target 的元素数量。而目标下标的数量即为数组中数值等于 target的元素数量。

```java
 public static List<Integer> targetIndices(int[] nums, int target) {
 //时间复杂度度O(n)
 //空间复杂度O(1)。
        List<Integer> res = new ArrayList<>();
        int n = nums.length;

        int l = 0;//记录小于target的个数
        int e = 0;//记录等于target的个数
        for ( int i = 0 ; i < n ; i++ ) {
            if ( nums[i] < target ) {
                l++;
            }
            if ( nums[i] == target ) {
                e++;
            }
        }

        for ( int i = l ; i < l + e ; i++ ) {
            res.add(i);
        }
        return res;
    }
```
[2090 . K Radius Subarray Averages](https://leetcode-cn.com/problems/k-radius-subarray-averages/)

滑动窗口的题目。

```java
class Solution {
 //时间复杂度度O(n)
 //空间复杂度O(1)
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result,-1);

        if ( n < 2 * k + 1 ) return result;

        long window_sum = 0L;//记得为long，int会越界。
        for (int i = 0; i < 2 * k + 1; i ++)
        {
            window_sum += nums[i];
        }
        
       int i = k;
        for ( ; i + k < n; ) {
            result[i] = (int)(window_sum/(2*k+1));

            //弹左端
            if ( i - k >= 0 ) {
                window_sum -= nums[i-k];
            }
            //进右端
            if ( i + k + 1 < n ) {
                window_sum += nums[i+k+1];
            }
            i++;
        }
        return result;
    }
}
```
[2091 . Removing Minimum and Maximum From Array](https://leetcode-cn.com/problems/removing-minimum-and-maximum-from-array/)

```java
class Solution {
    public int minimumDeletions(int[] nums) {
        int length = nums.length;
        int[] index = new int[2];

        for ( int i = 1 ; i < nums.length ; i++ ) {
            if ( nums[index[0]] < nums[i] ) index[0] = i;
            if ( nums[index[1]] > nums[i] ) index[1] = i;
        }

        int minTimes = Math.min(index[0]+1,nums.length-index[0]);
        int maxTimes = Math.min(index[1]+1,nums.length-index[1]);

        return Math.min(minTimes+maxTimes,Math.min(Math.max(nums.length-index[0],nums.length-index[1]),Math.max(index[0],index[1])+1));


    }
}
```

