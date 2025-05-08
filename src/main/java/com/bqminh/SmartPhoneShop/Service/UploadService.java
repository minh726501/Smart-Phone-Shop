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

    public String upLoadFile(MultipartFile file, String targetFolder) {
        if (file.isEmpty()) {
            return "";
        }
        String fileName = "";
        try {
            byte[] bytes = file.getBytes();

            // Lấy đường dẫn thực đến thư mục target
            String rootPath = servletContext.getRealPath("/resources/images/" + targetFolder);
            File dir = new File(rootPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Tạo file mới
            fileName = System.currentTimeMillis() + "." + file.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

}


