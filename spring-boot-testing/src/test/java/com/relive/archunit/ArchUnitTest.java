package com.relive.archunit;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

/**
 * @author: ReLive
 * @date: 2022/12/18 22:35
 */
@AnalyzeClasses(packages = "com.relive", importOptions = {ImportOption.DoNotIncludeTests.class,
        ArchUnitTest.ExcludeControllerImportOption.class})
public class ArchUnitTest {

    /**
     * 检查Application 类名称是否为“SpringBootTestApplication”
     */
    @ArchTest
    public static final ArchRule application_class_name_should_be =
            classes().that().areAnnotatedWith(SpringBootApplication.class)
                    .should().haveSimpleName("SpringBootTestApplication");

    /**
     * 检查是否所有 Controller 类都具有后缀“Controller”
     */
    @ArchTest
    static ArchRule controllers_suffixed_should_be =
            classes().that().resideInAPackage("..controller..")
                    .or().areAnnotatedWith(RestController.class)
                    .should().haveSimpleNameEndingWith("Controller")
                    .allowEmptyShould(true);

    /**
     * 检查实体类是否位于 "entity" 包下
     */
    @ArchTest
    static final ArchRule tablename_must_reside_in_a_entity_package =
            classes().that().areAnnotatedWith(TableName.class)
                    .should().resideInAPackage("..entity..")
                    .as("TableName should reside in a package '..entity..'")
                    .allowEmptyShould(true);

    /**
     * 检查配置类是否位于 "config" 包中
     */
    @ArchTest
    static final ArchRule configs_must_reside_in_a_config_package =
            classes().that().areAnnotatedWith(Configuration.class)
                    .or().areNotNestedClasses()
                    .and().areAnnotatedWith(ConfigurationProperties.class)
                    .should().resideInAPackage("..config..")
                    .as("Configs should reside in a package '..config..'")
                    .allowEmptyShould(true);

    /**
     * 检查配置类注释都应为@Configuration或@ConfigurationProperties
     */
    @ArchTest
    static ArchRule configs_should_be_annotated =
            classes()
                    .that().resideInAPackage("..config..")
                    .and().areNotNestedClasses()
                    .should().beAnnotatedWith(Configuration.class)
                    .orShould().beAnnotatedWith(ConfigurationProperties.class);


    /**
     * 图层检查，Service层仅被Controller访问，Dao层仅被Service层访问
     */
    @ArchTest
    static ArchRule layer_inspection = layeredArchitecture()
            .consideringAllDependencies()
            .layer("Controller").definedBy("..controller..")
            .layer("Service").definedBy("..service..")
            .layer("Dao").definedBy("..dao..")

            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
            .whereLayer("Dao").mayOnlyBeAccessedByLayers("Service");

    /**
     * 或者你可能有一个你想忽略的类，因为规则不适用于它。创建一个自定义规则并导入它。
     */
    static class ExcludeControllerImportOption implements ImportOption {
        @Override
        public boolean includes(Location location) {
            return !location.contains("SomeExcludedControllerClasses");
        }
    }
}
