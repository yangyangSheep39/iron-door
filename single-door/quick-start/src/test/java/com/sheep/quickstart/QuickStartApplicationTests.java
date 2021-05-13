package com.sheep.quickstart;

import com.sheep.quickstart.utils.PasswordEncoderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuickStartApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(PasswordEncoderUtil.encode("admin"));
    }

}
