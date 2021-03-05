package com.mydemo.util.common;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 驼峰法-下划线互转
 * @author cshaper
 * @since 2015.07.04
 * @version 1.0.0
 */
public class Hump2Underline {
  
  /**
   * 驼峰转下划线
   * @param humpString
   * created by hbd 20160722
   * @return
   */
  public static String humpToUnderline(String humpString) {
    if (StringUtils.isEmpty(humpString)) return "";
    String regexStr = "[A-Z]";
    Matcher matcher = Pattern.compile(regexStr).matcher(humpString);
    StringBuffer sb = new StringBuffer();
    String g = null;
    while (matcher.find()) {
      g = matcher.group();
      matcher.appendReplacement(sb, "_" + g.toLowerCase());
    }
    matcher.appendTail(sb);
    if (sb.charAt(0) == '_') {
      sb.delete(0, 1);
    }
    
    return sb.toString();
  }
  
  public static void main(String[] args) {
    String camel = "iIoveYou";
    System.out.println(humpToUnderline(camel));
  }

}