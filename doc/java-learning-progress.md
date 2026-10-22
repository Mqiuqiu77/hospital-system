# Java 学习 & hospital-system 项目交接摘要

> 更新时间：当前阶段（Redis 项目实战开始前）
>
> 本文档用于新的 ChatGPT / Codex 会话快速接手，不需要阅读之前全部聊天记录。

---

# 1. 学习者当前能力概况

## 1.1 Java 基础（已掌握）

### Java 面向对象
- ✅ 类、对象
- ✅ 封装
- ✅ 继承
- ✅ 多态
- ✅ 接口
- ✅ 抽象类
- ✅ static
- ✅ final
- ✅ this、super
- ✅ 构造方法
- ✅ toString()

---

### Java 集合

已掌握：

- ✅ List
- ✅ ArrayList
- ✅ LinkedList
- ✅ Map
- ✅ HashMap
- ✅ Set
- ✅ Iterator
- ✅ Lambda
- ✅ Stream
- ✅ 泛型

能够自己完成基本 CRUD。

---

### Java 常用类

已掌握：

- ✅ String
- ✅ StringBuilder
- ✅ 包装类
- ✅ BigDecimal

---

### 异常

已掌握：

- Exception
- RuntimeException
- Checked Exception
- try-catch-finally
- throw
- throws
- 自定义异常

能够理解：

- BusinessException 为什么继承 RuntimeException
- 为什么 RuntimeException 默认事务回滚

---

### IO

已掌握：

- File
- InputStream
- OutputStream
- 文件创建
- 文件读取
- try-with-resources

---

### 多线程

基础已完成：

- Thread
- Runnable
- Callable
- Future
- 线程池

目前不准备深入 JUC 源码。

---

# 1.2 SpringBoot

已掌握：

## IOC

理解：

对象交给 Spring 管理。

---

## DI

能够使用：

```java
@Autowired
```

理解依赖注入。

---

## Controller

能够编写：

- GET
- POST

掌握：

- @RestController
- @RequestMapping
- @GetMapping
- @PostMapping

---

## 请求参数

掌握：

- @RequestParam
- @PathVariable
- @RequestBody

---

## 三层架构

理解职责：

Controller

↓

Service

↓

Mapper

↓

MySQL

---

# 1.3 MyBatis

已掌握：

- Mapper
- XML
- CRUD
- 动态 SQL

包括：

- `<if>`
- `<where>`
- `<set>`
- `<foreach>`

---

# 1.4 DTO / VO

已掌握：

DTO：

负责接收请求。

VO：

负责返回响应。

理解：

不要直接返回 Entity。

---

# 1.5 全局异常

已掌握：

BusinessException

GlobalExceptionHandler

统一返回 Result。

---

# 1.6 Spring 事务

已掌握：

@Transactional

理解：

- 为什么放 Service
- RuntimeException 回滚
- private 方法事务失效
- this 调用事务失效
- catch 吃掉异常导致 commit

---

# 1.7 AOP

**定位：了解即可**

目前掌握：

- 为什么需要 AOP
- 代理思想
- AOP 与事务关系

不继续深入源码。

---

# 1.8 文件上传

理解：

MultipartFile

企业方案：

文件存磁盘/OSS

数据库保存路径。

---

# 1.9 分页

理解：

LIMIT

分页思想

PageResult

为什么查询：

count(*)

---

# 1.10 MyBatis Plus

已掌握：

BaseMapper

CRUD

Wrapper

LambdaQueryWrapper

理解：

简单 CRUD：

MyBatis Plus

复杂 SQL：

XML

---

# 1.11 Redis（当前进行中）

已掌握：

Redis 为什么存在

Redis 与 MySQL 关系

五种数据类型：

- String
- Hash
- List
- Set
- ZSet

理解：

热点数据缓存。

尚未真正完成：

SpringBoot 项目整合 Redis。

---

# 2. 原定学习路线

目前路线：

Java基础
↓

SpringBoot
↓

MyBatis
↓

事务
↓

文件上传
↓

分页
↓

MyBatis Plus
↓

Redis ← 当前阶段

↓

JWT

↓

Spring Security（了解）

↓

Docker

↓

医院管理系统完整项目

↓

Java 面试专题

---

当前所在阶段：

**Redis 项目实战阶段。**

---

# 3. 当前项目

项目名称：

hospital-system

定位：

SpringBoot 医院后台管理系统。

目标：

不是做完整业务，

而是作为 Java 后端学习项目，

所有知识都尽量放到这个项目中完成。

---

技术栈：

SpringBoot

MyBatis

MyBatis Plus

MySQL

Redis（正在接入）

Git

GitHub

后续：

JWT

Docker

---

项目目录：

```
hospital-system

src/main/java/com/hospital

├── controller
├── service
├── service/impl
├── mapper
├── entity
├── dto
├── vo
├── exception
├── aspect
└── utils（后续可能增加）
```

---

# 4. 已完成功能

已经完成：

✅ SpringBoot 项目创建

✅ Git 初始化

✅ GitHub 仓库关联

✅ Controller

✅ Service

✅ Mapper

✅ MySQL 连接

✅ MyBatis CRUD

✅ DTO

✅ VO

✅ Result 统一返回

✅ 全局异常

✅ 事务

