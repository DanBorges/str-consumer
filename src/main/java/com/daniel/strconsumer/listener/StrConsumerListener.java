package com.daniel.strconsumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import com.daniel.strconsumer.custom.StrConsumerCustomListener;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class StrConsumerListener {
	
	@StrConsumerCustomListener(groupId = "group-1")
	public void create(String message) {
		log.info(" CREATE ::: Recive message {}", message);
		throw new IllegalArgumentException("EXCEPTION...");
		
	}
	
	@KafkaListener(groupId = "group-1", topics = "str-topic", containerFactory = "strContainerFactory")
	public void log(String message) {
		log.info(" LOG ::: Recive message {}", message);
	}
	
	@KafkaListener(groupId = "group-2", topicPartitions = {
		@TopicPartition(topic = "str-topic", partitions = {"0"})	
	},containerFactory = "validMessageContainerFactory")
	public void history(String message) {
		log.info(" HISTORY ::: Recive message {}", message);
	}
	
	@KafkaListener(groupId = "group-2", topicPartitions = {
			@TopicPartition(topic= "str-topic", partitions = {"1"})
	}, containerFactory = "strContainerFactory")
	public void Test(String message) {
		log.info(" TEST ::: Recive message {}", message);
	}
	
	

}
