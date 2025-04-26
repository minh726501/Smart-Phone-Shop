package com.bqminh.SmartPhoneShop.Service;

import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class UploadService {
    private final ServletContext servletContext;

    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    public String uploadFile(MultipartFile file,String targetFolder){
        String rootPath=servletContext.getRealPath("/resources/images");
        String finalName="";
        try {
            byte[]bytes= file.getBytes();
            File dir=new File(rootPath+File.separator+targetFolder);
            if (!dir.exists())
                dir.mkdirs();
            //create the file on server
            finalName=dir.getAbsolutePath()+File.separator+System.currentTimeMillis()+"-"+file.getOriginalFilename();
            File serverFile=new File(finalName);
            BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return finalName;
    }
}
