# 🎯 Project 80 – Payment Service - Kafka Consumer + Producer | Spring Boot + Kafka | Port 8089 | Tier 8 Advanced Backend

<p align="left">
<img src="https://img.shields.io/badge/Tier-8%20Advanced%20Backend-0A1931?style=for-the-badge" alt="Tier 8">
<img src="https://img.shields.io/badge/Java-21-E76F00?logo=openjdk&logoColor=white" alt="Java 21">
<img src="https://img.shields.io/badge/Spring%20Boot-3.2.5-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot 3.2.5">
<img src="https://img.shields.io/badge/Spring%20Kafka-3.1.5-6DB33F?logo=apachekafka&logoColor=white" alt="Spring Kafka">
<img src="https://img.shields.io/badge/Port-8089-00BFFF" alt="8089">
<img src="https://img.shields.io/badge/Group-payment--group--RAVI--FINAL-FF6F00" alt="Group">
<img src="https://img.shields.io/badge/Status-Completed-20B000" alt="Completed">
</p>

## 📖 Project Overview - Tier 8 Interlinked with 78 + 79

Project 80 is **Tier 8 – Advanced Backend – Microservices – Architecture diagram in README, each service = repo** - GitHub Goal Achieved.

This project is **interlinked with Project 78 – Event-Driven Order + 79 Notification Service**:

- **Tier 8 Roadmap:** 73 Auth Service completed, 74 Product Service completed, 75 Order Service completed, 76 API Gateway completed, 77 Service Discovery completed, **78 Event-Driven Order completed**, **79 Notification Service completed**, **80 Payment Service – THIS REPO – Port 8089 – WORKING**, 81 Logging Service, 82 Config Server
- **Interlink Flow:** 78 Order Service (Port 8087) Producer -> Kafka Topic: orders -> **80 Payment Service (Port 8089) Consumer + Producer** -> PAYMENT PROCESSED + PRODUCED Payment PaymentId=4740b4f6 -> Kafka Topic: payments – demo1.png + demo2.png + demo3.png + demo4.png + demo5.png + demo6.jpg
- **This Repo:** Payment Service runs on port 8089 – http://localhost:8089/api/payments – RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic! – demo1.png – **Only Port 8089 – No 9092 badge – Same style as 79**
- **Linked Repo:** 78 Order Service – https://github.com/raviteja-dev950/78-event-driven-order – Producer – PRODUCED Event – demo4.png – Interlinked to 80 – Source event
- **Linked Repo:** 79 Notification Service – https://github.com/raviteja-dev950/79-notification-service – Consumer – NOTIFICATION SENT – demo3.png – Parallel to 80 – Same orders topic
- **Tech:** Spring Cloud, Eureka, Gateway, Config, Kafka, Redis, RabbitMQ – Tier 8 Focus – Kafka Consumer + Producer Pattern – **Dual Role – Consumer + Producer**

Backend flow interlinked:
- **78:** POST /api/orders {"id":"9999","product":"LAPTOP","qty":2} -> OrderController 8087 -> OrderProducer.send -> KafkaTemplate StringSerializer ObjectMapper -> topic orders – demo4.png PRODUCED Event + demo5.png curl POST Order placed
- **Kafka:** Topic orders – Partition 0 – Group payment-group-RAVI-FINAL + notification-group-RAVI-FINAL – earliest offset 0 – partitions assigned [orders-0] – Cluster ID f4AItejLSsCPDs7m9CixtA – demo2.png + demo3.png
- **80:** PaymentConsumer 8089 @KafkaListener(topics="orders", groupId="payment-group-RAVI-FINAL") -> StringDeserializer -> RAW MESSAGE -> CONSUMED -> PAYMENT PROCESSED Order ID 9999 Amount $200.0 SUCCESS + PRODUCED Payment PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0 to payments topic – demo2.png main proof interlinked 78->80 – **DUAL ROLE**

Verified with 6 screenshots interlinked proof:
- demo1.png – Browser – localhost:8089/api/payments – GET – 80-payment-service RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic! – Port 8089 health – Only 8089
- demo2.png – Console 80 – PRODUCED Payment: PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0 + JSON {"paymentId":"4740b4f6","orderId":"9999","status":"SUCCESS","amount":200.0} – 80 main proof – Interlinked from 78 – DUAL ROLE Consumer+Producer – **MAIN DEMO**
- demo3.png – Console 79 – RAW MESSAGE {"id":"9999","product":"LAPTOP","qty":2} -> CONSUMED -> NOTIFICATION SENT Order ID 9999 – 79 parallel proof – Interlinked from 78 – Shows parallel consumption 78->79+80
- demo4.png – Console 78 – PRODUCED Event Order{id='9999', product='LAPTOP', qty=2} -> JSON {"id":"9999","product":"LAPTOP","qty":2} – 78 producer proof – Interlinked to 79+80 – Source event
- demo5.png – CMD curl -X POST http://localhost:8087/api/orders -H "Content-Type: application/json" -d {"id":"9999","product":"LAPTOP","qty":2} -> Order placed: Order{id='9999', product='LAPTOP', qty=2} – Trigger point – Client -> 78
- demo6.jpg – Architecture – Tier 8 Advanced Backend – Event-Driven Microservices Architecture – Client curl -> 78 Order Service 8087 Producer -> Kafka Central Broker Port 9092 Topic orders + payments -> 79 Notification Service 8088 Consumer + 80 Payment Service 8089 Consumer+Producer -> NOTIFICATION SENT + PAYMENT PROCESSED


## 🏗 Architecture Diagram – Tier 8 Goal – Architecture diagram in README, each service = repo – Interlinked 78 + 79 + 80 – demo6.jpg

[architecture](screenshots/demo6.jpg)

```text
TIER 8 – ADVANCED BACKEND – MICROSERVICES – INTERLINKED – 78 + 79 + 80 – DUAL ROLE – PARALLEL CONSUMERS

Client curl -X POST /api/orders {"id":"9999","product":"LAPTOP","qty":2} – demo5.png – Order placed: Order{id='9999', product='LAPTOP', qty=2}
 -> [73 Auth Service] -> [74 Product Service] -> [75 Order Service] -> [76 API Gateway] -> [77 Service Discovery]
 -> 78 Order Service (Port 8087) REST API POST /api/orders -> OrderProducer KafkaTemplate StringSerializer ObjectMapper JSON – demo4.png PRODUCED Event
 -> Apache Kafka Central Event Broker Port 9092 Topic: orders Durable Partitioned Publish-Subscribe Buffering and streaming of events – Cluster ID f4AItejLSsCPDs7m9CixtA
 -> 79 Notification Service (Port 8088) Spring Boot Consumer @KafkaListener topics="orders" group-id notification-group-RAVI-FINAL StringDeserializer ObjectMapper JSON – demo3.png NOTIFICATION SENT – Parallel Consumer – Interlinked from 78
 -> 80 Payment Service (Port 8089) Spring Boot Consumer @KafkaListener topics="orders" group-id payment-group-RAVI-FINAL StringDeserializer ObjectMapper JSON – Interlinked Consumes from orders topic + Producer to payments topic – demo2.png PAYMENT PROCESSED + PRODUCED Payment PaymentId=4740b4f6 – DUAL ROLE Consumer+Producer
 -> Kafka Topic: payments Durable Partitioned – JSON {"paymentId":"4740b4f6","orderId":"9999","status":"SUCCESS","amount":200.0} – demo2.png – Produced by 80
 -> Future: 81 Logging Service Port 8090 Listens payments topic, 82 Config Server Port 8888 – All Tier 8

Data Flow Interlinked: 78 Producer -> Kafka Topic orders -> 79 Consumer -> Notification + 80 Consumer -> Payment -> Kafka Topic payments – Async Event-Driven – Not DB direct – Dual Consumer Pattern + Producer Pattern – Parallel consumption – Same order consumed by 2 services
GitHub Goal Tier 8: Architecture diagram in README, each service = repo – Achieved – 78 repo + 79 repo + 80 repo separate – Interlinked via Kafka topic orders + payments – demo6.jpg
```

Tech Stack Tier 8: Java 21 + Spring Boot 3.2.5 + Spring Cloud + Spring Kafka 3.1.5 + Kafka 3.6.2 + Redis + Eureka + Gateway – Focus Area: Spring Cloud, Eureka, Gateway, Config, Kafka


## ✨ Features – Tier 8 – Interlinked 78+79+80 – DUAL ROLE

### 🌐 REST API Layer – 8089 – Tier 8 Microservices
- PaymentController – Port 8089 – @RestController @RequestMapping("/api/payments") – Each service = repo – GitHub Goal Tier 8
- GET /api/payments -> RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic! – Health check – demo1.png – http://localhost:8089/api/payments – Only Port 8089 – No 9092 – Same style as 79
- Order Model – id, product, qty – Payment Model – paymentId, orderId, status, amount – Jackson ObjectMapper – Interlinked with 78 model – demo2.png PaymentId=4740b4f6

