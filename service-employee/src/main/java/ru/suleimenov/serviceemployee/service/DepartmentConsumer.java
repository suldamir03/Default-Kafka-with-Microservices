package ru.suleimenov.serviceemployee.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.suleimenov.serviceemployee.model.Department;

@Service
public class DepartmentConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentConsumer.class);
    @Autowired
    private ObjectMapper objectMapper;


    @KafkaListener(
            topics = "test4"
            ,groupId = "foo",
            containerFactory = "singleFactory"
    )
    public void consume( Department event) throws JsonProcessingException {

        LOGGER.info(String.format("Department event received in employee service => %s", writeValueAsString(event)));
        System.out.println("event: " + event.getName());

    }

    private String writeValueAsString(Department department) {
        try {
            return objectMapper.writeValueAsString(department);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + department.toString());
        }
    }
}