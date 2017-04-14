package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.remoting.exchange.Request;
import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;

@Controller
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(
			value="/pic/upload" 
			)
	@ResponseBody
	public Map pictureUpload(MultipartFile uploadFile){
		Map result = pictureService.uploadResult(uploadFile);
		return result;
	}
}
