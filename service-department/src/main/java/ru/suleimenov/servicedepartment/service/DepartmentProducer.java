package ru.suleimenov.servicedepartment.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.suleimenov.servicedepartment.model.Department;


@Service
public class DepartmentProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentProducer.class);

    private NewTopic topic;

    private KafkaTemplate kafkaTemplate;

    public DepartmentProducer(NewTopic topic, KafkaTemplate<String, Department> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Department event){
        LOGGER.info(String.format("Department event => %s", event.toString()));

//         create Message
        Message<Department> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}