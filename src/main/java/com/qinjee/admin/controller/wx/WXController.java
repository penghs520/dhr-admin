package com.qinjee.admin.controller.wx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RestController
public class WXController {

    @GetMapping("/token")
    public void token(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        System.out.println(signature);
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        //校验
        if (WxService.check(signature, timestamp, nonce)) {
            System.out.println("接入成功");
            PrintWriter writer = response.getWriter();
            writer.print(echostr);//校验则原样返回echostr
            writer.flush();
            writer.close();
        } else {
            System.out.println("接入失败");
        }
    }

    @PostMapping("/token")
    public void token2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("收到用户消息");
        Map<String, String> map = WxService.parseXML(request.getInputStream());
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        //只处理文本信息
        String msgType = map.get("MsgType");
        if ("text".equals(msgType)) {
            String content = map.get("Content");
            if ("发票".equals(content)) {
                //根据fromUserName 查询

                String msg="您的发票寄送物流单号是：157871247";
                String repXML = "<xml>\n" +
                        "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                        "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                        "  <CreateTime>12345678</CreateTime>\n" +
                        "  <MsgType><![CDATA[text]]></MsgType>\n" +
                        "  <Content><![CDATA[" + msg + "]]></Content>\n" +
                        "</xml>";
                writer.print(repXML);
                writer.flush();
                writer.close();
            }
        }
    }
}
