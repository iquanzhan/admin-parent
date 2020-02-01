# admin-parent

## 一、架构模式

admin-apis:集成Swagger实现对接口的定义

admin-common:通用工具类

admin-model：通用Model

admin-server:admin监控

admin-web:web应用 

## 二、接口规范

### 2.1前后端调试模式

本项目中所有接口，采用**restful风格**进行开发。

> 何为restful？请见：https://blog.csdn.net/x541211190/article/details/81141459
>
> 对于restful风格api的设计，推荐阮一峰大神的[RESTFul设计指南](http://www.ruanyifeng.com/blog/2014/05/restful_api.html)

接口是后端程序员开发的，如果等待后端程序员开发完毕，再去开发前端的内容，效率是很低的。

对于前端程序员而言，其实也只是关注请求路径、参数及响应体而已，这就意味着，我们可以根据定义使用工具生成接口文档，前后端程序员按照接口文档进行开发。哪怕以后会有接口的变动也不会很大。

对于前后端联调我们采用如下模式：

```
后端：后端通过集成swagger进行接口的定义，生成接口文档，导入easy-mock中。供前端使用。后端可以根据swagger或[postman](https://www.getpostman.com)配合进行测试。

前端：使用接口时，利用**easy-mock**进行数据请求模拟。前端程序员按照mock模拟的请求数据进行开发。
```

**总体而言前后端一定是独立开发**。

### 2.2请求规范

#### 2.2.1一些约定

1.请求路径尽量采用名词形式（如user、order、article等），尽量少采用动词如（createUser、createOrder等）。

2.路径中有多个单词时使用中划线'-'来连接

3.不允许在路径中出现大写字母（参数名除外）

4.返回的数据尽量使用json，不得使用xml等其他形式

#### 2.2.2基本操作示例定义如下：

```
GET /users                    # 获取用户列表
GET /users/{userId}       # 查看单个的用户信息
POST /users                 # 新建一个用户
PUT /users/{userId}       # 全量更新某一个用户信息
PATCH /users/{userId}   # 选择性更新某一个用户信息
DELETE /users/{userId} # 删除某一个用户
```

#### 2.2.3过滤信息、查询参数

```
?limit=10：指定返回记录的数量
?offset=10：指定返回记录的开始位置。
?page=2&per_page=100：指定第几页，以及每页的记录数。
?sortby=name&order=asc：指定返回结果按照哪个属性排序，以及排序顺序。
?animal_type_id=1：指定筛选条件
```

#### 2.2.4响应的数据格式

响应的数据格式如下：

1.**正常的响应**数据示例如下：

```json
{
  	"code":0,
    "msg":"成功",
    "data":[]|{}
}
```

2.**错误提示**及增删改等**执行**操作，数据响应示例如下：

```css
{
  	"code":0,
    "msg":'新增成功',
    "data":null
}
```

3.我们就需要额外**自定义错误码**，示例如下

```json
{
  "code": 40401,
  "msg": "user 11 not found!",
  "data":null
}
```

其中，code为自定义错误码40401，error为其对应的错误内容。40401的前缀404表示资源不存在，01可以表示具体表示user这种资源不存在。

4.分页数据响应如下：

```json
{
  	"code":0,
    "msg":'成功',
    "data":{
    	"total":200,
    	"pageSize":10,
    	"pageNumber":1,
    	"rows":[]
    }
}
```

### 2.3请求头参数

对于api的版本号、安全验证等信息，我们将其放入头信息（HTTP HEADER中），下面给出在参数命名的例子：

```json
X-Token:'125454df45ds4f5s4d5f' 		--用户的登录token
Api-Version:'v1.0.0' 							--api的版本号
```

## 三、依赖集成

### 3.1spring-boot集成

添加parent工程

```xml
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.11.RELEASE</version>
    <relativePath/>
  </parent>
```

设置父工程打包方式为pom

```xml
	<packaging>pom</packaging>
```

添加jdk及编码属性

```xml
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>
```

对于依赖所用版本，采用properties中定义，并引用的方式执行：

```xml
    <properties>
        <commons-lang3.version>3.8.1</commons-lang3.version>
    </properties>

	<dependencies>
        <!--commons工具包-->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>${commons-lang3.version}</version>
        </dependency>
    </dependencies>
```

### 3.2 spring boot test的使用

引入依赖

```xml
        <!-- spring test-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
```

添加单元测试：

```java
/**
 * 测试类示例
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/1 3:01 下午
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class UserControllerTest {

    @Before
    public void initUser() {
    }

    @Test
    public void testAddUser() {

    }
}
```

