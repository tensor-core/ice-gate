package test;

import com.cssrc.uc.UserCenterBootstrap;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UserCenterBootstrap.class)
@WebMvcTest
public class BaseUnitTest {
}
