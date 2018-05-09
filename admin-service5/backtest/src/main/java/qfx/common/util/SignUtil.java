package qfx.common.util;


import org.apache.commons.lang.StringUtils;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.net.URLEncoder;

public class SignUtil {
    public static final String TAG = SignUtil.class.getSimpleName();

    /*C%2BAWB0TBn7MPTk2eM%2FnpUIjyMwI%3D*/

    /*C%2BAWB0TBn7MPTk2eM%2FnpUIjyMwI%3D*/

    private static final String MAC_NAME = "HmacSHA1";

    public static final String SECRET_KEY = "B123456789";

    public static final String TIME = "time";

    public static final String SECRET_ID = "secretId";

    public static final String SECRET_ID_VALUE = "dianziqianzhang";

    public static final String KEY_SING = "sign";

    /**
     * 参数进行加密
     * @param map  返回添加完 sercretid  时间戳 和 加密信息的 参数map
     * @return
     */
    public static Map sign(Map map){
        if (map == null) {
            return null;
        }

        Map temp = insertSecretAndTime(map);

        String signInfo = getSignInfo(temp);
        temp.put(KEY_SING,URLEncoder.encode(signInfo));
        return temp;
    }


    public static Map insertSecretAndTime(Map map){

        map.put(TIME, String.valueOf(Calendar.getInstance().getTime().getTime()));

        map.put(SECRET_ID,SECRET_ID_VALUE);

        return map;
    }


    public static String getSignInfo(Map params) {

               Collection<String> keyset = params.keySet();

        List<String> list = new ArrayList<String>(keyset);

        Collections.sort(list);

        StringBuffer sb = new StringBuffer();
        for (String key : list) {
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        String s = sb.toString();
        s = s.substring(0, s.length() - 1);

        try {
            s = hmacSha1(s, SECRET_KEY);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return s;

    }

    /**
     * 获取 hmacSha1
     *
     * @param base
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String hmacSha1(String base, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        if (StringUtils.isEmpty(base) || StringUtils.isEmpty(key)) {
            return "";
        }

        SecretKeySpec secret = new SecretKeySpec(key.getBytes(), MAC_NAME);
        Mac mac = Mac.getInstance(MAC_NAME);
        mac.init(secret);
        byte[] digest = mac.doFinal(base.getBytes());
        String result ="";
        try {
            result = Base64.getEncoder().encodeToString(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
}
