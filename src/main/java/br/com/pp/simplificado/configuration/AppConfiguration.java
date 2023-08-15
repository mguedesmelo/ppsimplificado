package br.com.pp.simplificado.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class AppConfiguration implements WebMvcConfigurer {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    LocaleResolver localeResolver() {
//    	SessionLocaleResolver toReturn = new SessionLocaleResolver();
    	AcceptHeaderLocaleResolver toReturn = new AcceptHeaderLocaleResolver();
        toReturn.setDefaultLocale(new Locale("pt", "BR"));
        return toReturn;
    }

    @Bean
    ResourceBundleMessageSource messageSource() {
    	ResourceBundleMessageSource toReturn = new ResourceBundleMessageSource();
//       messageSource.setBasenames("messages");
       toReturn.setDefaultEncoding("UTF-8");
       return toReturn;
    }

    @Bean
    LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor toReturn = new LocaleChangeInterceptor();
//        toReturn.setParamName("lang");
        return toReturn;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
