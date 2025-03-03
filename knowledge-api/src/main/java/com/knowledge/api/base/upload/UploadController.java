package com.knowledge.api.base.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.knowledge.common.base.index.BaseController;
import com.knowledge.common.base.model.Result;
import com.knowledge.common.base.util.OSSUtil;

/**
 * 文件上传
 * 
 * @author francis
 *
 */
@RestController
@RequestMapping("/upload/")
public class UploadController extends BaseController {
	
	@Autowired
	private OSSUtil ossUtil;
	
	@PostMapping("uploadImg")
	@ResponseBody
	public Result uploadImg(MultipartFile file) {
		String folder="knowledge/image/";
		String imgUrl = ossUtil.saveImage(folder, file);
		return json(imgUrl);
	}

	@PostMapping("download")
	public void download(String fileName) {
		ossUtil.segmentDownload(fileName);
	}
	
}
