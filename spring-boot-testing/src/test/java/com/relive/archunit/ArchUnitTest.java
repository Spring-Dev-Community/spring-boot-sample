package com.relive.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * @author: ReLive
 * @date: 2022/12/18 22:35
 */
@AnalyzeClasses(packages = "com.relive")
public class ArchUnitTest {

    /**
     * Application 类名称应为“SpringBootTestApplication”
     */
    @ArchTest
    public static final ArchRule application_class_name_should_be = classes().that().areAnnotatedWith(SpringBootApplication.class)
            .should().haveSimpleName("SpringBootTestApplication");

    /**
     * 检查是否所有 Controller 类都具有后缀“Controller”
     */
    @ArchTest
    static ArchRule controllers_should_be_suffixed = classes()
            .that().resideInAPackage("..controller..")
            .or().areAnnotatedWith(RestController.class)
            .should().haveSimpleNameEndingWith("Controller")
            .allowEmptyShould(true);
}
