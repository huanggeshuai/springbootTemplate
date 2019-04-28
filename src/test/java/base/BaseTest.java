package base;

import com.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huang
 * @Classname base.BaseTest
 * @Description 测试基类
 * @Date 2019/4/27 18:18
 * @Created by huang
 */
@Slf4j(topic = "public")
@RunWith(SpringRunner.class)
//指定spring boot启动项
@SpringBootTest(classes = {Application.class})
public class BaseTest {

}
