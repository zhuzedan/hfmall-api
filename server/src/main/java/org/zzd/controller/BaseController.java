package org.zzd.controller;

import cn.hutool.core.lang.UUID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zzd.result.ResponseResult;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :zzd
 * @date : 2023-02-16 8:15
 */
@RestController
@Api(tags = "基础接口")
@RequestMapping("/api")
public class BaseController {
    @PostMapping("/upload")
    @ApiOperation("上传文件到本地映射")
    public ResponseResult insertOrderImg(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        // 返回Map
        Map<String, Object> resultMap = new HashMap<>();
        // 文件上传路径
        String location = "D:\\projectByUniapp\\hfmall-app\\static\\image\\"; //TODO 改为你的服务器路径

        if (!file.isEmpty()) {
            // 获取文件原始名称 a.png -> a
            String originalFilename = file.getOriginalFilename();
            // 获取文件后缀 .png
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));

            //设置允许上传文件类型
            String suffixList = ".jpg,.png,.ico,.bmp,.jpeg";
            // 判断是否包含
            if (suffixList.contains(extName.trim().toLowerCase())) {
                // 保存文件的路径
                String fileName = UUID.randomUUID() + extName;
                String path = location  + fileName;

                // 查看路径是否存在，不存在就创建
                //image/jpeg
                if (!new File(path).exists()) {
                    boolean mkdirs = new File(path).mkdirs();
                }
                //  spring的transferTo保存文件方法
                try {
                    file.transferTo(new File(path));
                    String imgUrl = "http://localhost:8888/image/" + fileName;
                    return ResponseResult.success(imgUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseResult.error();
                }
            }
        } else {
            return ResponseResult.error(500,"未选择图片");
        }
        return null;
    }
}
