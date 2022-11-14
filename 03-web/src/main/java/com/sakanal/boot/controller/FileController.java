package com.sakanal.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class FileController {
    @GetMapping("/file")
    public String toFile(){
        return "file";
    }

    @PostMapping("/file")
    @ResponseBody
    public String upload(@RequestParam( name = "file1",required = false) MultipartFile file1,
                         @RequestParam( name = "file2",required = false) MultipartFile file2,
                         HttpSession session) throws IOException {
        //上传路径保存设置
        String path = session.getServletContext().getRealPath("/upload");
        //如果路径不存在 创建一个
        File realPath = new File(path);
        if (!realPath.exists()) {
            //mkdir()创建单层文件夹
            realPath.mkdir();
            //mkdirs()可以创建多层文件夹
//            realPath.mkdirs();
        }
        System.out.println("上传文件保存地址:" + realPath);
        //通过ConmmonsMultipartFile的方法直接写文件
        if (file1 != null && !file1.isEmpty()) {
            file1.transferTo(new File(realPath + "/" + file1.getOriginalFilename()));
        }
        if (file2 != null && !file2.isEmpty()) {
            file2.transferTo(new File(realPath + "/" + file2.getOriginalFilename()));
        }
        return "success";
    }


    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile[] files,
                         HttpSession session) throws IOException {
        String path = session.getServletContext().getRealPath("/upload");
        File realPath = new File(path);
        if (!realPath.exists()) {
            //mkdir()创建单层文件夹
            realPath.mkdir();
            //mkdirs()可以创建多层文件夹
//            realPath.mkdirs();
        }
        if(files.length > 0){
            for (MultipartFile file : files) {
                if(!file.isEmpty()){
                    String originalFilename = file.getOriginalFilename();
                    file.transferTo(new File(path+originalFilename));
                }
            }
        }
        return "success";
    }

}