✅ 文件上传（基础版）

✅ 分页思想

✅ MyBatis Plus 基础 CRUD

---

# 5. 正在开发的功能

当前：

Redis 接入项目。

目前已经完成：

需求分析。

医院列表接口：

```
GET /hospital/list
```

准备改造成：

Redis

↓

MySQL

↓

Redis

↓

返回

目前还是伪代码阶段。

尚未开始真正编码。

---

# 6. 已确认的设计决定

## ① Controller 不处理业务

Controller：

只负责：

请求

↓

调用 Service

↓

返回 Result

原因：

保持三层架构职责清晰。

---

## ② 缓存写在 Service

Redis：

属于业务逻辑。

不放 Controller。

---

## ③ MyBatis Plus + XML 共存

简单 CRUD：

MyBatis Plus。

复杂统计：

XML。

原因：

医疗项目统计 SQL 很复杂。

---

## ④ DTO / VO 分离

请求：

DTO。

响应：

VO。

原因：

避免直接暴露 Entity。

---

## ⑤ 文件保存路径

数据库：

保存路径。

文件：

保存磁盘/对象存储。

原因：

数据库不适合保存大量文件。

---

## ⑥ Redis 使用策略

准备采用：

Cache Aside（旁路缓存）

流程：

Redis

↓

MySQL

↓

Redis

更新：

数据库

↓

删除 Redis

原因：

企业最常见方案，逻辑简单，不容易产生缓存不一致。

---

## ⑦ StringRedisTemplate 优先

后续项目：

优先：

StringRedisTemplate

原因：

对象统一转 JSON，更符合企业实际。

---

# 7. 尚未解决的问题 / 技术债

## Redis

尚未：

真正写：

SpringBoot + Redis。

目前停留：

项目设计。

---

## JSON

尚未学习：

Jackson

ObjectMapper

后续 Redis 缓存对象时需要。

---

## JWT

尚未开始。

---

## Docker

尚未开始。

---

## Spring Security

尚未开始。

---

# 8. 项目启动 / 测试 / 构建

开发环境：

IDEA

SpringBoot

MySQL

Redis（即将接入）

---

启动：

直接运行：

```
HospitalApplication
```

---

数据库：

MySQL

连接：

application.yml

---

Redis：

计划：

localhost

6379

database 0

---

Git：

常用：

```bash
git status

git add .

git commit -m "message"

git push
```

另一台电脑：

```bash
git pull origin main
```

---

# 9. 下一步最合理的任务

## 第一

真正完成：

hospital/list

Redis 缓存。

不是讲 API，

而是在项目里改代码。

---

## 第二

学习：

Jackson

完成：

List<HospitalVO>

↓

JSON

↓

Redis

↓

JSON

↓

List<HospitalVO>

---

## 第三

实现：

缓存删除。

医院修改：

更新数据库

↓

删除 Redis。

---

## 第四

Redis 缓存患者详情。

讨论：

哪些接口适合缓存，

哪些不适合。

---

## 第五

进入：

Redis 高级：

- 缓存穿透
- 缓存击穿
- 缓存雪崩

全部结合 hospital-system 项目。

---

# 10. 教学偏好（非常重要）

后续教学必须遵守：

## ① 项目驱动

不要：

先讲知识。

应该：

项目遇到问题

↓

为什么需要这个技术

↓

改造项目

↓

顺便学习 Java API。

---

## ② 尽量不要脱离 hospital-system

所有：

Redis

JWT

Docker

Spring Security

都优先结合项目。

---

## ③ 少讲源码

目标：

企业开发。

不是：

Spring 源码工程师。

---

## ④ 重视"为什么"

不要只讲：

怎么写。

更要讲：

为什么这么设计。

例如：

为什么：

Redis

↓

MySQL

为什么：

DTO

为什么：

VO

为什么：

删除缓存。

---

## ⑤ 多讲企业开发经验

例如：

项目里：

一般放哪层。

为什么这么分层。

为什么企业这样写。

---

## ⑥ 代码量适中

不要一节课大量代码。

优先：

理解流程

↓

再写代码。

---

## ⑦ 每节课尽量围绕一个真实需求

例如：

- 给医院列表加缓存
- 给患者详情加缓存
- 登录接口增加 JWT
- 上传病历附件
- 医院分页查询

知识点自然引出。

---

## ⑧ 目标

目标不是完成 demo，

而是逐步把 hospital-system 做成一个可以写进简历、用于面试讲解的 Java 后端项目。

---

# 11. 项目实战进度记录

## 2026-08-17：统一用户表映射（已完成）

完成内容：

- `schema.sql`、MyBatis XML 和 MyBatis Plus 实体注解统一使用 `users` 表。
- 保持 Java 类名 `User`、Mapper 接口名 `UserMapper` 和字段名 `username` 不变。
- 修复全局文本替换导致的 `userss`、`usersMapper`、`usersname` 等错误。

验证结果：

- Maven 编译通过。
- Spring 容器启动测试通过。
- 完整测试共 4 个，1 个通过，3 个登录集成测试仍失败。

结论：

用户表命名冲突已经解决。剩余失败来自数据库登录实现与旧的 Session / Spring Security 测试契约不一致，不属于本次表名修改问题。

下一任务：

