package com.qkforex.springbootstudy;

import com.qkforex.springbootstudy.chapter03.test.TestBean;
import com.qkforex.springbootstudy.chapter03.test.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles("prod")
public class DemoBeanIntegrationTests {
    @Autowired
    TestBean testBean;

    @Test
    public void prodBeanShouldInject() {
        String context = "from production profile";
        String actual = testBean.getContext();
        Assert.assertEquals(context, actual);
    }

}
