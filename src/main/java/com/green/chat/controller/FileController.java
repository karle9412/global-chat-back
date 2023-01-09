package com.green.chat.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.green.chat.dto.FileDTO;
import com.green.chat.model.FileEntity;
import com.green.chat.service.FileService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public void fileupload(@AuthenticationPrincipal String user_email, @RequestPart MultipartFile files) throws IOException{
        FileEntity file = new FileEntity();

        String sourceFileName = files.getOriginalFilename();
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
        FilenameUtils.removeExtension(sourceFileName);

        File destinationFile;
        String destinationFileName;
        String fileUrl = "C:\\Users\\82105\\Desktop\\ws\\chat\\chat\\src\\main\\resources\\static\\image\\";
        
    
        do{
            // 파일 이름 변환
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
            destinationFile = new File(fileUrl + destinationFileName);
        } while (destinationFile.exists());

        destinationFile.getParentFile().mkdirs();
        files.transferTo(destinationFile);
        
        file.setEmail(user_email);
        file.setFilename(destinationFileName);
        file.setFileOriname(sourceFileName);
        file.setFileUrl(fileUrl);
        fileService.save(file);
    
    }   
     @GetMapping("/upload/{id}")
     public ResponseEntity<?> getFile(@PathVariable String id) {
       
         Optional<FileEntity> file = fileService.findById(id);
         return ResponseEntity.ok(file);
        
     }

//      @GetMapping("/download/{id}")
//     public ResponseEntity<?> fileDownload(@PathVariable String id) throws IOException {
//     Optional<FileEntity> file = fileService.findById(id);
//     Path path = Paths.get(file.get().getFileUrl());
//     Resource resource = new InputStreamResource(Files.newInputStream(path));
//     return ResponseEntity.ok()
//             .contentType(MediaType.parseMediaType("application/octet-stream"))
//             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.get().getFileOriname() + "\"")
//             .body(resource);
// }
    
@GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable String id) throws MalformedURLException {

        FileEntity file = fileService.findById(id).orElse(null);

        UrlResource resource = new UrlResource("file:" + file.getFileUrl());

        String encodedFileName = UriUtils.encode(file.getFileOriname(), StandardCharsets.UTF_8);

        // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);
    }
}
