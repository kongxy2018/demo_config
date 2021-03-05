package com.mydemo.security;

/**
 * @author Zhangyz
 * @description
 * @date 2019/9/24
 */
public class Program {
    public static void main(String[] args) {
        /*说明：
         * 签名原串格式规则：
              1.按Key字母升序排列（ASCII码）。 其中，key为bizContent特殊，其值按其数据的键值对且以Key字母升序排列（ASCII码）,无分隔符。
              2.组装完原串后用SHA256哈希算法签名。

         * 签名原串（排序后）的格式为：
              bizContent=key1value1key2value2&hospitalNo=x&mchntId=x&posId=x&terminal=x&timestamp=x
         * 签名原串示例：
              bizContent=idCard330621199308053822mobile15888888888name张三&mchntId=000000000007&posId=00022020&terminal=SSYY0001&timestamp=20190604100658

         * 签名后数据示例：
              InCXCKXuyzPpLP0eANvQYl1CguLERpfcNJIYo/c+9qYlpToHaoEURTOca4j42GJdslDOCsbW4sBqFi4N98iJ/VTx5LCsLR4V+imIJLQkQfDQ76x3k25EVD2RKTjbZj3L0oApaXVUj9+PcA1EgVSfmOBT2vmmMh30CaXa1dlUZ/M=
         */

        //Step1: 对公共参数排序，请自行实现，此处省略。

        //Step2: 经排序过的公共请求参数
        String signSource = "bizContent=idCard330304198912210014&hosiptalNo=C0001&mchntId=330160400647&posId=88888888&terminal=ZYYY01&timestamp=20190920163401";

        //Step3: 读取密钥
        //String privateKey = FileUtil.readToString("/Users/shuqiang/java/idea_workspace/frontgateway-master/frontgateway-biz/src/main/java/com/yuantu/handler/zj/hz/yhfbyy/private.txt");
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


        System.out.println("私钥:" + privateKey);

        //Step4: 签名（获得加签后字符串）
        String signTarget = null;
        try {
            signTarget = RSASignature.sign(signSource, privateKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String afterSign = "wSWfFc+nTvBHp110aJhYm24iRHMcE+qmfh3W4fhzwp6C67x+HUgKmi/mb96tDnV0hzXrB49iJMNBLwOoFHBwgd6qF4z0KvyYAbmKbiqmQvIL4G1MCDXdHKRFizUC2qU+JOwl3nHVWN/ccXuKju1FDb9UeXoKGIYXzs0p9fSfk9HKDMKTPn86wvPK/zDUkCcuRmcGOQhsT1mI6OmNMmoMyjrMaFPuEOHMnJ2j8WbUW81M/IOdbzAcG5K0Los3aN8BwrDgP4RPcM+p76GXA/dhcBtul+KaevuV6PpCNO1qaxfW4vouI9jpHtTrgYbwfjfcnwqC9JTkWHVwj3OeUVhCk5olxEwT6qrxHaVYYt3eHjJw+Io4c8AHjAQ0HI0ePf3F2BEBO+f2/XyfmCyNw+2Vks1sgxZ9gaxqbAP7nCaYTlpQ9+M3iy8KWia73lb4I03mVZVcgZ+GRsYfvMiw7s76Fs4sclwniWKlNa5MWLtTrZKFmrioJIbOamRS4m2bWHFcIUk5DQhHo+E49eL+yeha9Ft9ru4KrVyT/O4c56/UihONs0DFcjGh4Ichm87isRLvfPRAm/uUjB1K7C9IH6MvkMz9ix5scd8piToVL6LK4eA1RtvtYM2nq+4lc3nVmBRRkiM/iy6ol4/BWriN4yYfD/yVUfSUBLMysj5/xU7MdKA=";
        System.out.println("签名:" + signTarget);
        System.out.println(signTarget.equals(afterSign)?"加签成功":"加签失败");

        //Step5: 将signTarget赋值给公共请求参数:sign
    }
}
