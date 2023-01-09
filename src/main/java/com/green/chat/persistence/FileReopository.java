package com.green.chat.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.green.chat.model.FileEntity;

@Repository
public interface FileReopository extends JpaRepository<FileEntity, String> {



    
}
