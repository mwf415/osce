package com.youyicn.util;


import com.youyicn.model.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
	private String algorithmName = "md5";
	private int hashIterations = 2;

	public void encryptPassword(User user) {
		String newPassword = new SimpleHash(algorithmName, user.getUserPwd()).toHex();
		user.setUserPwd(newPassword);

	}
	public static void main(String[] args) {
		PasswordHelper passwordHelper = new PasswordHelper();
		User user = new User();
		user.setLoginName("admin");
			user.setUserPwd("123456");
		passwordHelper.encryptPassword(user);
		System.out.println(user.getUserPwd());
	}
}
