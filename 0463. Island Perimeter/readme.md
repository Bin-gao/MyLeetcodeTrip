## 463 . Island Perimeter

------

#### [力扣链接](https://leetcode-cn.com/problems/island-perimeter/)

## 分析

解法1：遍历每一个空格，遇到岛屿，计算其上下左右的情况，遇到水域或者出界的情况，就可以计算边了。

```java
public class Solution {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static int islandPerimeter(int[][] grid) {
        int cnt = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] != 0) {
                    for (int k = 0; k < 4; k++) { //上下左右四个方向
                        int x = i + dx[k];        //计算x，y坐标
                        int y = j + dy[k];

                        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {              									//判断上下左右遇到水域或者出界
                            cnt++;
                        }
                    }
                }
            }
        }


        return cnt;
    }
```

解法2：计算出总的岛屿数量，因为有一对相邻两个陆地，边的总数就减2，那么在计算出相邻岛屿的数量就可以了。

result = 岛屿数量 * 4 - cover * 2;

```java
public class Solution {

     public static int islandPerimeter1(int[][] grid) {
        int cnt = 0;//岛屿数量
        int cover = 0;//相邻数量

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

               if (grid[i][j] == 1) {
                   cnt++;
                   // 统计上边相邻陆地
                   if (i >= 1 && grid[i - 1][j] == 1) cover++;
                   // 统计左边相邻陆地
                   if (j >= 1 && grid[i][j - 1] == 1) cover++;
                   // 为什么没统计下边和右边？ 因为避免重复计算
               }
            }
        }

        return cnt * 4 - cover * 2;
    }
 }
```