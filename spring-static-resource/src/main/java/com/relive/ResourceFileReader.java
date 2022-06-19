package com.relive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author: ReLive
 * @date: 2022/6/19 9:30 下午
 */
@Component
public class ResourceFileReader {

    @Autowired
    ResourceLoader resourceLoader;

    /**
     * 开发环境和生产环境(Linux下jar包运行读取)都能读取到文件
     *
     * @throws IOException
     */
    public String classPathResource() throws IOException {
        Resource resource = new ClassPathResource("test.txt");
        InputStream fis = resource.getInputStream();
        StringBuilder builder = new StringBuilder();
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf, 0, buf.length)) != -1) {
            builder.append(new String(buf, StandardCharsets.UTF_8));
        }
        fis.close();
        return builder.toString();
    }


    /**
     * 开发环境和生产环境(Linux下jar包运行读取)都能读取到文件
     *
     * @throws IOException
     */
    public String inputStreamMethod1() throws IOException {
        InputStream fis = this.getClass().getResourceAsStream("/test.txt");
        StringBuilder builder = new StringBuilder();
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf, 0, buf.length)) != -1) {
            builder.append(new String(buf, StandardCharsets.UTF_8));
        }
        fis.close();
        return builder.toString();
    }

    /**
     * 开发环境和生产环境(Linux下jar包运行读取)都能读取到文件
     *
     * @throws IOException
     */
    public String inputStreamMethod2() throws IOException {
        InputStream fis = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.txt");
        StringBuilder builder = new StringBuilder();
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf, 0, buf.length)) != -1) {
            builder.append(new String(buf, StandardCharsets.UTF_8));
        }
        fis.close();
        return builder.toString();
    }

    /**
     * 开发环境和生产环境(Linux下jar包运行读取)都能读取到文件
     *
     * @throws IOException
     */
    public String resourceLoader() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:test.txt");
        InputStream fis = resource.getInputStream();
        StringBuilder builder = new StringBuilder();
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf, 0, buf.length)) != -1) {
            builder.append(new String(buf, StandardCharsets.UTF_8));
        }
        fis.close();
        return builder.toString();
    }

    /**
     * 只能在开发环境中读取，不能再生产环境中读取(Linux下jar包)运行读取文件
     *
     * @throws IOException
     */
    public String fileReadMethod() throws IOException {
        File file = new File("src/main/resources/test.txt");
        InputStream fis = new FileInputStream(file);
        StringBuilder builder = new StringBuilder();
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf, 0, buf.length)) != -1) {
            builder.append(new String(buf, StandardCharsets.UTF_8));
        }
        fis.close();
        return builder.toString();
    }


    /**
     * 只能在开发环境中读取，不能再生产环境中读取(Linux下jar包)运行读取文件
     *
     * @throws IOException
     */
    public String resourceUtils() throws IOException {
        File file = ResourceUtils.getFile("src/main/resources/test.txt");
        InputStream fis = new FileInputStream(file);
        StringBuilder builder = new StringBuilder();
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf, 0, buf.length)) != -1) {
            builder.append(new String(buf, StandardCharsets.UTF_8));
        }
        fis.close();
        return builder.toString();
    }
}
