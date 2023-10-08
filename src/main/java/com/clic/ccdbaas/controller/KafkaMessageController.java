package com.clic.ccdbaas.controller;

import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.service.ResourceApplicationService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("kafka")
public class KafkaMessageController {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageController.class);
    @Resource
    KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    ResourceApplicationService resourceApplicationService;
    @Value("${spring.kafka.producer.topic}")
    String producerTopic;

    /**
     * 触发一条生产者信息(测试)
     * 读本地文件
     */
    @RequestMapping("send")
    @ResponseBody
    public JSONObject sendMessage() {
        String path = "C:\\Users\\Administrator\\Desktop\\demo.json";
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(new FileInputStream(path), JSONObject.class);
            kafkaTemplate.send(producerTopic, jsonObject.toString());
            System.out.println(jsonObject);
            logger.info("成功生产 1 条数据");
            return jsonObject;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 触发一条生产者信息(测试)
     * 读JsonBody
     */
    @RequestMapping("sendFromBody")
    @ResponseBody
    public String sendMessageFromBody(@RequestBody JSONObject requestParams) {
        String flag = "false";
        try {
            kafkaTemplate.send(producerTopic, requestParams.toString());
            flag = "true";
            logger.info("成功生产 1 条数据");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    /**
     * 生产者信息回传
     */
    @RequestMapping("kafkaProducerMessage")
    @ResponseBody
    public JSONObject kafkaProducerMessage(@RequestParam String workOrder) {
        JSONObject jsonObject = resourceApplicationService.getProducerMessage(workOrder);
        kafkaTemplate.send(producerTopic, jsonObject.toString());
        System.out.println(jsonObject);
        logger.info("成功生产 1 条数据");
        return jsonObject;
    }

    /**
     * 插入一条信息(测试)
     *
     * @param requestParams
     */
    @RequestMapping("insertFromBody")
    @ResponseBody
    public void insertFromJsonBody(@RequestBody JSONObject requestParams) {
        resourceApplicationService.insertResourceApplication(requestParams);
    }

    /**
     * 消费者监听
     *
     * @param ConsumerRecord
     */
//    @KafkaListener(topics = "${spring.kafka.consumer.topic}")
    public void kafkaConsumer(ConsumerRecord<String, String> ConsumerRecord) {
        System.out.println("[Kafka-Consumer] key = " + ConsumerRecord.key() +
                "、value = " + ConsumerRecord.value());
        JSONObject jsonObject = JSONObject.parseObject(ConsumerRecord.value());
        if (jsonObject.get("applyType").equals(0)) {
            logger.info("成功消费 1 条新增资源数据");
            resourceApplicationService.insertResourceApplication(jsonObject);
        } else if (jsonObject.get("applyType").equals(1)) {
            logger.info("成功消费 1 条扩容资源数据");
        }
    }

    /**
     * 消费者监听资源申请信息(0-虚机 1-物理机 2-宿主机)
     *
     * @param ConsumerRecord
     */
    @KafkaListener(topics = "${spring.kafka.consumer.resourceApplication.topic}")
    public void resourceApplicationConsumer(ConsumerRecord<String, String> ConsumerRecord) {
        logger.info("[Kafka-Consumer] key = " + ConsumerRecord.key() +
                "、value = " + ConsumerRecord.value());
        try {
            JSONObject jsonObject = JSONObject.parseObject(ConsumerRecord.value());
            resourceApplicationService.updateResourceApplication(jsonObject);
            logger.info("成功消费 1 条资源申请数据");
        } catch (Exception e) {
            logger.error("提供资源申请信息有误");
            throw new RuntimeException(e);
        }
    }

    /**
     * 消费者监听资源回收信息(0-虚机 1-物理机 2-宿主机)
     *
     * @param ConsumerRecord
     */
    @KafkaListener(topics = "${spring.kafka.consumer.resourceRecycling.topic}")
    public void resourceRecyclingConsumer(ConsumerRecord<String, String> ConsumerRecord) {
        logger.info("[Kafka-Consumer] key = " + ConsumerRecord.key() +
                "、value = " + ConsumerRecord.value());
        try {
            JSONObject jsonObject = JSONObject.parseObject(ConsumerRecord.value());
            resourceApplicationService.updateResourceRecycling(jsonObject);
            logger.info("成功消费 1 条资源回收数据");
        } catch (Exception e) {
            logger.error("提供资源回收信息有误");
            throw new RuntimeException(e);
        }
    }
}
