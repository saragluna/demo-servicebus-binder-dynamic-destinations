package com.example.demo_servicebus_binder;

import com.azure.spring.cloud.stream.binder.servicebus.core.properties.ServiceBusProducerProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.cloud.stream.binding.NewDestinationBindingCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.function.Function;

@Configuration
public class FunctionConfiguration {

    @PostConstruct
    void init() {
        System.out.println("init");
    }

    @Bean
    public NewDestinationBindingCallback<ServiceBusProducerProperties> dynamicConfigurer() {
        return (name, channel, props, extended) -> {
            System.out.println("NewDestinationBindingCallback: name=" + name);
//            extended.setEntityType(ServiceBusEntityType.QUEUE);
        };
    }



    @Bean
    public Function<String, Message<String>> destinationAsPayload() {
        return value -> MessageBuilder.withPayload(value)
                .setHeader("spring.cloud.stream.sendto.destination", value).build();
    }

}
