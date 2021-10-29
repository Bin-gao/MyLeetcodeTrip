## 501. Find Mode in Binary Search Tree

------



####[力扣链接](https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/)

给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

例如：
给定 BST [1,null,2,2]

```
  1
    \
     2
    /
   2
```

使用pre指针和cur指针的技巧

弄一个指针指向前一个节点，这样每次cur（当前节点）才能和pre（前一个节点）作比较。

而且初始化的时候pre = NULL，这样当pre为NULL时候，我们就知道这是比较的第一个元素。

其实只要遍历一次就够了，maxCount和count同时更新，而且count 大于 maxCount的时候，不仅要更新maxCount，还要清空结果集。

而每次cur和pre的值不相等时，count就重新开始计数。

```java
		 Integer rootValue = (Integer) root.val;
        // 计数
        if (pre == null || rootValue != pre.val) {
            count = 1;
        } else {
            count++;
        }

        pre = root;

        if ( maxCount == count ) {
            result.add((Integer) root.val);
        }

        if ( maxCount < count ){
            maxCount = count;
            result.clear();
            result.add((Integer) root.val);
        }
```