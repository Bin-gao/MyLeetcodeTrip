## HashMap和HashTable
1. 关于HashMap的一些说法： 
	a)  HashMap实际上是一个“链表散列”的数据结构，即数组和链表的结合体。
	  HashMap的底层结构是一个数组，数组中的每一项是一条链表。 
	b)  HashMap的实例有俩个参数影响其性能： “初始容量” 和 装填因子。 
	c)  HashMap实现不同步，线程不安全。  HashTable线程安全。
    d)  HashMap中的key-value都是存储在Entry中的。 
    e)  HashMap可以存null键和null值，不保证元素的顺序恒久不变，它的底层使用的是数组和链表，通过hashCode()方法和equals方法保证键的唯一性 
    f)  解决冲突主要有三种方法：定址法，拉链法，再散列法。
    HashMap是采用拉链法解决哈希冲突的。 
    注：
    /** 链表法是将相同hash值的对象组成一个链表放在hash值对应的槽位。**
    /**用开放定址法解决冲突的做法是：当冲突发生时，使用某种探查(亦称探测)技术在散列表中形成一个探查(测)序列。 沿此序列逐个单元地查找，直到找到给定 的关键字，或者碰到一个开放的地址(即该地址单元为空)为止（若要插入，在探查到开放的地址，则可将待插入的新结点存人该地址单元）。**/
    /**拉链法解决冲突的做法是： 将所有关键字为同义词的结点链接在同一个单链表中 。若选定的散列表长度为m，则可将散列表定义为一个由m个头指针组成的指针数 组T[0..m-1]。凡是散列地址为i的结点，均插入到以T[i]为头指针的单链表中。T中各分量的初值均应为空指针。在拉链法中，装填因子α可以大于1，但一般均取α≤1。拉链法适合未规定元素的大小。**/
   
2.  Hashtable和HashMap的区别：
     a)   继承不同。  public class Hashtable extends Dictionary implements Map 
                public class HashMap extends  AbstractMap implements Map 
     b)  Hashtable中的方法是同步的，而HashMap中的方法在缺省情况下是非同步的。在多线程并发的环境下，可以直接使用Hashtable，但是要使用HashMap的话就要自己增加同步处理了。
     c)  Hashtable 中， key 和 value 都不允许出现 null 值。 在 HashMap 中， null 可以作为键，这样的键只有一个；可以有一个或多个键所对应的值为 null 。当 get() 方法返回 null 值时，即可以表示 HashMap 中没有该键，也可以表示该键所对应的值为 null 。因此，在 HashMap 中不能由 get() 方法来判断 HashMap 中是否存在某个键， 而应该用 containsKey() 方法来判断。 
     d)  两个遍历方式的内部实现上不同。Hashtable、HashMap都使用了Iterator。而由于历史原因，Hashtable还使用了Enumeration的方式 。
     e)  哈希值的使用不同，HashTable直接使用对象的hashCode。而HashMap重新计算hash值。 
     f)  Hashtable和HashMap它们两个内部实现方式的数组的初始大小和扩容的方式。HashTable中hash数组默认大小是11，增加的方式是old*2+1。
    HashMap中hash数组的默认大小是16，而且一定是2的指数。 

/**注：  HashSet子类依靠hashCode()和equal()方法来区分重复元素。**/  
HashSet内部使用Map保存数据，即将HashSet的数据作为Map的key值保存，这也是HashSet中元素不能重复的原因。而Map中保存key值的,会去判断当前Map中是否含有该Key对象，内部是先通过key的hashCode,确定有相同的hashCode之后，再通过equals方法判断是否相同。

