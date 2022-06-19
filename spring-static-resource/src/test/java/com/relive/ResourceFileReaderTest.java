package com.relive;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.IOException;

/**
 * @author: ReLive
 * @date: 2022/6/19 9:43 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = ResourceFileReader.class,
        loader = AnnotationConfigContextLoader.class)
public class ResourceFileReaderTest {

    @Autowired
    private ResourceFileReader resourceFileReader;

    @Test
    public void classPathResourceTest() throws IOException {
        Assert.assertEquals("Test resource read", resourceFileReader.classPathResource().trim());
    }

    @Test
    public void inputStreamMethod1Test() throws IOException {
        Assert.assertEquals("Test resource read", resourceFileReader.inputStreamMethod1().trim());
    }

    @Test
    public void inputStreamMethod2Test() throws IOException {
        Assert.assertEquals("Test resource read", resourceFileReader.inputStreamMethod2().trim());
    }

    @Test
    public void fileReadMethodTest() throws IOException {
        Assert.assertEquals("Test resource read", resourceFileReader.fileReadMethod().trim());
    }


    @Test
    public void resourceUtils() throws IOException {
        Assert.assertEquals("Test resource read", resourceFileReader.resourceUtils().trim());
    }
}