### 📥 Kafka Consumer + Producer – Event Listening + Publishing – MAIN FEATURE – demo2.png – Interlinked with 78 – DUAL ROLE – MAIN PROOF
- PaymentConsumer – @KafkaListener(topics="orders", groupId="payment-group-RAVI-FINAL") – Listens orders topic from 78 – **Consumer Role** – demo2.png
- Logs >>> [80-PAYMENT] RAW MESSAGE: {"id":"9999","product":"LAPTOP","qty":2} – Interlinked from 78 PRODUCED – demo2.png – Source demo4.png
- Logs >>> [80-PAYMENT] CONSUMED Event: Order{id='9999', product='LAPTOP', qty=2} – demo2.png – Consumer
- Logs ********** PAYMENT PROCESSED ********** Payment ID: 4740b4f6 Order ID: 9999 Amount: $200.0 Status: SUCCESS – demo2.png main proof interlinked 78->80! – **DUAL ROLE**
- Logs >>> [80-PAYMENT] PRODUCED Payment: PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0 – **Producer Role** – demo2.png – Produces to payments topic
- Logs >>> JSON: {"paymentId":"4740b4f6","orderId":"9999","status":"SUCCESS","amount":200.0} – demo2.png – JSON to payments topic – Producer

### 📤 Kafka Parallel Consumer Proof – 79 Side – Interlinked – demo3.png – Parallel to 80
- NotificationConsumer 79 – @KafkaListener(topics="orders", groupId="notification-group-RAVI-FINAL") – Parallel consumption with 80 – Different group – demo3.png
- Logs >>> [79-NOTIFICATION] RAW MESSAGE: {"id":"9999","product":"LAPTOP","qty":2} – demo3.png – Parallel to 80 – Same source 78 demo4.png
- Logs >>> [79-NOTIFICATION] CONSUMED Event: Order{id='9999', product='LAPTOP', qty=2} – demo3.png
- Logs >>> Product: LAPTOP Qty: 2 – demo3.png
- Logs ********** NOTIFICATION SENT ********** Order ID: 9999 Product: LAPTOP Quantity: 2 Email sent to customer for order 9999 – demo3.png – 79 parallel to 80 – Both consume same order event! – Parallel consumption proof

### 📤 Kafka Producer Proof – 78 Side – Interlinked – demo4.png – Source for 79+80
- OrderProducer 78 – KafkaTemplate<String, String> – StringSerializer – ObjectMapper JSON – https://github.com/raviteja-dev950/78-event-driven-order – Source
- Logs >>> PRODUCED Event: Order{id='9999', product='LAPTOP', qty=2} – demo4.png – Interlinked to 79+80 – Source event – Triggered by demo5.png curl
- Logs >>> JSON: {"id":"9999","product":"LAPTOP","qty":2} – demo4.png – Interlinked to 79 CONSUMED + 80 CONSUMED – Source JSON

### 📤 Client Trigger – demo5.png – Curl POST – Source of Flow
- CMD curl -X POST http://localhost:8087/api/orders -H "Content-Type: application/json" -d {"id":"9999","product":"LAPTOP","qty":2} – demo5.png
- Response Order placed: Order{id='9999', product='LAPTOP', qty=2} – demo5.png – Triggers 78 -> Kafka -> 79+80
- Flow Start: Client -> 78 -> Kafka -> 79+80 – demo5.png -> demo4.png -> demo2.png + demo3.png

### 📡 Kafka Configuration – Fixed Issues – demo2.png – Tier 8 Learning – DUAL ROLE
- Consumer group-id payment-group-RAVI-FINAL – auto-offset-reset earliest – Found no committed offset for partition orders-0 -> Resetting offset to 0 – demo2.png
- Producer config – payments topic – value.serializer = class org.apache.kafka.common.serialization.StringSerializer – demo2.png
- Producer clientId=producer-1 Instantiated idempotent producer – ProducerId set to 1 with epoch 0 – demo2.png – DUAL ROLE – Producer role
- StringDeserializer + StringSerializer – ObjectMapper readValue/writeValueAsString – Fixed ClassNotFound JavaType – Added jackson-databind – demo2+demo3+demo4 fixed
- partitions assigned: [orders-0] – Successfully joined group generation 1 – Cluster ID f4AItejLSsCPDs7m9CixtA – demo2.png – Interlinked 78->80 working
- WARN LEADER_NOT_AVAILABLE for payments topic fixed by docker-compose up -d – Metadata Cluster ID f4AItejLSsCPDs7m9CixtA – demo2.png
- Tier 8 Goal: Architecture diagram in README – demo6.jpg – Achieved – Same style as 79 demo4.jpg

## 🛠 Technologies Used – Tier 8 – Advanced Backend

| Technology | Version | Purpose – Tier 8 Focus |
|---|---|---|
| Java | 21 | Backend – 21.0.10 – Core |
| Spring Boot | 3.2.5 | 80-payment-service – Port 8089 – Microservices – Consumer+Producer – DUAL ROLE |
| Spring Kafka | 3.1.5 | Consumer @KafkaListener + Producer KafkaTemplate – Spring Cloud, Kafka – Tier 8 – demo2.png |
| Kafka | 3.6.2 | Topic orders (consume) + payments (produce) – Group RAVI-FINAL – Event-Driven – Redis, RabbitMQ – demo2+demo3+demo4+demo5+demo6 interlinked – DUAL ROLE |
| Jackson Databind | - | ObjectMapper JSON -> Order -> Payment – Fixed ClassNotFound – demo2.png PaymentId=4740b4f6 |
| Maven | - | Build – mvnw.cmd – Dependency mgmt – Tier 4 goal |
| Docker | 29.8.2 | kafka-docker docker-compose up -d – zookeeper + kafka – 2 containers – Cluster ID f4AItejLSsCPDs7m9CixtA – Tier 9 DevOps |
| Tier 8 | - | Spring Cloud, Eureka, Gateway, Config, Kafka – Focus Area – Interlinked services 78+79+80 – Parallel + Dual |

## 📂 Project Structure – Tier 8 – Each service = repo – Interlinked 78+79+80 – 6 Demos

```text
100 Projects Java Full Stack – Tier 8 – Advanced Backend – Microservices – 80/100
├── 73-auth-service/ – completed – Port 8081 – Auth Service
├── 74-product-service/ – completed – Port 8082 – Product Service
├── 75-order-service/ – completed – Port 8083 – Order Service
├── 76-api-gateway/ – completed – Port 8080 – API Gateway
├── 77-service-discovery/ – completed – Port 8761 – Eureka
├── 78-event-driven-order/ – completed – Port 8087 – Producer – PRODUCED Event – demo4.png + demo5.png – https://github.com/raviteja-dev950/78-event-driven-order – Interlinked to 79+80 – Source
├── 79-notification-service/ – completed – Port 8088 – Consumer – NOTIFICATION SENT – demo3.png – https://github.com/raviteja-dev950/79-notification-service – Parallel to 80
├── 80-payment-service/ – THIS REPO – Port 8089 – Consumer+Producer – PAYMENT PROCESSED + PRODUCED Payment PaymentId=4740b4f6 – demo1 + demo2 + demo6.jpg – Interlinked from 78 parallel with 79 – DUAL ROLE – 6 Demos
│   ├── src/main/java/com/payment/
│   │   ├── Application.java – Port 8089 – 80-payment-service RUNNING – demo1.png – Only Port 8089 – No 9092
│   │   ├── controller/PaymentController.java – GET RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic! – demo1.png – Tier 8 each service repo – Only 8089 badge
│   │   ├── kafka/PaymentConsumer.java – @KafkaListener topics="orders" group payment-group-RAVI-FINAL -> RAW MESSAGE -> PAYMENT PROCESSED + PRODUCED Payment PaymentId=4740b4f6 – demo2.png – Interlinked – DUAL ROLE Consumer+Producer
│   │   └── model/Order.java, Payment.java – id, product, qty, paymentId, orderId, status, amount – Interlinked model with 78 – demo2.png
│   ├── src/main/resources/
│   │   ├── application.yml – server.port 8089, group payment-group-RAVI-FINAL, auto-offset-reset earliest, payments topic, bootstrap-servers localhost:9092 – Tier 8 Config
│   │   └── README.md – Architecture diagram in README – Tier 8 GitHub Goal – demo6.jpg – Only Port 8089 badge – Same style as 79
│   ├── screenshots/
│   │   ├── demo1.png – Browser GET localhost:8089/api/payments – RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic! – Port 8089 health – Only 8089 – Same style 79 demo1
│   │   ├── demo2.png – Console 80 – PRODUCED Payment PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0 + JSON – Main proof 80 interlinked from 78! – DUAL ROLE – MAIN DEMO – Same style 79 demo2
│   │   ├── demo3.png – Console 79 – RAW MESSAGE + CONSUMED + NOTIFICATION SENT Order ID 9999 Product LAPTOP – Parallel proof 79 interlinked from 78 – Parallel to 80 – Same style 79 demo2 parallel
│   │   ├── demo4.png – Console 78 – PRODUCED Event Order{id='9999', product='LAPTOP', qty=2} + JSON – Producer proof interlinked to 79+80! – Source – Same style 79 demo3
│   │   ├── demo5.png – CMD curl POST /api/orders {"id":"9999","product":"LAPTOP","qty":2} -> Order placed: Order{id='9999', product='LAPTOP', qty=2} – Trigger – Client -> 78 – Same style 79 curl
│   │   └── demo6.jpg – Architecture – Tier 8 Advanced Backend – Event-Driven Microservices – Client -> 78 Order Service 8087 Producer -> Kafka 9092 Topic orders + payments -> 79 Notification 8088 Consumer + 80 Payment 8089 Consumer+Producer -> NOTIFICATION SENT + PAYMENT PROCESSED – Tier 8 Architecture diagram goal – Same style 79 demo4.jpg
│   ├── pom.xml – spring-kafka + jackson-databind – Fixed ClassNotFound – Consumer+Producer – Same style 79
│   └── target/
├── 81-logging-service/ – Next – Port 8090 – Listens payments topic from 80
└── 82-config-server/ – Next – Port 8888 – Config Server
```

