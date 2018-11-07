import com.lusiwei.util.MD5Utils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;

/**
 * @Author: lusiwei
 * @Date: 2018/11/5 21:39
 * @Description:
 */
public class Demo {
    @Test
    public void test(){
        String encryptedPassword = MD5Utils.calc("123456");
        System.out.println(encryptedPassword);
    }
}
