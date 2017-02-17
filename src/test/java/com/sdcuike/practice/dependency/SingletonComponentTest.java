package com.sdcuike.practice.dependency;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SingletonComponentTest {

    @Autowired
    private SingletonComponent singletonComponent;

    @Autowired
    private SingletonComponent singletonComponent2;

    @Autowired
    private ScopedComponent    scopedComponent;
    @Autowired
    private ScopedComponent    scopedComponent2;

    @Test
    public void testGetScopedBeanHasdCode() {
        int a = singletonComponent.getScopedBeanHasdCode();
        int b = singletonComponent.getScopedBeanHasdCode();
        Assert.assertNotEquals(a, b);
        Assert.assertNotEquals(scopedComponent.getHashCode(), scopedComponent2.getHashCode());
        Assert.assertThat(singletonComponent.hashCode(), IsEqual.equalTo(singletonComponent2.hashCode()));
    }

}
