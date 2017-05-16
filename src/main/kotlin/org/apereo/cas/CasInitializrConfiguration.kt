package org.apereo.cas

import io.spring.initializr.generator.ProjectGenerator
import io.spring.initializr.generator.ProjectRequestPostProcessor
import io.spring.initializr.generator.ProjectRequestResolver
import io.spring.initializr.metadata.*
import io.spring.initializr.util.TemplateRenderer
import io.spring.initializr.web.project.MainController
import org.apereo.cas.generator.CasProjectGenerator
import org.apereo.cas.web.CasMainController
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.resource.ResourceUrlProvider

@Configuration
open class CasInitializrConfiguration(postProcessors: ObjectProvider<List<ProjectRequestPostProcessor>>) {
    private val postProcessors: List<ProjectRequestPostProcessor> = postProcessors.ifAvailable ?: listOf()

    @Bean
    open fun initializrMainController(
            metadataProvider: InitializrMetadataProvider,
            templateRenderer: TemplateRenderer,
            resourceUrlProvider: ResourceUrlProvider,
            projectGenerator: ProjectGenerator,
            dependencyMetadataProvider: DependencyMetadataProvider): MainController {
        return CasMainController(metadataProvider, templateRenderer, resourceUrlProvider,
                projectGenerator, dependencyMetadataProvider)
    }

    @Bean
    open fun initializrMetadataProvider(properties: InitializrProperties): InitializrMetadataProvider {
        val metadata = InitializrMetadataBuilder.fromInitializrProperties(properties).build()
        return SimpleInitializrMetadataProvider(metadata)
    }

    @Bean
    @ConditionalOnMissingBean
    open fun projectRequestResolver(): ProjectRequestResolver {
        return ProjectRequestResolver(postProcessors)
    }

    @Bean
    open fun projectGenerator(): ProjectGenerator {
        return CasProjectGenerator()
    }
}
