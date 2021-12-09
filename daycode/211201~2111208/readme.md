## 211201~211208

把这几天做过的题总结一下。

队列和栈题目
[#20 有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)
[#1047 删除字符串中的所有相邻重复项](https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/)
[#150 逆波兰表达式求值](https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/)

字符串类型题目，对KMP，双指针等算法复习。
[#28 实现 strStr()](https://leetcode-cn.com/problems/implement-strstr/)
[#459 重复的子字符串](https://leetcode-cn.com/problems/repeated-substring-pattern/)
[#344 反转字符串 I](https://leetcode-cn.com/problems/reverse-string)
[#541 反转字符串 II](https://leetcode-cn.com/problems/reverse-string-ii/)
[#151 翻转字符串里的单词](https://leetcode-cn.com/problems/reverse-words-in-a-string/)
[剑指 Offer 58 - II . 左旋转字符串](https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/)
[剑指 Offer 05 . 替换空格](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/)

链表
[#25 K 个一组翻转链表](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)
[#61 旋转链表](https://leetcode-cn.com/problems/rotate-list/)

按照上面的顺序，依次进行自己的总结。

### 队列和栈题目

这三道题，本质上解决问题思路其实是一样的，题目给了一个字符串，我们创建一个栈，遍历这个

字符串，依次把每个元素入栈，同时判断符不符合出栈要求，完成字符串遍历后，根据栈是否为空

来判断字符串是否符合题目条件。关键还是在判断符不符合出栈的要求。

[#20 有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)，一个不符合要求的字符串，有3种情况：

1. 第一种情况，字符串里左方向的括号多余了 ，所以不匹配。 
![](https://img-blog.csdnimg.cn/2020080915505387.png)

2. 第二种情况，括号没有多余，但是 括号的类型没有匹配上。 
![](https://img-blog.csdnimg.cn/20200809155107397.png)

3. 第三种情况，字符串里右方向的括号多余了，所以不匹配。
![](https://img-blog.csdnimg.cn/20200809155115779.png)

```java
public boolean isValid(String s) {
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            //碰到左括号，就把相应的右括号入栈
            if (ch == '(') {
                deque.push(')');
            }else if (ch == '{') {
                deque.push('}');
            }else if (ch == '[') {
                deque.push(']');
            } else if (deque.isEmpty() || deque.peek() != ch) {
                return false;
            }else {//如果是右括号判断是否和栈顶元素匹配
                deque.pop();
            }
        }
        //最后判断栈中元素是否匹配
        return deque.isEmpty();
    }
```
[#1047 删除字符串中的所有相邻重复项](https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/),把字符串顺序放到一个栈中，然后如果相同的话 栈就弹出，这样最后栈里剩下的元素都是相邻不相同的元素了。

```java
//使用 Deque 作为堆栈
class Solution {
    public String removeDuplicates(String S) {
        //ArrayDeque会比LinkedList在除了删除元素这一点外会快一点
        //参考：https://stackoverflow.com/questions/6163166/why-is-arraydeque-better-than-linkedlist
        ArrayDeque<Character> deque = new ArrayDeque<>();
        char ch;
        for (int i = 0; i < S.length(); i++) {
            ch = S.charAt(i);
            if (deque.isEmpty() || deque.peek() != ch) {
                deque.push(ch);
            } else {
                deque.pop();
            }
        }
        String str = "";
        //剩余的元素即为不重复的元素
        while (!deque.isEmpty()) {
            str = deque.pop() + str;
        }
        return str;
    }
}
//拿字符串直接作为栈，省去了栈还要转为字符串的操作。
class Solution {
    public String removeDuplicates(String s) {
        // 将 res 当做栈
        StringBuffer res = new StringBuffer();
        // top为 res 的长度
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 当 top > 0,即栈中有字符时，当前字符如果和栈中字符相等，弹出栈顶字符，同时 top--
            if (top >= 0 && res.charAt(top) == c) {
                res.deleteCharAt(top);
                top--;
            // 否则，将该字符 入栈，同时top++
            } else {
                res.append(c);
                top++;
            }
        }
        return res.toString();
    }
}
```

[#150 逆波兰表达式求值](https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/)和上面两道题基本一样，本题中每一个子表达式要得出一个结果，然后拿这个结果再进行运算，那么这岂不就是一个相邻字符串消除的过程。

```java
public static int evalRPN(String[] tokens) {


        Deque<Integer> stack = new LinkedList();

        for (String token :
                tokens) {
            if ( isOpe(token) ) {
                if ( token.equals("+") ) {
                    stack.addFirst(stack.pop() + stack.pop());
                }else if ( token.equals("-") ) {
                    stack.push(-stack.pop() + stack.pop());
                }else if ( token.equals("*") ) {
                    stack.push(stack.pop() * stack.pop());
                }else if ( token.equals("/") ) {
                    int num1 = stack.removeFirst();
                    int num2 = stack.pop();
                    stack.push(num2/num1);
                }
            }else {
                //如果不是符号，则说明是数字，直接入栈
                stack.push(Integer.valueOf(token));
            }


        }
        System.out.println(stack.getFirst());
        return stack.pop();
    }

    //判读是不是四则运算
    public static boolean isOpe(String s) {
        return s.length() == 1 && (s.charAt(0) > '9' || s.charAt(0) < '0');
    }
```

### 字符串类型题目

[#28 实现 strStr()](https://leetcode-cn.com/problems/implement-strstr/)
在一个字符串中找另一个字符串，KMP题目，之前做过总结[KMP](https://github.com/Bin-gao/MyLeetcodeTrip/tree/master/attainment/KMP%E5%AD%97%E7%AC%A6%E4%B8%B2%E5%8C%B9%E9%85%8D)

[#459 重复的子字符串](https://leetcode-cn.com/problems/repeated-substring-pattern/)
也是标准KMP题目。

说一说next数组了，next 数组记录的就是最长相同前后缀，如果 next[len - 1] != -1，则说明字符串有最长相同的前后缀（就是字符串里的前缀子串和后缀子串相同的最长长度）。

比如  a  b   c   a  b  c   a   b  c  a   b  c
    0 0 0 1 2 3 4 5 6 7 8 9
这个字符串对应的next数组。

**数组长度减去最长相同前后缀的长度相当于是第一个周期的长度，也就是一个周期的长度，如果这个周期可以被整除，就说明整个数组就是这个周期的循环。**


[#344 反转字符串 I](https://leetcode-cn.com/problems/reverse-string)
很简单，用双指针，直接上代码了。
```java
class Solution {
    public void reverseString(char[] s) {
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }
}
```
[#541 反转字符串 II](https://leetcode-cn.com/problems/reverse-string-ii/)
这道题目其实也是模拟，实现题目中规定的反转规则就可以了。

在遍历字符串的过程中，只要让 i += (2 * k)，i 每次移动 2 * k 就可以了，然后判断是否需要有反转的区间。

```java
 public String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        int n = s.length();

        for ( int i = 0 ; i < n ; i += (2 * k) ) {
            if ( ( i + 2 * k ) < n || ( i + k <= n) ) {//左闭右闭
                ch = swap(ch,i,i+k-1);
            }else if ( (n-i) < k ) {
                ch = swap(ch, i, n - 1);
            }
        }
        return new String(ch);
    }

    public char[] swap(char[] ch,int begin,int end) {
        while ( begin <= end ) {

            char c = ch[begin];
            ch[begin] = ch[end];
            ch[end] = c;

            begin++;
            end--;
        }
        return ch;
    }
```

[#151 翻转字符串里的单词](https://leetcode-cn.com/problems/reverse-words-in-a-string/)
这道题目可以说是综合考察了字符串的多种操作。

我们将整个字符串都反转过来，那么单词的顺序指定是倒序了，只不过单词本身也倒叙了，那么再把单词反转一下，单词不就正过来了。

所以解题思路如下：
1. 移除多余空格
2. 将整个字符串反转
3. 将每个单词反转

移除多余空格可以用快慢指针：
```java
private StringBuilder removeSpace(String s) {
        // System.out.println("ReverseWords.removeSpace() called with: s = [" + s + "]");
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') start++;
        while (s.charAt(end) == ' ') end--;
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        // System.out.println("ReverseWords.removeSpace returned: sb = [" + sb + "]");
        return sb;
    }
```
[剑指 Offer 58 - II . 左旋转字符串](https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/)
上面那道题使用整体反转+局部反转就可以实现，反转单词顺序的目的。

这道题目也非常类似，依然可以通过局部反转+整体反转 达到左旋转的目的。

具体步骤为：
1. 反转区间为前n的子串
2. 反转区间为n到末尾的子串
3. 反转整个字符串


翻转 a b c d e f g

![image-20211209204724718](C:\Users\lbgao\AppData\Roaming\Typora\typora-user-images\image-20211209204724718.png)

[剑指 Offer 05 . 替换空格](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/)

首先扩充数组到每个空格替换成"%20"之后的大小。然后从后向前替换空格，也就是双指针法，

从前向后填充就是O(n^2)的算法了，因为每次添加元素都要将添加元素之后的所有元素向后移动。

```java
public String replaceSpace(String s) {
    if(s == null || s.length() == 0){
        return s;
    }
    //扩充空间，空格数量2倍
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
        if(s.charAt(i) == ' '){
            str.append("  ");
        }
    }
    //若是没有空格直接返回
    if(str.length() == 0){
        return s;
    }
    //有空格情况 定义两个指针
    int left = s.length() - 1;//左指针：指向原始字符串最后一个位置
    s += str.toString();
    int right = s.length()-1;//右指针：指向扩展字符串的最后一个位置
    char[] chars = s.toCharArray();
    while(left>=0){
        if(chars[left] == ' '){
            chars[right--] = '0';
            chars[right--] = '2';
            chars[right] = '%';
        }else{
            chars[right] = chars[left];
        }
        left--;
        right--;
    }
    return new String(chars);
}
```

### 链表

[#25 K 个一组翻转链表](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)
第一次自己解决hard题目，用了3个小时。简单说说吧。

这道题没涉及到什么算法，就是按题目的规则模拟就行了，但是需要处理很多细节。

可能看了题意，应该都有一个思路，就是定义一个遍历cout来计数，遍历这个链表，每次cout等于k的时候就把那一段链表翻转一下，直到这个链表遍历结束。

可是一写起代码就会发现，出了很多问题，翻转一个链表后，（这里可以参考下[206 . 翻转链表](https://leetcode-cn.com/problems/reverse-linked-list)），头节点变成尾节点，而尾节点变成了头节点，我们怎么把翻转后的链表重新接回去啊？

首先，head 前面是没有节点 pre 的。没有条件，我们就创造条件，这样也方便我们解题，让它作为 pre 的初始值，这样 head 前面就有了一个节点。

我们把链表怎么重新接回去，分成2部分：

**怎么连接前面部分**
从上面可以知道，pre是指向需要翻转的链表的前面的一个节点，在完成翻转后，我们让pre指向翻转后的头节点就连接起了前面。

**怎么连接后面部分**
我们把翻转前链表的头节点记为start，它翻转后就变成尾节点了，再翻转后，让start指向没翻转前链表最后一个节点的下一个节点next（这个是我们要在翻转前记录下来的）。

整个过程，我们用head节点进行遍历即可。

```java
 public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        //需要翻转的链表的头节点的前一个节点
        ListNode pre = dummy;
        int cnt = 1;

        while ( head.next != null ) {
            head = head.next;
            cnt++;
            //3 2 1 4 5
            if ( cnt == k ) {
                //保存要接下去的节点
                ListNode next = head.next;
                //原链表的头节点，反转链表后变成最后一个节点
                ListNode start = pre.next;
                
                //翻转需要判空
                head.next = null;

                //返回的是反转后的头节点
                pre.next = reverseList(pre.next);
                
                //原链表的头节点，反转链表后变成最后一个节点,直接指向要接下去的节点
                start.next = next;

				//如果next已经为空，则链表已经遍历完成
                if ( next == null ) break;
                
                
                cnt = 1;
                
                head = next;
                pre = start;
            }
        }

        return dummy.next;

    }

     public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while ( cur != null ) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;

    }
```

[#61 旋转链表](https://leetcode-cn.com/problems/rotate-list/)
做过上面那题，这题就变得简单过了。

我们只要向右移动k%count个位置就可以了。

```java
public static ListNode rotateRight(ListNode head, int k) {
        if ( head == null ) return head;
			
		//记录链表长度
        int count = 1;
        
        ListNode end = head;
        ListNode newHead = head;
        ListNode pre = head;


        while ( end.next != null ) {
        	//链表尾节点
        	end = end.next;
            count++;
        }

        if ( 0 == ( k = k % count ) ) return head;

        System.out.println(count);
        System.out.println("end" + end.val);

        for ( int i = 1 ; i <= (count-k) ; i++ ) {
            pre = newHead;
            //链表新头节点
            newHead = newHead.next;
        }

        pre.next = null;
        end.next = head;

        return newHead;
    }
```

