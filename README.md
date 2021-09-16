# 计算机课程资源分享网站
> 为避免文件上传占用服务器的存储过大，在项目部署在服务器上的时候我将这功能注释了，未来可以使用七牛云作为图床。

## 一、项目介绍

ishare 是一个计算机课程资源分享网站。用户在通过邮箱注册登录以后，
可以查看平台上的所有课程资源。把自己喜欢的课程收藏以后，就可以获取课程的网盘链接和提取码。用户也可以上传自己的课程，分享给平台的所有用户。当课程资源失效后，用户可以通过留言板留言具体是哪个资源失效。如果有其他用户回复该用户的留言后，会有邮箱提示用户。用户也可以对自己的留言删除或点赞，点赞的功能的出现是如果存在热门课程的失效，可以让更多的其他用户及时补链接。





## 二、项目功能截图

1. 首页

    <img src="https://gitee.com/Roroldo/ImgRepo/raw/master/image-20210901151124881.png" alt="image-20210901151124881" style="zoom:50%;" />

2. 用户的登录注册：

<img src="https://gitee.com/Roroldo/ImgRepo/raw/master/image-20210901150917832.png" alt="image-20210901150917832" style="zoom:67%;" />

<img src="https://gitee.com/Roroldo/ImgRepo/raw/master/image-20210901151016350.png" alt="image-20210901151016350" style="zoom: 50%;" />

3. 搜索课程信息：

    <img src="https://gitee.com/Roroldo/ImgRepo/raw/master/image-20210901151438187.png" alt="image-20210901151438187" style="zoom: 33%;" />

    

4. 查看具体的课程：

    <img src="https://gitee.com/Roroldo/ImgRepo/raw/master/image-20210901151239788.png" alt="image-20210901151239788" style="zoom: 33%;" />

5. 用户在登陆以后，可以收藏课程和管理课程：

    <img src="https://gitee.com/Roroldo/ImgRepo/raw/master/image-20210901153218559.png" alt="image-20210901153218559" style="zoom:80%;" />

    <img src="https://gitee.com/Roroldo/ImgRepo/raw/master/image-20210901152635021.png" alt="image-20210901152635021" style="zoom: 67%;" />

6. 上传课程：

    <img src="https://gitee.com/Roroldo/ImgRepo/raw/master/image-20210901153318159.png" alt="image-20210901153318159" style="zoom:80%;" />

7. 管理上传课程：

    <img src="C:\Users\落霞不孤\AppData\Roaming\Typora\typora-user-images\image-20210901153437391.png" alt="image-20210901153437391" style="zoom: 67%;" />8

8. 留言板

    <img src="https://gitee.com/Roroldo/ImgRepo/raw/master/image-20210901153639877.png" alt="image-20210901153639877"  />

    <img src="https://gitee.com/Roroldo/ImgRepo/raw/master/image-20210901153603196.png" alt="image-20210901153603196" style="zoom: 67%;" />

    



## 三、技术介绍

* **Web层**
    * Servlet：控制器
    * Jsp：视图层（html + css + js + jquery + ajax），前端框架 Bootstrap
    * Filter：过滤器
    * BeanUtils：java bean 数据封装
    * Jackson：json 序列化工具
    * common-fileupload、common-io：文件上传工具
* **Service 层**
    * JavaMail：java 发送邮件工具
    * Redis：Nosql 内存数据库
    * Jedis：java 的连接 redis 的客户端
* **Dao层**
    * MySQL 数据库
    * Druid 数据库连接池
    * SpringJDBC：简化数据库的操纵。

采用 maven 管理项目，用的是 tomcat 7 插件。版本改良：采用七牛云图床作为存储中心。



## 四、如何部署项目

1. 导入 sql 文件；
2. 更改 resources 文件夹下的 MySQL 和 Jedis 的配置信息；
3. 修改 MailUtil 为自己的邮箱，并开启 SMTP 服务。具体怎么开启，请参考百度。



## 四、项目总结

遇到的问题：

1. 使用了反射知识避免大量 Servlet 的出现，可以实现一张表对应一个 servlet；
2. tomcat7 的 Get 请求携带中文参数乱码问题，需要自己手动重新编码；
3. Jedis 客户端连接用完以后没有及时释放，导致连接池资源不够用，造成导航栏无法显示；
4. SpringJDBC 查询数据时，如果没有查到会抛出异常，需要处理；
5. 前端 CSS 样式失效问题：转发导致地址不对，解决方法是把所有引入静态文件的地方加上项目的绝对路径
6. 使用线程池，使邮件发送和数据库操作变成异步操作，提升用户体验
7. 解决项目部署上线以后邮箱服务不能用问题。参考：https://www.it610.com/article/1280180696450613248.htm


