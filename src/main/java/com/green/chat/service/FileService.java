package com.green.chat.service;

import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;


import com.green.chat.model.FileEntity;
import com.green.chat.persistence.FileReopository;

@Service
public class FileService {
    
    @Autowired
    private FileReopository fileReopository;

    public void save(FileEntity file) {
        fileReopository.save(file);
    }

    public Optional<FileEntity> findById(String id) {
        Optional<FileEntity> file = fileReopository.findById(id);
        return file;
    }

    public FileEntity getById(String id) {
        return fileReopository.getById(id);
    }

    

    // public FileEntity getById(String id) {
    //     return FileReopository.getById(id);
    // }

    

}
