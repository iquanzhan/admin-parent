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

目标：实现对相关依赖的集成工作，并可简单使用。

### 3.1 spring-boot集成

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

### 3.3引入common-lang3

引入依赖

```xml
<commons-lang3.version>3.9</commons-lang3.version>
<commons-codec.version>1.13</commons-codec.version>

<!--摘要算法加密解密包-->
<dependency>
    <groupId>commons-codec</groupId>
    <artifactId>commons-codec</artifactId>
    <version>${commons-codec.version}</version>
</dependency>

<!--commons工具包-->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>${commons-lang3.version}</version>
</dependency>
```

常用API参考：

https://blog.csdn.net/qq_35418518/article/details/89519979

### 3.4 hutool集成

Hutool是一个小而全的Java工具类库，通过静态方法封装，降低相关API的学习成本，提高工作效率，使Java拥有函数式语言般的优雅，让Java语言也可以“甜甜的”。

依赖引入：

```
<hutool.version>5.1.2</hutool.version>

<!--hutool工具包-->
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>${hutool.version}</version>
</dependency>
```

具体API参考：

https://hutool.cn/docs/#/

### 3.5 集成JSR-349实现请求参数验证

添加依赖

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

使用：

在pojo或dto对象上添加验证注解

```java
public class Singer {  
    @NotNull(message = "名称不能为空")  
    @Size(min = 2, max = 5)   
    private String fistName;  
    private String lastName;   
    @NotNull(message = "性别不能为空")  
    private Genre genre;
    ....
}
```

常用验证注解包括：

```
1 @Null 被注释的元素必须为 null
2 @NotNull 被注释的元素必须不为 null
4 @AssertTrue 被注释的元素必须为 true
5 @AssertFalse 被注释的元素必须为 false
6 @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
7 @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
8 @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
9 @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
10 @Size(max=, min=) 被注释的元素的大小必须在指定的范围内
11 @Digits (integer, fraction) 被注释的元素必须是一个数字，其值必须在可接受的范围内
12 @Past 被注释的元素必须是一个过去的日期
13 @Future 被注释的元素必须是一个将来的日期
14 @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
15 Hibernate Validator 附加的 constraint
16 @NotBlank(message =) 验证字符串非null，且长度必须大于0
17 @Email 被注释的元素必须是电子邮箱地址
18 @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
19 @NotEmpty 被注释的字符串的必须非空
20 @Range(min=,max=,message=) 被注释的元素必须在合适的范围内
...
```

controller 中添加：

```java
@RestController
public class SingerController { 
    /**    
    * 添加@Valid 注解    
    * @param singer    
    * @return   
    */ 
    @PostMapping("/singer")    
    private String validator(@Valid @RequestBody Singer singer) {      
        System.out.print(singer);     
        return "ok"; 
}
```

此时如果校验失败，会返回JSR349的默认信息。我们需要对其进行统一处理。参见统一异常处理部分

### 3.6 集成Druid数据库连接池

Druid是Java语言中最好的数据库连接池。Druid提供了强大的监控和扩展能力

添加依赖

```xml
<druid.version>1.1.9</druid.version>     

<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>druid-spring-boot-starter</artifactId>
  <version>${druid.version}</version>
</dependency>
```

application.yml

```
spring:
  datasource:
    druid:
      url: jdbc:mysql://127.0.0.1:3306/test?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false
      username: root
      password: 123456
      driver-class-name: com.mysql.cj.jdbc.Driver
      # 初始化物理连接个数
      initial-size: 1
      # 最大连接池数量
      max-active: 20
      # 最小连接池数量
      min-idle: 5
      # 获取连接时最大等待时间(ms)
      max-wait: 60000
      # 开启缓存preparedStatement(PSCache)
      pool-prepared-statements: true
      # 启用PSCache后，指定每个连接上PSCache的大小
      max-pool-prepared-statement-per-connection-size: 20
      # 用来检测连接是否有效的sql
      validation-query: select 'x'
      # 申请连接时不检测连接是否有效
      test-on-borrow: false
      # 归还连接时不检测连接是否有效
      test-on-return: false
      # 申请连接时检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效（不影响性能）
      test-while-idle: true
      # 检测连接的间隔时间，若连接空闲时间 >= minEvictableIdleTimeMillis，则关闭物理连接
      time-between-eviction-runs-millis: 60000
      # 连接保持空闲而不被驱逐的最小时间(ms)
      min-evictable-idle-time-millis: 300000
      # 配置监控统计拦截的filters（不配置则监控界面sql无法统计），监控统计filter:stat，日志filter:log4j，防御sql注入filter:wall
      filters: stat,log4j,wall
      # 支持合并多个DruidDataSource的监控数据
      use-global-data-source-stat: true
      # 通过connectProperties属性来打开mergeSql(Sql合并)功能；慢SQL记录(配置超过5秒就是慢，默认是3秒)
      connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
```

