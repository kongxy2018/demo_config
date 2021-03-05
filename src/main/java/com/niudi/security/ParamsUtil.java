package com.niudi.security;

import java.util.*;


/**
 * @author johny
 */
public class ParamsUtil {

    private String privateKey = null;

    public String getPrivateKey() {
        privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJWsLsfeSXA++pfgFNzRm0jKu7csTNx4Xlmmjjvhmk9vLE" +
                "/jVo/hsY3JcGaFmPQh4ZTizAyGDCszBLuhCqjOBDsw/wpCkSJod2" +
                "+L56esv5kfvfQWI9wwfoe7cIjb7rg99qwUpaDZYX6YV98ijf1RIbN6cwe8Ca25" +
                "+L3LaJW3dBrJAgMBAAECgYBw3fglFpaQw51bnEp/Pr14BhHKjuijNU4JExWdSIKCNlbXI5uqQssPHgHZ/hMavtYK3YB8TTWt" +
                "+fout8F/DGSZSxIbULan7f0/mVgvPYxqyfYTkedsn3vSWBkTsScsPkujKFOUTrPg6iZaNqhLb1t4D3VFkASMOWC" +
                "+pvIm8W4Q9QJBAMdi7uWftWyVknuh7WAXX3jpVZE85L4aDGd0" +
                "+TcirsqUTtzzPPbu14eFE9HzXG9gWDksAcDffaY5jWH2FbvKGVsCQQDAK6HDjGBeCB1ava7KeGmF7Dy4nTDGMnmee" +
                "/QKfxDDLrj3M8RxdLYGfj6fCrkLCPut8lScnS1RDANnlV9GDHGrAkAcTx6uOZvPu/jdVuNWJltm1nJQub2chCRIuCH+Qw1gM" +
                "+hylJKOWOD/G9TxlUUFhz9fdrEVCmZTqI47X248iHhLAkAtEY6Ml" +
                "/150BliUJ4PqhHFcNQE0AjCUtAnFRb05zv9KpYCk8d8fEy8VRtGnXMsXVz5PJ4PZOVd3p1ly6hee0ilAkBp7iMTnAU" +
                "//4ioYxZqqVgx0590znTcL+79+gkvsbFEHP+f0u8ajSTUwomnaw+961G3aH9Y082S+8+DKvkWJ+kw";
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * 生成要请求的参数数组
     *
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {

        // 除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(sParaTemp);
        // 生成签名结果
        String mysign = buildRequestMysign(sPara);

        // 签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", SecurityConfig.sign_type);

        return sPara;
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key) == null ? null : String.valueOf(sArray.get(key));
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type") || key.equalsIgnoreCase("signType")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    public static void main(String[] args) {
        ParamsUtil paramsUtil = new ParamsUtil();
        Map map = new HashMap();
        map.put("abcdefg", "12334");
        map.put("abbdefg", "12334");
        map.put("fbcdefg", "12334");
        map.put("dbcdefg", "12334");
        map.put("zbcdefg", "12334");
        map.put("aaa", "12334");
        String linkString = paramsUtil.createLinkString(map);
        System.out.println(linkString);
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuilder prestr = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
//        prestr = prestr + key + "=" + value;
                prestr.append(key).append("=").append(value);
            } else {
//        prestr = prestr + key + "=" + value + "&";
                prestr.append(key).append("=").append(value).append("&");
            }
        }
        return prestr.toString();
    }

    /**
     * 生成签名结果
     *
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestMysign(Map<String, String> sPara) {
        String prestr = createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        System.out.println(prestr);
        String mysign = "";
        String privateKey = "MIIJQwIBADANBgkqhkiG9w0BAQEFAASCCS0wggkpAgEAAoICAQDRKF3nHh7BNlQY\n" +
                "OrodHIcwHfArwwBnzzd6xjF8g5KanN3IeZ0KJH9h+AUrrWnRMLbZuc9jL0bisNJd\n" +
                "JSoQT8kGZ4tMrGMIKQFaOJEGIOiASxoMSiEFuVjNh6XJaoWLs+sqdhyzWYrgaXvc\n" +
                "zYRUGNqZ69BpwvCIcBWTickzEVopGzLxLE1tyeXKCdVQmXP6RBUVyxsDVNZvjeAr\n" +
                "0zOPltm3KrxtOc4OC+0DvFOnR3fWhC3OmSiyGSYlNZI0uo4g1prQPuIrPniU60lg\n" +
                "vocWRss97eGD5XV42RWxHO6ZnzKTrynR8yUiVWf88LifgLfXCXgRtLiX/KXEWegU\n" +
                "ov4YCKR6BDn8+QkIYUGNQgaMBZentk0e7TQ0os1NH9J1NwEiIxLwx2bpi//1Yq4w\n" +
                "2XsyY5h0nMWvTTP/oBCqXZNQO0Lh4cNbCMm6HHyGfe0JLoF9yANORsZ9kgEeS4Ul\n" +
                "ytJY+uVd9tf9J8hEy0PmJ1uqIdBu+zBcsXw5jCUOYZ9s0DMF1CvGw/uXDKV0+/8G\n" +
                "/KWrTOnAsR+gvxTXK850pS3DKuMAHsqAJ6u+VfD5VMIcP2vW7G/kVvfdNcU+mldR\n" +
                "AAzWp5wHo2/i2pmLerHYQwvqh2qaA+peOR59R9KluIRZfYqlECxOYi9fgWRyO2aS\n" +
                "OKoA9oQ0RN2sxLViGAW8l4NVpyGGKQIDAQABAoICAQC1b9H9kkhVjR5oIlGevUec\n" +
                "euwD2lX4t23GKDV3cN91FgziQnTy/nQt9SvvFrCLkjmcJ656uOFB9ueVe47AtaWJ\n" +
                "07qoGYaS5pdQv282pMdruzpuXMPGpmEdccVkMu6EIOAoZmtBPbTWNUsIYJOhLCo9\n" +
                "ia2ygTOM98YPxV/u1+ehbwHC6o6EZbbbi6orD+Lmv7aWwCi/vBGElctX2Q+KSO62\n" +
                "EPGBwro3vyqsqerbWojfYqwl4wDFdkF717vr18VBQUjsyxABOWD6thxiOwUOUv0t\n" +
                "zLRraVp9KbvCd3mXc9049aNOiCC3ZuUdK2jMnKlddc5ddasH1VerO96yQC4xhRW/\n" +
                "W/fZOx4xqbwlC1jWLDt1Lb5/6FmEpu1hsYA4EavaVh4Zsoq9WepceZ7pKPWMf/F0\n" +
                "BJjwxoAjMAdZXBzwhMJBaoNdw56GA0JEkfvsfZAxQdjXtR5fim2S4aLOCpoIq/il\n" +
                "WoBpw+EKvlzJ8uocEEhIfRIIAPiz2UubLiqlYYqRKvVnk12tAOFwXrQ7DmvllCXU\n" +
                "VtjHMFi0sy9wItAxRejiqoUfQVrX44ejBoHP6nkRH8q2YaiKdlQir1knSvZwXt6z\n" +
                "utmblb9fIC6HpF+BQWQxhUDnkIBxavQ3QDnn6awfurKedhbvMUu+EehfkUM4o63g\n" +
                "3LOO/0t772Ym6Py7kpkUAQKCAQEA/JeT5HxijlCAZQBbpAR56iu6863QkCBsTXgY\n" +
                "1Lnl2U4eqCuH5uWyYp/58wMmLiE26zSGKBjDkHv3xreNXHd1C8UvcLXx87b/FUtX\n" +
                "SRddzsPnQAZkXRGZRdVefeGNffvESbUCFu1MgrBDomukf4ZmdPA0QYTazTSuuxEb\n" +
                "5mnwE5jeYHzS6vSm6DbPCZW3yQGL/zfxnkR1rQkyvTcpyMId0I2tuS5r/xNX69AL\n" +
                "kyGRjv068Syziw1aK1nk4I64tUxGhIh+5tvv5mqGn3FoKmGflGNcCMrDSBcc8XRi\n" +
                "+PA54LEpqDblGRvNBF//Bb/WJnYvlUe3hykZUPD133fx4IjUqQKCAQEA0/rFnNAQ\n" +
                "KaJbWTMMzqFVoCDqLsn9a6r9GLecZP+Y5Z/qQCyGyYdU5AXKUxUkSeaOKFDY4tFw\n" +
                "3L5704RZxGPa+c5iGlkFimZdAwhhEKv6QZ6SIj2nEfXXJsatfxzYsyCO1RoQRzUZ\n" +
                "XX7HsWCenfghGH8ma4XtRli6fsEztd7iouapQM2Nru8pUP9nZQZAdAuv8YgoRZTZ\n" +
                "2hKeFfuBzPdE6js/OM8QQGp6MVND1I5XESQMLaHuyUcJwJJNWDDs0UJa8feIH4O2\n" +
                "lxgyIKWMObdNorO5YejX/wODiLsrvabKhz0IatsLl4556moGrhBNt0W9kBp5PMxe\n" +
                "tArGELuQBCiVgQKCAQB8NYFpMu039Ege4p0tu3HEADsPhuJvbTI0qiDzwnJPeIXE\n" +
                "xpsupLcinm5H3L2So4yg/pQV9Y99ZiiClFI0LR3matGYSDAxW0R2UTUb4RylB1qY\n" +
                "9ku2JGhNmc28cwSj9gEhCZx8b7ZW8ZxgqzQZ7ZpGWLQpn84EaKqfnJsuo9uFl3+K\n" +
                "zPbeyO2mdQU8Pl5Avrz8BKOQ4zNGoqZLu2uVqDcHDhRmYniC1x09/XEjqbctLEMm\n" +
                "w4eKkkdL2Amfjy8vH9HxbDmTBoYitTF8CEVjyduyGWXxgNukgJEf/s6IgYDEh/nn\n" +
                "GQ9xWGVpiFccmagSA74EV17dXQ1w6vzNJetQMZj5AoIBAFll/ic3HEnivfGtdO5D\n" +
                "3i+tEfZvm1/n+duNu/hgTyQwQAOWg7XETEnqIzzMwsQj5rob9owZ1iZSczhuLcmE\n" +
                "b1pA7uC7wHlw2DMqOn1keiNoc6rbI3WQn48r5QmhqcZJqdM3rbvKKgtjJ2aoH9zx\n" +
                "Q0voe9SG491yGBzS8DL4b85Rxvmcdu9az6roO7vAWjhWoXoUeR93OjUc+uy8aC5/\n" +
                "7eF24R/dO7Ze8W+R9R5UdWW2NSidjW33dqZvTQ0vfQB3opE/Wnon+z5XpvRbvPqu\n" +
                "UqCWeU1QfJ+A5N0qSrUt/WXxmHYwMQBWxxXUZ0LLf2sWj26Rq6W3bw0xKfzujTsj\n" +
                "voECggEBAMahaddpnTmxafhklw2oCGG9DhoCSok0U7mpMLjgjxongovo5ZR8rvyf\n" +
                "jDcO1bDQoBnE5z+hzrLRfrljA8tPslGRCRrBpZ/i+XF2eJblaFGfL9ywVUEaOJLK\n" +
                "YsN3MnZgIyiHqRSbtI0hPCHFiuWw6i4F4cAOtXMH2P93wymCCcEBE+E6LbMj02Vs\n" +
                "HicpHA96bzyt87aK8s+qefqkZ4TY3JZh6PVeURXKVxduvLN0iYstg8fzzJF5Ifyt\n" +
                "lFuSry6c//8JXrEH3ypWWs7CtGLzdXRmic9rdNb0DUiUQI9RnPQ3/vwZzZlANgDE\n" +
                "dR+MmVdP0Y69GiZxXukih/P9Nuuvp7Y=";
        //if (SecurityConfig.sign_type.equals("RSA")) {
            mysign = RSA.sign(prestr, privateKey, SecurityConfig.input_charset);
        //}
        return mysign;
    }

}
