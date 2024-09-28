package lk.riyapola.system;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootTest
public class DemoTest {

    @Test
    public void generateSecretKey(){

        SecretKey key = Jwts.SIG.HS256.key().build();

        String base64EncodeKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(" Generate jwt Secret Key :" + base64EncodeKey);
        //      2bSU7ttILA8FdHYfiQdrYyW0sCxJ3QebJgDEbCG8i9E=
    }
}
