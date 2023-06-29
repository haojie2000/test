package com.ncu.edu;


import com.ncu.edu.util.SnowflakeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        SnowflakeUtil snowflakeUtil = new SnowflakeUtil();
        for (int i = 0; i < 5; i++) {

            String s = snowflakeUtil.snowflakeId();

            System.out.println(s);
        }



    }
}
