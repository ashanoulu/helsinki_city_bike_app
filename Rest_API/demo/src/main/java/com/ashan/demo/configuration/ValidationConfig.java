//package com.ashan.demo.configuration;
//
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//
//@Configuration
//public class ValidationConfig {
//    private final MessageSource validationMessageSource;
//
//    public ValidationConfig(MessageSource validationMessageSource) {
//        this.validationMessageSource = validationMessageSource;
//    }
//
//    @Bean
//    public LocalValidatorFactoryBean getValidator() {
//        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//        bean.setValidationMessageSource(validationMessageSource);
//
//        return bean;
//    }
//}