统一当前阶段的登录方案和测试契约。

## 2026-08-17：统一登录方案和测试契约（已完成）

当前阶段决定：

- 保留 `UserController -> UserServiceImpl -> UserMapper -> users` 的数据库登录流程。
- JWT 和 Spring Security 按原学习路线留到后续阶段，不在当前阶段混用 Session 方案。

完成内容：

- 移除旧的 Session / Spring Security 配置、内存登录服务和重复异常处理器。
- 删除已经不符合当前方案的 Session 登录集成测试。
- `UserServiceImpl` 改用构造器注入，便于测试和明确依赖关系。
- 新增登录 Service 单元测试，覆盖登录成功、用户不存在、密码错误。
- 测试使用模拟 `UserMapper`，不依赖本机数据库中的用户数据。

验证结果：

- Maven 干净构建成功。
- 完整测试共 4 个，4 个全部通过。
- 项目仍按 Java 17 字节码目标编译；当前使用 IDEA 自带 JDK 25 验证成功。

环境差异：

- 本机旧 JDK 19 在编译器关闭阶段会异常失败。
- 下一步先把项目开发 JDK 统一到长期支持版本，再进入医院列表功能。

下一任务：

统一项目 JDK 开发环境，确保命令行和 IDEA 使用同一个可重复构建的 JDK。

## 2026-08-19：医院列表 v1 - 创建医院表（已完成）

完成内容：

- 新增 `hospitals` 表，包含医院名称、地址、联系电话、医院等级和审计时间字段。
- 为医院名称建立唯一索引，避免同一系统中重复维护同名医院主数据。

验证结果：

- Spring 启动时成功执行建表脚本。
- 使用当前可用的 JDK 25 运行 Maven 完整测试，4 个测试全部通过。

下一任务：

创建 `Hospital` 实体类，并理解数据库字段与 Java 属性之间的映射关系。

## 2026-08-20：医院列表 v1 - 创建 Hospital 实体（已完成）

完成内容：

- 创建 `Hospital` 实体类，与 `hospitals` 表字段完整对应。
- 使用 `@TableName("hospitals")` 指定表名。
- 使用 `@TableId(type = IdType.AUTO)` 声明自增主键。
- 使用下划线转驼峰规则映射 `hospital_level`、`create_time` 和 `update_time`。

验证结果：

- Java 编译通过。
- Maven 完整测试共 4 个，全部通过。

下一任务：

创建 `HospitalMapper`，使用 MyBatis Plus 查询医院列表。

## 2026-08-21：医院列表 v1 - 创建 HospitalMapper（已完成）

完成内容：

- `HospitalMapper` 继承 `BaseMapper<Hospital>`。
- 通过 MyBatis Plus 获得 `selectList` 等基础 CRUD 方法，无需重复声明查询全部的方法。
- 明确简单 CRUD 使用 MyBatis Plus，复杂 SQL 再使用 Mapper XML。

验证结果：

- Spring 成功扫描并加载 `HospitalMapper`。
- Maven 完整测试共 4 个，全部通过。

下一任务：

设计医院列表的 Service 契约，并将 Entity 转换为响应 VO。

## 2026-08-31：医院列表 v1 - 完成分层接口（已完成）

完成内容：

- `HospitalController` 接收医院列表请求并统一包装响应。
- `HospitalService` 返回 `List<HospitalVO>`，负责业务编排和 Entity 到 VO 的转换。
- `HospitalMapper.selectList(null)` 查询全部医院数据；`null` 表示本次没有查询条件构造器。
- 明确 Controller 保持轻量、Service 承担业务处理、Mapper 负责数据库访问的分层职责。

验证结果：

- 医院列表接口能够成功返回数据库中的医院数据。
- 返回字段由 `HospitalVO` 控制，没有直接向接口暴露持久化实体。

## 2026-08-31：Redis 基础环境与首次 Java 读写（已完成）

完成内容：

- 通过 WSL Ubuntu 安装并启动 Redis Server 和 `redis-cli`。
- Windows 通过 `localhost:6379` 成功访问 WSL 中的 Redis。
- 项目加入 `spring-boot-starter-data-redis`，配置 Redis host、port 和 database。
- 使用 `StringRedisTemplate` 完成 String 类型数据的写入、读取和删除。
- 使用 JUnit `assertEquals` 验证 Redis 实际读取值，并通过 `try-finally` 保证测试数据被清理。

验证结果：

- `redis-cli ping` 返回 `PONG`。
- `RedisConnectionTests` 单独运行成功，1 个测试全部通过。
- 已确认 Spring Boot 到 Redis 的真实读写链路可用。

下一任务：

为医院列表设计并实现 Cache Aside 缓存流程，先完成 `List<HospitalVO>` 与 JSON 字符串之间的转换。

## 2026-08-31：医院列表缓存 - JSON 序列化基础（已完成）

完成内容：

- 使用 Spring Boot 4.1 实际提供的 Jackson 3 `ObjectMapper`。
- 将 `List<HospitalVO>` 序列化为 JSON 字符串。
- 使用 `TypeReference<List<HospitalVO>>` 将 JSON 反序列化为带有正确元素类型的集合。
- 使用 JUnit 断言验证集合大小和医院名称，理解 `Tests run: 0` 与测试真正执行的区别。

