package com.bookstore.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102400749243";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC2dohS8oo1je0lfpI4gROkW3otuoXKRLs0NwW+bymcrvZ20otvaMiaoROvYNV7JtxSlh31Ve02iU+j88Pzc399CZUOg+BXKu297XOGLwmDOka4+nXrOWvB3uyNl38Jjx9ngHuOLf6JcMHz1WjGEOrSMV6zMLZ/M2VFvsh9bxJTQ7tQqj5hqK1mCAFKLO6NfIpruyCvNSrASujyJiYy0d28eciVSdzZHNWiCemCWxJK4MUZryyxxNNUS8PAl9ShAhNSVprAlws7w4ZGPEoclAQF66bTyprJ+T28p1TJCgtoLtB1AAyNQzXcOX3/bY/xbrh7BHWGFEyuGY1Gzjr5R9stAgMBAAECggEAGpafOucY+MDbw9Dz4cP9Zr/lx/AcOuNVHEDAV4W/XJOqWppHs05jnRTH15CWy0FYbbhuWSfST1YnAs8syLcV5tSUoe7ZrBoXVsvZP75u9m5xi9cDg+l0KGYhtmhTHMN8Ygq49nkBVGnX+X4prGMetM547Ee28EY/BbDNamnbE8LVLg3hTUgb8cmnDcfQoEXgCAwC0Qla1glnc4p2q0Nbv+x1TqRxLvLoZix6x+hdZgvLuxhkEC4anT7B2X96r5smlLivFVUz8wpzbM554vmzv/LcjT4Kl8O5CJgp5hCOnNmdJ+KR1QOUoCqhBZ2j9MDJBzcoffoEk9iq/89ncsWjiQKBgQD87ro8pYmpdfcZH7TsR/41f6B4/PTXa20LJetuY97Kvm8Qm5p4808GOabaHFt3FS88FPnZm8nZcVRAZ2gyAy+MtPJwdhH5sVuHjSGOjk08i3bMHn9B/NHluhIhdDJvI3SwS6RCZUZrxPl2lBHa4aw0qzcMqGl6yb3KGkgv4CSA7wKBgQC4rQUzdMdTcq1TZ/4i+QdNvYN/gyzCpcL3c+6HZ1DQvxhbW1+6BAn7IGpTbCIZBwS/dwHMcwwcYH2fU8oI05mjTQu1ru2vlX5JDMvZl/AReRmJ6UhZV7vURogNXhrgQ9z5QJSE9BsyiO3BZRFbrcov9i8ZfSamana09Cp+MxxtowKBgQDoKE80/1R+ANMPcnTkM6h3fm/S3Yv2dpoeE24nYu/J9EWJXunIfrtYIW6u4Dxr7zvtz+sM+9uoBEeq3b9buGTap/OnmhghsdMrdjUI3kCyg+hErvFrph0a2VFu93KbZFXkw2k/sCmVHbVES+CfoWgo58adja9TjvzvFnSPzRJ0bQKBgQC0A9v9A4lE25T+p3D/kDXBXsluo2QfPMtmwLgzfs81HAFXFCOkxEkiZL+gH9jBAqlrJUs4irC0fI4Xn+phXjn2B6UxbZm9PQDDnlia+1MFBUGSwDZI+I9b5YC7Xw7dqukcggoD65sTUIxJpwYbkrVyuQvCUc86Cq85xoQhThsY9wKBgQDugsHcvABaieHX8jWdZNjFCHtuXfTOz6ccW27FdKoZBBoE/h7Kh49nWvo9mEJbsMVY7gg3ONyK3nqcT/AwcedOch75523HswAQxAQra58LhnMSy69eDxGP8ZpWrWjPjv0pICmYhnLqUlv1L4YT4mybM9n1uwMlybC3BiajON7b5A==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq5wsqZLcpyEEq5/H22GnrHvmqn26d5fCbaT0GepvoPu1RZbD6laxJR9Ja8Vkl/lvIPJPMIqqNjbfO2lndZWFx2y/e0WaKq17YsZ5wlsKgPlwmSM15Q48UGJ6s1UXGVuPM+zfiyWYc8To48DnB4Vefk6dJraHe5/YZrfkzPXn5KN0UCuJISmAKtePnIA5m9ecZWZPMJkP/e+AEveQ8jl+yb5/CtpLEysTpBRvUbWJP/LSuBqgz4Hw1UhW4H3RF/PczRUlMWvgJ4tQXtNIdwkiLyr5zxAaTFI50+tUyHCr4zjRwjb8Z064wSn2BpZlVafnorbF2KDjekGkrAfddetOhwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/client/order/paysuccess";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/client/order/paysuccess";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

