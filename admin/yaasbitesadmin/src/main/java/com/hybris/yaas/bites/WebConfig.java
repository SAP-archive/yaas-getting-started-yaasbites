package com.hybris.yaas.bites;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	private final Logger LOG = LoggerFactory.getLogger(WebConfig.class);
    
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
		LOG.debug("IN addCorsMappingsaddCorsMappingsaddCorsMappingsaddCorsMappingsaddCorsMappingsaddCorsMappingsaddCorsMappingsaddCorsMappings " );
	}
}