![](https://uploadfiles.nowcoder.com/images/20200807/9020120_1596779885503_A80B4ADCAD66C9260793FE489DB233A2)

 什么时候会产生冲突

HashMap中调用 hashCode() 方法来计算hashCode。
由于在Java中两个不同的对象可能有一样的hashCode,所以不同的键可能有一样hashCode，从而导致冲突的产升。
HashMap底层是 数组和链表 的结合体。底层是一个线性数组结构，数组中的每一项又是一个链表。当新建一个HashMap的时候，就会初始化一个数组。数组是 Entry[] 数组，静态内部类。 E ntry就是数组中的元素，每个 Map.Entry 其实就是一个key-value对，它持有一个指向下一个元素的引用 next ，这就构成了链表。所以 很明显是链地址法。 

源码是这么描述的：ArrayList 继承了 AbstractList， 其中AbstractList 中有个modCount 代表了集合修改的次数。在ArrayList的iterator方法中会判断 expectedModCount与 modCount是否相等，如果相等继续执行，不相等报错，只有iterator的remove方***在调用自身的remove之后让 expectedModCount与modCount再相等，所以是安全的。

## 内部类

在Java中，可以将一个类定义在另一个类里面或者一个方法里边，这样的类称为内部类，广泛意义上的内部类一般包括四种：成员内部类，局部内部类，匿名内部类，静态内部类 。

1.成员内部类

（1）该类像是外部类的一个成员，可以无条件的访问外部类的所有成员属性和成员方法（包括private成员和静态成员）；

（2）成员内部类拥有与外部类同名的成员变量时，会发生隐藏现象，即默认情况下访问的是成员内部类中的成员。如果要访问外部类中的成员，需要以下形式访问：【外部类.this.成员变量  或  外部类.this.成员方法】；

（3）在外部类中如果要访问成员内部类的成员，必须先创建一个成员内部类的对象，再通过指向这个对象的引用来访问；

（4）成员内部类是依附外部类而存在的，也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象；

（5）内部类可以拥有private访问权限、protected访问权限、public访问权限及包访问权限。如果成员内部类用private修饰，则只能在外部类的内部访问；如果用public修饰，则任何地方都能访问；如果用protected修饰，则只能在同一个包下或者继承外部类的情况下访问；如果是默认访问权限，则只能在同一个包下访问。/**外部类只能被public和包访问两种权限修饰。

2.局部内部类

（1）局部内部类是/**定义在一个方法或者一个作用域里面的类**/，它和成员内部类的区别在于局部内部类的访问仅限于方法内或者该作用域内；

（2）局部内部类就像是方法里面的一个局部变量一样，是不能有public、protected、private以及static修饰符的。

3.匿名内部类

（1）一般使用匿名内部类的方法来编写事件监听代码；

（2）匿名内部类是不能有访问修饰符和static修饰符的；

（3）匿名内部类是唯一一种没有构造器的类；

（4）匿名内部类用于继承其他类或是实现接口，并不需要增加额外的方法，只是对继承方法的实现或是重写。

4.内部静态类

（1）静态内部类是不需要依赖于外部类的，这点和类的静态成员属性有点类似；

（2）不能使用外部类的非static成员变量或者方法。


## ==和equals
`public class Demo {`
    `public static void main(String args[]) {`
        `String str1 = new String("hello");`
        `String str2 = new String("hello");`
        `String str3 = "hello";`
        `String str4 = "hello";`
        `String str5 = "he"+"llo";`
        `String str6 = "he";`
        `String str7 = "llo";`
        `System.out.println(str1==str2);//false`
        `System.out.println(str1==str3);`//false
        `System.out.println(str3==str4);`//true
        `System.out.println(str3=="hello");`//true
        `System.out.println(str4==(str6+str7));`//false
    `}`
`}`

## java参数
Java参数，不管是原始类型还是引用类型，传递的都是副本(有另外一种说法是传值，但是说传副本更好理解吧，传值通常是相对传址而言)。

如果参数类型是原始类型，那么传过来的就是这个参数的一个副本，也就是这个原始参数的值，这个跟之前所谈的传值是一样的。如果在函数中改变了副本的 值不会改变原始的值。

如果参数类型是引用类型，那么传过来的就是这个引用参数的副本，这个副本存放的是参数的地址。如果在函数中没有改变这个副本的地址，而是改变了地址中的 值，那么在函数内的改变会影响到传入的参数。如果在函数中改变了副本的地址，如new一个，那么副本就指向了一个新的地址，此时传入的参数还是指向原来的 地址，所以不会改变参数的值。


## 调用构造方法
1.使用new创建对象会自动调用构造方法
   Person person=new Person();
2.使用Class类的newInstance方调用构造方法
   Person person=(Person) Class.forName("com.Person").newInstance();
3.用反射中的constructor类 的newInstance 调用构造方法
Constructor  constructor=Person.class.getConstructor ();
Person person=constructor.newInstance();
2.3均属于反射机制
上面三中创建对象的时候回自动调用构造方法，
但不是所有的创建对象的时候会调用，比如clone方法（对象的复制），
序列化的时候也会创建对象，这是有jvm创建的，所以也不会调用 


## 数据类型
![](https://uploadfiles.nowcoder.com/images/20160818/787709_1471525792085_AB322A66BF8C4D77C58DCBA0353D0B44)


## java导包

java.lang包是java语言包，是自动导入的。
java.util包是java的工具包，需要手动导入。
java.sql包，JDBC接口类，需要手动导入。
java.io;各种输入输入流，需要手动导入。

java.lang包是java语言的核心包，lang是language的缩写 java.lang包定义了一些基本的类型，包括Integer,String之类的，是java程序必备的包，有解释器自动引入，无需手动导入

## Servlet
HttpServlet容器响应Web客户请求流程如下：

1）Web客户向Servlet容器发出Http请求；

2）Servlet容器解析Web客户的Http请求；

3）Servlet容器创建一个HttpRequest对象，在这个对象中封装Http请求信息；

4）Servlet容器创建一个HttpResponse对象；

5）Servlet容器调用/**HttpServlet的service方法，这个方法中会根据request的Method来判断具体是执行doGet还是doPost，把HttpRequest和HttpResponse对象作为service方法的参数传给HttpServlet对象；**/

6）HttpServlet调用HttpRequest的有关方法，获取HTTP请求信息；

7）HttpServlet调用HttpResponse的有关方法，生成响应数据；

8）Servlet容器把HttpServlet的响应结果传给Web客户。


##  事务属性的种类：传播行为、隔离级别、只读和事务超时

a)   传播行为定义了被调用方法的事务边界。

传播行为
	

意义

PROPERGATION_MANDATORY
表示方法必须运行在一个事务中，如果当前事务不存在，就抛出异常

PROPAGATION_NESTED
表示如果当前事务存在，则方法应该运行在一个嵌套事务中。否则，它看起来和 PROPAGATION_REQUIRED 看起来没什么俩样

PROPAGATION_NEVER
表示方法不能运行在一个事务中，否则抛出异常

PROPAGATION_NOT_SUPPORTED
表示方法不能运行在一个事务中，如果当前存在一个事务，则该方法将被挂起

PROPAGATION_REQUIRED
表示当前方法必须运行在一个事务中，如果当前存在一个事务，那么该方法运行在这个事务中，否则，将创建一个新的事务

PROPAGATION_REQUIRES_NEW
表示当前方法必须运行在自己的事务中，如果当前存在一个事务，那么这个事务将在该方法运行期间被挂起

PROPAGATION_SUPPORTS
表示当前方法不需要运行在一个是事务中，但如果有一个事务已经存在，该方法也可以运行在这个事务中

b)   隔离级别

