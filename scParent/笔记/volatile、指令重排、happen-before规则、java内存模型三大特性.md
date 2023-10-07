<div id="article_content" class="article_content clearfix">
        <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/kdoc_html_views-1a98987dfd.css">
        <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/ck_htmledit_views-dc4a025e85.css">
                <div id="content_views" class="markdown_views prism-atom-one-light">
                    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                        <path stroke-linecap="round" d="M5,0 0,2.5 5,5z" id="raphael-marker-block" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                    </svg>
                    <p>volatile是Java的一个关键字，它并不是用Java实现的，因为涉及指令重排等更加底层的知识，比起源码更难理解其实现，所以笔者一路看下来，决定自己写一篇文章，总结一下，大致包括的知识点如标题所示，包括了volatile，指令重排，happen before规则，java内存模型三大特性。但是笔者会挑选一个尽量合适的顺序来讲下这些点。</p> 
<h3><a name="t0"></a><a id="java_2"></a>java内存模型三大特性</h3> 
<p>在讲volatile或者synchronized时，常会说到原子性、顺序行、可见性等特性，其实这三个特性指的就是指的Java内存模型的三大特性。</p> 
<p><strong>内存模型</strong><br> 而<strong>Java内存模型（Java Main Memory简称JMM）<strong>是一个</strong>抽象概念</strong>（注意JMM不是实现，JVM才是实现），和计算机的内存模型有很多相似的地方。JMM主要包扩线程、：工作内存、主内存三者来交互，其中工作内存可以类比计算的高速缓存，不过线程间工作内存是互相独立的；主内存类比计算机的主内存，线程间变量值传递主要是通过主内存来完成的。同时JMM也有优化代码执行顺序的指令重排序。简单的说就是代码的编写顺序不一定就是代码的执行顺序。[2]如下图所示[6]：</p> 
<p><img src="https://img-blog.csdnimg.cn/c21c497c214d4417bcf1bac0afd3d1d0.png" alt="在这里插入图片描述"><br> JMM是一种抽象的概念，JVM作为具体的实现不一定会具有这样的结构。如栈帧可能是分配在堆中的（如：CLDC HI）[6]。JVM作为遵循JMM的实现，内存分为私有区和公有区。</p> 
<p><strong>1.原子性</strong><br> <strong>定义：</strong> <strong>即一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。</strong></p> 
<p><strong>这里需要注意的是，所谓的”执行的过程不会被任何因素打断“可以理解为在执行过程中切换线程</strong>。<strong>换句话说，原子性是拒绝多线程操作的，不论是多核还是单核，具有原子性的量，同一时刻只能有一个线程来对它进行操作。简而言之，在整个操作过程中不会被线程调度器中断的操作，都可认为是原子性</strong>。例如 a=1是原子性操作，但是a++和a +=1就不是原子性操作。因为a++实际为a = a + 1;在执行的过程中需要先读取a的值，再计算+1的结果，最后赋值。a可能被其他线程读取值或者修改值，哪怕不影响a++的结果，也不满足原子性。而a=1则不同，直接对变量赋值，只有这一个操作，因此满足原子性。</p> 
<p>Java中的原子性操作包括：<br> （1）基本类型的读取和赋值操作，且赋值必须是值赋给变量，变量之间的相互赋值不是原子性操作。<br> （2）所有引用reference的赋值操作<br> （3）java.concurrent.Atomic.* 包中所有类的一切操作[1]</p> 
<p><strong>2.可见性</strong><br> <strong>定义：指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。</strong></p> 
<p>在多线程环境下，一个线程对共享变量的操作对其他线程是不可见的。Java提供了volatile来保证可见性，当一个变量被volatile修饰后，表示着线程本地内存无效，当一个线程修改共享变量后他会立即被更新到主内存中，其他线程读取共享变量时，会直接从主内存中读取。当然，synchronize和Lock都可以保证可见性。synchronized和Lock能保证同一时刻只有一个线程获取锁然后执行同步代码，并且在释放锁之前会将对变量的修改刷新到主存当中。因此可以保证可见性。</p> 
<p><strong>3.有序性</strong><br> <strong>定义：即程序执行的顺序按照代码的先后顺序执行</strong>。[3]</p> 
<p>在Java内存模型中，为了效率是允许编译器和处理器对指令进行重排序，<strong>当然重排序不会影响单线程的运行结果，但是对多线程会有影响</strong>。Java提供volatile来保证一定的有序性。最著名的例子就是单例模式里面的DCL（双重检查锁）。另外，可以通过synchronized和Lock来保证有序性，synchronized和Lock保证每个时刻是有一个线程执行同步代码，相当于是让线程顺序执行同步代码，自然就保证了有序性。</p> 
<p>所以到这里，我们可以说，volatile满足了可见性和有序性。但是不满足原子性。</p> 
<p>那么上文提到的指令重排是什么呢？</p> 
<h3><a name="t1"></a><a id="_38"></a>指令重排</h3> 
<p>指令重排序是指编译器或CPU为了优化程序的执行性能而对指令进行重新排序的一种手段[4]。Java语言规范JVM线程内部维持顺序化语义，即只要程序的最终结果与它顺序化情况的结果相等，那么指令的执行顺序可以与代码逻辑顺序不一致，这个过程就叫做指令的重排序[5]。注意，它会带来有序性的问题。具体可参考下列代码[4]：</p> 

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
            });
            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            String result = "第" + i + "次(" + x + "," + y + ")";
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            }
        }
    }
}

