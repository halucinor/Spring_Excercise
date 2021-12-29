package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import hello.core.order.orderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /*
    @Bean memberService => new MemoryMemberRepository();
    @Bean orderService => new MemoryMemberRepository();
    -> 객체가 두번 생성 되는가?
    예상
    call Appconfig.memberService
    call Appconfig.memberRepository
    call Appconfig.memberRepository
    call Appconfig.orderService
    call Appconfig.memberRepository

    실제
    call Appconfig.memberService
    call Appconfig.memberRepository
    call Appconfig.orderService

    */


    //생성자 주입
    @Bean
    public MemberService memberService(){
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public orderService orderService(){
        System.out.println("AppConfig.orderService");
        //return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
        //return new FixDiscountPolicy();
    }
}
