# RabbitMQ 秒杀系统 - 流量削峰演示

## 项目简介
这是一个基于Spring Boot + RabbitMQ的秒杀系统演示项目，主要用于展示如何通过消息队列实现流量削峰，解决高并发场景下的系统压力问题。

## 技术栈
- **框架**: Spring Boot 2.3.2
- **数据库**: MySQL 8.0
- **连接池**: Druid 1.1.12
- **ORM**: MyBatis-Plus 3.2.0
- **消息队列**: RabbitMQ
- **Java版本**: JDK 1.8

## 项目结构
```
src/
├── main/
│   ├── java/com/wangshili/
│   │   ├── controller/
│   │   │   └── TestController.java      # 秒杀接口控制器
│   │   ├── dao/
│   │   │   └── KucunDao.java           # 库存数据访问层
│   │   ├── pojo/
│   │   │   └── Kucun.java              # 库存实体类
│   │   ├── service/
│   │   │   └── TestService.java        # 消息队列消费者服务
│   │   └── RabbitmqSpringbootMiaoshaApplication.java  # 启动类
│   └── resources/
│       └── application.properties      # 配置文件
└── test/
    └── java/com/wangshili/
        └── RabbitmqSpringbootMiaoshaApplicationTests.java
```

## 核心功能

### 1. 秒杀接口 (`/miaosha`)
- **功能**: 接收用户秒杀请求
- **实现**: 将用户ID发送到RabbitMQ队列，实现异步处理
- **特点**: 快速响应，避免直接操作数据库

### 2. 消息队列消费者
- **队列名**: `miaosha`
- **功能**: 异步处理秒杀请求
- **流程**: 
  1. 检查库存数量
  2. 库存充足时减1
  3. 更新数据库
  4. 记录成功/失败日志

### 3. 数据库设计
- **kucun表**: 存储商品库存信息
  - `id`: 主键
  - `number`: 库存数量
- **qiangok表**: 存储抢购成功记录（预留）

## 配置说明

### 数据库配置
```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/rabbitmq_demo
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
```

### RabbitMQ配置
```properties
spring.rabbitmq.host=127.0.0.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=emp
spring.rabbitmq.password=123
spring.rabbitmq.virtual-host=/ems
# 限流配置：每次只处理一个消息
spring.rabbitmq.listener.simple.prefetch=1
# 消费者配置：固定10个消费者
spring.rabbitmq.listener.simple.concurrency=10
spring.rabbitmq.listener.simple.max-concurrency=10
```

## 运行步骤

### 1. 环境准备
- 安装MySQL 8.0+
- 安装RabbitMQ
- 安装JDK 1.8+

### 2. 数据库初始化
```sql
-- 创建数据库
CREATE DATABASE rabbitmq_demo;

-- 执行项目根目录下的 rabbitmq_demo.sql 脚本
-- 初始化库存数据（当前设置为0，可修改为100进行测试）
```

### 3. 启动应用
```bash
# 使用Maven启动
mvn spring-boot:run

# 或编译后运行
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

### 4. 测试秒杀
- 访问: `http://localhost/miaosha`
- 使用JMeter等工具模拟并发请求
- 观察控制台日志输出

## 流量削峰原理

### 传统方式问题
- 高并发直接访问数据库
- 数据库压力过大
- 系统响应缓慢或崩溃

### 消息队列解决方案
1. **异步处理**: 用户请求立即返回，实际处理异步进行
2. **流量缓冲**: 消息队列作为缓冲区，平滑处理突发流量
3. **限流控制**: 通过消费者数量控制处理速度
4. **系统解耦**: 前端和后端处理逻辑分离

## 项目特点

### 优点
- 架构简单，易于理解
- 演示了消息队列的基本用法
- 实现了基本的流量削峰功能

### 待优化点
- 缺少Redis缓存，库存检查效率较低
- 没有分布式锁，可能存在超卖问题
- 缺少异常处理和重试机制
- 没有监控和日志记录
- 缺少单元测试

## 使用建议

1. **测试环境**: 建议将库存设置为100，使用JMeter模拟100+并发请求
2. **生产环境**: 需要添加Redis缓存、分布式锁、监控告警等
3. **扩展功能**: 可考虑添加用户认证、商品管理、订单系统等

## 注意事项

- 当前库存初始值为0，测试前请先修改数据库
- RabbitMQ需要提前启动并创建虚拟主机
- 建议使用JMeter等工具进行压力测试
- 生产环境需要配置更完善的监控和日志系统