配置

```
/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/22 9:32 下午
 * @Description:
 */
@Configuration
public class DruidConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);

    @Bean
    public ServletRegistrationBean druidServlet() {
        logger.info("init Druid Servlet Configuration ");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP白名单
//        servletRegistrationBean.addInitParameter("allow", "*");
        // IP黑名单(共同存在时，deny优先于allow)
//        servletRegistrationBean.addInitParameter("deny", "192.168.1.100");
        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
```

配置说明：

1.可能有人对PSCache这两项配置存在疑问？

```bash
# 开启缓存preparedStatement(PSCache)
pool-prepared-statements: true
# 启用PSCache后，指定每个连接上PSCache的大小
max-pool-prepared-statement-per-connection-size: 20
```

Druid官方建议对于MySQL数据库，关闭preparedStatement缓存(即PSCache)，即pool-prepared-statements配置为false。原因是：PSCache对支持游标的数据库性能提升巨大，比如说oracle。

访问：

http://localhost:8080/druid/

### 3.7 集成swagger便捷进行接口测试与文档编写

添加依赖

```xml
<swagger.version>2.8.0</swagger.version>

<!--swagger-->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>${swagger.version}</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>${swagger.version}</version>
</dependency>
```

配置

```
@Configuration
@EnableSwagger2
@Profile({"dev", "test"})// 设置 dev test 环境开启 prod 环境就关闭了
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chengxiaoxiao.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档标题")
                .description("接口文档描述")
//                .termsOfServiceUrl("http:/xxx/xxx")
                .contact("作者")
                .version("1.0")
                .build();
    }
}
```

```
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
```

常用注解：

@Api : 用在类上，说明该类的主要作用。

@ApiOperation：用在方法上，给API增加方法说明。

@ApiImplicitParams : 用在方法上，包含一组参数说明。

@ApiImplicitParam：用来注解来给方法入参增加说明。

@ApiResponses：用于表示一组响应。

@ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息

@ApiModel：用在返回对象类上，描述一个Model的信息（一般用在请求参数无法使用@ApiImplicitParam注解进行描述的时候）

 @ApiModelProperty：描述一个model的属性

使用示例：

```java

@RestController
@RequestMapping("emp")
@Api(value = "用户管理类")
public class EmployeeController {
 @Autowired
 private EmployeeReposiroty employeeReposiroty;
      /**
      * 增加人物
      * @param employee
      * @return
      */
     @PostMapping(value = "employee")
     @ApiOperation(value = "新增一个用户",notes = "新增之后返回对象")
     @ApiImplicitParam(paramType = "query",name = "employee",value = "用户",required = true)
     public String insert(Employee employee){
         
     }
      /**
      * 删除单个用户
      * @param id
      * @return
      */
      @DeleteMapping(value = "employee/{id}")
      @ApiOperation(value = "删除用户",notes = "根据成员id删除单个用户")
      @ApiImplicitParam(paramType = "path",name = "id",value = "用户id",required = true,dataType = "Integer")
      public String delete(@PathVariable("id")Integer id){
           
      }

      /**
      * 修改单个成员
      * @param employee
      * @return
      */
      @PutMapping(value = "employee/{id}")
      @ApiOperation(value = "修改用户信息",notes = "根据成员id修改单个用户")
      public String update(Employee employee){
           /**
           * save方法如果参数属性缺失，会导致原本存在的数据为null
           */
           Employee employee1 = employeeReposiroty.saveAndFlush(employee);
           if (employee1 != null) {
                return SysNode.Judge.SUCCESS.getResult();
           }else {
               return SysNode.Judge.FAILD.getResult();
           }
      }

      /**
      * 获取所有成员,升序排列
      * @return
      */
      @GetMapping(value = "employee/sort")
      @ApiOperation(value = "查询全部用户",notes = "默认根据升序查询全部用户信息")
      public List<Employee> findAll(){
           Sort orders = new Sort(Sort.Direction.DESC,"employeeId");
           List<Employee> employeeList = employeeReposiroty.findAll(orders);
           return employeeList;
      }

      /**
     * 获取所有成员,升序排列
     * @return
      */
      @GetMapping(value = "employee/pageSort")
      @ApiOperation(value = "查询用户信息",notes = "查询用户信息")
      @ApiImplicitParams({
           @ApiImplicitParam(paramType = "query",name = "sort",value = "排序方式:asc|desc",dataType = "String",required = true),
           @ApiImplicitParam(paramType = "query",name = "pagenumber",value = "第几页",dataType = "Integer",required = true),
           @ApiImplicitParam(paramType = "query",name = "pageSize",value = "分页数",dataType = "Integer",required = true)
      })
      public List<Employee> findAllByPage(String sort,Integer pagenumber,Integer pageSize){
      }
}
```

