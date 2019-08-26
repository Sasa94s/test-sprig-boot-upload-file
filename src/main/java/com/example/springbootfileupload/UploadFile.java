package com.example.springbootfileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadFile {

    Map<String, Object> result = new HashMap<>();
    /// Receive message
    @RequestMapping("/upload")
    public Map<String, Object> upload(@RequestParam("attach")MultipartFile file) throws IOException, URISyntaxException {
        // File info
        System.out.println("File name = "  + file.getOriginalFilename());
        System.out.println("File type = " + file.getContentType());

        // Save to disk
        // file path example 1) Windows c:/, 3) Mac ~/Documents/
        String filePath = "E:/";
        File uploadedFile = new File(new URI("file:///"+filePath+file.getOriginalFilename()));

        if(!uploadedFile.exists())
            uploadedFile.createNewFile();

        file.transferTo(uploadedFile);
        result.put("Success", true);
        return result;
    }
}
