package com.niudi.security;


/**
 * 参数加密公钥、编码方式配置
 * 
 * @author johny
 *
 */
public class SecurityConfig {

  // 字符编码格式 目前utf-8
  public static final String input_charset = "utf-8";

  // 签名方式 不需修改
  public static final String sign_type = "RSA";

  //第三方支付原路退费public_key
  public static final String backFee_publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC6cWfSWI86G5CLd+ebqPT49jUX6IoonP90EoKau5eSv+yFv6ZfHPdhopnRH6hviuy5hN4aSmYAnfyoVsTN9vJfWPlywpQOoXeWHF1J+qDqfo7S1TOKtuJvg+Au0B9mHUDBrIvUfWW6ZnZEbHDgEWxRmAQ48KgXLoWBo0EORQBq0QIDAQAB";

}