验证结果：

- `HospitalVOJsonTests` 单独运行成功，1 个测试全部通过。
- JSON 往返转换后，医院列表的数量和 `HospitalVO` 字段保持正确。

下一任务：

在 `HospitalServiceImpl` 中实现 Cache Aside 的缓存命中分支：从 Redis 读取医院列表 JSON，命中时反序列化并直接返回。

## 2026-08-31：医院列表缓存 - Cache Aside 回填与 TTL（已完成）

完成内容：

- `HospitalServiceImpl` 在查询数据库前读取 `hospital:list`。
- 缓存命中时将 JSON 反序列化为 `List<HospitalVO>` 并直接返回。
- 缓存未命中时查询 MySQL、完成 Entity 到 VO 转换，再将结果序列化并写入 Redis。
- 为医院列表缓存设置 30 分钟 TTL，避免数据永久过期失效。

验证结果：

- Maven 完整测试共 6 个，全部通过。
- Redis DB0 中成功生成 `hospital:list`，内容为两个医院 VO 的 JSON 数组。
- 实际观察到 TTL 为 1735 秒，符合不超过 1800 秒且大于 0 的预期。

下一任务：

通过可观察实验验证缓存命中分支，理解如何判断接口返回值来自 Redis 而不是 MySQL。

## 2026-08-31：医院列表缓存 - 手动验证命中与回源（已完成）

完成内容：

- 临时修改 Redis 中的医院名称，通过接口返回值确认缓存命中时数据来自 Redis。
- 删除 `hospital:list` 后再次调用接口，确认服务回源 MySQL 并重新生成缓存。
- 理解仅看到缓存 key 只能证明回填成功，使用可区分的数据才能明确验证读取来源。

验证结果：

- 缓存存在时，接口返回临时缓存值。
- 缓存删除后，接口恢复返回数据库值并重新写入 Redis。

下一任务：

按学习者当前偏好暂缓缓存分支单元测试，继续项目开发主线：实现医院修改接口，并在数据库更新成功后删除医院列表缓存。

## 2026-08-31：医院修改 - 设计更新 DTO（已完成）

完成内容：

- 创建 `HospitalUpdateDTO`，只包含允许客户端修改的医院名称、地址、电话和医院等级。
- 不在请求体中接收 ID、创建时间和更新时间，明确 URL 标识资源、DTO 描述可修改内容的职责。
- 使用 `@NotBlank` 约束必填字段，并使用 `@Size` 与数据库 VARCHAR 长度保持一致。

验证结果：

- Maven 编译成功。
- DTO 字段和校验规则与 `hospitals` 表约束一致。

下一任务：

在 `HospitalService` 中定义医院修改业务契约，再由实现类完成数据库更新和缓存删除。

## 2026-09-01：医院修改 - Service 更新与缓存失效（已完成）

完成内容：

- 在 `HospitalService` 中定义 `updateHospital(Long id, HospitalUpdateDTO dto)` 业务契约。
- 使用 MyBatis Plus `updateById()` 按医院 ID 更新可编辑字段。
- 根据受影响行数判断医院是否存在，更新数为 0 时抛出 `BusinessException`。
- 数据库更新成功后删除 `hospital:list`，使下一次列表查询回源并重建缓存。

验证结果：

- Maven 编译成功。
- 更新数据库与删除缓存的执行顺序符合当前 Cache Aside 一致性策略。

下一任务：

在 `HospitalController` 中提供 `PUT /hospital/{id}` 接口，接收并校验 `HospitalUpdateDTO`。

## 2026-09-01：医院修改 - PUT 接口与缓存一致性验证（已完成）

完成内容：

- 提供 `PUT /hospital/{id}`，使用路径变量标识医院，使用 `HospitalUpdateDTO` 接收修改内容。
- 通过 `@Valid` 触发请求参数校验，并由 Controller 调用 Service 完成修改。
- 修改成功后删除 `hospital:list`，下一次列表请求回源数据库并重新写入缓存。

验证结果：

- 医院数据修改成功。
- 修改后列表缓存被删除。
- 再次查询列表返回修改后的数据库数据，并重新生成带 TTL 的缓存。

下一任务：

进入患者详情主线：先建立真实的患者数据模型和查询接口，再为 `patient:detail:{id}` 接入 Redis 缓存。

## 2026-09-01：患者详情 - 创建 patients 表（已完成）

完成内容：

- 新增 `patients` 表，包含患者编号、姓名、性别、出生日期、联系电话、地址和审计时间。
- 使用唯一索引约束 `patient_no`，作为患者业务编号。
- 使用 `birth_date` 保存出生日期，不直接存储会随时间变化的年龄。
- 表引擎、字符集、排序规则和审计字段方案与现有业务表保持一致。

验证结果：

- Spring 启动时成功执行建表脚本。
- Maven 完整测试共 6 个，全部通过。

下一任务：

创建 `Patient` 实体，完成 `DATE -> LocalDate`、下划线字段到驼峰属性的映射。

## 2026-09-01：患者详情 - 创建 Patient 实体（已完成）

完成内容：

