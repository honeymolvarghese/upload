package com.fileupload.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@SpringBootApplication
@RestController
public class UploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadApplication.class, args);
	}
	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException
	{
		File convertFile = new File("E:\\test\\"+ file.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		
		//for counting the words in file
		int count =0;
		FileInputStream fis = new FileInputStream(convertFile);
		byte[] bytesArray = new byte[(int)convertFile.length()];
		fis.read(bytesArray);
		String s = new String(bytesArray);
	    String [] data = s.split(" ");
	
	    for (int i=0; i<data.length; i++) 
	    {
	         count++;
	    }
	    System.out.println("Number of words in the  file are " + count);
	    
	    
		fout.close();
		return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
}
}