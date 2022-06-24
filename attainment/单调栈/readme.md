## 单调栈

求数组中某个元素左边或者右边第一个大于或者小于它的元素，或者该元素的下标都可以用单调栈来做；

一般单调栈里面是一个单调数列；

如果是求小于元素的数，栈应该是递增的；

如果是求大于于元素的数，栈应该是递减的。



求数组中元素左边第一个它小的元素

```c++
#include <iostream>
using namespace std;
const int N = 100010;
int stk[N], tt;

//数组来模拟栈
int main()
{
    int n;
    cin >> n;
    while (n -- )
    {
        int x;
        scanf("%d", &x);
        while (tt && stk[tt] >= x) tt -- ;//如果栈顶元素大于当前待入栈元素，则出栈
        if (!tt) printf("-1 ");//如果栈空，则没有比该元素小的值。
        else printf("%d ", stk[tt]);//栈顶元素就是左侧第一个比它小的元素。
        stk[ ++ tt] = x;
    }
    return 0;
}
```



还可以用来解决一些区间问题；

[]()

就是求最大的子数组和；

找出数组中每个元素左边和右边第一个比它大的元素的下标，则在这个开区间（左边和右边合起来）内，这个元素就是最大值，同理数组元素左边和右边第一个比它小的元素的下标，在这个开区间内，这个元素就是最小值；

这个开区间有多大，就一共有多少个以这个元素为最值的子数组；

所以只需最大值区间个数乘以该元素减去最小值区间个数成以该元素即可。

```java
public static long subArrayRanges(int[] nums) {
        long res = 0;
        int n = nums.length;

        int[] minLeft = new int[n];
        int[] maxLeft = new int[n];
        int[] minRight = new int[n];
        int [] maxRight = new int[n];

        Deque<Integer> minDeque = new ArrayDeque<Integer>();
        Deque<Integer> maxDeque = new ArrayDeque<Integer>();
        for (int i = 0 ; i < n ; i++) {
            while (!minDeque.isEmpty() && nums[minDeque.peek()] > nums[i]) minDeque.pop();

            if (!minDeque.isEmpty()) minLeft[i] = minDeque.peek();
            else minLeft[i] = -1;

            minDeque.push(i);

            while (!maxDeque.isEmpty() && nums[maxDeque.peek()] <= nums[i] ) maxDeque.pop();
            if (!maxDeque.isEmpty()) maxLeft[i] = maxDeque.peek();
            else maxLeft[i] = -1;
            maxDeque.push(i);
        }
        maxDeque.clear();
        minDeque.clear();

        for (int i = n - 1 ; i >= 0 ; i--)  {
            while (!minDeque.isEmpty() && nums[i] <= nums[minDeque.peek()]) minDeque.pop();

            if (!minDeque.isEmpty()) minRight[i] = minDeque.peek();
            else minRight[i] = n;

            minDeque.push(i);

            while(!maxDeque.isEmpty() && nums[i] > nums[maxDeque.peek()]) maxDeque.pop();

            if (!maxDeque.isEmpty()) maxRight[i] = maxDeque.peek();
            else maxRight[i] = n;

            maxDeque.push(i);

        }
        // long sumMax = 0, sumMin = 0;
        for (int i = 0 ; i < n ; i++) {
            res += (long)(maxRight[i] - i) * (i - maxLeft[i]) * nums[i]
                    - (long)(minRight[i] - i) * (i - minLeft[i]) * nums[i];
            // sumMax += (long) (maxRight[i] - i) * (i - maxLeft[i]) * nums[i];
            // sumMin += (long) (minRight[i] - i) * (i - minLeft[i]) * nums[i];
        }

        return res;
    }
```

