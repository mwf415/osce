package com.youyicn.controller;

import com.youyicn.model.Exam;
import com.youyicn.model.ExamUser;
import com.youyicn.service.ExamService;
import com.youyicn.service.ExamUserService;
import com.youyicn.util.QrCodeUtil;
import com.youyicn.util.WriteResponseUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * author by mowenfeng
 **/

@Controller
@RequestMapping("/downLoad")
public class BuilderQrCodeController {

    @Value("${server.port}")
    private String servicePort;
    private static QrCodeUtil qrCodeUtil = QrCodeUtil.getInstance(); // 生成二维码的方法

    @Resource
    private ExamUserService examUserService;
    @Resource
    private ExamService examService;

    @RequestMapping(value = "/allQR", method = RequestMethod.GET)
    public void downloadAll(@Param("examId") Integer examId, HttpServletRequest request, HttpServletResponse response) {
        /**
         * 拼接二维码数据
         */
        Exam exam = examService.selectByKey(examId);
        List<ExamUser> examUsers = examUserService.selectById(examId);
        List<File> allFile = new ArrayList<>();
        if (examUsers.size() > 0) {
            for (ExamUser examUser : examUsers) {
                String realName = examUser.getRealName();
                String userId = examUser.getUserId();
                Map<String, String> strings = new HashMap<>(); // 二维码中的信息
                LinkedHashMap<String, String> wordsString = new LinkedHashMap<>(); // 文字信息

                wordsString.put("用户名", realName);
                wordsString.put("编号", userId);
                strings.put("userId", userId);
                strings.put("examId", examId + "");
                File file = this.genertQrcode(realName, userId, examId + "");
                allFile.add(file);
            }
        }

        response.setContentType("application/force-download");// 设置强制下载不打开
        String fileName = examId+"";
        WriteResponseUtil.writeZipResponse(request,response,allFile,fileName);
        System.out.println("导出文件夹压缩完成");

    }


    @RequestMapping(value = "/userQrcode", method = RequestMethod.GET)
    public void download(String realName, String userId, String examId, HttpServletRequest request, HttpServletResponse response) {
        /**
         * 拼接二维码数据
         */
        File file = this.genertQrcode(realName, userId, examId);
        String fileName = "";
        try {
            fileName = new String((realName + ":" + userId).getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName + ".png");// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private File genertQrcode(String realName, String userId, String examId) {

        /**
         * 拼接二维码数据
         */
        Map<String, String> strings = new HashMap<>(); // 二维码中的信息
        LinkedHashMap<String, String> wordsString = new LinkedHashMap<>(); // 文字信息

        wordsString.put("用户名", realName);
        wordsString.put("编号", userId);

        strings.put("userId", userId);
        strings.put("examId", examId);

        String urlMethod = "qrCodeController";
        File file = QrCodeUtil.getInstance().createQRCode(urlMethod, servicePort, strings, wordsString,15);

        return file;
    }

}
