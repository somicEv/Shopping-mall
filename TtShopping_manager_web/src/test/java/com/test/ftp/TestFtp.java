package com.test.ftp;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class TestFtp {
	
	@Test
	public void testFTPClient() throws Exception {
		// 创建一个 FTPClient 对象
		FTPClient ftpClient = new FTPClient();
		// 创建一个ftp连接 默认端口21
		ftpClient.connect("192.168.56.101", 21);
		// 登录ftp服务器，使用用户名和密码
		ftpClient.login("ftpuser", "ftpuser");
		// 上传文件
		// 设置上传后文件路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		// 修改文件上传格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		// 读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\浩瀚\\Desktop\\download.jpg"));
		// 参数一： 上传后文件名   参数二 ： 文件流
		ftpClient.storeFile("test2.jpg", inputStream);
		// 关闭连接
		ftpClient.logout();
		
	}
}
