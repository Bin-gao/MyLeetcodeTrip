## 1 . Two Sum

------


####[力扣链接](https://leetcode-cn.com/problems/two-sum/)

给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

示例 2：

输入：nums = [3,2,4], target = 6
输出：[1,2]

示例 3：

输入：nums = [3,3], target = 6
输出：[0,1]

## 分析

1.暴力遍历，两层for循环查找，时间复杂度是O(n^2)

2.哈希法，由于题目没有顺序要求，可以使用haspmap作为哈希表，key为数值，value为数值下标，对于哈希法有两遍哈希和一遍哈希。

两遍哈希表：使用了两次迭代。在第一次迭代中, 将每个元素的值和它的索引添加到表中。然后, 在第二次迭代中, 检查每个元素所对应的目标元素（target−nums[i]）是否存在于表中。注意, 该目标元素不能是 nums[i]本身！也就是 map.get(complement) != i 的含义.

```java
public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            //小细节，元素不能重复
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

```

一遍哈希表：使用一次迭代，直接判断第二个元素是否在表中，再将第一个元素加入表中。可保证不会让 元素 和自己匹配, 因为在匹配时, x还未插入到哈希表中。

```java
public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int[] res = new int[2];
        for ( int i = 0 ; i < nums.length ; i++ ) {
            if ( map.containsKey(target - nums[i])) {
                res = new int[]{map.get(target - nums[i]),i};
                break;
            }
            map.put(nums[i],i);
        }
        return res;
    }
```


对于使用哈希表的算法, 有人提出了异议, HashMap的containsKey里面还有一个循环, 也就还是O(n^2), map还增加了空间复杂度和开销, 综合来看还是暴力法最为有效, 但是这个观点也有点问题: 这个containsKey里的循环, 只有冲突了才会进入, 同时如果冲突频繁, 会改用getTreeNode方法去获取值, getTreeNode是从一棵红黑树中获取值, 时间复杂度顶多O(logN), 综合来看, 还是降低了时间复杂度.
