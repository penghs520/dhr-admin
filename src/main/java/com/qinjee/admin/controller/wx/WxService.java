package com.qinjee.admin.controller.wx;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WxService {
    private static final String TOKEN = "phs";

    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean check(String signature, String timestamp, String nonce) {
        //1、将token、timestamp、nonce三个参数进行字典排序
        String[] strs = new String[]{TOKEN, timestamp, nonce};
        Arrays.sort(strs);
        //2、将三个参数拼接成一个字符串进行sha1加密
        String str = strs[0] + strs[1] + strs[2];
        //3、开发者获得加密后的字符串与signature比较
        String sig = sha1(str);
        System.out.println(sig);
        return signature.equals(sig);
    }

    private static String sha1(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            byte[] bytes = md.digest(str.getBytes());
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            StringBuilder sb = new StringBuilder();
            //处理加密结果
            for (byte b : bytes) {
                sb.append(chars[(b >> 4) & 15]);//处理高四位，向右移动四位,&15
                sb.append(chars[b & 15]);//处理b四位
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> parseXML(ServletInputStream is) {
        SAXReader reader = new SAXReader();
        Map<String, String> result = new HashMap<>();
        try {
            Document document = reader.read(is);
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for (Element element : elements) {
                result.put(element.getName(), element.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return result;
    }
}
