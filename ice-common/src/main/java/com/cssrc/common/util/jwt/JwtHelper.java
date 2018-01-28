package com.cssrc.common.util.jwt;

import com.cssrc.common.constant.CommonConstants;
import com.cssrc.common.util.StringHelper;
import com.cssrc.common.util.KeyHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtHelper {

    private static final Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    /**
     * 密钥加密token
     * @param jwtInfo
     * @param priKeyPath
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(IJwtInfo jwtInfo,String priKeyPath,int expire) throws Exception{

        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getUniqueName())
                .claim(CommonConstants.JWT_KEY_USER_ID,jwtInfo.getId())
                .claim(CommonConstants.JWT_KEY_NAME,jwtInfo.getName())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, KeyHelper.getPrivateKey(priKeyPath))
                .compact();
        return compactJws;
    }

    /**
     * 公钥解密token
     * @param token
     * @param pubKeyPath
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token,String pubKeyPath) throws Exception{
        Jws<Claims> claims =  Jwts.parser().setSigningKey(KeyHelper.getPublicKey(pubKeyPath)).parseClaimsJws(token);
        return claims;
    }

    /**
     * 获取token中的用户信息
     * @param token
     * @param pubKeyPath
     * @return
     * @throws Exception
     */
    public  static IJwtInfo getInfoFromToken(String token,String pubKeyPath) throws Exception{
        Jws<Claims> claimsJws = parserToken(token,pubKeyPath);
        Claims body = claimsJws.getBody();
        return new JwtInfo(body.getSubject(), StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_USER_ID)),StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_NAME)));

    }
}
