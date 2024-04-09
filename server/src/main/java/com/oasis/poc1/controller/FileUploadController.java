package com.oasis.poc1.controller;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.oasis.poc1.config.AzureBlobStorageConfig;
import com.oasis.poc1.entity.Token;
import com.oasis.poc1.service.Poc1Service;

import reactor.core.publisher.Mono;

@CrossOrigin(origins = {"https://oasis-poc2-dynamicui.azurewebsites.net","http://localhost:4200"})
@RestController
@RequestMapping("/oasis/poc1")
public class FileUploadController {
    
	Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
    private final BlobContainerClient blobContainerClient;

    public FileUploadController(AzureBlobStorageConfig config) {
    	    	
        blobContainerClient = new BlobContainerClientBuilder()
                .connectionString(config.getConnectionString())
                .containerName(config.getContainerName())
                .buildClient();
    }
    
    @PostMapping("/file-upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    	logger.info("****  inside uploadFile() begins ****");
        BlobContainerClient containerClient = blobContainerClient;

        if (!containerClient.exists()) {
            containerClient.create();
        }
        
        String fileName = file.getOriginalFilename();
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        ResponseEntity<String> response = null;
        try {
			blobClient.upload(file.getInputStream(), file.getSize());
			response = ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body("Successfully uploaded: " + fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(" File name or path is empty ");
			response = ResponseEntity.noContent().build();
			e.printStackTrace();
		}
        
        logger.info("Response : " +response);
        logger.info("**** inside uploadFile() ends ****");
        
        return response;
    }

}
