## 454 . 4Sum II

------

#### [力扣链接](https://leetcode-cn.com/problems/4sum-ii/)

Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:

    0 <= i, j, k, l < n
    nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

Example 1:

Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
Output: 2
Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0

Example 2:

Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
Output: 1

## 分析

#### 哈希法
这道题是四个独立的数组，只要找到A[i]+B[j]+C[k]+D[l] =0,即可。相对于18.四数之和简单了一点。

解题思路：
   1.  定义一个HashMap，key为a，b之和，value为a,b之和出现的次数。
   2.  两层for循环遍历A和B数组，将元素和和出现的次数放入map中。
   3.  定义一个变量res，用来统计结果。
   4.  两层for循环遍历C和D数组，定义tmp为他们元素和，判断map中是否有键值(0-tmp）即map.containsKey(0-tmp)，如果有获取map.get(0-tmp)，同时res +=map.get(0-tmp)
   5.  最后返回res。


代码如下：
```java
//    时间复杂度：O(n2)
//    空间复杂度：O(n2)，即为哈希映射需要使用的空间
 public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();//key:a+b的数值，value:a+b数值出现的次数
        
        // 遍历大A和大B数组，统计两个数组元素之和，和出现的次数，放到map中
        for ( int i = 0 ; i < nums1.length ; i++ ) {
            for ( int j = 0 ; j < nums2.length ; j++ ) {
                int temp = nums1[i] + nums2[j];
                if ( map.containsKey(temp)) {
                    int count = map.get(temp);
                    map.put(temp,++count);
                }else {
                    map.put(temp,1);
                }
            }
        }
        // 统计a+b+c+d = 0 出现的次数
        int result = 0;
        // 在遍历大C和大D数组，找到如果 0-(c+d) 在map中出现过的话，就把map中key对应的value也就是出现次数统计出来。
        for ( int i = 0 ; i < nums3.length ; i++ ) {
            for ( int j = 0 ; j < nums4.length ; j++ ) {
                int temp = nums3[i] + nums4[j];
                if ( map.containsKey(0-temp) ) {
                    result += map.get(0-temp);
                }
            }
        }
        System.out.println(result);
        return result;
    }
```
