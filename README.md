# 个人对团体

本系统选题是12自然语言处理：对个人博客的内容进行处理，筛选出最适合用户，以及搜索引擎。是个人面向团体的博客操作系统。



# 项目思路：

​    本人英语一直不是很好，一次机会下，请教到大四回来的学长，问到了一些关于英语阅读的技巧，同时有了一些想法，想把这个想法应用到这次J2EE的期末项目上来。
​    学长说，在做英语阅读的时候，我们的侧重点主要放在动词上面，作为名词的主语，我们主要用来定位问题在文章的那个地方，并且通常会被同义替换。而一个动词，则决定了这个选项是不是对的。比如：小明在房间里睡觉。如果碰到选项是小明在家里打游戏。打游戏和睡觉两个动词相悖，所以我们基本上可以断定这个选项是错的了。
​    因此我对于现在的这个博客系统的理解是，将标题，简介，内容进行语法的分析。

- 对于标题：当你使用搜索引擎的时候，标题中出现关键字的权重是更大的，对于博客系统来说，一般动词出现的可能性不大，就算出现的话也不会是我们的重点，因此我们尽量的降低它的权重，而对于名词来说则不一样，对详细博文的描述上来说，名词的出现的频率比较高，也是比较重要的，因此我们增加对它的搜索权重。
- 对于简介：我们和标题的筛查其实是差不多的。侧重点不同的是，在主观思路上的不同，作为搜索引擎搜索出来的内容，标题的做出现的权重肯定是要比简介出现的权重要大，在细节方面，简介出现动词的可能性是明显要高于标题的，我的做法是，适当放大动词的权重。
- 对于内容：由于内容上可能会出现串联到别的知识点。所以我对内容的所有权重占比是最低的。





# 引用

> hanLP:<http://www.hankcs.com/>
>
> elasticsearch 6.4 文档：<https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.4/_search_apis.html>
>
> elasticsearch 6.4 中文：
>
> <https://www.elastic.co/guide/cn/elasticsearch/guide/current/intro.html>
>
> Aho-Corasick自动机算法：<https://blog.csdn.net/lemon_tree12138/article/details/49335051>
>
> KMP算法思路：
>
> <https://qwhai.blog.csdn.net/article/details/48488813>
>
> springcloud官方文档：<https://spring.io/projects/spring-cloud>
>
> 前端模板：<http://sc.chinaz.com/>
>
> 仍有部分引用遗漏！抱歉！









# 主要思想：

### 搜索------不止分词

词图：一个词A的下一个词可能是B的话，那么A和B之间具有一条路径E(A,B)。一个词可能有多个后续，同时也可能有多个前驱，它们构成的图我称作词图。

![1559396390837](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559396390837.png)



### 从朴素模式匹配算法到Aho-Corasick算法

朴素模式：算法——逐个比较。因为使用了嵌套循环，所以效率比较低。我们假定主字符串的长度为n，匹配模式字符串的长度为m。那么对于上面的算法，时间复杂度就是O(m*n)。对于一些需求不太严苛或是m,n比较小的情况下。这种算法还是可以接受的。但是如果不是上述情况，这样的一个时间复杂度就不合适了。

KMP算法的关键是为我们排除了一些重复匹配，使用主字符串的匹配位置“指针”不需要回溯。由于在数据结构中大家应该都学过了，这里不多赘述。

Aho-Corasick是基于Trie树且是KMP模式匹配算法的扩展：Trie树的核心点是状态转移，KMP模式匹配的核心点是减少重复匹配。

> 详细描述了AC算法：https://blog.csdn.net/lemon_tree12138/article/details/49335051

- success:从一个状态成功转移到另一个状态(有时也叫goto表或是success表)。

- failure:从一个状态按照普通流程会匹配失败，这时我们要通过failure函数来做状态跳转。

- emits:命中一个模式串(也称为output表)。



###  自动摘要的功能:

​	利用了学术界评判学术论文重要性的方法：看论文被引用的次数。同样适用于网页的判断，如果一个网页被很多其他网页链接到的话说明这个网页比较重要，PageRank值比较高，而这个PageRank值比较高的网页链接到的网页同样PageRank值也会相应的提高。
​    而TextRank的打分思想就是从PageRank的迭代思想衍生过来多的。如果一个单词出现在很多单词后面的话，那么说明这个单词比较重要一个。如果TextRank值很高的单词后面跟着的一个单词，那么这个单词的TextRank值会相应地因此而提高

​    正规的TextRank公式在PageRank的公式的基础上，引入了边的权值的概念，代表两个句子的相似度。

​	它的公式是：

![1559394683825](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559394683825.png)

​	TextRank中一个单词i的权重取决于与在i前面的各个点j组成的(j,i)这条边的权重，以及j这个点到其他其他边的权重之和。右侧的求和表示每个相邻句子对本句子的贡献程度。





# 微服务架构

### 各个微服务的作用：

- SpringCloudConfig：作为配置中心调派的
- admin：
- backups：前端界面备份
- common：提供通用的依赖和库
- config：从github上拉去配置文件
- detail：预备微服务，作为将来验证模块的微服务
- eureka：分布式配置中心，所有微服务都会在这里注册
- hystrix：
- item：用户的展示页面
- search：结合了hanLP的elasticsearch的客户机
- zuul：分布式链路中心





### 所用技术

- springcloud，mybatis，elasticsearch，mysql，hanLP，feign，tk-mapper

- git，maven，bootstrap，fastjson，热部署



### 所用思想

- mapreduce







### 优化

- 对于每篇文章，我会给读者推荐在文库中最相近的博文。而且由于相似程度很可能很接近，所以会随机推荐最相近的**一部分**博客

- 对于搜索引擎，当用户搜索的关键字一个匹配度很低的词，我们将会推荐热销博文或者随机推荐或者最贴近的博文





### eureka服务注册与发现

![1559477982958](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559477982958.png)

每个服务都会自动注册到eureka上面，每当有服务宕机就能即使发现。

并且eureka集群自己注册自己，防止eureka自己宕机没能即使发现。





### admin微服务

作为只有自己能登陆的微服务，当有需要的时候登录进行对博客的操作和修改。

主要功能有：

登录：

![1559478664539](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559478664539.png)





查看：

![1559478145120](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559478145120.png)



添加博客：

![1559478585487](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559478585487.png)

删除：

![1559478721795](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559478721795.png) 



编辑：

![1559478760728](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559478760728.png)









###item

用户使用的主要模块：

主要功能有:

展示：

![1559480020688](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559480020688.png)



热门文章：

![1559480006909](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559480006909.png)



所有标签：

![1559480035704](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559480035704.png)



根据标签进行搜索：

![1559482203355](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559482203355.png)



详情内容页面：

![1559480060668](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559480060668.png)



自动推荐：

![1559480082317](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559480082317.png)



评论功能：

![1559480120374](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559480120374.png)



更新评论：

![1559480646567](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559480646567.png)

![1559480730051](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559480730051.png)



搜索引擎，推荐进行强调处理：

![1559482311350](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559482311350.png)







### search微服务

主要的推荐，排序功能在这里实现：

![1559482914973](C:\Users\YuXingZh\AppData\Roaming\Typora\typora-user-images\1559482914973.png)





### 熔断器-防止服务雪崩







### 待完成的部分：

- 验证微服务
- 根据时间的热度进行筛选
- like功能
- 把评论和like功能添加进筛选博客的内容中去