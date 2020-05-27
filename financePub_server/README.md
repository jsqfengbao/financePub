**项目说明** 
- financePub是一个轻量级的，前后端分离的Java快速开发平台,在此平台上开发了收支记账管理软件;
- 支持MySQL、Oracle、SQL Server、PostgreSQL等主流数据库
- 前端地址：https://github.com/jsqfengbao/financePub/financePub_vue
<br> 
<br>
 

**具有如下特点** 
- 收入管理，对多种途径的收入进行汇总分析；
- 支出管理，对不同消费的类型进行分类分析；
- 预算管理，能够对每月、每种消费类型进行提前预算管理；
- 年度预算管理，提前规划每年度的支出，方便进行年度预算管理；
- 汇总分析，包括今日收支管理，年收支管理，支出类别统计、本月收入支出前五项、收入类别分析等等；
- 友好的代码结构及注释，便于阅读及二次开发
- 实现前后端分离，通过token进行数据交互，前端再也不用关注后端技术
- 灵活的权限控制，可控制到页面或按钮，满足绝大部分的权限需求
- 页面交互使用Vue2.x，极大的提高了开发效率
- 完善的代码生成机制，可在线生成entity、xml、dao、service、vue、sql代码，减少70%以上的开发任务
- 引入quartz定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能
- 引入API模板，根据token作为登录令牌，极大的方便了APP接口开发
- 引入Hibernate Validator校验框架，轻松实现后端校验
- 引入云存储服务，已支持：七牛云、阿里云、腾讯云等
- 引入swagger文档支持，方便编写API接口文档
<br> 

**项目结构** 
```
renren-fast
├─db  项目SQL语句
│
├─common 公共模块
│  ├─aspect 系统日志
│  ├─exception 异常处理
│  ├─validator 后台校验
│  └─xss XSS过滤
│ 
├─config 配置信息
│ 
├─modules 功能模块
│  ├─app API接口模块(APP调用)
│  ├─job 定时任务模块
│  ├─oss 文件服务模块
│  └─sys 权限模块
│ 
├─RenrenApplication 项目启动类
│  
├──resources 
│  ├─mapper SQL对应的XML文件
│  └─static 静态资源

```
<br> 

**如何交流、反馈、参与贡献？** 
- 直接在github仓库提交issue或者进入交流群交流反馈
- Git仓库：https://github.com/financePub
- financePub交流QQ群：975355187
- 技术讨论、二次开发等咨询、问题和建议，请移步到人人开源社区，我会在第一时间进行解答和回复！
- 如需关注项目最新动态，请Watch、Star项目，同时也是对项目最好的支持
- 打赏项目，给作者更多开发的动力
<br/>
![输入图片说明](https://s1.ax1x.com/2020/05/27/tEsyyn.png  "在这里输入图片标题")
![输入图片说明](https://s1.ax1x.com/2020/05/27/tEs6Lq.png  "在这里输入图片标题")
<br> 


**技术选型：** 
- 核心框架：Spring Boot 2.1
- 安全框架：Apache Shiro 1.4
- 视图框架：Spring MVC 5.0
- 持久层框架：MyBatis 3.3
- 定时器：Quartz 2.3
- 数据库连接池：Druid 1.0
- 日志管理：SLF4J 1.7、Log4j
- 页面交互：Vue2.x 
<br> 


 **后端部署**
- 通过git下载源码
- idea、eclipse需安装lombok插件，不然会提示找不到entity的get set方法
- 创建数据库finance-pub，数据库编码为UTF-8
- 执行db/finance-pub.sql文件，初始化数据
- 修改application-dev.yml，更新MySQL账号和密码
- Eclipse、IDEA运行FinancePubApplication.java，则可启动项目
- Swagger路径：http://localhost:8080/financepub_server/swagger/index.html

<br> 

 **前端部署**
 - 本项目是前后端分离的，还需要部署前端，才能运行起来
 - 前端下载地址：https://github.com/jsqfengbao/financePub/tree/master/financePub_vue
 - 前端部署完毕，就可以访问项目了，账号：admin，密码：admin
 
 <br>
<br> 

**效果图：**
![输入图片说明](https://s1.ax1x.com/2020/05/27/tEyXBq.png "在这里输入图片标题")
![输入图片说明](https://s1.ax1x.com/2020/05/27/tE62PU.png "在这里输入图片标题")
![输入图片说明](https://s1.ax1x.com/2020/05/27/tEcnZq.png "在这里输入图片标题")
![输入图片说明](https://s1.ax1x.com/2020/05/27/tEHpct.png "在这里输入图片标题")

<br>
