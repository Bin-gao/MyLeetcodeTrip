## 移除元素总结

------

### 力扣题目链接
- [26 . Remove Duplicates from Sorted Array](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)
- [27 . Remove Element](https://leetcode-cn.com/problems/remove-element/)
- [80 . Remove Duplicates from Sorted Array II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/)

### 双指针
这3道题目都是关于数组中移除元素的，其中[26 . Remove Duplicates from Sorted Array](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)和[80 . Remove Duplicates from Sorted Array II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/)本质上就是同一题目。
双指针的题目要先明确左右指针的含义究竟是什么？如果不明白这一点，就算把题目做出来了，也算是没有彻底弄懂题目。

#### 双指针解0027
对于[27 . Remove Element](https://leetcode-cn.com/problems/remove-element/)，使用双指针来确定两个位置，右指针right指向当前将要处理的元素, 左指针left指向下一个将要赋值的位置, 这是两个指针的作用说明. 

下面就是在遍历的过程中会出现的情况了：
   1. 如果右指针指向的元素不等于val，那么这个元素就是我们要输出的元素，我们就把这个元素赋值给左指针，左右指针同时移动。
   2. 如果右指针指向的元素等于val，那么这个元素是我们要删除的，就不需要把元素赋值给左指针，只要右指针移动就可以了。
在遍历完整个数组后，我们得到了一个区间（0，left）,这个区间的所有元素都是不等于val的，所以这个区间的长度就是我们最终需要的结果。   
```java
	 public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0; // 左指针从0开始,指向下一个将要赋值的位置
        // 右指针从0开始,指向当前将要处理的元素
        for ( int right = 0; right < n; right++ ) {
            // 右指针指向的元素不等于val,是输出数组的元素
            // 将右指针指向的元素复制到左指针位置,然后将左右指针同时右移
            if ( nums[right] != val ) {
                nums[left] = nums[right];
                left++;
            }
        }   // 右指针指向的元素等于val,不在输出数组里,左指针不动,右指针右移一位
        return left; // left的值就是输出数组的长度
    }
```
#### 双指针优化0027
对于上面的思路，我们可以进一步进行优化，观察上面的算法可以发现, 我们都是对满足条件(会保留下来的数据)进行操作的, 但是最坏的情况下, 如果数组中没有需要移除的元素, 那两个指针就白白地从头遍历到尾了. 而且我们根据实际情况来说, 正常情况下 需要移除的元素 必然是远小于 需要保留的元素的, 那我们直接对 移除元素 进行操作岂不是更有效.

这时候我们调整左右指针的含义，左指针指向要处理的元素也就是等于value的元素，初始值为数组的第一个元素，而右指针指向末尾，用于和左指针交换元素。

整体的思路，左指针不断向右移动，如果指向的元素等于val，就和右指针指向的元素交换，同时要做2件事。
  1. 右指针左移一位。
  2. 判断交换过来的元素是不是等于val，如果是，就再做交换，直到不等于val。

```java
	 public int removeElement(int[] nums, int val) {
        int left = 0; // 两个指针初始时分别位于数组的首尾
        int right = nums.length;
        while (left < right) {
            // 左指针等于val,将右指针元素复制到左指针的位置,右指针左移一位
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else { // 左指针不等于val,左指针右移一位,右指针不动
                left++;
            }
        }
        return left; // left的值就是输出数组的长度
    }
```

#### 双指针解0026和0080
##### 0026和0027的区别
虽然都是双指针，但[26 . Remove Duplicates from Sorted Array](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)是使用快慢指针，具体使用起来还是有区别的，在[27 . Remove Element](https://leetcode-cn.com/problems/remove-element/)中，我们要移除的元素是题目给出的，是定值，而且是唯一的，从头到尾都不会发生变化，而在[26 . Remove Duplicates from Sorted Array](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)中，我们不再是固定元素的比较，这也提升了一定难度，我们是要比较元素nums[fast]和元素nums[fast-1]，因为这样比较, 才能确定两个相邻的元素是否为 **重复元素**, 从而决定是否要保留当前元素，这是两道题的最大不同。

还有一个小细节注意下, 因为 移除元素 中被移除的元素可能是任意一个位置的元素, 所以两个指针的下标都是 从0开始 的. 但是在本题中, 数组的第一个元素一定是被保留下来的元素, 所以我们直接从 第二个元素 开始遍历就可以了, 也就是 双指针的下标都是从1开始的。

##### 0026和0080的区别
这两道题的区别就是：重复元素只保留一个和最多保留二个，改动看似挺简单, 实则是有一定的难度的, 这也直接让本题由 简单 直接提升到 中等 的难度 。

如果没有想通第一道的原理，只是简单过一遍代码就直接去做第二道就会出现很多问题，比如怎么记录每个元素出现的次数，左右指针从哪里开始等等。
##### 本题拓展
既然都已经扩展到了 每个元素最多出现两次了, 那么同样可以扩展为 每个元素最多出现k次, 这样就形成了此题的通解问题, 解决了这个问题, 只需把k替换一下, 我们就可以解决任意次数的问题了。

有了两次的经验之后, 其实这个扩展也很容易就理解了, 能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较,不相同则保留, 也就是直接比较 nums[slow - k] 和 nums[fast] 两个元素即可, 在两次的代码上稍微修改下就能实现了, 这样我们就成功的将这一类问题完美的解决了!

```java
// 删除有序数组的重复项0026
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

// 删除有序数组中的重复项II0080 每个元素最多出现两次
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2; // 数组的前两个数必然可以被保留
        while (fast < n) {
            // 检查上上个应该被保留的元素nums[slow−2]是否和当前待检查元素nums[fast]相同
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow; // 从nums[0]到nums[slow−1]的每个元素都不相同
    }
}

// 通解扩展
class Solution {
    public int removeDuplicates(int[] nums) {   
        return process(nums, 2);
    }
    int process(int[] nums, int k) { // 最多保留k位相同数字
        int slow = 0; // 慢指针从0开始
        for (int fast : nums) { // 快指针遍历整个数组
            // 检查被保留的元素nums[slow−k]是否和当前待检查元素fast相同
            if (slow < k || nums[slow - k] != fast) nums[slow++] = fast;
        }
        return slow; // 从nums[0]到nums[slow−1]的每个元素都不相同
    }
}


```

