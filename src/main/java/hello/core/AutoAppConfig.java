package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import hello.core.order.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "hello.core.member",
        //basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    @Autowired MemberRepository memberRepository;
//    @Autowired DiscountPolicy discountPolicy;
//
//    @Bean
//    public orderService orderService(){
//        return new OrderServiceImpl(memberRepository,discountPolicy);
//    }

//    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}
