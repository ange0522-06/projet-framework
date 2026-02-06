package com.framework.backend;

import com.framework.backend.config.DataConfig;
import com.framework.backend.config.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, DataConfig.class})
@WebAppConfiguration
public class BackendApplicationTests {

    @Test
    public void contextLoads() {
        // Test que le contexte Spring se charge correctement
    }
}
