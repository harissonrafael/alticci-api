package com.altice.alticci;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
class AlticciApplicationTests {

    @Autowired
    private ConfigurableApplicationContext context;

    @Test
    void main() {
        AlticciApplication.main(new String[]{});
        assertTrue(context.isActive());
    }

}