设置接收对象的参数：

```
@Data
@Entity
@Table(name = "employee")
@ApiModel(value = "用户对象模型")
public class Employee {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "employee_id")
   @Min(value = 1,groups = Employee.Children.class)
   private Integer employeeId;

   @Column(name = "user_name",length = 20,nullable = true)
   @ApiModelProperty(value = "userName",required = true)
   private String userName;

   @Column(nullable = true)
   @Size(min = 0,max = 65,message = "年龄超过范围限制",groups = Employee.Audit.class)
   @ApiModelProperty(value = "age",required = true)
   private Integer age;

   @Column(name="gra_id")
   @ApiModelProperty(value = "graId",required = true)
   //@Digits(integer = 12,fraction = 4)  //限制必须为一个小数，且整数部分的 位数 不能超过integer，小数部分的 位数 不能超过fraction
   private Integer graId;

   public interface Audit{};

   public interface Children{};
}
```

访问：

[http:localhost:9001/swagger-ui.html]()

### 3.8 利用spring-boot-devtools进行项目热部署与加载

添加依赖

```xml
    <!--热部署插件-->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
      <optional>true</optional>
    </dependency>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork> <!-- 如果没有该配置，devtools不会生效 -->
                </configuration>
            </plugin>
        </plugins>
    </build>
```

### 3.9 打包jar和war

添加依赖

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-tomcat</artifactId>
  <scope>provided</scope>
</dependency>
    
<build>
    <finalName>${project.artifactId}</finalName>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

> 3.修改打包方式为war

```
<packaging>war</packaging>
```

> 4.mainApplication需要继承SpringBootServletInitializer,并重写configure方法

```
@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(MainApplication.class);
}
```

> 5.项目根目录打包：mvn clean package 
>
> 6.放tomcat目录下，启动（解决需要增加应用名） 
>
> 1.可以把context设置为空 
>
> 2.直接放到ROOT目录下

### 3.10 yml实现多环境部署

在`Spring Boot`中多环境配置文件名需要满足`application-{profile}.yml`的格式，其中`{profile}`对应你的环境标识;

```
application-dev 开发环境
application-test 测试环境
application-prod 生产环境
```

如果我们要激活某一个环境，只需要在 `application.yml`里：

```
spring:
  profiles:
    active: dev
```

多环境profile打包

pom文件中添加profile节点，并在build下的resources节点添加打包过滤的配置文件规则

```xml
    <profiles>
        <profile>
            <!--	开发环境		-->
            <id>dev</id>
            <properties>
                <profileActive>dev</profileActive>
            </properties>
            <!--	默认激活的环境		-->
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
        </profile>
        <profile>
            <!--	测试环境		-->
            <id>test</id>
            <properties>
                <profileActive>test</profileActive>
            </properties>
        </profile>
        <profile>
            <!--	生产环境		-->
            <id>prod</id>
            <properties>
                <profileActive>prod</profileActive>
            </properties>
        </profile>
    </profiles>
    
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>application-${profileActive}.yml</include>
                    <include>application.yml</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
        </resources>
    </build>
```

在`application.yml`中配置一个动态属性进行占位，默认的分隔符是@属性名@，这个属性会通过maven打包时传入参数进行替换;

```yml
spring:
  profiles:
    active: @profileActive@
```

打包过滤配置文件规则也是用一个占位符进行占位，打包时也会通过maven传入参数进行替换。

- 1、`通过 -D命令传入属性值profileActive`，如：

```
clean install -Dmaven.test.skip=true -DprofileActive=dev
复制代码
```

- 2、`通过-P命令指定profile环境`，如：

```
clean package -P prod
```

### 3.11 JWT引入

添加依赖

```xml
<dependency>
  <groupId>io.jsonwebtoken</groupId>
  <artifactId>jjwt</artifactId>
  <version>${jwt.version}</version>
</dependency>
```

使用：

登录时创建token：

```java
String token = jwtUtil.createJWT(adminUser.getId(), adminUser.getLoginname(), "admin");
```

获取权限：

```java
Claims claims=(Claims)request.getAttribute("admin_claims");
```

### 3.12 权限控制spring-security

添加依赖



### 3.13 日志引入

添加依赖

```xml
<!-- JWT依赖 -->
<dependency>
  <groupId>org.springframework.security</groupId>
  <artifactId>spring-security-jwt</artifactId>
  <version>1.0.9.RELEASE</version>
</dependency>
<dependency>
  <groupId>io.jsonwebtoken</groupId>
  <artifactId>jjwt</artifactId>
  <version>0.9.0</version>
</dependency>
```



### 3.14 异步调用

### 3.15 Websocket

### 3.16 全局异常处理

### 3.17 实现问卷效果

### 3.18 Mybatis

### 3.19 接口幂等性验证

### 3.20 activiti