- 创建 `Patient` 实体并通过 `@TableName("patients")` 映射患者表。
- 使用 `@TableId(type = IdType.AUTO)` 映射数据库自增主键。
- 使用 `LocalDate` 映射出生日期，使用 `LocalDateTime` 映射审计时间。
- 依靠下划线转驼峰规则映射 `patient_no`、`birth_date`、`create_time` 和 `update_time`。

验证结果：

- Maven 编译成功。
- 实体属性与 `patients` 表字段完整对应。

下一任务：

创建 `PatientMapper`，使用 MyBatis Plus 提供按主键查询患者的基础能力。

## 2026-09-01：患者详情 - 创建 PatientMapper（已完成）

完成内容：

- 创建 `PatientMapper` 并继承 `BaseMapper<Patient>`。
- 获得 `selectById()` 等 MyBatis Plus 基础 CRUD 能力，无需为按主键查询编写 XML。
- 确认启动类已经通过 `@MapperScan("com.hospital.mapper")` 统一扫描 Mapper，因此接口无需重复添加 `@Mapper`。

验证结果：

- Maven 编译成功。
- Mapper 泛型和扫描配置正确。

下一任务：

创建 `PatientVO`，明确患者详情接口和 Redis 缓存允许包含的字段。

## 2026-09-01：患者详情 - 数据库查询与 Redis 缓存（已完成）

完成内容：

- 创建 `PatientVO`，患者详情响应不包含数据库审计时间。
- 建立 `PatientController -> PatientService -> PatientMapper` 的真实按 ID 查询链路。
- 患者不存在时抛出 `BusinessException`，存在时将 Entity 转换为 VO。
- 使用 `patient:detail:{id}` 作为缓存 key，命中时反序列化单个 `PatientVO`。
- 缓存未命中时查询 MySQL、回填 VO JSON，并设置 10 分钟 TTL。

验证结果：

- Maven 完整测试共 6 个，全部通过。
- `GET /patients/1` 实际返回患者数据。
- Redis 中成功生成 `patient:detail:1`，内容为 `PatientVO` JSON，实际观察 TTL 为 474 秒。

下一任务：

结合不存在的患者 ID 学习缓存穿透，并为患者详情设计空值缓存策略。

## 2026-09-01：Redis 高级 - 患者详情缓存穿透防护（已完成）

完成内容：

- 为不存在的患者使用 `__NULL__` 空值标记，避免同一无效 ID 反复访问 MySQL。
- 读取缓存时先识别空值标记，再解析正常的 `PatientVO` JSON。
- 数据库查询为空时写入空值标记，并设置 2 分钟短 TTL。
- 保持正常患者详情缓存 10 分钟 TTL，区分真实数据与不存在数据的生命周期。

验证结果：

- Maven 完整测试共 6 个，全部通过。
- 删除 `patient:detail:999999` 后第一次请求返回“患者不存在”并写入 `__NULL__`。
- 第二次请求继续返回相同业务结果，Redis 中空值标记存在，实测 TTL 为 120 秒。

下一任务：

学习缓存击穿：分析热点患者详情缓存刚好失效时，大量并发请求为何会同时访问数据库，以及常见解决策略。

## 2026-09-02：Redis 高级 - 缓存击穿锁基础（进行中）

已完成内容：

- 理解热点 key 失效时多个请求可能同时回源数据库的缓存击穿问题。
- 理解获取锁后的缓存二次检查，以及等待请求应重新从缓存读取开始循环。
- 使用 `setIfAbsent`、唯一锁值和锁 TTL 实现非阻塞的锁竞争基础方法。
- 使用 Lua 脚本原子完成“锁值属于自己才删除”，避免误删其他请求后来获得的锁。

验证结果：

- Redis 锁获取和安全释放辅助代码编译成功。
- 当前辅助方法尚未接入患者详情主流程，不改变现有接口行为。

下一任务：

提取患者缓存读取方法，为加锁前检查和获得锁后的二次检查复用同一套空值/JSON判断逻辑。

## 2026-09-02：Redis 高级 - 患者详情缓存击穿防护（已完成）

完成内容：

- 提取 `getPatientFromCache()` 和 `loadPatientAndCache()`，分别负责读取缓存与数据库回源回填。
- 使用 Redis `SET NX` 与锁 TTL 实现患者详情互斥锁获取。
- 为每次请求生成 UUID 锁值，并通过 Lua 脚本原子比较锁拥有者后安全删除锁。
- 使用有界重试循环：未获得锁时短暂等待并从缓存检查重新开始。
- 获得锁后再次检查缓存，仍未命中时才查询数据库。
- 使用 `finally` 保证正常返回和异常路径都会尝试释放锁。

验证结果：

- Maven 完整测试共 6 个，全部通过。
- 使用最新代码启动临时实例后，`GET /patients/1` 正常返回患者详情。
- 缓存未命中时成功生成 `patient:detail:1`，TTL 为 600 秒。
- 请求结束后 `lock:patient:detail:1` 不存在，锁已释放。

当前验证边界：

- 已验证单请求下的获取锁、回填和释放流程。
- 尚未使用并发压测工具量化多个请求下的数据库访问次数。

下一任务：

学习缓存雪崩：分析大量 key 同时过期或 Redis 故障对数据库的影响，并为业务缓存 TTL 增加随机抖动。

## 2026-09-03：Redis 高级 - 缓存雪崩与 TTL 随机抖动（已完成）

