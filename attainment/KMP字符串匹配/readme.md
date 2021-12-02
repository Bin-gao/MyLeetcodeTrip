## KMP



比较难的一个算法，可以按着这个思路顺一遍。

- 什么是KMP
- 什么是前缀表
- 最长公共前后缀？
- 如何计算前缀表
- 时间复杂度分析
- 构造next数组
- 总结



####  什么是KMP

KMP主要应用在字符串匹配上。

KMP的主要思想**是当出现字符串不匹配时，可以知道一部分之前已经匹配的文本内容，可以利用
这些信息避免从头再去做匹配了。**

#### 什么是前缀表

next数组就是一个前缀表（prefix table）。

前缀表有什么作用呢？

前缀表是用来回退的，它记录了模式串与主串(文本串)不匹配的时候，模式串应该从哪里开始重新匹配。

如果暴力匹配，会发现不匹配，此时就要从头匹配了。但如果使用前缀表，就不会从头匹配，而是从上次已经匹配的内容开始匹配，找到了模式串中第三个字符b继续开始匹配。

此时就要问了前缀表是如何记录的呢？

![](https://code-thinking.cdn.bcebos.com/gifs/KMP%E7%B2%BE%E8%AE%B21.gif)

首先要知道前缀表的任务是当前位置匹配失败，找到之前已经匹配上的位置，再重新匹配，此也意味着在某个字符失配时，前缀表会告诉你下一步匹配中，模式串应该跳到哪个位置。

那么什么是前缀表：记录下标i之前（包括i）的字符串中，有多大长度的相同前缀后缀。

#### 最长公共前后缀？

文章中字符串的前缀是指不包含最后一个字符的所有以第一个字符开头的连续子串。

后缀是指不包含第一个字符的所有以最后一个字符结尾的连续子串。

**因为前缀表要求的就是相同前后缀的长度。**

而最长公共前后缀里面的“公共”，更像是说前缀和后缀公共的长度。这其实并不是前缀表所需要的。

所以字符串a的最长相等前后缀为0。 字符串aa的最长相等前后缀为1。 字符串aaa的最长相等前后缀为2。 等等.....。

#### 为什么要使用前缀表

还是这个例子，当匹配的过程在下标5的地方遇到不匹配，模式串是指向f，
![](https://code-thinking.cdn.bcebos.com/pics/KMP%E7%B2%BE%E8%AE%B21.png)

然后就找到了下标2，指向b，继续匹配：如图：
![](https://code-thinking.cdn.bcebos.com/pics/KMP%E7%B2%BE%E8%AE%B22.png)

**下标5之前这部分的字符串（也就是字符串aabaa）的最长相等的前缀 和 后缀字符串是 子字符串aa ，因为找到了最长相等的前缀和后缀，匹配失败的位置是后缀子串的后面，那么我们找到与其相同的前缀的后面从新匹配就可以了。**

所以前缀表具有告诉我们当前位置匹配失败，跳到之前已经匹配过的地方的能力。

#### 如何计算前缀表

如图：
![](https://code-thinking.cdn.bcebos.com/pics/KMP%E7%B2%BE%E8%AE%B25.png)

长度为前1个字符的子串a，最长相同前后缀的长度为0。

![](https://code-thinking.cdn.bcebos.com/pics/KMP%E7%B2%BE%E8%AE%B26.png)

长度为前2个字符的子串aa，最长相同前后缀的长度为1。

![](https://code-thinking.cdn.bcebos.com/pics/KMP%E7%B2%BE%E8%AE%B27.png)

长度为前3个字符的子串aab，最长相同前后缀的长度为0。

以此类推： 长度为前4个字符的子串aaba，最长相同前后缀的长度为1。 长度为前5个字符的子串aabaa，最长相同前后缀的长度为2。 长度为前6个字符的子串aabaaf，最长相同前后缀的长度为0。

那么把求得的最长相同前后缀的长度就是对应前缀表的元素，如图：
![](https://code-thinking.cdn.bcebos.com/pics/KMP%E7%B2%BE%E8%AE%B28.png)

再来看一下如何利用 前缀表找到 当字符不匹配的时候应该指针应该移动的位置。如动画所示：

![](https://code-thinking.cdn.bcebos.com/gifs/KMP%E7%B2%BE%E8%AE%B22.gif)

找到的不匹配的位置， 那么此时我们要看它的前一个字符的前缀表的数值是多少。把下标移到对应位置再进行匹配。

#### 时间复杂度分析

其中n为文本串长度，m为模式串长度，因为在匹配的过程中，根据前缀表不断调整匹配的位置，可以看出匹配的过程是O(n)，之前还要单独生成next数组，时间复杂度是O(m)。所以整个KMP算法的时间复杂度是O(n+m)的。

#### 构造next数组

next数组就可以是前缀表，但是很多实现都是把前缀表统一减一（右移一位，初始位置为-1）之后作为next数组。

构造next数组其实就是计算模式串s，前缀表的过程。 主要有如下三步：

1. 初始化
定义两个指针i和j指向前缀和后缀的位置。

前缀是指不包含最后一个字符的所有以第一个字符开头的连续子串。
后缀是指不包含第一个字符的所有以最后一个字符结尾的连续子串。

这里我们使：
i指向后缀的起始位置。 
j指向前缀的起始位置。

```java
int j = -1;
next[0] = -1;
```

j 为什么要初始化为 -1呢，因为之前说过 前缀表要统一减一的操作仅仅是其中的一种实现，我们这里选择j初始化为-1，


2. 处理前后缀不相同的情况

因为j初始化为-1，那么i就从1开始，进行s[i] 与 s[j+1]的比较。

```java
for(int i = 1; i < s.size(); i++) {}
```

如果 s[i] 与 s[j+1]不相同，也就是遇到 前后缀末尾不相同的情况，就要向前回退。

怎么回退呢？

next[j]就是记录着j（包括j）之前的子串的相同前后缀的长度。

那么 s[i] 与 s[j+1] 不相同，就要找 j+1前一个元素在next数组里的值（就是next[j]）。

所以，处理前后缀不相同的情况代码如下：

```java
while (j >= 0 && s[i] != s[j + 1]) { // 前后缀不相同了
    j = next[j]; // 向前回退
}
```

3. 处理前后缀相同的情况

如果s[i] 与 s[j + 1] 相同，那么就同时向后移动i 和j 说明找到了相同的前后缀，同时还要将j（前缀的长度）赋给next[i], 因为next[i]要记录相同前后缀的长度。

```java
if (s[i] == s[j + 1]) { // 找到相同的前后缀
    j++;
}
next[i] = j;
```

最后整体构建next数组的函数代码如下：
```java
void getNext(int[] next,  String s){
    int j = -1;
    next[0] = j;
    for(int i = 1; i < s.length ; i++) { // 注意i从1开始
        while (j >= 0 && s[i] != s[j + 1]) { // 前后缀不相同了
            j = next[j]; // 向前回退
        }
        if (s[i] == s[j + 1]) { // 找到相同的前后缀
            j++;
        }
        next[i] = j; // 将j（前缀的长度）赋给next[i]
    }
}
```

得到了next数组之后，就要用这个来做匹配了。

#### 使用next数组做匹配

在文本串s里 找是否出现过模式串t。

定义两个下标j 指向模式串起始位置，i指向文本串起始位置。

那么j初始值依然为-1，为什么呢？ 依然因为next数组里记录的起始位置为-1。

i就从0开始，遍历文本串，代码如下：
```java
for ( int i = 0 ; i < s.length ; i++)
```

接下来就是 s[i] 与 t[j + 1] （因为j从-1开始的） 进行比较。
如果 s[i] 与 t[j + 1] 不相同，j就要从next数组里寻找下一个匹配的位置。

```java
while(j >= 0 && s[i] != t[j + 1]) {
    j = next[j];
}
```

如果 s[i] 与 t[j + 1] 相同，那么i 和 j 同时向后移动， 代码如下：
```java
if (s[i] == t[j + 1]) {
    j++; // i的增加在for循环里
}
```

如何判断在文本串s里出现了模式串t呢，如果j指向了模式串t的末尾，那么就说明模式串t完全匹配文本串s里的某个子串了。

```java
 if(j==needle.length()-1){
                return (i-needle.length()+1);
            }
```

此时所有逻辑的代码都已经写出来了,[28. Implement strStr()](https://leetcode-cn.com/problems/implement-strstr/).实现strStr 题目的整体代码如下：

```java
class Solution {
    public void getNext(int[] next, String s){
        int j = -1;
        next[0] = j;
        for (int i = 1; i<s.length(); i++){
            while(j>=0 && s.charAt(i) != s.charAt(j+1)){
                j=next[j];
            }

            if(s.charAt(i)==s.charAt(j+1)){
                j++;
            }
            next[i] = j;
        }
    }
    public int strStr(String haystack, String needle) {
        if(needle.length()==0){
            return 0;
        }

        int[] next = new int[needle.length()];
        getNext(next, needle);
        int j = -1;
        for(int i = 0; i<haystack.length();i++){
            while(j>=0 && haystack.charAt(i) != needle.charAt(j+1)){
                j = next[j];
            }
            if(haystack.charAt(i)==needle.charAt(j+1)){
                j++;
            }
            if(j==needle.length()-1){
                return (i-needle.length()+1);
            }
        }

        return -1;
    }
}

```

