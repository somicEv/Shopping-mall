package com.taotao.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {

	// 主机地址
	@Value("${host}")
	private String host;
	// 端口号
	@Value("${port}")
	private Integer port;
	// 用户名
	@Value("${username}")
	private String username;
	// 密码
	@Value("${password}")
	private String password;
	// 服务器基础目录
	@Value("${basePath}")
	private String basePath;
	// 图片服务器的基础地址
	@Value("${image_base_url}")
	private String url;

	@Override
	public Map uploadResult(MultipartFile uploadFile) {
		Map resultMap = new HashMap<>();
		try {
			String oldName = uploadFile.getOriginalFilename();
			// 生成新的文件名
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			// 定义文件存放路径
			String filePath = new DateTime().toString("/yyyy/MM/dd");
			// 文件最终的存放路径
			String resultURL = url + filePath + "/" + newName;
			// 文件输入流
			InputStream inputStream = uploadFile.getInputStream();
			// 上传文件
			boolean result = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, newName,
					inputStream);
			// 返回结果
			if (!result) {
				resultMap.put("error", 1);
				resultMap.put("message", "上传文件失败");
				System.out.println("上传文件失败");
				return resultMap;
			}
			resultMap.put("error", 0);
			resultMap.put("url", resultURL);
			System.out.println("上传文件成功");
			return resultMap;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("error", 1);
			resultMap.put("message", e.getMessage());
			return resultMap;
		}
	}

}