完成内容：

- 理解大量缓存 key 在相近时间同时失效时，请求会集中回源数据库并形成缓存雪崩。
- 为正常患者详情缓存增加随机 TTL，将原来的固定 10 分钟调整为 10～15 分钟。
- 空值缓存仍保持固定 2 分钟短 TTL，避免不存在数据被长时间缓存。
- 使用 `ThreadLocalRandom` 生成随机分钟数，使不同正常缓存的过期时间自然分散。

验证结果：

- 正常患者缓存调用 `randomPatientCacheTtl()`，空值缓存调用 `Duration.ofMinutes(2)`。
- 多次删除并重建患者详情缓存后，实际 TTL 在预期随机区间内变化，确认随机抖动已生效。
- Maven 完整测试共 6 个，全部通过。

下一任务：

实际观察多个患者详情缓存的 TTL 分布，确认随机抖动生效；随后实现患者资料修改接口，并学习患者详情缓存的 Cache Aside 一致性处理。

## 2026-09-03：患者资料修改与详情缓存一致性（已完成）

完成内容：

- 创建 `PatientUpdateDTO`，使用 Jakarta Validation 校验姓名、性别、出生日期、电话和地址。
- 使用 `@PastOrPresent` 限制出生日期不能晚于当前日期。
- 提供 `PUT /patients/{id}`，由 Controller 接收并校验请求，由 Service 执行业务更新。
- 使用 MyBatis Plus `updateById()` 更新患者资料，并根据影响行数判断患者是否存在。
- 数据库更新成功后删除 `patient:detail:{id}`，沿用 Cache Aside 一致性策略。

验证结果：

- Maven 完整测试共 6 个，全部通过。
- 修改前患者详情缓存存在；PUT 成功后对应缓存被删除。
- 再次 GET 返回更新后的患者数据，并重新生成详情缓存。
- 新缓存实测 TTL 为 900 秒，符合 10～15 分钟随机 TTL 设计。

下一任务：

结束 Redis 项目实战主线，进入 JWT：理解无状态认证流程，并为项目引入 JJWT 依赖。

## 2026-09-04：JWT 基础 - 引入 JJWT（已完成）

完成内容：

- 理解 JWT 在登录认证中的位置，以及 `Header.Payload.Signature` 三段结构。
- 明确 JWT Payload 只是编码而非加密，不能保存密码等敏感信息。
- 引入 `jjwt-api`、`jjwt-impl` 和 `jjwt-jackson` 0.13.0。
- 将公开 API 保持为编译依赖，将实现和 Jackson 适配模块设为运行时依赖。

验证结果：

- Maven 成功解析并下载三个 JJWT 模块及其依赖。
- Maven 完整测试共 6 个，全部通过。

下一任务：

设计 JWT 密钥和过期时间配置，并通过 Spring 配置绑定为后续令牌生成工具提供参数。

## 2026-09-04：JWT 基础 - 密钥与有效期配置（已完成）

完成内容：

- 在项目配置中加入 Base64 编码的 JWT 签名密钥和 `2h` 有效期。
- 创建 `JwtProperties`，使用 `@ConfigurationProperties(prefix = "jwt")` 完成类型安全的配置绑定。
- 使用 `Duration` 接收有效期，使配置可以使用 `2h` 等可读时间单位。
- JWT 密钥在检查过程中保持脱敏，未输出到日志或对话中。

验证结果：

- Spring Boot 应用上下文成功启动，说明配置类已被组件扫描且属性能够完成绑定。
- Maven 完整测试共 6 个，全部通过。

下一任务：

创建 JWT 组件，使用配置中的密钥、用户身份和有效期生成带签名的访问令牌。

## 2026-09-05：JWT 基础 - 签名密钥与令牌生成（已完成）

完成内容：

- 创建由 Spring 管理的 `JwtUtil`，通过构造器注入 `JwtProperties`。
- 将配置中的 Base64 密钥解码为字节，并转换为 HMAC `SecretKey`。
- 使用用户 ID 作为标准 `sub`，使用用户名作为自定义 Claim。
- 设置 JWT 的签发时间和过期时间，并使用同一个签名密钥生成紧凑令牌字符串。

验证结果：

- 配置密钥能够成功解码，长度满足 HMAC 签名要求。
- Spring 应用上下文成功创建 `JwtUtil`。
- Maven 完整测试共 6 个，全部通过。

下一任务：

设计登录响应 VO，将登录用户信息和访问令牌组合为清晰的接口响应模型。

## 2026-09-05：JWT 登录 - 签发访问令牌（已完成）

完成内容：

- 创建 `LoginVO`，将访问令牌和安全的 `UserVO` 组合为登录响应。
- `UserServiceImpl` 注入 `JwtUtil`，仅在用户名和密码校验成功后签发令牌。
- 将 `UserService.login()` 和 `/user/login` 的返回类型调整为 `LoginVO`。
- 同步调整已有登录测试，使其适配新的构造器和响应模型。

验证结果：

- Maven 完整测试共 6 个，全部通过。
- 使用真实账号调用登录接口成功返回 JWT 和用户信息。
- 返回的 token 包含两个点号，符合 `Header.Payload.Signature` 三段结构。

下一任务：

