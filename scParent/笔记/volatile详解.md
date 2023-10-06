<div id="article_content" class="article_content clearfix">
        <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/kdoc_html_views-1a98987dfd.css">
        <link rel="stylesheet" href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/ck_htmledit_views-dc4a025e85.css">
                <div id="content_views" class="markdown_views prism-atom-one-dark">
                    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
                        <path stroke-linecap="round" d="M5,0 0,2.5 5,5z" id="raphael-marker-block" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path>
                    </svg>
                    <h3><a name="t0"></a><a id="_0"></a>指令重排序</h3> 
<p>​ 重排序通常是编译器或运行时环境为了优化程序性能而采取的对指令进行重新排序执行的一种手段。重排序分为两类：编译期重排序和运行期重排序，分别对应编译时和运行时环境。</p> 
<p>​ 在本线程内观察，所有操作都是有序的；在一个线程观察另一个线程，所有操作都是无序的，无序是因为发生了指令重排序</p> 
<p>​ 编译期重排序的典型就是通过调整指令顺序，在不改变程序语义的前提下，尽可能<strong>减少寄存器的读取、存储次数，充分复用寄存器的存储值</strong></p> 
<pre data-index="0" class="prettyprint"><code class="prism language-java has-numbering" onclick="mdcp.copyCode(event)" style="position: unset;"><span class="token keyword">int</span> i <span class="token operator">=</span> <span class="token number">1</span><span class="token punctuation">;</span>
<span class="token keyword">int</span> j <span class="token operator">=</span> <span class="token number">2</span><span class="token punctuation">;</span>
<span class="token comment">//同一线程内</span>
<div class="hljs-button {2}" data-title="复制"></div></code><ul class="pre-numbering" style=""><li style="color: rgb(153, 153, 153);">1</li><li style="color: rgb(153, 153, 153);">2</li><li style="color: rgb(153, 153, 153);">3</li></ul></pre> 
<p>​ <strong>"int j = 2"的代码完全可能先被处理器执行，这并不影响先行先发生原则，因为我们在这条线程中没有办法感知到这点。（都是赋值操作，无论先发生哪一个，最终结果都与顺序执行一样）。但如果有其他线程在读取这两个值，重排序就会造成读取错误！对于单一线程，如果有办法感知到这点，即 int a = 1;int b = a;（前后有逻辑性，有数据依赖存在，不允许指令重排）那么就不能重排序</strong></p> 
<p>​ 如果两个操作访问同一个变量，且这两个操作中有一个为写操作，此时这两个操作之间就存在数据依赖。数据依赖分下列三种类型：</p> 
<div class="table-box"><table><thead><tr><th>名称</th><th>代码示例</th><th>说明</th></tr></thead><tbody><tr><td>写后读</td><td>a = 1;b = a;</td><td>写一个变量，再读这个位置</td></tr><tr><td>写后写</td><td>a = 1; a = 2;</td><td>写一个变量后，再写这个变量</td></tr><tr><td>读后写</td><td>a = b; b = 1;</td><td>读一个变量之后，再写这个变量</td></tr></tbody></table></div> 
<p>​ 上面三种情况，只要重排序两个操作的执行顺序，程序的执行结果将会被改变。所以，编译器和处理器在重排序时，会遵守数据依赖性，<font color="red">编译器和处理器不会改变存在数据依赖关系的两个操作的执行顺序</font>。</p> 
<ul><li><strong>在单线程环境下 ， 指令执行的最终效果应当与其在顺序执行下的效果一致</strong> ， 否则这种优化便会失去意义。这句话有个专业术语叫做 意义。这句话有个专业术语叫做 as-if-serial semantics(as-if-serial 语义)</li><li>多线程环境中线程交替执行，由于编译器优化重排，会获取其他线程处在不同阶段的指令同时执行<br> 下列代码中的结果为什么<strong>有可能为0</strong></li></ul> 
<pre data-index="1" class="prettyprint"><code class="prism language-java has-numbering" onclick="mdcp.copyCode(event)" style="position: unset;"><span class="token keyword">int</span> num <span class="token operator">=</span> <span class="token number">0</span><span class="token punctuation">;</span>
<span class="token keyword">boolean</span> ready <span class="token operator">=</span> <span class="token boolean">false</span><span class="token punctuation">;</span>
<span class="token comment">// 线程1 执行此方法</span>
<span class="token keyword">public</span> <span class="token keyword">void</span> <span class="token function">actor1</span><span class="token punctuation">(</span><span class="token class-name">I_Result</span> r<span class="token punctuation">)</span> <span class="token punctuation">{<!-- --></span>
	<span class="token keyword">if</span><span class="token punctuation">(</span>ready<span class="token punctuation">)</span> <span class="token punctuation">{<!-- --></span>
		r<span class="token punctuation">.</span>r1 <span class="token operator">=</span> num <span class="token operator">+</span> num<span class="token punctuation">;</span>
	<span class="token punctuation">}</span> <span class="token keyword">else</span> <span class="token punctuation">{<!-- --></span>
		r<span class="token punctuation">.</span>r1 <span class="token operator">=</span> <span class="token number">1</span><span class="token punctuation">;</span>
	<span class="token punctuation">}</span>
<span class="token punctuation">}</span>
<span class="token comment">// 线程2 执行此方法</span>
<span class="token keyword">public</span> <span class="token keyword">void</span> <span class="token function">actor2</span><span class="token punctuation">(</span><span class="token class-name">I_Result</span> r<span class="token punctuation">)</span> <span class="token punctuation">{<!-- --></span>
	num <span class="token operator">=</span> <span class="token number">2</span><span class="token punctuation">;</span>
	ready <span class="token operator">=</span> <span class="token boolean">true</span><span class="token punctuation">;</span>
<span class="token punctuation">}</span>
<div class="hljs-button {2}" data-title="复制"></div></code><ul class="pre-numbering" style=""><li style="color: rgb(153, 153, 153);">1</li><li style="color: rgb(153, 153, 153);">2</li><li style="color: rgb(153, 153, 153);">3</li><li style="color: rgb(153, 153, 153);">4</li><li style="color: rgb(153, 153, 153);">5</li><li style="color: rgb(153, 153, 153);">6</li><li style="color: rgb(153, 153, 153);">7</li><li style="color: rgb(153, 153, 153);">8</li><li style="color: rgb(153, 153, 153);">9</li><li style="color: rgb(153, 153, 153);">10</li><li style="color: rgb(153, 153, 153);">11</li><li style="color: rgb(153, 153, 153);">12</li><li style="color: rgb(153, 153, 153);">13</li><li style="color: rgb(153, 153, 153);">14</li><li style="color: rgb(153, 153, 153);">15</li></ul></pre> 
<p>线程2 先执行了 ready = true（线程二内部发生了指令重排，前后没有数据依赖关系），切换到线程1，进入 if 分支，相加为 0，再切回线程2 执行 num = 2。</p> 
<p><strong>解决</strong>：可以使用 synchronized 修饰这两个方法，或者将 ready 设为 volatile 解决这个问题（由先行先发生原则）</p> 
<p>​ 如果 A happens- before B，JMM 并不要求 A 一定要在 B 之前执行。<strong>JMM 仅仅要求前一个操作（执行的结果）对后一个操作可见</strong>，且前一个操作按顺序排在第二个操作之前。这里操作 A 的执行结果不需要对操作 B 可见；而且重排序操作 A 和操作 B 后的执行结果，与操作 A 和操作 B 按 happens- before 顺序执行的结果一致。在这种情况下，JMM 会认为这种重排序并不非法（not illegal），JMM 允许这种重排序。（<strong>即使在先行先发的原则下，没有数据依赖性且指令执行的最终效果应当与其在顺序执行下的效果一致，也允许重排</strong>）</p> 
<h3><a name="t1"></a><a id="volatile_53"></a>volatile</h3> 
<p>volatile 是 Java 虚拟机提供的<strong>轻量级</strong>的同步机制</p> 
<ul><li>保证可见性</li><li>不保证原子性</li><li>保证有序性（禁止指令重排）</li></ul> 
<h4><a name="t2"></a><a id="_61"></a>可见性</h4> 
<p>可见性：是指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值</p> 
<p>存在不可见的根本原因是由于<strong>缓存（工作内存）的存在</strong>，线程持有的是共享变量的副本，无法感知其他线程对于共享变量的更改，导致读取的值不是最新的。</p> 
<p>​ Volatile <strong>保证可见性</strong>，在工作内存中，每次使用 volatile 变量都必须先从主内存刷新最新值，使用后立刻同步回主内存（<strong>volatile 变量依然有工作内存的拷贝，但由于它特殊的操作顺序（每次使用前都需要刷新），所以看起来如同直接在主内存中读写访问一般</strong>）（<font color="red">将 use 和 load动作相关联，即 use 前必须是 load，load 后必须是 use；将 assign 和 store 相关联，即 store前必须是 assign，assign 后必须是 store，<strong>java模型中规定</strong>不允许 read 和 load、store（存储） 和 write 操作之一单独出现，<strong>即可得出下面三条规则</strong></font>）</p> 
<ul><li>线程对变量的 use 与 load、read 操作是相关联的，所以变量使用前<strong>必须先从主存加载</strong></li><li>线程对变量的 assign 与 store、write 操作是相关联的，所以变量使用后必须同步至主存</li><li>线程 1 和线程 2 谁先对变量执行 read 操作，就会先进行 write 操作，防止指令重排</li></ul> 
<h4><a name="t3"></a><a id="_73"></a>有序性</h4> 
<p>​ Volatile <strong>保证有序性</strong>（禁止指令重排序优化，jkd1.5 以后重新修复）：先行先发生原则中 volatile变量规则：<strong>对一个 volatile 变量的写操作先行发生于（能被后面的读操作感知到）后面（时间上）对这个变量的读操作</strong>。通过<strong>内存屏障</strong>（Memory Barrier）实现，指令重排序时不能把后面的指令重排序到内存屏障之前的位置。</p> 
<ul><li>写屏障（sfence，Store Barrier）保证在<strong>该屏障之前</strong>的，对共享变量的改动，都同步到主存当中，写屏障会确保指令重排序时，不会将写屏障之前的代码排在写屏障之后</li><li>读屏障（lfence，Load Barrier）保证在<strong>该屏障之后</strong>的，对共享变量的读取，从主存刷新变量值，加载的是主存中最新数据，读屏障会确保指令重排序时，不会将读屏障之后的代码排在读屏障之前</li></ul> 
<h4><a name="t4"></a><a id="_80"></a>不保证原子性</h4> 
<p>​ volatile <strong>不能保证原子性</strong>。如果我们对一个 volatile 修饰的变量进行多线程下的自增操作，还是会出现线程安全问题。根本原因在于 volatile 关键字无法对自增进行安全性修饰，因为自增分为三步，读取、+1、写入。中间多个线程同时执行+1 操作，还是会出现线程安全性问题（<strong>无法解决指令交错的问题</strong>）。</p> 
<h4><a name="t5"></a><a id="_84"></a>性能</h4> 
<p>volatile 修饰的变量进行读操作与普通变量几乎没什么差别，但是**写操作相对慢一些，因为需要在本地代码中插入很多内存屏障来保证指令不会发生乱序执行，**但是开销比锁要小</p> 
<h4><a name="t6"></a><a id="volatilesynchronized_88"></a>volatile对比synchronized</h4> 
<ul><li>volatile 轻量级，只能修饰变量。synchronized 重量级，<strong>还可修饰方法</strong></li><li>volatile 只能保证数据的可见性，不能用来同步，因为多个线程并发访问 volatile 修饰的变量不会阻塞。（<strong>常用于循环判断标志</strong>）</li><li>synchronized 不仅保证可见性，而且还保证原子性，因为，只有获得了锁的线程才能进入临界区（操作共享变量的代码段），从而保证临界区中的所有语句都全部执行。多个线程争抢synchronized 锁对象时，会出现阻塞（blocked）。</li></ul> 
<h4><a name="t7"></a><a id="font_colorredfont_94"></a><font color="red">总结</font></h4> 
<ul><li>volatile 可见性（use 和 load和read、assign 和 store和write），</li><li>有序性（禁止重排序，happens-before，内存屏障）</li><li>原子性：synchronized</li><li>可见性：volatile、synchronized、final（ final 修饰的变量是不可变的，就算有缓存，也不会存在不可见的问题）</li><li>有序性：volatile、synchronized（相当于保证只有一个线程执行代码段，单线程的指令重排不影响）</li></ul>
                </div><div data-report-view="{&quot;mod&quot;:&quot;1585297308_001&quot;,&quot;spm&quot;:&quot;1001.2101.3001.6548&quot;,&quot;dest&quot;:&quot;https://blog.csdn.net/sylnb/article/details/129759850&quot;,&quot;extend1&quot;:&quot;pc&quot;,&quot;ab&quot;:&quot;new&quot;}"><div></div></div>
                <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/editerView/markdown_views-98b95bb57c.css" rel="stylesheet">
                <link href="https://csdnimg.cn/release/blogv2/dist/mdeditor/css/style-c216769e99.css" rel="stylesheet">
        </div>