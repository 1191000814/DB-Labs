package school;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest{

    @Test
    public void test1(){
        System.out.println(Float.parseFloat("1"));
    }
}