<div class="hljs-button {2}" data-title="复制"></div></code><ul class="pre-numbering" style=""><li style="color: rgb(153, 153, 153);">1</li><li style="color: rgb(153, 153, 153);">2</li><li style="color: rgb(153, 153, 153);">3</li><li style="color: rgb(153, 153, 153);">4</li><li style="color: rgb(153, 153, 153);">5</li><li style="color: rgb(153, 153, 153);">6</li><li style="color: rgb(153, 153, 153);">7</li><li style="color: rgb(153, 153, 153);">8</li><li style="color: rgb(153, 153, 153);">9</li><li style="color: rgb(153, 153, 153);">10</li><li style="color: rgb(153, 153, 153);">11</li><li style="color: rgb(153, 153, 153);">12</li><li style="color: rgb(153, 153, 153);">13</li><li style="color: rgb(153, 153, 153);">14</li><li style="color: rgb(153, 153, 153);">15</li><li style="color: rgb(153, 153, 153);">16</li><li style="color: rgb(153, 153, 153);">17</li><li style="color: rgb(153, 153, 153);">18</li><li style="color: rgb(153, 153, 153);">19</li><li style="color: rgb(153, 153, 153);">20</li><li style="color: rgb(153, 153, 153);">21</li><li style="color: rgb(153, 153, 153);">22</li><li style="color: rgb(153, 153, 153);">23</li><li style="color: rgb(153, 153, 153);">24</li><li style="color: rgb(153, 153, 153);">25</li><li style="color: rgb(153, 153, 153);">26</li><li style="color: rgb(153, 153, 153);">27</li><li style="color: rgb(153, 153, 153);">28</li><li style="color: rgb(153, 153, 153);">29</li><li style="color: rgb(153, 153, 153);">30</li><li style="color: rgb(153, 153, 153);">31</li><li style="color: rgb(153, 153, 153);">32</li><li style="color: rgb(153, 153, 153);">33</li><li style="color: rgb(153, 153, 153);">34</li></ul></pre> 
<p>运行这一部分代码，会发现没有加volatile的变量无法保证有序性，也就是说由于线程中的2个赋值操作都发生了指令重排，顺序都发生了改变，导致的（0,0）的结果。如果给变量加上volatile则不会发生指令重排，也就不会有有序性的问题。</p> 
<p><strong>注意，我们结合这个代码，以及从原子性、可见性、有序性的定义中可以体会到，这三个特性都是针对的多线程情况下的特性，如果是单线程情况，所谓的原子性、可见性、有序性根本不会存在这样的问题。</strong><br> 例如有序性，在单线程的情况下，即使发生了指令重排，那2行Java代码互换了位置，也不会影响单线程情况下的运行结果（遵循了程序顺序原则），但是会影响多线程情况下的运行结果（不满足有序性）。加了volatile以后，禁止了指令重排，才满足了有序性，对多线程情况下也足以保证按照代码的先后顺序执行。</p> 
<h3><a name="t2"></a><a id="happensbefore__83"></a>happens-before 规则</h3> 
<p>当然指令重排也是需要遵循一些规则的，不是想怎么排就怎么排的。指令重排需要遵循的是happens-before 规则，也就是说前一个操作的结果对后续操作时可见的（当然这里的指的是单线程的情况了，并不涉及复杂的多线程情况）。那么具体需要怎么去做呢，有以下几条规则：</p> 
<ul><li> <p>程序顺序原则：一个线程内保证语义的串行性</p> </li><li> <p>volatile规则：volatile 变量的写，先发生于读，这保证了volatile变量的可见性</p> </li><li> <p>锁规则：解锁（unlock）必然发生在随后的加锁（lock）前</p> </li><li> <p>传递性：A先于B，B先于C，那么A必然先于C</p> </li><li> <p>线程的start（）方法先于它的每一个动作</p> </li><li> <p>线程的所有操作先于线程的终结（Thread.join()）</p> </li><li> <p>线程的中断（interrupt（））先于被中断线程的代码</p> </li><li> <p>对象的构造函数执行,结束先于finalize() 方法</p> </li></ul> 
<p>这里要特别注意的程序顺序规则，因为它只针对单线程的情况，所以上面的代码中，变量没加volatile，发生了指令重排，满足了单线程情况下的程序顺序原则，但是不满足多线程情况下的有序性，因此出现了(0，0)的情况。</p> 
<p><strong>参考资料</strong><br> [1]，<a href="https://blog.csdn.net/u012723673/article/details/80682208">Java volatile关键字最全总结：原理剖析与实例讲解(简单易懂)</a><br> [2]，<a href="https://blog.csdn.net/anyway8090/article/details/96641258">Java内存模型及三大特性</a><br> [3]，<a href="https://blog.csdn.net/u014344668/article/details/109599791">java内存模型的三大特性</a><br> [4]，<a href="https://www.cnblogs.com/zwtblog/p/15619334.html#:~:text=%E6%8C%87%E4%BB%A4%E9%87%8D%E6%8E%92%E5%BA%8F,%E6%98%AF%E6%8C%87%E7%BC%96%E8%AF%91%E5%99%A8%E6%88%96CPU%E4%B8%BA%E4%BA%86%E4%BC%98%E5%8C%96%E7%A8%8B%E5%BA%8F%E7%9A%84%E6%89%A7%E8%A1%8C%E6%80%A7%E8%83%BD%E8%80%8C%E5%AF%B9%E6%8C%87%E4%BB%A4%E8%BF%9B%E8%A1%8C%E9%87%8D%E6%96%B0%E6%8E%92%E5%BA%8F%E7%9A%84%E4%B8%80%E7%A7%8D%E6%89%8B%E6%AE%B5%EF%BC%8C%E9%87%8D%E6%8E%92%E5%BA%8F%E4%BC%9A%E5%B8%A6%E6%9D%A5%E5%8F%AF%E8%A7%81%E6%80%A7%E9%97%AE%E9%A2%98%EF%BC%8C%E6%89%80%E4%BB%A5%E5%9C%A8%E5%A4%9A%E7%BA%BF%E7%A8%8B%E5%BC%80%E5%8F%91%E4%B8%AD%E5%BF%85%E9%A1%BB%E8%A6%81%E5%85%B3%E6%B3%A8%E5%B9%B6%E8%A7%84%E9%81%BF%E9%87%8D%E6%8E%92%E5%BA%8F%E3%80%82">什么是指令重排？</a><br> [5]，<a href="https://www.cnblogs.com/lusaisai/p/12731593.html#:~:text=Java%E8%AF%AD%E8%A8%80%E8%A7%84%E8%8C%83,%E5%81%9A%E6%8C%87%E4%BB%A4%E7%9A%84%E9%87%8D%E6%8E%92%E5%BA%8F%E3%80%82">初识指令重排序,Java 中的锁</a><br> [6]，<a href="https://juejin.cn/post/6844904198337724430">JVM内存模型（JMM）和内存区域，别再傻傻分不清楚</a></p>
                </div><div><div></div></div>
                <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/markdown_views-98b95bb57c.css" rel="stylesheet">
                <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/style-c216769e99.css" rel="stylesheet">
        </div>