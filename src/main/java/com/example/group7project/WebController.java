package com.example.group7project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

// Sets different pages like /upload to their proper html files and adds attributes to them
@Controller
public class WebController {

    @RequestMapping("/")
    public ModelAndView viewHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }
 
    @GetMapping("/upload")
    public String viewUploadPage() {
        return "upload";
    }
     
    @PostMapping("/uploadsuccessful")
    public String handleUploadForm(Model model, String description, @RequestParam("file") MultipartFile multipart) {
        String fileName = multipart.getOriginalFilename();
         
        System.out.println("\nDescription: " + description);
        System.out.println("filename: " + fileName);
         
        String message = "";
         
        try {
            S3Util.uploadFile(fileName, multipart.getInputStream());
            message = "Your file has been uploaded successfully!";
        } catch (Exception ex) {
            message = "Error uploading file: " + ex.getMessage();
        }
         
        model.addAttribute("message", message);
        model.addAttribute("uploaded_image", "https://group-7-bucket.s3.us-west-1.amazonaws.com/" + fileName);

        // Update S3 bucket objects after uploading a photo
        S3Util object1 = new S3Util();
		object1.getS3Objects();
         
        return "uploadsuccessful";              
    }

}