## ▶ How to Run – Tier 8 – Interlinked 78 + 79 + 80 – 6 Demos

### 1. Clone Interlinked Repos
```bash
# 78 Producer – Interlinked – Source – demo4.png + demo5.png
git clone https://github.com/raviteja-dev950/78-event-driven-order.git
cd 78-event-driven-order
mvn clean package -DskipTests
java -jar target/78-event-driven-order-0.0.1-SNAPSHOT.jar # Port 8087 – demo4.png PRODUCED Event – Source for 79+80

# 79 Consumer – Parallel – Interlinked – demo3.png
git clone https://github.com/raviteja-dev950/79-notification-service.git
cd 79-notification-service
mvn clean package -DskipTests
java -jar target/79-notification-service-0.0.1-SNAPSHOT.jar # Port 8088 – demo3.png NOTIFICATION SENT – Parallel to 80

# 80 Consumer+Producer – THIS REPO – Interlinked – DUAL ROLE – demo1.png + demo2.png + demo6.jpg
git clone https://github.com/raviteja-dev950/80-payment-service.git
cd 80-payment-service
```

### 2. Application YML – FINAL – WORKING – demo2.png proof – Tier 8 Config Server pattern – Only Port 8089

**application.yml – 8089 + Kafka Consumer+Producer RAVI-FINAL – Only Port 8089 – No 9092 badge – Same style as 79**
```yaml
server:
  port: 8089
spring:
  application:
    name: 80-payment-service
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: payment-group-RAVI-FINAL
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
      properties:
        enable.idempotence: true
```

### 3. Pom.xml – FINAL – FIXED Jackson – demo2+demo3+demo4+demo5+demo6 working – Tier 8 Dependency mgmt – Same style 79

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>3.2.5</version>
</parent>
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
  </dependency>
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
  </dependency>
</dependencies>
```

### 4. Run – Tier 8 – Interlinked Proof – Order: Consumers First! – 6 Demos

```bash
# IMPORTANT: Consumers first, then producer – Tier 8 Event-Driven Pattern – Same style 79
# Start Kafka Docker – 2 containers – Cluster ID f4AItejLSsCPDs7m9CixtA
E:\kafka-docker> docker-compose up -d
docker ps # zookeeper + kafka running – 2 containers – Tier 9 DevOps

# 1. Start 80-payment-service Port 8089 – Must start first to listen – demo1.png + demo2.png – DUAL ROLE
mvn clean package -DskipTests
java -jar target/80-payment-service-0.0.1-SNAPSHOT.jar
# Wait: Tomcat started on port 8089 – payment-group-RAVI-FINAL: partitions assigned: [orders-0] – demo2.png – Tier 8 each service repo – Only Port 8089

# 2. Start 79-notification-service Port 8088 – Parallel – demo3.png
# Wait: Tomcat started on port 8088 – notification-group-RAVI-FINAL: partitions assigned: [orders-0] – demo3.png – Parallel

# 3. Start 78-event-driven-order Port 8087 – Last – Producer – demo4.png
# Wait: Tomcat started on port 8087 – demo4.png PRODUCED Event – Interlinked – Source

# 4. Test E2E – demo5.png – Trigger
curl -X POST http://localhost:8087/api/orders -H "Content-Type: application/json" -d "{\"id\":\"9999\",\"product\":\"LAPTOP\",\"qty\":2}"
# Response: Order placed: Order{id='9999', product='LAPTOP', qty=2} – demo5.png – Client trigger
# Then check 79 console -> RAW MESSAGE + NOTIFICATION SENT – demo3.png – 79 parallel – Interlinked proof 78->79
# Then check 80 console -> PRODUCED Payment PaymentId=4740b4f6 + JSON – demo2.png – 80 main proof – Interlinked proof 78->80 – DUAL ROLE Consumer+Producer!
# Check demo6.jpg architecture – Client -> 78 -> Kafka -> 79+80 -> payments – Tier 8 architecture diagram goal achieved!
```

### 5. Backend Logic – demo2.png + demo3.png + demo4.png + demo5.png + demo6.jpg – Interlinked 78->79+80 – DUAL ROLE

```java
@Component
public class PaymentConsumer { // 80 Consumer+Producer – Interlinked – demo2.png main proof – DUAL ROLE – MAIN DEMO
    @Autowired KafkaTemplate<String, String> kafkaTemplate;
    
