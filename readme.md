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

## 抽象类和接口
![](https://uploadfiles.nowcoder.com/images/20200122/2577961_1579696469496_072774B6B658B3603E1AA7198722775C)

在JDK1.8之前的版本（不包括JDK1.8），接口中不能有静态方法，抽象类中因为有普通方法，故也可以有静态方法。

在JDK1.8后（包括JDK1.8），在抽象类中依旧可以有静态方法，同时在接口中也可以定义静态方法了。 

 在JDK1.8，接口中新加了默认方法和静态方法：

 默认方法：使用default修饰，在接口的实现类中，可以直接调用该方法，也可以重写该方法。

 静态方法：使用static修饰，通过接口直接调用。
        
在JDK1.9，接口中新加了私有方法，使用private修饰，私有方法供接口内的默认方法调用

- 抽象类中的抽象方法（其前有abstract修饰）不能用private、static、synchronized、native访问修饰符修饰。原因如下：

抽象方法没有方法体，是用来被继承的，所以不能用private修饰；

static修饰的方法可以通过类名来访问该方法（即该方法的方法体），抽象方法用static修饰没有意义；

使用synchronized关键字是为该方法加一个锁。而如果该关键字修饰的方法是static方法。则使用的锁就是class变量的锁。如果是修饰类方法。则用this变量锁。但是抽象类不能实例化对象，因为该方法不是在该抽象类中实现的。是在其子类实现的。所以。锁应该归其子类所有。所以。抽象方法也就不能用synchronized关键字修饰了；

native，这个东西本身就和abstract冲突，他们都是方法的声明，只是一个吧方法实现移交给子类，另一个是移交给本地操作系统。如果同时出现，就相当于即把实现移交给子类，又把实现移交给本地操作系统，那到底谁来实现具体方法呢？

- 接口是一种特殊的抽象类，接口中的方法全部是抽象方法（但其前的abstract可以省略），所以抽象类中的抽象方法不能用的访问修饰符这里也不能用。而且protected访问修饰符也不能使用，因为接口可以让所有的类去实现（非继承），不只是其子类，但是要用public去修饰。接口可以去继承一个已有的接口。



![WeChat Image_20220222235926](D:\21.04.14\javaP\java\WeChat Image_20220222235926.png)



![WeChat Image_20220223000106](D:\21.04.14\javaP\java\WeChat Image_20220223000106.png)



  **一．类** 

  **类的修饰符：** 

  **Public**:可以在其他任何类中使用，默认为统一包下的任意类。 

  **Abstract:抽象类**，不能被实例化，可以包含抽象方法，抽象方法没有被实现，无具体功能，只能衍生子类。 

  **Final**:不能被继承。 

  **二．变量** 

  变量修饰符： 

  一个类的成员变量的声明必须在类体中，而不能在方法中，方法中声明的是**局部变量**。 

  1.    可访问修饰符： 

  2.    **static**：**类变量**：一个类所拥有的变量，不是类的每个实例有的变量。类变量是指不管类创建了多少对象，系统仅在第一次调用类的时候为类变量分配内存，所有对象共享该类的类变量，因此可以通过类本身或者某个对象来访问类变量。 

  3.    **final**：**常量**。 

  4.    **volatile**：声明一个可能同时被并存运行的几个线程所控制和修改的变量。 

  **实例变量**：和类变量对应，即每个对象都拥有各自独立的实例变量。 

  **三．方法：**（和变量对象分为实例方法和类方法，并用有无static修饰区别） 

  **类方法**：使用static关键字说明的方法 

  1.第一次调用含类方法的类是，系统只为该类创建一个版本，这个版本被该类和该类的所有实例共享。 

  2.类方法只能操作类变量，不能访问实例变量。类方法可以在类中被调用，不必创建实例来调用，当然也可以通过对象来调用。 

  **实例方法**：实例方法可以对当前对象的实例变量操作，而且可以访问类变量。 

  方法可以**重载**，要求：方法名相同，但是参数必须有区别。（参数不同可以使类型不同，顺序不同，个数不同） 

  方法的返回类型：若无返回类型，则声明为void. 

  方法中的变量作用域： 

  1.    成员变量：整个类。 

  2.    局部变量：定义起到方法块结束为止。 

  3.    方法参数：整个方法或者构造方法。 

  4.    异常处理参数：参数传递给异常处理方法。 

 构造方法：和类同名的方法。为新建对象开辟内存空间后，用于初始化新建的对象。不能用对象显式的调用。

静态初始化器：格式：static{<赋值语句组>} 
	


方法的修饰符：

抽象方法：用abstract修饰，只有声明部分，方法体为空，具体在子类中完成。

类方法：静态方法，用static修饰，

1.       调用时，使用类名作为前缀，而不是类的某个实例对象名

2.       不能被单独对象拥有，属于整个类共享。

3.       不能处理成员变量。

最终方法：用final修饰，不能被子类重新定义的方法。

本地方法：用native修饰的方法，表示用其他语言书写的特殊方法，包括C，C++，FORTRAN，汇编语言等。 



  **四．类成员的访问控制符**： 

  即类的方法和成员变量的访问控制符，一个类作为整体对象不可见，并不代表他的所有域和方法也对程序其他部分不可见，需要有他们的访问修饰符判断。 

  权限如下： 

![WeChat Image_20220223000629](D:\21.04.14\javaP\java\WeChat Image_20220223000629.png)

## 异常

运行时异常，可以通过java虚拟机来自行处理。
非运行时异常，我们应该捕获或者抛出。

Java异常都继承自类Throwable，Throwable子类有Error和Exception，其中Exception又分为运行时异常和编译时异常。
编译时异常是未雨绸缪性质的异常，是防范，需要显示处理。运行时异常是程序员问题造成，并不强制进行显示处理。 

![](https://uploadfiles.nowcoder.com/images/20180723/3807435_1532313263755_04B2BC932B6C0C76EC4ED0586D74C8FD)

## Collection 接口常用的方法 


  1. size():返回集合中元素的个数

  2. add(Object obj):向集合中添加一个元素
    
  3. addAll(Colletion coll):将形参coll包含的所有元素添加到当前集合中
    
  4. isEmpty():判断这个集合是否为空
    
  5. clear():清空集合元素
    
  6. contains(Object obj):判断集合中是否包含指定的obj元素
      ① 判断的依据：根据元素所在类的equals()方法进行判断
      ②明确：如果存入集合中的元素是自定义的类对象，要去：自定义类要重写equals()方法
   
  7. constainsAll(Collection coll):判断当前集合中是否包含coll的所有元素
    
  8. rentainAll(Collection coll):求当前集合与coll的共有集合，返回给当前集合
    
  9. remove(Object obj):删除集合中obj元素，若删除成功，返回ture否则
   
  10. removeAll(Collection coll):从当前集合中删除包含coll的元素
    
  11. equals(Object obj):判断集合中的所有元素 是否相同
    
  12. hashCode():返回集合的哈希值
    
  13. toArray(T[] a):将集合转化为数组
      ①如有参数，返回数组的运行时类型与指定数组的运行时类型相同。
    
  14. iterator():返回一个Iterator接口实现类的对象,进而实现集合的遍历。
   
  15. 数组转换为集合：Arrays.asList(数组) 

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

## java虚拟机
![](https://uploadfiles.nowcoder.com/images/20181010/5032673_1539139922699_59B2900AA03CB2182A51CDB520B535B6)

Java运行时内存
1. 程序计数器，线程私有。可以看作是当前线程所执行的字节码的行号指示器，字节码解释器工作时就是通过改变整个计数器的值来选取下一条需要执行的字节码指令，分支、循环、跳转、异常处理、线程恢复等功能。
由于JVM的多线程是通过线程轮流切换并分配处理器执行时间的方式来实现的，在任何一个确定的时刻，一个处理器（对于多核处理器来说是一个内核）都只会执行一条线程中的指令。因此为了线程切换后能恢复到正确的执行位置，每条线程都需要一个独立的程序计数器，各条线程之间计数器互不影响，独立存储，所以线程私有。
程序计数器是唯一一个在Java虚拟机规范中没有规定任何OOM的区域。
2. Java虚拟机栈，线程私有。生命周期和线程相同。虚拟机栈描述的是Java方法执行的内存模型，每个方法在执行的同时都会创建一个栈帧。每个方法从调用到执行完成的过程，就对应着一个栈帧在虚拟机中入栈到出栈的过程。
3. 本地方法栈，线程私有。和虚拟机栈的区别就在于一个是为了执行Java方法服务，一个是为了虚拟机使用到的Native方法服务。
4. 堆，线程共享。存放对象实例和数组。
5. 方法区，线程共享。存放已经被JVM加载的类信息，常量，静态变量，即时编译器编译后的代码。


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
