## n数之和总结

------

### 力扣题目链接
- [1 . Two Sum](https://leetcode-cn.com/problems/two-sum/)
- [167 . Two Sum II - Input Array Is Sorted](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)
- [15 . 3Sum](https://leetcode-cn.com/problems/3sum/)
- [18 . 4Sum](https://leetcode-cn.com/problems/4sum/)
- [454 . 4Sum II](https://leetcode-cn.com/problems/4sum-ii/)

### 两种常用方法

#### 哈希表
一般哈希表都是用来快速判断一个元素是否出现在集合里，主要目的是降低时间复杂度（将时间复复杂度由O(n)将为O(1)），但空间复杂度会相应增加，其中无序数组是哈希表的主要条件，有序数组可以用双指针来降低时间复杂度，同时空间复杂度也会更低，所以当数组有序后，优先考虑双指针。

一般用数组，set，map作为哈希表，他们主要有一下区别：
数组作为哈希表是最节约资源的，如果题目限制了数值的大小，就可以用数组作为哈希表了。例如下面这些题目
- [242 . Valid Anagram](https://leetcode-cn.com/problems/valid-anagram/)
- [383 . Ransom Note](https://leetcode-cn.com/problems/ransom-note/)
- [438 . Find All Anagrams in a String](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/)
- [49 . Group Anagrams](https://leetcode-cn.com/problems/group-anagrams/)

如果题目没有限制数值的大小，就无法用数组来做哈希表了，而且如果哈希值比较少，特别分散，跨度特别大，会造成很多的空间浪费。这时可以用set作为哈希表，set通过HashFunction把数值映射到key，这个数值一般是通过hashCode特定的编码计算的。但不要小看这个耗时，数组量大的时候，差距还是很明显的，所以能用数组一般就不要用set了。

来说一说：使用数组和set来做哈希法的局限。

- 数组的大小是受限制的，而且如果元素很少，而哈希值太大会造成内存空间的浪费。
- set是一个集合，里面放的元素只能是一个key，而两数之和这道题目，不仅要判断y是否存在而且还要记录y的下标位置，因为要返回x 和 y的下标。所以set 也不能用。

map是一种<key, value>的结构，本题可以用key保存数值，用value在保存数值所在的下表。所以使用map最为合适。


哈希表也有一个重要的优化，就是遍历两遍哈希表和遍历一遍哈希表的区别。
以[两数之和](https://leetcode-cn.com/problems/two-sum/)为例讲解。
遍历两遍哈希表:将数组的每个元素放入哈希表，然后再去数组中寻找另一个元素。
遍历一遍哈希表:在哈希表中寻找第二个元素，再将第一个元素放入哈希表。
```java
	//两遍哈希表
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for ( int i = 0; i < nums.length; i++ ) {
            map.put(nums[i], i);
        }
        for ( int i = 0; i < nums.length; i++ ) {
            int complement = target - nums[i];
            //这里有一个小细节，不能是同一个数
            if ( map.containsKey(complement) && map.get(complement) != i  ) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
 
//一遍哈希表
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for ( int i = 0; i < nums.length; i++ ) {
            int complement = target - nums[i];
            if ( map.containsKey(complement) ) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

```

#### 双指针
对于n数之和题型，使用双指针必须保证数组是有序的，对于其他题型，数组可以是无序的。
对于[三数之和](https://leetcode-cn.com/problems/3sum/),[四数之和](https://leetcode-cn.com/problems/4sum/)，这些题目并不合适使用哈希法，因为在去重的操作中有很多细节需要注意。


对于[167 . Two Sum II - Input Array Is Sorted](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)，因为已经是有序的数组，我们可以缩短用二分法来缩短查找的范围，就是二分法缩小范围，双指针来查找，这样时间复杂度是最低的。

```
  因为num1 + num2 = target （num2 > num1）即num2 <= target - num[0]
  这时我们可以找出第一个target - num[0]的数所在的位置pos,然后使用双指针。
  p1=0，p2=pos
 
 例：
  array = {0 1 1 3 4}  target = 2  pos = 3 即从下标0找到下标为3结束。
    int pos = binarySearch(numbers, target - numbers[0]);
        int p1 = 0, p2 = pos;
        while (p1 < p2 && numbers[p1] + numbers[p2] != target) {
            while (p1 < p2 && numbers[p1] + numbers[p2] < target)
                ++p1;
            while (p2 > p1 && numbers[p1] + numbers[p2] > target)
                --p2;
        }
```



