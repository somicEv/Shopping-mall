package com.picture.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class TestUploadPicture {
	public static void main(String[] args) throws Exception {
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.87.128",21);
		ftpClient.login("ftpuser", "ftpuser");
		FileInputStream inputStream = new FileInputStream(new File("D:\\test.jpg"));
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.storeFile("1234.jpg", inputStream);
		inputStream.close();
		
		ftpClient.logout();
	}
}