为 `JwtUtil` 增加令牌解析与验签能力，理解“看到 Payload”和“确认 token 可信”的区别。

## 2026-09-05：JWT 基础 - 令牌解析与验签（已完成）

完成内容：

- 使用 `Jwts.parser()` 创建解析器，并通过同一个 `signingKey` 配置签名验证。
- 使用 `parseSignedClaims()` 解析带签名的 JWT，取得经过验证的 Claims。
- 理解 Base64 解码只能看到 Payload，验签成功才能确认令牌未被篡改。

验证结果：

- `parseToken()` 编译正确，Spring 应用上下文正常启动。
- Maven 完整测试共 6 个，全部通过。

下一任务：

轮换已暴露的开发密钥；随后从 Claims 提取用户身份，并将 JWT 接入请求认证流程。

## 2026-09-08：JWT 认证 - 拦截器接入与验签（已完成）

完成内容：

- 从经过验签的 Claims 中提取用户 ID 和用户名，并保存到当前请求属性。
- 创建 `UnauthorizedException`，通过全局异常处理同时返回 HTTP 401 和响应体 code 401。
- `JwtInterceptor` 读取并校验 `Authorization: Bearer <token>` 请求头。
- 将过期 token 与其他无效 token 分别转换为清晰的 401 认证错误。
- 通过 `WebMvcConfig` 将拦截器注册到受保护接口，并排除登录、健康检查和错误处理路径。

验证结果：

- Maven 完整测试共 6 个，全部通过。
- `/user/login` 无 token 可以正常登录并签发新 token。
- 受保护接口无 token 返回 HTTP 401。
- 携带合法 Bearer token 可以正常访问患者接口。
- 篡改 token 后验签失败并返回 HTTP 401 与“登录凭证无效”。

下一任务：

增加 `/user/me` 接口，通过 `@RequestAttribute` 读取拦截器写入的用户 ID 和用户名，验证身份在请求链中的传递。

## 2026-09-08：JWT 认证 - 当前用户身份传递（已完成）

完成内容：

- 创建 `CurrentUserVO`，用于返回当前 token 中的用户 ID 和用户名。
- 提供受 JWT 拦截器保护的 `GET /user/me` 接口。
- Controller 使用 `@RequestAttribute` 读取拦截器写入的身份，而不是信任客户端请求参数。
- 当前身份只在单次 HTTP 请求内传递，请求结束后自动释放。

验证结果：

- Maven 完整测试共 6 个，全部通过。
- 携带合法 token 调用 `/user/me`，成功返回 token 中的真实用户身份。
- 即使伪造 `userId`、`username` 查询参数，接口仍返回 Claims 中的身份。
- 不携带 token 调用 `/user/me` 返回 HTTP 401。

下一任务：

复盘手写 JWT 认证完整链路，理解认证与授权的区别，以及 Spring Security 能替代当前哪些手工代码。

## 2026-09-09：Spring Security - 渐进接入 Filter Chain（已完成）

完成内容：

- 理解认证用于确认“你是谁”，授权用于判断“你能做什么”。
- 引入 `spring-boot-starter-security`，创建自定义 `SecurityFilterChain`。
- 关闭默认表单登录和 HTTP Basic，设置无状态 Session 策略。
- 当前 Security 访问规则暂时 `permitAll`，继续由既有 `JwtInterceptor` 执行实际 JWT 认证。
- 理解框架配置应根据安全需求查阅当前版本文档和项目模板，不要求逐行死记。

验证结果：

- Maven 完整测试共 6 个，全部通过。
- 登录接口仍可匿名访问并正常签发 token。
- 患者接口无 token 仍返回 401，合法 token 可以正常访问。
- Spring Security 加入后，原有 JWT 认证行为保持不变。

下一任务：

创建基于 `OncePerRequestFilter` 的 JWT Filter 外壳，开始将认证从 MVC Interceptor 迁移到 Spring Security Filter Chain。

## 2026-09-20：Spring Security - 独立接管 JWT 认证（已完成）

完成内容：

- 创建 `JwtAuthenticationFilter`，从 Bearer Token 中解析并验证 JWT。
- 使用 `LoginUser` 作为 principal，构造已认证的 `Authentication` 并写入 `SecurityContext`。
- 将 JWT Filter 注册到 Spring Security Filter Chain，并置于授权判断之前。
- 配置 `/user/login`、`/hello`、`/error` 允许匿名访问，其他接口要求已认证。
- 创建 `RestAuthenticationEntryPoint`，将未登录和无效 Token 统一转换为 HTTP 401 与 `Result` JSON。
- `/user/me` 改用 `@AuthenticationPrincipal` 读取当前用户，不再依赖 request 属性。
- 删除旧的 `JwtInterceptor` 和仅用于注册该拦截器的 `WebMvcConfig`，结束双重 JWT 验证状态。

验证结果：

- 登录接口与健康检查接口无需 Token 可以正常访问。
- 受保护接口无 Token 或携带篡改 Token 时返回 HTTP 401，响应体 code 为 401。
- 携带合法 Token 可以正常访问患者接口。
- `/user/me` 返回 Token 中的真实用户身份，不受伪造查询参数影响。
- Maven 完整测试共 6 个，全部通过。

当前认证链路：

