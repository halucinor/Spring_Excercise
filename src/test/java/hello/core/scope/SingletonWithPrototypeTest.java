package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest {

    @Test
    public void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);

        prototypeBean.addCount();
        assertThat(prototypeBean.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUrePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
//        assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean{

//        @Autowired
//        ApplicationContext applicationContext;
//        private final PrototypeBean prototypeBean;  //생성시점에 주입

        @Autowired
            private Provider<PrototypeBean> prototypeBeanProvider;

//        private ObjectFactory<PrototypeBean> prototypeBeanProvider;
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;  //ObjectFactory를 상속 받아 편의기능을 추가함

//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean){
//            this.prototypeBean = prototypeBean;
//        }

        public int logic(){
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            this.count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean = " + this);
        }

        @PreDestroy
        public void destory(){
            System.out.println("PrototypeBean.destory");
        }
    }
}
