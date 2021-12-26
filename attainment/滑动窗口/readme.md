## 滑动窗口系列

滑动窗口，就是不断调节子序列的起始位置和终止位置，从而得到我们想要的结果。

相关题目：

[209 . 长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/)

[3 . 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters)

[217 . 存在重复元素](https://leetcode-cn.com/problems/contains-duplicate/)

[219 . 存在重复元素 II](https://leetcode-cn.com/problems/contains-duplicate-ii)

[220 . 存在重复元素 III](https://leetcode-cn.com/problems/contains-duplicate-iii)

[904 . 水果成篮](https://leetcode-cn.com/problems/fruit-into-baskets/)

[76 . 最小覆盖子串](https://leetcode-cn.com/problems/minimum-window-substring/)

[438 . 找到字符串中所有字母异位词](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string)

[1456 . 定长子串中元音的最大数目](https://leetcode-cn.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length)

[2090 . 半径为 k 的子数组平均值](https://leetcode-cn.com/problems/k-radius-subarray-averages/)

## 题目分析

实现滑动窗口，主要确定如下三点：
  - 窗口内是什么？
  - 如何移动窗口的起始位置？
  - 如何移动窗口的结束位置？


[209 . 长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/)


暴力解法两个for循环，时间复杂度很明显是O(n^2)。


```C++
for (int i = 0; i < nums.size(); i++) { // 设置子序列起点为i
            sum = 0;
            for (int j = i; j < nums.size(); j++) { // 设置子序列终止位置为j
                sum += nums[j];
                if (sum >= s) { // 一旦发现子序列和超过了s，更新result
                    subLength = j - i + 1; // 取子序列的长度
                    result = result < subLength ? result : subLength;
                    break; // 因为我们是找符合条件最短的子序列，所以一旦符合条件就break
                }
            }
        }
```

```C++
public:
    int minSubArrayLen(int s, vector<int>& nums) {
        int result = INT32_MAX;
        int sum = 0; // 滑动窗口数值之和
        int i = 0; // 滑动窗口起始位置
        int subLength = 0; // 滑动窗口的长度
        for (int j = 0; j < nums.size(); j++) {
            sum += nums[j];
            // 注意这里使用while，每次更新 i（起始位置），并不断比较子序列是否符合条件
            while (sum >= s) {
                subLength = (j - i + 1); // 取子序列的长度
                result = result < subLength ? result : subLength;
                sum -= nums[i++]; // 这里体现出滑动窗口的精髓之处，不断变更i（子序列的起始位置）
            }
        }
        // 如果result没有被赋值的话，就返回0，说明没有符合条件的子序列
        return result == INT32_MAX ? 0 : result;
    }
```

**不要以为for里放一个while就以为是O(n^2)啊， 主要是看每一个元素被操作的次数，每个元素在**

**滑动窗后进来操作一次，出去操作一次，每个元素都是被被操作两次，所以时间复杂度是 2 × n 也就**

**是O(n)。**


[3 . 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters)

- 思路：set可以不包含重复元素，用set来做窗口，窗口内就是最长不重复子串。滑动窗口不断向    前，如果set中不包含当前元素，就加入set，更新最大长度len。如果包含当前元素，窗口就向前移动，并删除窗口之外的元素，直到没有重复的元素。
- 复杂度：时间复杂度O(n)，n是字符串的长度。空间复杂度是O(n)，即set的空间，最差的情况是O(n)

```java
 public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();//set做窗口
        int res = 0;
        int n = s.length();
        int j = 0;//j控制窗口结束位置

        for ( int i = 0 ; i < n ; i++ ) {//i控制窗口开始位置

            if ( i != 0 ) {
                set.remove(s.charAt(i-1));
            }
            while ( j <= (n-1) && !set.contains(s.charAt(j)) ) {
                set.add(s.charAt(j));
                ++j;
            }
            res = Math.max(res,j-i);

        }
        return res;
    }
```

[217 . 存在重复元素](https://leetcode-cn.com/problems/contains-duplicate/)

[219 . 存在重复元素 II](https://leetcode-cn.com/problems/contains-duplicate-ii)

[220 . 存在重复元素 III](https://leetcode-cn.com/problems/contains-duplicate-iii)

第一个题遍历数组，用set记录下元素，如果存在重复元素返回true，遍历结束后还没找到重复元素

返回false。

```java
 public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int x : nums) {
            if (!set.add(x)) {
                return true;
            }
        }
        return false;
    }
```


第二个题，多了一个限制条件，即存在的重复元素，必须在长度为k+1的区间内。

- 思路：遍历数组，还是用set作为滑动窗口，不重复元素加入set中，i++右端口移动，如果在窗口
  小于等于k之前，出现重复元素返回true，如果窗口大于k，则缩小窗口，移除最先进入窗口的元   	素即nums[i-k]。

- 复杂度：时间复杂度O(n)，空间复杂度O(min(n, k))



第三个题，再多一个限制条件，不再是找重复的元素，而是给定任意数t，在k+1区间内，对于元素x，是否存在元素y使得
$$
x-t  <=  y  <=  x+t
$$

- 思路 ：实现方面，我们在有序集合中查找大于等于 x−t  的最小的元素 y，如果 y 存在，且 y≤x+t我们就找到了一对符合条件的元素。如果最小元素y不存在，或者y>x+t，我们就将x插入到有序集合中，当有序集合中元素数量超过了k，就将有序集合中最早被插入的元素删除即可。
**如果当前有序集合中存在相同的元素，那么此时程序将直接返回true。**
- 复杂度：时间复杂度：O(nlog(min(n,k)))，其中 n 是给定数组的长度。每个元素至多被插入有序集合和从有序集合中删除一次，每次操作时间复杂度均为 O(log(min(n,k))。


```java
 public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        int left = 0;

        TreeSet<Long> set = new TreeSet<Long>();

        for ( int i = 0 ; i < n ; i++ ) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (  null != ceiling && ceiling <= (long) nums[i] + (long) t ) {
                return true;
            }
            set.add((long) nums[i]);

            if (  i >= k ) {
                set.remove((long)nums[i-k]);

            }


        }
        return false;
    }
```

[904 . 水果成篮](https://leetcode-cn.com/problems/fruit-into-baskets/)

- 思路：map做滑动窗口，key为水果种类，val为该种类的数量，遍历fruits数组，map.size()>2时，装了2种类型水果，更新窗口左边界，更新窗口内水果数量。如果水果数量等于0，则移除窗口。
- 时间复杂度O(n)，空间复杂度O(n)。

```java
public int totalFruit(int[] tree) {
        if ( tree.length == 1 ) return 1;

        int n = tree.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int ans = 0;

        for ( int i = 0 ; i < n ; i++ ) {
            map.put(tree[i],map.getOrDefault(tree[i],0)+1);
            while ( map.size() > 2 ) {
                map.put(tree[left],map.get(tree[left])-1);
                if ( map.get(tree[left]) == 0 ) map.remove(tree[left]);

                left++;
            }
            ans = Math.max(ans,i-left+1);
        }
        return ans;
    }
```

[76 . 最小覆盖子串](https://leetcode-cn.com/problems/minimum-window-substring/)

- 思路：用左右两个指针遍历s字符串，当滑动窗口中的字符不能覆盖t中的字符时，右指针右移，扩大窗口，把右边的字符加入滑动窗口，当滑动窗口中的字符能覆盖t中的字符时，不断左移左指针，缩小窗口，直到窗口中的字符刚好能覆盖t中的字符，这个时候在左移就不能覆盖t中的字符了，在指针移动的过程中，不断更新最小覆盖子串。
- 复杂度：时间复杂度o(n)，n是s的长度，空间复杂度o(t)，t是字符集的大小。

```java
 public static String minWindow(String s, String t) {
        int n = s.length();

        Map<Character,Integer> needMap = new HashMap<Character,Integer>();
        Map<Character,Integer> windowsMap = new HashMap<Character,Integer>();

        for ( Character c : t.toCharArray() ) {
            needMap.put(c,needMap.getOrDefault(c,0)+1);
        }
        int len = Integer.MAX_VALUE;

        int left = 0;
        int start = 0;
        int val = 0;
        for ( int i = 0 ; i < n ; i++ ) {
            char c = s.charAt(i);
            if ( needMap.containsKey(c) ) {
                windowsMap.put(c,windowsMap.getOrDefault(c,0)+1);
                if ( needMap.get(c).equals(windowsMap.get(c)) ) {
                    val++;
                }
            }

            while ( val == needMap.size() ) {
                if ( i - left + 1 < len ) {
                    len = i - left + 1;
                    start = left;
                }

                char ch = s.charAt(left);
                ++left;

                if ( needMap.containsKey(ch) ) {
                    if ( windowsMap.get(ch).equals(needMap.get(ch)) ) {
                        val--;
                    }
                    windowsMap.put(ch, windowsMap.get(ch) - 1);
                }

            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start,start+len);
    }
```

[438 . 找到字符串中所有字母异位词](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string)

- 思路：用滑动窗口的思路，遍历字符串
1. 判定进入窗口的字符时否是需要的字符，并且加入窗口后该字符的数量是否和need中的字符数量一致。
2. 判定出窗口的字符是否是需要的字符，并且该字符在窗口中的数量是否和need中的字符数量一致。
3. 判断窗口中和`need`中符合要求的字符是否一致 如果一致 则这个窗口形成的子串就是一个异位词。
- 复杂度：时间复杂度O(n)，n是字符串的长度。空间复杂度O(k)，k是字符集的空间

```js
//思路写法 判断后再加入
var findAnagrams = function (s, p) {
    let need = {};//需要的字符
    let win = {};//窗口中的字符
    for (let a of p) {//统计异位词的数量
        need[a] = (need[a] || 0) + 1;
    }
    //左右指针
    let left = 0,
        right = 0;
    let val = 0;//窗口中和need中字符数量一致的字符种类
    let res = [];
    while (right < s.length) {
        let c = s[right];
        right++;//右边的字符进入窗口
        if (need[c]) {
            win[c] = (win[c] || 0) + 1;//当前字符在need中，更新窗口中的字符数量
            if (win[c] == need[c]) {
                val++;//该字符在窗口中和need中的字符匹配时，字符种类+1
            }
        }
        while (right - left >= p.length) {//不断出窗口
            if (val == Object.keys(need).length) {//如果此时窗口中的子串和p是异位词则将左边界加入res中
                res.push(left);
            }
            let d = s[left];
            left++;//出窗口
            if (need[d]) {//如果该字符在need中 更新窗口中的字符数量 和字符种类
                if (win[d] == need[d]) {
                    val--;
                }
                win[d]--;
            }
        }
    }
    return res;
};
```

```java
//写法2 加入后再判断
 public static List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] letters = new int[26];//存储需要的异位词的种类和数量
        int[] window = new int[26];//存储窗口中的字符和对应的频次
        int n = s.length();
        int m = p.length();

        for ( int i = 0 ; i < m ; i++ ) {
            letters[p.charAt(i)-'a']++;
        }
        int j = 0;//窗口右临界
        for ( int i = 0 ; i < n ; i++ ) {

           while ( j < n && (j-i+1) <= m ) {//当窗口大小大于异位词长度，退出循环
               window[s.charAt(j)-'a']++;
               ++j;
           }
           if (Arrays.equals(window,letters)) {
               list.add(i);
           }
            window[s.charAt(i)-'a']--;
        }
        return list;
    }
```

[1456 . 定长子串中元音的最大数目](https://leetcode-cn.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length)

类比化：给定任意要求，寻找长度为k的符合要求的子串，其实和[3 . 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters)很像。

思路：滑动窗口遍历字符串，不断更新最大元音个数。
复杂度：时间复杂度O(n)，n是字符串长度。空间复杂度O(1)。


```java
public static int maxVowels(String s, int k) {
        int n = s.length();
        int res = 0;
        int ans = 0;
        
        int j = 0;
        for ( int i = 0 ; i < n ; i++ ) {
            while ( j < n && (j-i+1) <= k ) {
                if ( isVowel(s.charAt(j)) ) res++;
                j++;
            }
            ans = Math.max(res,ans);
            if ( isVowel(s.charAt(i))) res--;

        }
        return ans;
    }

    public static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
```
