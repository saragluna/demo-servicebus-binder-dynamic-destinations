package com.example.demo_servicebus_binder;

import com.azure.spring.cloud.service.servicebus.properties.ServiceBusEntityType;
import com.azure.spring.cloud.stream.binder.servicebus.core.properties.ServiceBusBindingProperties;
import com.azure.spring.cloud.stream.binder.servicebus.core.properties.ServiceBusConsumerProperties;
import com.azure.spring.cloud.stream.binder.servicebus.core.properties.ServiceBusExtendedBindingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.binding.NewDestinationBindingCallback;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;

import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
public class DemoServicebusBinderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoServicebusBinderApplication.class, args);
	}

	@Autowired
	private StreamBridge streamBridge;

	@Override
	public void run(String... args) throws Exception {

		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				streamBridge.send("destinationAsPayload-in-0", "even");
			} else {
				streamBridge.send("destinationAsPayload-in-0", "odd");
			}
		}

		Thread.sleep(1000 * 1000);
	}

	@Bean
	public Consumer<String> ceven() {
		return value -> {
			System.out.println("RECEIVED FROM EVEN: " + value);
		};
	}

	@Bean
	public Consumer<String> codd() {
		return value -> {
			System.out.println("RECEIVED FROM ODD: " + value);
		};
	}

	@Bean
	public Consumer<ErrorMessage> myErrorHandler() {
		return v -> {
			// send SMS notification code
		};
	}

}
