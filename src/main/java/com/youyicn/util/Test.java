package com.youyicn.util;

import java.io.IOException;

public class Test {

    public static void main(String[] a) throws IOException {
//        Tool tool = new Tool();
//        tool.setName("儿科设备");
//        tool.setDescri("你好");
//        tool.setBuyTime(new Date());
//        tool.setProductor("IBM");
//        tool.setStatus(0);
//        tool.setToolNum("000000");
//			createApplyCode("e:/", 100, "www.baidu.com") ;
//        QrCodeUtil.createStringMark(tool, "E:/", 100, "http://www.baidu.com");
//        String type = "2";// 生成用户
//        String url = "www.baidu.com";
//        Map<String, String> strings = new HashMap<>();
//        LinkedHashMap<String, String> wordsString = new LinkedHashMap<>();
//        wordsString.put("name", "张三");
//        wordsString.put("学号", "10000");
//        wordsString.put("学校","北京科技大学");
//        strings.put("userNum", "55015445");
//
//        strings.put("type","5");
//
//        String urlMethod ="adduser";
//        File file= QrCodeUtil.getInstance().createQRCode(urlMethod, strings, wordsString);

//        System.out.println("a = [" + file.getName() + "]");

//
//        BASE64Encoder encoder = new BASE64Encoder();
//        BASE64Decoder decoder = new BASE64Decoder();

//        String test11="32123.mowenfeng";
//
//        String test = encoder.encode(test11.getBytes());
//        String aaaa =new String ( decoder.decodeBuffer(test));
//        System.out.println("a = [" + aaaa + "]");
//        System.out.println("test = [" + test + "]");


        String skuMainPicUrlTemp = "aaaa,57871,adf";
        String[] skuMainPicUrls = skuMainPicUrlTemp.split(",");
        String skuMainPicUrl = skuMainPicUrls[0];
        System.out.println("a = [" + skuMainPicUrl + "]");

    }

}
