package com.big_event;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BigEventApplicationTests {

    @Test
    void contextLoads() {
        HelloService helloService = new HelloService();
        helloService.getById(12_222_222);
    }

}