`JwtAuthenticationFilter -> SecurityContext -> authorizeHttpRequests -> Controller`

下一任务：

将用户角色加入 JWT 身份与 Spring Security authorities，使用角色规则产生真实的 403 场景，并统一权限不足响应。

## 2026-10-16：Spring Security - 角色授权与统一 403（已完成）

完成内容：

- 将数据库用户角色写入 JWT 的 `role` Claim，并在 JWT Filter 中恢复到 `LoginUser`。
- 将业务角色 `ADMIN` 转换为 Spring Security authority `ROLE_ADMIN`，写入 `Authentication`。
- 使用 `hasRole("ADMIN")` 保护患者资料修改接口；角色为 `USER` 的合法 Token 被拒绝。
- 创建 `RestAccessDeniedHandler`，将已认证但权限不足的请求统一返回 HTTP 403 与 `Result` JSON。
- 明确 401 代表未认证，403 代表已认证但权限不足；篡改 Token 属于 401，不能用来验证 403。

验证结果：

- 角色为 `ADMIN` 的新 Token 可以访问管理员修改接口。
- 角色为 `USER` 的新 Token 调用 `PUT /patients/{id}` 返回 HTTP 403。
- 无 Token 调用同一接口仍返回 HTTP 401。
- JWT Filter 中的 `authentication.getAuthorities()` 能观察到 `ROLE_ADMIN` 或对应角色 authority。

下一任务：

为数据库登录接入 BCrypt 密码哈希，替换当前明文密码比较，并完成本地测试账号的密码迁移。

## 2026-10-19：登录安全 - BCrypt 密码哈希（已完成）

完成内容：

- 在 `SecurityConfig` 中注册 `PasswordEncoder`，使用 `BCryptPasswordEncoder`。
- `UserServiceImpl` 通过构造器注入 `PasswordEncoder`，使用 `matches(明文密码, 数据库哈希)` 校验登录密码。
- 删除遗留的明文 `equals()` 密码比较代码。
- 本地测试账号的数据库密码已迁移为 BCrypt 哈希；不在代码、Token 或接口响应中保存密码。
- 同步更新登录 Service 测试，使用真实 BCrypt 哈希构造模拟数据库用户。

验证结果：

- 使用正确密码可正常登录并签发 JWT。
- 错误密码仍返回“密码错误”。
- Maven 主代码和测试代码编译通过。
- 当前受限执行环境中 Mockito 测试进程无法完成；需以本机 IDEA/终端的测试结果为准。

下一任务：

复盘完整登录安全链路，并实现用户注册或密码修改时的 BCrypt 哈希写入，确保以后新增密码不会以明文保存。

## 2026-10-20：用户注册 - BCrypt 密码写入（已完成）

完成内容：

- 创建 `RegisterDto`，接收用户名、密码和确认密码，并进行基础参数校验。
- 注册前使用 `selectByUsername()` 精确检查重复用户名，不扫描全部用户数据。
- Service 层校验两次密码一致，使用 `PasswordEncoder.encode()` 保存 BCrypt 哈希。
- 注册角色由服务端固定为 `USER`；请求 DTO 不接收角色，防止客户端自行注册管理员账号。
- 设置审计时间并通过现有 Mapper 插入用户，返回不含密码的 `UserVO`。
- 提供公开的 `POST /user/register`，并将该路径加入 Spring Security 的 `permitAll` 规则。

验证结果：

- 注册成功后数据库保存 BCrypt 哈希密码和 `USER` 角色。
- 新用户可以使用原始密码成功登录并取得 JWT。
- 重复用户名与两次密码不一致均返回对应业务错误。
- Maven 主代码和测试代码编译通过（`mvn -DskipTests test`）。

下一任务：

实现“当前登录用户修改自己的密码”：通过 `@AuthenticationPrincipal` 获取身份，校验旧密码、写入新 BCrypt 哈希，并讨论修改密码后旧 JWT 的失效策略。

## 2026-10-21：当前用户修改密码（已完成）

完成内容：

- 通过 `@AuthenticationPrincipal` 获取当前登录用户 ID，不信任客户端传入的用户 ID。
- 校验旧密码与数据库中的 BCrypt 哈希，并校验新密码确认值。
- 将新密码编码为 BCrypt 哈希，通过专用 Mapper 更新语句持久化，并更新时间。

验证结果：

- 修改密码接口成功；旧密码登录失败，新密码登录成功。

后续 Token 失效策略的实现与验证见下节记录。

## 2026-10-21：基于 Token 版本使旧 JWT 失效（已完成）

完成内容：

- 在用户表增加 `token_version`，并映射到 `User.tokenVersion`。
- 登录签发 JWT 时写入 `tokenVersion` Claim。
- 修改密码时通过单条 SQL 更新 BCrypt 哈希并递增用户版本号。
- JWT Filter 查询用户当前版本，只有版本匹配时才建立认证上下文。

验证结果：

- 改密前签发的 Token 在改密后访问受保护接口返回 401。
- 使用新密码重新登录后，新 Token 可以正常访问。
- 新密码登录校验通过。

下一任务：

使用 Redis 缓存用户当前 Token 版本，减少 JWT Filter 对 MySQL 的逐请求查询，并处理改密后的缓存失效。
