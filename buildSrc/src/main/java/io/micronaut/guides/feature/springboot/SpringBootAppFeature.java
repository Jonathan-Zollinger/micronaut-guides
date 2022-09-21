package io.micronaut.guides.feature.springboot;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.starter.application.ApplicationType;
import io.micronaut.starter.application.generator.GeneratorContext;
import io.micronaut.starter.build.gradle.GradleDsl;
import io.micronaut.starter.build.gradle.GradlePlugin;
import io.micronaut.starter.feature.build.gradle.templates.useJunitPlatform;
import io.micronaut.starter.options.TestFramework;
import io.micronaut.starter.template.RockerTemplate;
import jakarta.inject.Singleton;

@Singleton
public class SpringBootAppFeature implements SpringBootApplicationFeature {
    @Override
    @NonNull
    public String getName() {
        return "spring-boot-app";
    }

    @Override
    public String getTitle() {
        return "Spring Boot application";
    }

    @Override
    @NonNull
    public String getDescription() {
        return "Generates a Spring Boot application such as the one will generate in spring initializr";
    }

    @Override
    public String getCategory() {
        return "Spring Boot";
    }

    @Override
    public boolean supports(ApplicationType applicationType) {
        return ApplicationType.DEFAULT == applicationType;
    }

    @Override
    public String getThirdPartyDocumentation() {
        return "https://start.spring.io";
    }

    @Override
    public void apply(GeneratorContext generatorContext) {
        if (generatorContext.getBuildTool().isGradle()) {
            applySpringBootGradlePlugin(generatorContext);
            applySpringDependencyManagementGradlePlugin(generatorContext);
        }
    }

    /**
     * <a href="https://plugins.gradle.org/plugin/io.spring.dependency-management">Spring Dependency Management</a>
     * @param generatorContext Generator Context
     */
    private void applySpringDependencyManagementGradlePlugin(GeneratorContext generatorContext) {
        generatorContext.addBuildPlugin(gradlePlugin("io.spring.dependency-management", "dependency-management-plugin").build());
    }

    /**
     * @see <a href="https://plugins.gradle.org/plugin/org.springframework.boot">Spring Boot Gradle Plugin</a>
     * @param generatorContext Generator Context
     */
    private static void applySpringBootGradlePlugin(GeneratorContext generatorContext) {
        GradlePlugin.Builder gradlePlugin = gradlePlugin("org.springframework.boot", "spring-boot-gradle-plugin");
        if (generatorContext.getTestFramework() == TestFramework.JUNIT || generatorContext.getTestFramework() == TestFramework.SPOCK) {
            generatorContext.addBuildPlugin(gradlePlugin
                    .extension(new RockerTemplate(useJunitPlatform.template(generatorContext.getBuildTool().getGradleDsl().orElse(GradleDsl.GROOVY))))
                    .build()
            );
        } else {
            generatorContext.addBuildPlugin(gradlePlugin.build());
        }
    }
    private static GradlePlugin.Builder gradlePlugin(String id, String lookupArtifactId) {
        return GradlePlugin.builder()
                .id(id)
                .lookupArtifactId(lookupArtifactId);
    }
}

