package com.youyicn.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/file")
public class FileUploadController {
	private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Value("${file.upload.dir}")
	private String UPLOAD_DIR;
	
	// 单文件上传
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
		Map<String, Object> result = Maps.newHashMap();
		String uploadFileUrl = "";
		result.put("success", false);
		result.put("message", "上传失败！");
		try {
			String oldFile = file.getOriginalFilename();
			String newFile = oldFile.split(".")[0]+new DateTime().toString("yyyyMMddHHmmss")+oldFile.split(".")[1];
			logger.info("上传的文件名为：" + oldFile);
			if (file.isEmpty()) {
				result.put("message", "文件 "+oldFile+" 为空,上传失败！");
				logger.error("文件 "+oldFile+" 为空,上传失败！");
				return result;
			}
			// 获取文件的后缀名
			String suffixName = oldFile.substring(oldFile.lastIndexOf("."));
			logger.info("文件的后缀名为：" + suffixName);
			
			uploadFileUrl = newFile;
			File dest = new File(UPLOAD_DIR + newFile);
			// 检测是否存在目录
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();// 新建文件夹
			}
			file.transferTo(dest);// 文件写入
			result.put("success", true);
			result.put("code", 200);
			result.put("uploadFileUrl", uploadFileUrl);
			result.put("message", "上传成功！");
			return result;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 多文件上传
	@RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> multiUpload(@RequestParam("file") MultipartFile[] files) {
		Map<String, Object> result = Maps.newHashMap();
		List<String> uploadFileUrl = Lists.newArrayList();
		result.put("success", false);
		for (MultipartFile file: files) {
			String oldFile = null;
			if (!file.isEmpty()) {
				try {
					oldFile = file.getOriginalFilename();
					String[] split = oldFile.split("\\.");
					String newFile = split[0]+new DateTime().toString("yyyyMMddHHmmss")+"."+split[1];
					//文件存储路径
					uploadFileUrl.add(newFile);
					File dest = new File(UPLOAD_DIR+newFile);
					// 检测是否存在目录
					if (!dest.getParentFile().exists()) {
						dest.getParentFile().mkdirs();// 新建文件夹
					}
					file.transferTo(dest);// 文件写入
				} catch (Exception e) {
					result.put("message", "文件 "+oldFile+" 上传失败 ！");
					logger.error("文件 "+oldFile+" 上传失败 ！ ==> " + e.getMessage()); 
					return result;
				}
			} else {
				result.put("message", "文件 "+oldFile+" 为空,上传失败！");
				logger.error("文件 "+oldFile+" 为空,上传失败！");
				return result;
			}
		}
		result.put("success", true);
		result.put("code", 200);
		result.put("message", "上传成功！");
		result.put("uploadFileUrl", StringUtils.join(uploadFileUrl, ";"));
		return result;
	}

	// 文件下载相关代码
	/**
	 * discription:下载文件
	 * @param fileName 文件名
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/download")
	public String download(@RequestParam String fileName,HttpServletRequest request, HttpServletResponse response) {
		if (fileName != null) {
			// 设置文件路径
			File file = new File(UPLOAD_DIR, fileName);
			logger.info("下载的文件名为：" + UPLOAD_DIR+fileName);
			if (file.exists()) {
				 String downlaodFilename = "";
				 try {
					 //解决中文乱码问题
					 downlaodFilename = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				response.setContentType("application/force-download");// 设置强制下载不打开
				response.addHeader("Content-Disposition", "attachment;fileName=" + downlaodFilename);// 设置文件名
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
		}
		return null;
	}

	private boolean isImage(String fileName){
		String[] imageTypes = {"jpg", "png", "gif", "bmp", "jpeg"};
		if(StringUtils.isNotBlank(fileName)){
			// 获取文件的后缀名
			String suffixName = fileName.substring(fileName.lastIndexOf(".")+1);
			for(String type: imageTypes){
				if (type.equalsIgnoreCase(suffixName)) {  
	                return true;  
	            }  
			}
		}
		return false;
	}
}
