## 59.Spiral Matrix II

#### [力扣链接](https://leetcode-cn.com/problems/spiral-matrix-ii/)

###### Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

###### ![](https://assets.leetcode.com/uploads/2020/11/13/spiraln.jpg)

```
Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
```



### 边界问题

------

###### **临界经常写乱，主要是因为区间的定义没有想清楚，区间的定义就是不变量。每次在循环（while或者for）中寻找每一次边界的处理都要坚持区间的定义来操作，这就是循环不变量规则。**



```java
 public int[][] generateMatrix(int n) {
            int[][] result = new int[n][n];
            int loop  = n / 2;//循环次数，若n为奇数，则需要处理中间位置
            int count = 1;
            int offset = 1;//每一圈循环，需要控制每一条边遍历的长度
            int startX = 0;//每次需要填充位置的行
            int startY = 0;//每次需要填充位置的列

            while ( loop > 0 ){
                //上 区间左闭右开
                for ( ; startY < (n - offset) ; startY++ ) {
                    result[startX][startY] = count++;
                }
                //右
                for ( ; startX < (n - offset) ; startX++ ) {
                    result[startX][n - offset] = count++;
                }
				//下
                for ( ; startY > offset-1 ; startY-- ) {
                    result[n - offset][startY] = count++;
                }
				//左
                for ( ; startX > offset-1 ; startX-- ) {
                    result[startX][startY] = count++;
                }

                loop--;
                startX++;
                startY++;
                offset++;
            }
     		//若为偶数，则刚好填满数组，直接返回
            if ( (n & 1) == 0 ) return result;
     		//若为奇数，需要处理中间位置
            result[startX][startY] = count;
            return result;

        }
```