    @KafkaListener(topics="orders", groupId="payment-group-RAVI-FINAL")
    public void consume(String message) {
        System.out.println("========================================");
        System.out.println(">>> [80-PAYMENT] RAW MESSAGE: " + message); // Interlinked from 78 demo4.png + demo5.png curl
        try {
            ObjectMapper mapper = new ObjectMapper();
            Order order = mapper.readValue(message, Order.class);
            System.out.println(">>> [80-PAYMENT] CONSUMED Event: Order{id='"+order.getId()+"', product='"+order.getProduct()+"', qty="+order.getQty()+"}");
            System.out.println("========================================");
            System.out.println("********** PAYMENT PROCESSED **********");
            String paymentId = UUID.randomUUID().toString().substring(0,8); // 4740b4f6 – demo2.png
            System.out.println("Payment ID: " + paymentId);
            System.out.println("Order ID: " + order.getId());
            System.out.println("Amount: $200.0");
            System.out.println("Status: SUCCESS");
            System.out.println("***************************************");
            System.out.println("========================================");
            // PRODUCE to payments topic – Producer Role – demo2.png – DUAL ROLE
            Payment payment = new Payment(paymentId, order.getId(), "SUCCESS", 200.0);
            String paymentJson = mapper.writeValueAsString(payment);
            kafkaTemplate.send("payments", paymentJson);
            System.out.println(">>> [80-PAYMENT] PRODUCED Payment: PaymentId="+paymentId+" OrderId="+order.getId()+" Status=SUCCESS Amount=$200.0");
            System.out.println(">>> JSON: {\"paymentId\":\""+paymentId+"\",\"orderId\":\""+order.getId()+"\",\"status\":\"SUCCESS\",\"amount\":"+200.0+"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

@RestController
@RequestMapping("/api/payments")
public class PaymentController { // 80 – Port 8089 only – demo1.png – Only Port 8089 badge – Same style as 79
    @GetMapping
    public String health() { 
        return "80-payment-service RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic!"; // demo1.png – Only 8089
    }
}
```

## 🔄 Application Flow – demo1 + demo2 + demo3 + demo4 + demo5 + demo6 – Interlinked Tier 8 – DUAL ROLE – 6 Demos

```text
TIER 8 FLOW INTERLINKED – 78 -> 79+80 – DUAL CONSUMER + PRODUCER – 6 DEMOS – ONLY PORT 8089 BADGE

Client curl POST {"id":"9999","product":"LAPTOP","qty":2} to 8087 (78) – demo5.png – Order placed: Order{id='9999', product='LAPTOP', qty=2}
 -> OrderController 8087 POST /api/orders -> orderProducer.send() -> KafkaTemplate StringSerializer ObjectMapper {"id":"9999"...} -> PRODUCED Event + JSON – demo4.png – 78 repo https://github.com/raviteja-dev950/78-event-driven-order – Source
 -> Kafka Topic orders Partition 0 – Group payment-group-RAVI-FINAL + notification-group-RAVI-FINAL – Cluster ID f4AItejLSsCPDs7m9CixtA – demo2.png + demo3.png
 -> 79 NotificationConsumer 8088 @KafkaListener group notification-group-RAVI-FINAL auto-offset-reset earliest – Each service = repo – Tier 8 GitHub Goal
 -> Found no committed offset -> Resetting offset to 0 -> partitions assigned [orders-0] – demo3.png – Parallel consumer – Different group from 80
 -> >>> [79-NOTIFICATION] RAW MESSAGE: {"id":"9999","product":"LAPTOP","qty":2} – demo3.png – Interlinked from 78 demo4.png + demo5.png – Parallel to 80
 -> ********** NOTIFICATION SENT ********** Email sent Order ID 9999 Product LAPTOP Quantity 2 – demo3.png – Parallel proof 78->79 – Shows 78->79+80 parallel consumption
 -> 80 PaymentConsumer 8089 @KafkaListener group payment-group-RAVI-FINAL auto-offset-reset earliest – Each service = repo – Tier 8 – DUAL ROLE – demo1.png + demo2.png
 -> Found no committed offset -> Resetting offset to 0 -> partitions assigned [orders-0] -> Cluster ID f4AItejLSsCPDs7m9CixtA – demo2.png – Interlinked 78->80 – payment-group-RAVI-FINAL
 -> >>> [80-PAYMENT] RAW MESSAGE: {"id":"9999","product":"LAPTOP","qty":2} – demo2.png – Interlinked from 78 demo4.png + demo5.png – Same source as 79
 -> >>> CONSUMED Event: Order{id='9999'...} – demo2.png – Consumer role
 -> ********** PAYMENT PROCESSED ********** Payment ID 4740b4f6 Order ID 9999 Amount $200.0 Status SUCCESS – demo2.png main proof interlinked 78->80! – DUAL ROLE Consumer+Producer – MAIN DEMO
 -> >>> PRODUCED Payment: PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0 -> JSON {"paymentId":"4740b4f6","orderId":"9999","status":"SUCCESS","amount":200.0} -> Kafka Topic payments – demo2.png – Producer role – DUAL ROLE
 -> Future: 81 Logging Service Port 8090 Listens payments topic from 80, 82 Config Server Port 8888 – Tier 8 Complete -> Tier 9 DevOps Dockerize – demo6.jpg architecture

Flow 6 Demos: demo5 curl Order placed -> demo4 PRODUCED Event 78 -> Kafka orders -> demo3 NOTIFICATION SENT 79 parallel + demo2 PAYMENT PROCESSED + PRODUCED Payment 80 main -> demo1 health 8089 RUNNING -> demo6 architecture Client->78->Kafka->79+80
```

## 🧪 API Testing – 6 Demos – Interlinked 78+79+80 – DUAL ROLE – Same style 79

```bash
# Health – GET – demo1.png – Browser link – Only Port 8089 – No 9092 – Only 8089 badge – Same style 79 demo1
http://localhost:8089/api/payments
# 80-payment-service RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic!

# Trigger event from 78 – Main flow interlinked – demo2+demo3+demo4+demo5+demo6 – E2E Tier 8 – Dual consumer + Producer – Same style 79 curl
curl -X POST http://localhost:8087/api/orders -H "Content-Type: application/json" -d "{\"id\":\"9999\",\"product\":\"LAPTOP\",\"qty\":2}"
# Response: Order placed: Order{id='9999', product='LAPTOP', qty=2} – demo5.png – Client trigger – Same style 79 demo
# Then check 78 console -> PRODUCED Event + JSON – demo4.png – 78 source – Interlinked proof 78->79+80 – Same style 79 demo3
# Then check 79 console -> RAW MESSAGE + NOTIFICATION SENT – demo3.png – 79 parallel – Interlinked proof 78->79 – Same style 79 demo2
# Then check 80 console -> PRODUCED Payment PaymentId=4740b4f6 + JSON {"paymentId":"4740b4f6"...} – demo2.png – 80 main proof – Interlinked proof 78->80 – DUAL ROLE Consumer+Producer – MAIN DEMO – Same style 79 demo2 main proof
# Check architecture demo6.jpg – Client -> 78 -> Kafka -> 79+80 -> payments – Tier 8 architecture diagram goal achieved – Same style 79 demo4.jpg
```

## 📡 API Endpoints – Tier 8 – Interlinked 78+79+80 – 6 Demos – Only Port 8089

| Method | Endpoint | Purpose – Interlinked | Demo | Status | Roadmap – Same style 79 |
|---|---|---|---|---|---|
| GET | `http://localhost:8089/api/payments` | Health – RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic! – Port 8089 Only – Only 8089 badge | demo1.png | ✅ | Tier 8 each service repo – Only Port 8089 – Same style 79 demo1 |
| POST | `http://localhost:8087/api/orders` (78) | Place order -> Publish -> 79+80 consume – Interlinked 78->79+80 parallel – Trigger | demo5.png + demo4.png | ✅ Main E2E Trigger | 78 completed – Same style 79 POST 8087 |
| - | `Kafka orders topic` | 79+80 consume event – RAW MESSAGE -> NOTIFICATION SENT + PAYMENT PROCESSED – Interlinked parallel – Same orders | demo2.png + demo3.png | ✅ 79+80 Proof Parallel | Tier 8 Architecture diagram goal – Same style 79 |
| - | `Kafka payments topic` | 80 produces payment – PRODUCED Payment PaymentId=4740b4f6 – Dual role – Consumer+Producer | demo2.png JSON | ✅ 80 Producer Proof | Tier 8 Consumer+Producer – DUAL ROLE – Same style 79 producer |
| GET | `http://localhost:8088/api/notifications` (79) | Health parallel – RUNNING - Listening to orders topic! – Parallel to 80 | demo3.png context | ✅ Parallel | 79 completed – Same style 79 GET 8088 |

## 🗄 Kafka Consumer+Producer Note – demo1+demo2+demo3+demo4+demo5+demo6 – Tier 8 Interlinked – DUAL ROLE – 6 Demos – Only Port 8089

```text
TIER 8 – ADVANCED BACKEND – MICROSERVICES – INTERLINKED – 78+79+80 – GitHub Goal: Architecture diagram in README, each service = repo – Achieved – DUAL ROLE – PARALLEL CONSUMERS – ONLY PORT 8089 BADGE – 6 DEMOS – SAME STYLE AS 79

POST on 78 {"id":"9999","product":"LAPTOP","qty":2} – demo5.png curl Order placed: Order{id='9999', product='LAPTOP', qty=2} – demo4.png PRODUCED Event – https://github.com/raviteja-dev950/78-event-driven-order – Client trigger
-> 78 Producer JSON StringSerializer ObjectMapper -> Kafka Topic orders – Group payment-group-RAVI-FINAL + notification-group-RAVI-FINAL – Parallel consumers – Cluster ID f4AItejLSsCPDs7m9CixtA
-> 79 Consumer @KafkaListener group notification-group-RAVI-FINAL earliest offset 0 partitions assigned [orders-0] – demo3.png – Each service repo separate – Tier 8 goal – Parallel to 80 – Same style 79 demo2
-> 80 Consumer @KafkaListener group payment-group-RAVI-FINAL earliest offset 0 partitions assigned [orders-0] Cluster ID f4AItejLSsCPDs7m9CixtA – demo2.png – Each service repo separate – Tier 8 goal – DUAL ROLE – Same style 79 demo2 main
-> 79 Consumes String -> ObjectMapper -> Order -> Log RAW MESSAGE {"id":"9999","product":"LAPTOP","qty":2} -> CONSUMED Event Order{id='9999'...} -> NOTIFICATION SENT Order ID 9999 Product LAPTOP Quantity 2 Email sent – demo3.png – Interlinked 78->79 parallel E2E proof – Same style 79
-> 80 Consumes String -> ObjectMapper -> Order -> Log RAW MESSAGE -> CONSUMED -> PAYMENT PROCESSED Order ID 9999 Amount $200.0 Status SUCCESS -> PRODUCE PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0 JSON {"paymentId":"4740b4f6","orderId":"9999","status":"SUCCESS","amount":200.0} -> Kafka Topic payments – demo2.png – Interlinked 78->80 E2E proof – DUAL ROLE Consumer+Producer – MAIN DEMO – Same style 79 but dual role
-> 80 Health demo1.png GET RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic! – Only Port 8089 – No 9092 badge – Only 8089 badge as requested – Same style 79 demo1 health
-> Architecture demo6.jpg Tier 8 Advanced Backend Event-Driven Microservices Architecture Apache Kafka Producer/Consumer Kafka Listener – Client curl -X POST /api/orders {"id":"9999","product":"LAPTOP","qty":2} -> 78-event-driven-order Service Port 8087 Producer PRODUCE orders JSON StringSerializer -> Apache Kafka Central Event Broker Port 9092 Topic orders (Partition 0) durable partitioned + Topic payments durable partitioned -> 79-notification-service Port 8088 Consumer @KafkaListener group notification-group-RAVI-FINAL NOTIFICATION SENT + 80-payment-service Port 8089 Consumer+Producer @KafkaListener group payment-group-RAVI-FINAL PAYMENT PROCESSED + PRODUCE payments topic PaymentId=4740b4f6 – Legend Producer publishes events to Kafka topics Consumer subscribes via @KafkaListener in consumer group – Same style 79 demo4.jpg architecture

Fixes Tier 8 Learning – Same style 79:
Jackson ClassNotFound JavaType -> Added jackson-databind + Changed to StringDeserializer/Serializer + ObjectMapper -> demo2+demo3+demo4+demo5+demo6 fixed – Spring Boot 3.2.5 – Same fix as 79
Not consuming old messages -> Changed group-id to payment-group-RAVI-FINAL + notification-group-RAVI-FINAL + auto-offset-reset earliest -> Resetting offset to 0 -> partitions assigned [orders-0] -> Cluster ID f4AItejLSsCPDs7m9CixtA -> demo2+demo3 working – Consumer group management parallel groups – Same fix as 79 group-id change
Producer JsonSerializer error -> Changed to StringSerializer + JSON: {"id":"9999"...} + {"paymentId":"4740b4f6"...} -> demo2+demo4+demo5 working – KafkaTemplate<String,String> – payments topic LEADER_NOT_AVAILABLE fixed by docker-compose up -d – Metadata Cluster ID – demo2.png – Same fix as 79 StringSerializer

Verified: demo1 GET RUNNING 8089 Only Port 8089 No 9092 badge – Only 8089 badge as requested – Same style 79 demo1, demo2 Console PRODUCED Payment PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0 + JSON interlinked from 78 – MAIN PROOF – DUAL ROLE – Same style 79 demo2 main proof, demo3 Console NOTIFICATION SENT Order ID 9999 Product LAPTOP Quantity 2 parallel from 78 – Parallel consumption proof – Same style 79 demo2, demo4 Console PRODUCED Event Order{id='9999', product='LAPTOP', qty=2} + JSON interlinked to 79+80 – Source event – Same style 79 demo3, demo5 CMD curl POST Order placed: Order{id='9999', product='LAPTOP', qty=2} – Trigger – Same style 79 curl, demo6 Architecture Tier 8 Event-Driven Microservices 78->Kafka->79+80 – Tier 8 Complete – 80 COMPLETE! – Interlinked with 78+79 – DUAL ROLE Consumer+Producer – Only Port 8089 badge – 6 Demos Done! – Same style as 79 but with 6 demos!
```

## 📸 Screenshots – 6 Demos – Completed – Tier 8 Interlinked Proof 78+79+80 – DUAL ROLE – Only Port 8089 – Same style as 79

### 1. Health Check – Browser – localhost:8089/api/payments – RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic! – Only Port 8089 – No 9092 – Same style 79 demo1

**Browser Link – Only 8089 – Tier 8 Each service = repo – Only Port 8089 badge as requested – Same style as 79 demo1 health:**
```
http://localhost:8089/api/payments
```

[demo1](screenshots/demo1.png)

---

### 2. Event Consumed + Produced – Console 80 – PRODUCED Payment PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0 + JSON – Main proof! 78->80 Interlinked WORKS! – DUAL ROLE – Tier 8 – Same style 79 demo2 main proof

**Console Log After curl to 8087 – Interlinked from 78 demo4.png + demo5.png – MAIN PROOF 80 – DUAL ROLE Consumer+Producer – Same style 79 demo2 main proof:**
```text
ssl.keymanager.algorithm = SunX509
ssl.keystore.certificate.chain = null
ssl.keystore.key = null
ssl.keystore.location = null
ssl.keystore.password = null
ssl.keystore.type = JKS
ssl.protocol = TLSv1.3
ssl.provider = null
ssl.secure.random.implementation = null
ssl.trustmanager.algorithm = PKIX
ssl.truststore.certificates = null
ssl.truststore.location = null
ssl.truststore.password = null
ssl.truststore.type = JKS
transaction.timeout.ms = 60000
transactional.id = null
value.serializer = class org.apache.kafka.common.serialization.StringSerializer
2026-10-16T09:57:56.034+05:30 INFO 1080 --- [80-payment-service] [ntainer#0-0-C-1] o.a.k.clients.producer.KafkaProducer : [Producer clientId=producer-1] Instantiated an idempotent producer
>>> [80-PAYMENT] PRODUCED Payment: PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0
>>> JSON: {"paymentId":"4740b4f6","orderId":"9999","status":"SUCCESS","amount":200.0}
```

[demo2](screenshots/demo2.png)

---

### 3. Event Consumed – Console 79 – RAW MESSAGE + CONSUMED + NOTIFICATION SENT – Parallel proof! 78->79 Interlinked – Parallel to 80! – Same style 79 demo2

**Console Log 79 – Parallel to 80 – Interlinked repo https://github.com/raviteja-dev950/79-notification-service – Parallel to 80 – Same style 79 demo2:**
```text
79-notification-service RUNNING on 8088 - Listening to orders topic!
Consumer Group: notification-group - Topic: orders
Health: http://localhost:8088/api/notifications
notification-group-RAVI-FINAL: partitions assigned: [orders-0]
>>> [79-NOTIFICATION] RAW MESSAGE: {"id":"9999","product":"LAPTOP","qty":2}
>>> [79-NOTIFICATION] CONSUMED Event: Order{id='9999', product='LAPTOP', qty=2}
>>> Product: LAPTOP Qty: 2
********** NOTIFICATION SENT **********
Order ID: 9999
Product: LAPTOP
Quantity: 2
Email sent to customer for order 9999
***************************************
```

[demo3](screenshots/demo3.png)

---

### 4. Event Produced – Console 78 – PRODUCED Event + JSON – Producer proof! Order Service 78 Interlinked to 79+80 – Source Event! – Same style 79 demo3

**Console Log 78 – Interlinked repo https://github.com/raviteja-dev950/78-event-driven-order – Source for 79+80 – Same style 79 demo3:**
```text
>>> PRODUCED Event: Order{id='9999', product='LAPTOP', qty=2}
>>> JSON: {"id":"9999","product":"LAPTOP","qty":2}
```

[demo4](screenshots/demo4.png)

---

### 5. Client Trigger – CMD curl POST – Order placed – Source of Flow – Same style 79 curl trigger

**CMD Log – Trigger point – Client -> 78 -> Kafka -> 79+80 – Same style 79 curl – demo5.png:**
```text
Microsoft Windows [Version 10.0.26300.9550]
C:\Users\Ravi Teja>curl -X POST http://localhost:8087/api/orders -H "Content-Type: application/json" -d "{\"id\":\"9999\",\"product\":\"LAPTOP\",\"qty\":2}"
Order placed: Order{id='9999', product='LAPTOP', qty=2}
```

[demo5](screenshots/demo5.png)

---

### 6. Architecture – Tier 8 Advanced Backend – Event-Driven Microservices – Client -> 78 Producer -> Kafka -> 79 Consumer + 80 Consumer+Producer – Flow diagram – Tier 8 Architecture diagram in README Goal – Same style 79 demo4.jpg

**Tier 8 GitHub Goal Achieved – Architecture diagram in README, each service = repo – Interlinked 78+79+80 – DUAL ROLE – Parallel consumers – Same style 79 demo4.jpg but with 80 added:**

[architecture](screenshots/demo6.jpg)

---

## 🎯 Learning Outcomes – Tier 8 – Advanced Backend – Interlinked 78+79+80 – DUAL ROLE – 6 Demos – Only Port 8089 – Same style as 79

- **Tier 8 Roadmap:** Focus Area Spring Cloud, Eureka, Gateway, Config, Kafka, Redis, RabbitMQ – Sample Projects 73 Auth Service completed, 74 Product Service completed, 75 Order Service completed, 76 API Gateway completed, 77 Service Discovery completed, 78 Event-Driven Order completed – https://github.com/raviteja-dev950/78-event-driven-order – 79 Notification Service completed – https://github.com/raviteja-dev950/79-notification-service – 80 Payment Service – THIS REPO – demo1+demo2+demo3+demo4+demo5+demo6.jpg – 81 Logging Service, 82 Config Server – GitHub Goal Architecture diagram in README, each service = repo – Achieved – **DUAL ROLE Consumer+Producer – 6 Demos**
- **Interlinked:** 78 Producer (Port 8087) -> Kafka Topic orders -> 79 Consumer (Port 8088) -> NOTIFICATION SENT + 80 Consumer+Producer (Port 8089) -> PAYMENT PROCESSED + PRODUCED Payment PaymentId=4740b4f6 to payments topic – demo1.png + demo2.png + demo3.png + demo4.png + demo5.png + demo6.jpg – E2E proof – https://github.com/raviteja-dev950/78-event-driven-order + https://github.com/raviteja-dev950/79-notification-service interlinked – **Parallel consumption + Dual role**
- **Kafka Consumer+Producer – DUAL ROLE – MAIN FEATURE – 6 Demos:** @KafkaListener + KafkaTemplate – Port 8089 Only – No 9092 badge – Only 8089 badge as requested – Same style as 79 – Topic orders (consume) + payments (produce) – Group RAVI-FINAL – demo2.png partitions assigned [orders-0] + PRODUCED Payment PaymentId=4740b4f6 + JSON – Tier 8 Microservices – **Main feature dual role – Same style as 79 but with producer added**
- **Kafka Parallel Consumers:** 79 group notification-group-RAVI-FINAL + 80 group payment-group-RAVI-FINAL – Both consume same orders topic – Different groups – Parallel processing – demo2.png + demo3.png – Both receive same message {"id":"9999","product":"LAPTOP","qty":2} – Tier 8 pattern – Parallel – Same style 79 parallel concept extended to 2 consumers
- **StringDeserializer + StringSerializer + ObjectMapper:** Fixed Jackson ClassNotFound – No JsonDeserializer – demo2+demo3+demo4+demo5+demo6 fixed – Spring Boot 3.2.5 + jackson-databind – Consumer + Producer both String – Same fix as 79 – **DUAL ROLE both**
- **Consumer Group Management:** payment-group-RAVI-FINAL + notification-group-RAVI-FINAL – earliest – offset 0 – Found no committed offset -> Resetting offset to 0 -> partitions assigned [orders-0] -> Cluster ID f4AItejLSsCPDs7m9CixtA – Successfully joined group – demo2.png + demo3.png – Parallel groups – Same style 79 group management
- **Client Trigger – demo5.png – Curl POST – Same style 79:** curl -X POST http://localhost:8087/api/orders -> Order placed: Order{id='9999', product='LAPTOP', qty=2} – demo5.png – Triggers 78 PRODUCED Event demo4.png -> Kafka -> 79 NOTIFICATION SENT demo3.png + 80 PAYMENT PROCESSED + PRODUCED Payment demo2.png – Same style 79 curl trigger flow
- **Architecture Diagram – demo6.jpg – Tier 8 Goal – Same style 79 demo4.jpg:** Tier 8 Advanced Backend Event-Driven Microservices Architecture Apache Kafka Producer/Consumer Kafka Listener – Client curl -X POST /api/orders {"id":"9999","product":"LAPTOP","qty":2} -> 78-event-driven-order Service Port 8087 Producer PRODUCE orders JSON StringSerializer -> Apache Kafka Central Event Broker Port 9092 Topic orders (Partition 0) durable partitioned + Topic payments durable partitioned -> 79-notification-service Port 8088 Consumer @KafkaListener group notification-group-RAVI-FINAL NOTIFICATION SENT + 80-payment-service Port 8089 Consumer+Producer @KafkaListener group payment-group-RAVI-FINAL PAYMENT PROCESSED + PRODUCE payments topic PaymentId=4740b4f6 – Legend Producer publishes events to Kafka topics Consumer subscribes via @KafkaListener in consumer group Topics are durable and partitioned – Same style 79 architecture but with 80 added and payments topic
- **Event-Driven Communication – 6 Demos:** demo5 curl Order placed -> demo4 78 produces JSON {"id":"9999"...} -> Kafka Topic orders -> demo3 79 consumes RAW MESSAGE -> NOTIFICATION SENT Order ID 9999 + demo2 80 consumes RAW MESSAGE -> PAYMENT PROCESSED Order ID 9999 Amount $200.0 SUCCESS -> PRODUCED Payment PaymentId=4740b4f6 to payments topic JSON – demo1 health 8089 RUNNING – demo6 architecture – 6 Demos E2E – Async not DB direct – Tier 8 pattern – **DUAL ROLE – Same style as 79 but with 6 demos**
- **GitHub Goal Tier 8:** Architecture diagram in README – demo6.jpg – Achieved – Each service = repo – 78 repo separate + 79 repo separate + 80 repo separate – Interlinked via Kafka topic orders + payments – Clean repos, great READMEs, commit history – Tier 1 goal also – **Only Port 8089 badge as requested – Same style as 79 Only Port 8088 badge**

## 🚀 Future Enhancements – Tier 8 -> Tier 9 DevOps + Cloud – DUAL ROLE – 6 Demos – Same style 79

- **81 Logging Service + 82 Config Server – Port 8090 + 8888 – Interlinked – 6 Demos:** 81 Listens payments topic from 80 PaymentId=4740b4f6 -> Logs payment -> 82 Config Server central config for 78+79+80 – Tier 8 Complete – All microservices done – Architecture diagram in README for each – https://github.com/raviteja-dev950/80-payment-service interlinked to 81 – Same style 79 future 80
- **Tier 9 DevOps + Cloud – Dockerize – 6 Demos + Docker:** 83 Dockerize App – Dockerfile – Docker Compose 78 + 79 + 80 + Kafka + Zookeeper – 5 services – 84 K8s Deploy – 85 GitHub Actions CI – Badges build passing, coverage, deployed link – Tier 9 GitHub Goal – Same style 79 future Tier 9 – **Docker Compose with 5 services + payments topic**
- **Add Real Payment Gateway – DUAL ROLE Real Payment – demo2.png real payment:** Stripe/PayPal – Amount $200.0 -> Real payment processing – PaymentId=4740b4f6 real transaction – demo2.png real payment – Interlinked enhancement – DUAL ROLE real payment – Same style 79 future JavaMailSender
- **Add DB – Save payments – JPA MySQL – Tier 2 Java + SQL – ER diagrams, SQL scripts – Payment table:** Payment table paymentId, orderId, status, amount – demo2.png PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0 – Same style 79 future DB
- **Add React Frontend – Fetch payments – Tier 6 Frontend Mastery – React Hooks Axios Tailwind – Screenshots/GIFs – Payment status UI:** Fetch http://localhost:8089/api/payments + http://localhost:8089/api/payments/9999 – Payment status SUCCESS – Same style 79 future React

## 👨💻 Author – 100 Projects Java Full Stack Development and GitHub Profile Roadmap – 80/100 – 6 Demos – Same style as 79

### Vemula Leela Venkata Ravi Teja
Java Full Stack Developer
100 Java Full Stack Projects Challenge – 80/100 – Tier 8 Advanced Backend – Microservices Track – Payment Service - Kafka Consumer+Producer – Interlinked with 78 Event-Driven Order + 79 Notification Service – DUAL ROLE – 6 Demos – Same style as 79

**Roadmap Progress – Tier 8 – Advanced Backend – Microservices – Architecture diagram in README, each service = repo – Same style as 79:**
- Tier 1 Foundations Core Java + Git Clean repos, great READMEs, commit history – 1-12 completed – Same style 79
- Tier 2 Java + SQL JDBC MySQL ER diagrams SQL scripts – 13-22 completed – Same style 79
- Tier 3 Web Basics Servlets JSP Deploy Render/Railway live link – 23-32 completed – Same style 79
- Tier 4 Spring Core Spring IoC DI Beans MVC Thymeleaf Use Spring Initializr – 33-42 completed – Same style 79
- Tier 5 Spring Boot + REST APIs Postman Swagger OpenAPI docs – 43-52 completed – Same style 79
- Tier 6 Frontend Mastery React Hooks Axios Tailwind Separate frontend repos screenshots/GIFs – 53-62 completed – Same style 79
- Tier 7 Full Stack Integration Complete Apps Dockerfiles docker-compose monorepo or 2 repos – 63-72 completed – Same style 79
- **Tier 8 Advanced Backend Microservices Spring Cloud Eureka Gateway Config Kafka Redis RabbitMQ Architecture diagram in README each service = repo – 73 Auth completed, 74 Product completed, 75 Order completed, 76 Gateway completed, 77 Discovery completed, 78 Event-Driven Order completed – https://github.com/raviteja-dev950/78-event-driven-order – 79 Notification Service completed – https://github.com/raviteja-dev950/79-notification-service – 80 Payment Service – THIS REPO – Port 8089 – demo1+demo2+demo3+demo4+demo5+demo6.jpg – 6 Demos – DUAL ROLE Consumer+Producer – Parallel consumers – Only Port 8089 badge – Same style as 79 but with 6 demos – GitHub Goal Achieved!**
- Tier 9 DevOps + Cloud Deployment Docker K8s CI/CD Nginx AWS Badges build passing coverage deployed link – 83-92 next – Same style 79
- Tier 10 System Design Level Scale Polish Caching security testing JUnit Mockito Redis Elasticsearch CONTRIBUTING.md issues release tags – 93-102 next – Capstone OSS Tool – Same style 79

### Test Consumer+Producer – 6 Demos – Interlinked Tier 8 – DUAL ROLE – Only Port 8089 – Same style as 79

- `localhost:8089/api/payments` / `GET` / `RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic!` – demo1.png – Browser link http://localhost:8089/api/payments – Only Port 8089 No 9092 – Tier 8 each service repo – **Only 8089 badge as requested – Same style as 79 demo1 health**
- `Kafka Consumer+Producer 80` / `PRODUCED Payment: PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0` / `JSON {"paymentId":"4740b4f6","orderId":"9999","status":"SUCCESS","amount":200.0}` – demo2.png – 80 console main proof – Interlinked from 78 demo4.png + demo5.png – **MAIN PROOF – DUAL ROLE – Same style as 79 demo2 main proof**
- `Kafka Consumer 79` / `RAW MESSAGE {"id":"9999"...}` / `NOTIFICATION SENT Order ID 9999 Product LAPTOP Quantity 2` – demo3.png – 79 console parallel proof – Interlinked from 78 demo4.png + demo5.png – Parallel to 80 – Shows 78->79+80 parallel – Same style as 79 demo2 parallel
- `Kafka Producer 78` / `PRODUCED Event Order{id='9999'...}` / `JSON {"id":"9999","product":"LAPTOP","qty":2}` – demo4.png – 78 console producer proof – https://github.com/raviteja-dev950/78-event-driven-order – Interlinked to 79+80 – Source event – Same style as 79 demo3 producer proof
- `Client curl` / `curl -X POST http://localhost:8087/api/orders` / `Order placed: Order{id='9999', product='LAPTOP', qty=2}` – demo5.png – CMD curl trigger – Client -> 78 -> Kafka -> 79+80 – Same style as 79 curl trigger
- `Architecture` / `Client -> Order Service 78 -> Kafka Topic orders + payments -> Notification Service 79 -> NOTIFICATION SENT + Payment Service 80 -> PAYMENT PROCESSED + PRODUCED payments` – demo6.jpg – Tier 8 Architecture diagram in README goal achieved – Same style as 79 demo4.jpg but with 80 + payments topic added

## ⭐ Support – Tier 8 – DUAL ROLE – 6 Demos – Same style as 79

If you found this project helpful learning Kafka Consumer + Producer / @KafkaListener + KafkaTemplate / StringDeserializer + StringSerializer / ObjectMapper / Tier 8 Microservices / Event-Driven / Port 8089 Only / Interlinked 78->79+80 / DUAL ROLE Consumer+Producer / Parallel Consumers / 6 Demos / 100 Projects Roadmap / Same style as 79, give it a ⭐ Star!

### Repo – Interlinked – Tier 8 Each service = repo – DUAL ROLE – Parallel Consumers – 6 Demos – Same style as 79

- **78 Event-Driven Order – Producer – Port 8087 – Interlinked – Source – demo4.png + demo5.png:** https://github.com/raviteja-dev950/78-event-driven-order – PRODUCED Event – demo4.png – Source for 79+80 – Trigger demo5.png Order placed
- **79 Notification Service – Consumer – Port 8088 – Interlinked – Parallel – demo3.png:** https://github.com/raviteja-dev950/79-notification-service – NOTIFICATION SENT – demo3.png – Parallel to 80 – Same orders topic different group – Same style 79
- **80 Payment Service – Consumer+Producer – Port 8089 – THIS REPO – Interlinked – DUAL ROLE – demo1.png + demo2.png + demo6.jpg – 6 Demos:** https://github.com/raviteja-dev950/80-payment-service – PAYMENT PROCESSED + PRODUCED Payment PaymentId=4740b4f6 – demo1+demo2+demo6 – Tier 8 Architecture diagram goal – **Only Port 8089 badge – Same style as 79 but 6 demos**

### Run – Simplest – demo1+demo2+demo3+demo4+demo5+demo6 – Interlinked Tier 8 – DUAL ROLE – Consumers First! – 6 Demos – Same style as 79

```bash
# Tier 8 – Interlinked – 78+79+80 – Each service = repo – Architecture diagram in README – DUAL ROLE – Parallel consumers – 6 Demos – Same style as 79 but with 6 demos

# Kafka Docker – 2 containers – Cluster ID f4AItejLSsCPDs7m9CixtA – Tier 9 DevOps
E:\kafka-docker> docker-compose up -d
docker ps # zookeeper + kafka running – 2 containers

# 78 Producer – https://github.com/raviteja-dev950/78-event-driven-order – Source – demo4.png + demo5.png – Same style 79 78
git clone https://github.com/raviteja-dev950/78-event-driven-order.git
cd 78-event-driven-order
mvn clean package -DskipTests
java -jar target/78-event-driven-order-0.0.1-SNAPSHOT.jar # Port 8087 – demo4.png PRODUCED Event Order{id='9999', product='LAPTOP', qty=2} + JSON – Source for 79+80 – Trigger demo5.png Order placed

# 79 Consumer – Parallel – https://github.com/raviteja-dev950/79-notification-service – Parallel to 80 – demo3.png – Same style 79
git clone https://github.com/raviteja-dev950/79-notification-service.git
cd 79-notification-service
mvn clean package -DskipTests
java -jar target/79-notification-service-0.0.1-SNAPSHOT.jar # Port 8088 – demo3.png NOTIFICATION SENT Order ID 9999 Product LAPTOP Quantity 2 – Parallel – Same style 79 demo2

# 80 Consumer+Producer – THIS REPO – Interlinked – DUAL ROLE – MAIN – demo1.png + demo2.png + demo6.jpg – 6 Demos – Same style 79 but dual role + 6 demos
git clone https://github.com/raviteja-dev950/80-payment-service.git
cd 80-payment-service
mvn clean package -DskipTests
java -jar target/80-payment-service-0.0.1-SNAPSHOT.jar
# Browser demo1 – GET http://localhost:8089/api/payments – RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic! – Only Port 8089 – No 9092 badge – Only 8089 badge as requested – Same style 79 demo1 health – demo1.png
# Console demo2 – PRODUCED Payment: PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0 + JSON {"paymentId":"4740b4f6","orderId":"9999","status":"SUCCESS","amount":200.0} – Main proof interlinked from 78 – DUAL ROLE Consumer+Producer! – Same style 79 demo2 main proof but dual role – demo2.png MAIN DEMO
# Console demo3 – RAW MESSAGE + NOTIFICATION SENT Order ID 9999 Product LAPTOP – Parallel proof 79 interlinked from 78 – Shows parallel consumption 78->79+80 – Same style 79 demo2 parallel – demo3.png
# Console demo4 – PRODUCED Event Order{id='9999', product='LAPTOP', qty=2} + JSON – 78 side proof – E2E interlinked complete! – Same style 79 demo3 producer – demo4.png source
# CMD demo5 – curl POST Order placed: Order{id='9999', product='LAPTOP', qty=2} – Trigger – Client -> 78 – Same style 79 curl – demo5.png trigger
# Architecture demo6 – Tier 8 Advanced Backend Event-Driven Microservices Client -> 78 Order Service 8087 Producer -> Kafka 9092 Topic orders + payments -> 79 Notification 8088 Consumer + 80 Payment 8089 Consumer+Producer -> NOTIFICATION SENT + PAYMENT PROCESSED + PRODUCED payments – Tier 8 architecture diagram goal – Same style 79 demo4.jpg but with 80 + payments – demo6.jpg
```

### Work Process Summary – 6 Demos – Tier 8 Interlinked – Only Port 8089 – No 9092 – DUAL ROLE – Consumers First! – Same style as 79 Work Process Summary

```text
TIER 8 – ADVANCED BACKEND – MICROSERVICES – 100 PROJECTS ROADMAP – INTERLINKED 78+79+80 – GITHUB GOAL: ARCHITECTURE DIAGRAM IN README, EACH SERVICE = REPO – ACHIEVED – DUAL ROLE – PARALLEL CONSUMERS – ONLY PORT 8089 BADGE – 6 DEMOS – SAME STYLE AS 79

1. demo1 – Browser GET http://localhost:8089/api/payments – RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic! – Health proof – Port 8089 Only – No 9092 badge – Only Port 8089 badge as requested – Tier 8 each service repo separate – Same style as 79 demo1 health – Only Port 8088 in 79, Only Port 8089 in 80 – No 9092 badge mistake fixed
2. demo2 – Console 80 – ssl.keymanager.algorithm = SunX509 ... value.serializer = class org.apache.kafka.common.serialization.StringSerializer – KafkaProducer [Producer clientId=producer-1] Instantiated an idempotent producer – Kafka version 3.6.2 – PRODUCED Payment: PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0 + JSON {"paymentId":"4740b4f6","orderId":"9999","status":"SUCCESS","amount":200.0} – Main proof 78->80 interlinked complete! – Group payment-group-RAVI-FINAL earliest offset 0 partitions assigned [orders-0] Cluster ID f4AItejLSsCPDs7m9CixtA – DUAL ROLE Consumer+Producer – MAIN DEMO – Same style as 79 demo2 main proof but with PRODUCED Payment
3. demo3 – Console 79 – 79-notification-service RUNNING on 8088 - Listening to orders topic! Consumer Group notification-group Topic orders Health http://localhost:8088/api/notifications notification-group-RAVI-FINAL partitions assigned [orders-0] RAW MESSAGE {"id":"9999","product":"LAPTOP","qty":2} CONSUMED Event Order{id='9999', product='LAPTOP', qty=2} Product LAPTOP Qty 2 NOTIFICATION SENT Order ID 9999 Product LAPTOP Quantity 2 Email sent to customer for order 9999 – Parallel proof 78->79 interlinked complete! – Group notification-group-RAVI-FINAL earliest offset 0 partitions assigned [orders-0] – Parallel to 80 – Shows parallel consumption same orders topic different groups – Both 79+80 receive same event from 78 – Same style as 79 demo2
4. demo4 – Console 78 – ssl.enabled.protocols = [TLSv1.2, TLSv1.3] ... value.serializer = class org.apache.kafka.common.serialization.StringSerializer – KafkaProducer Instantiated an idempotent producer Kafka version 3.6.2 ProducerId set to 0 with epoch PRODUCED Event Order{id='9999', product='LAPTOP', qty=2} JSON {"id":"9999","product":"LAPTOP","qty":2} – Producer proof – https://github.com/raviteja-dev950/78-event-driven-order – Interlinked to 79+80 – Source event for both parallel consumers – Triggered by demo5.png curl – Same style as 79 demo3 producer proof
5. demo5 – CMD – Microsoft Windows Version 10.0.26300.9550 (c) Microsoft Corporation – C:\Users\Ravi Teja>curl -X POST http://localhost:8087/api/orders -H "Content-Type: application/json" -d {"id":"9999","product":"LAPTOP","qty":2} Order placed: Order{id='9999', product='LAPTOP', qty=2} – Trigger – Client -> 78 Order Service 8087 -> Kafka -> 79+80 – Same style as 79 curl trigger but with LAPTOP qty 2 – Client source
6. demo6 – Architecture – Tier 8 Advanced Backend Event-Driven Microservices Architecture Apache Kafka Producer/Consumer Kafka Listener – Client curl -X POST /api/orders {"id":"9999","product":"LAPTOP","qty":2} HTTP POST -> 78-event-driven-order Service Port 8087 Producer PRODUCE orders JSON StringSerializer -> Apache Kafka Central Event Broker Port 9092 Topic orders (Partition 0) durable partitioned + Topic payments durable partitioned -> 79-notification-service Port 8088 Consumer @KafkaListener group notification-group-RAVI-FINAL NOTIFICATION SENT + 80-payment-service Port 8089 Consumer+Producer @KafkaListener group payment-group-RAVI-FINAL PAYMENT PROCESSED + PRODUCE payments topic PaymentId=4740b4f6 – Legend Producer publishes events to Kafka topics Consumer subscribes via @KafkaListener in consumer group Topics are durable and partitioned – Same style as 79 demo4.jpg architecture but with 80 + payments topic + demo5 trigger added – 6 Demos

Flow Interlinked Tier 8 – DUAL ROLE – 6 Demos – Only Port 8089 Badge – Same style as 79: demo5 curl Order placed: Order{id='9999', product='LAPTOP', qty=2} -> demo4 PRODUCED Event Order{id='9999', product='LAPTOP', qty=2} JSON {"id":"9999","product":"LAPTOP","qty":2} -> Kafka Topic orders Cluster ID f4AItejLSsCPDs7m9CixtA Group payment-group-RAVI-FINAL + notification-group-RAVI-FINAL -> demo3 RAW MESSAGE + NOTIFICATION SENT Order ID 9999 Product LAPTOP Quantity 2 – Parallel – demo2 PRODUCED Payment PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0 JSON {"paymentId":"4740b4f6","orderId":"9999","status":"SUCCESS","amount":200.0} -> Kafka Topic payments -> demo1 GET RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic! – Only Port 8089 badge – demo6 architecture Client->78->Kafka->79+80 – Tier 8 architecture diagram in README goal achieved – 6 Demos Done!
Group: payment-group-RAVI-FINAL + notification-group-RAVI-FINAL earliest offset 0 partitions assigned [orders-0] Cluster ID f4AItejLSsCPDs7m9CixtA Found no committed offset Resetting offset to 0 Successfully joined group generation 1 – demo2.png + demo3.png – Parallel groups – Same style as 79 group management
Tech: Java 21 + Spring Boot 3.2.5 + Spring Kafka 3.1.5 + Jackson Databind + Kafka 3.6.2 + Docker 29.8.2 – Tier 8 Focus Spring Cloud Eureka Gateway Config Kafka Redis RabbitMQ – Only Port 8089 No 9092 – Complete – 6 Demos Done! – Interlinked with 78+79 – Roadmap 80/100 Tier 8 – Next 81 Logging Service – DUAL ROLE Consumer+Producer – Only 8089 badge – Same style as 79 but with 6 demos
```

### Logs Proof – FINAL WORKING – demo1+demo2+demo3+demo4+demo5+demo6 – Tier 8 Interlinked – Only Port 8089 – DUAL ROLE – 6 Demos – Same style as 79 Logs Proof

```text
# 80 Consumer+Producer – demo1+demo2.png – Port 8089 ONLY – No 9092 badge – Only 8089 badge as requested – Tier 8 each service repo – Interlinked from 78 – DUAL ROLE – MAIN PROOF – 6 Demos – Same style as 79 Logs Proof
Health: http://localhost:8089/api/payments – demo1.png – Only 8089 – 80-payment-service RUNNING - Port 8089 - Listening to orders topic, Publishing to payments topic! – Port 8089 health – Only Port 8089 No 9092 – Same style 79 demo1 health
Tomcat started on port 8089 – 8089 ready – demo1.png – Tier 8 – Only Port 8089 badge – Same style 79
payment-group-RAVI-FINAL: partitions assigned: [orders-0] – demo2.png – Interlinked – Cluster ID f4AItejLSsCPDs7m9CixtA – Same style 79
Found no committed offset -> Resetting offset to 0 -> partitions assigned [orders-0] – demo2.png – Tier 8 consumer group management – Same style 79
ssl.keymanager.algorithm = SunX509 ... value.serializer = class org.apache.kafka.common.serialization.StringSerializer – demo2.png – Producer config – value.serializer StringSerializer – DUAL ROLE
2026-10-16T09:57:56.034+05:30 INFO 1080 --- [80-payment-service] [ntainer#0-0-C-1] o.a.k.clients.producer.KafkaProducer : [Producer clientId=producer-1] Instantiated an idempotent producer – demo2.png – DUAL ROLE – Producer role instantiated
>>> [80-PAYMENT] PRODUCED Payment: PaymentId=4740b4f6 OrderId=9999 Status=SUCCESS Amount=$200.0 – demo2.png – MAIN PROOF – Interlinked from 78 demo4.png + demo5.png – DUAL ROLE Producer role – Produces to payments topic
>>> JSON: {"paymentId":"4740b4f6","orderId":"9999","status":"SUCCESS","amount":200.0} – demo2.png – JSON to payments topic – DUAL ROLE – MAIN DEMO – Same style 79 demo2 main proof but with PRODUCED Payment
DispatcherServlet Initializing Servlet 'dispatcherServlet' Completed initialization in 7 ms – demo2.png – Tomcat 8089

# 79 Consumer – demo3.png – Port 8088 – Parallel to 80 – Interlinked from 78 – Same style 79 Logs Proof
Health: http://localhost:8088/api/notifications – 79 RUNNING - Listening to orders topic! – Parallel to 80 – Same style 79 health
79-notification-service RUNNING on 8088 - Listening to orders topic! Consumer Group notification-group Topic orders Health http://localhost:8088/api/notifications – demo3.png – Parallel consumer
notification-group-RAVI-FINAL: partitions assigned: [orders-0] – demo3.png – Parallel consumer – Different group from 80 – Same style 79
>>> [79-NOTIFICATION] RAW MESSAGE: {"id":"9999","product":"LAPTOP","qty":2} – demo3.png – Parallel to 80 RAW MESSAGE – Same source 78 demo4.png + demo5.png
>>> [79-NOTIFICATION] CONSUMED Event: Order{id='9999', product='LAPTOP', qty=2} – demo3.png – Parallel – Same style 79
>>> Product: LAPTOP Qty: 2 – demo3.png – Same style 79
********** NOTIFICATION SENT ********** – demo3.png parallel proof interlinked 78->79 – Tier 8 parallel consumers – Both 79+80 receive same event! – Same style 79 main proof
Order ID: 9999 Product: LAPTOP Quantity: 2 Email sent to customer for order 9999 – demo3.png – Same style 79

# 78 Producer – demo4.png – Port 8087 – Interlinked to 79+80 – Source – Same style 79 Logs Proof 78
ssl.enabled.protocols = [TLSv1.2, TLSv1.3] ... value.serializer = class org.apache.kafka.common.serialization.StringSerializer – demo4.png – Producer config – Same style 79 78 logs
[Producer clientId=producer-1] Instantiated an idempotent producer – demo4.png – Tier 8 – Source – Same style 79
Kafka version 3.6.2 – demo4.png – Same style 79
>>> PRODUCED Event: Order{id='9999', product='LAPTOP', qty=2} – demo4.png – Interlinked to 79+80 – Source event for both parallel consumers – Triggered by demo5.png curl – Same style 79 demo3 producer proof
>>> JSON: {"id":"9999","product":"LAPTOP","qty":2} – demo4.png – Interlinked to 79 CONSUMED + 80 CONSUMED – Source JSON – Same style 79
ProducerId set to 0 with epoch 0 – 78 COMPLETE! – E2E 78->79+80 WORKING! – Tier 8 Architecture diagram in README each service repo – 73-80 completed – 80/100 – DUAL ROLE – Parallel consumers – Only Port 8089 badge – 80 COMPLETE! – Same style 79 78 COMPLETE

# Client Trigger – demo5.png – CMD curl – Same style 79 curl trigger
Microsoft Windows [Version 10.0.26300.9550] (c) Microsoft Corporation – Same style 79 Windows version
C:\Users\Ravi Teja>curl -X POST http://localhost:8087/api/orders -H "Content-Type: application/json" -d "{\"id\":\"9999\",\"product\":\"LAPTOP\",\"qty\":2}" – demo5.png – Same style 79 curl command
Order placed: Order{id='9999', product='LAPTOP', qty=2} – demo5.png – Client trigger – Same style 79 Order placed – Triggers 78 PRODUCED Event demo4.png -> Kafka -> 79 NOTIFICATION SENT demo3.png + 80 PAYMENT PROCESSED + PRODUCED Payment demo2.png – E2E trigger – Same style 79

# Architecture – demo6.jpg – Tier 8 Advanced Backend Event-Driven Microservices Architecture – Same style 79 demo4.jpg but with 80 + payments
Tier 8 Advanced Backend Event-Driven Microservices Architecture Apache Kafka Producer/Consumer Kafka Listener – demo6.jpg – Same style 79 demo4.jpg but with 80 added
Client curl -X POST /api/orders {"id":"9999","product":"LAPTOP","qty":2} HTTP POST – demo5.png trigger – demo6.jpg Client box
78-event-driven-order Service Port 8087 Producer PRODUCE orders JSON StringSerializer – demo4.png – demo6.jpg left blue box
Apache Kafka Central Event Broker Port 9092 Topic orders (Partition 0) durable partitioned + Topic payments durable partitioned – demo6.jpg black center – Cluster ID f4AItejLSsCPDs7m9CixtA
79-notification-service Port 8088 Consumer @KafkaListener group notification-group-RAVI-FINAL NOTIFICATION SENT – demo3.png – demo6.jpg green left bottom
80-payment-service Port 8089 Consumer+Producer @KafkaListener group payment-group-RAVI-FINAL PAYMENT PROCESSED + PRODUCE payments topic PaymentId=4740b4f6 – demo2.png – demo6.jpg purple right bottom – DUAL ROLE
Legend Producer publishes events to Kafka topics Consumer subscribes via @KafkaListener in consumer group Topics are durable and partitioned for reliability – demo6.jpg bottom – Same style 79 legend
Tier 8 GitHub Goal Achieved – Architecture diagram in README, each service = repo – 78+79+80 – demo6.jpg – Same style 79 goal but with 6 demos
```
