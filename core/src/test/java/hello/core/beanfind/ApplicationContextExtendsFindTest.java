package hello.core.beanfind;


import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("parent")
    void findBeanByParentType(){
        DiscountPolicy bean =ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class,()->
                ac.getBean(DiscountPolicy.class));
    }
    @Test
    @DisplayName("parent Duplicate")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rate = ac.getBean("rate", DiscountPolicy.class);
        assertThat(rate).isInstanceOf(RateDiscountPolicy.class);
    }
    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