在操作数据时可能带来 3 个副作用，分别是脏读、不可重复读、幻读。为了避免这 3 中副作用的发生，在标准的 SQL 语句中定义了 4 种隔离级别，分别是未提交读、已提交读、可重复读、可序列化。而在 spring 事务中提供了 5 种隔离级别来对应在 SQL 中定义的 4 种隔离级别，如下：

隔离级别
	

意义

ISOLATION_DEFAULT
	
使用后端数据库默认的隔离级别

ISOLATION_READ_UNCOMMITTED	
允许读取未提交的数据（对应未提交读），可能导致脏读、不可重复读、幻读

ISOLATION_READ_COMMITTED
允许在一个事务中读取另一个已经提交的事务中的数据（对应已提交读）。可以避免脏读，但是无法避免不可重复读和幻读

ISOLATION_REPEATABLE_READ
一个事务不可能更新由另一个事务修改但尚未提交（回滚）的数据（对应可重复读）。可以避免脏读和不可重复读，但无法避免幻读

ISOLATION_SERIALIZABLE
这种隔离级别是所有的事务都在一个执行队列中，依次顺序执行，而不是并行（对应可序列化）。可以避免脏读、不可重复读、幻读。但是这种隔离级别效率很低，因此，除非必须，否则不建议使用。

 
c)    只读

如果在一个事务中所有关于数据库的操作都是只读的，也就是说，这些操作只读取数据库中的数据，而并不更新数据，那么应将事务设为只读模式（ READ_ONLY_MARKER ） , 这样更有利于数据库进行优化 。

因为只读的优化措施是事务启动后由数据库实施的，因此，只有将那些具有可能启动新事务的传播行为 (PROPAGATION_NESTED 、 PROPAGATION_REQUIRED 、 PROPAGATION_REQUIRED_NEW) 的方法的事务标记成只读才有意义。

如果使用 Hibernate 作为持久化机制，那么将事务标记为只读后，会将 Hibernate 的 flush 模式设置为 FULSH_NEVER, 以告诉 Hibernate 避免和数据库之间进行不必要的同步，并将所有更新延迟到事务结束。

d)   事务超时

如果一个事务长时间运行，这时为了尽量避免浪费系统资源，应为这个事务设置一个有效时间，使其等待数秒后自动回滚。与设

置“只读”属性一样，事务有效属性也需要给那些具有可能启动新事物的传播行为的方法的事务标记成只读才有意义。 
