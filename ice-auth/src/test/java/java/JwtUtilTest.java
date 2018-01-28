package java;

import com.cssrc.auth.util.JwtUtil;
import com.cssrc.common.util.jwt.IJwtInfo;
import com.cssrc.common.util.jwt.JwtInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class JwtUtilTest extends BaseUnitTest{
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testJwt() throws Exception{
        String username="admin";
        String userId="1";
        String name="ice";
        String token =jwtUtil.generateToken(new JwtInfo(username,userId,name));
        IJwtInfo infoFromToken = jwtUtil.getInfoFromToken(token);
        //Thread.sleep(3000);
        assertEquals(username,infoFromToken.getUniqueName());
    }

}
