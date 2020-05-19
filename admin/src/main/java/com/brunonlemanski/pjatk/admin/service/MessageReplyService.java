package com.brunonlemanski.pjatk.admin.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class MessageReplyService {

    @KafkaListener(topics = "request_topic", containerFactory = "requestListenerContainerFactory")
    @SendTo
    public String receive(String message) {

        System.out.println("KIEROWCA: " + message);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Wpisz TAK/NIE: ");
        String odp = scanner.nextLine();

        return odp;
    }
}
