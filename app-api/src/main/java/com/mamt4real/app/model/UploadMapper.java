package com.mamt4real.app.model;
import com.mamt4real.entities.JpaUpload;

public class UploadMapper {

    public static JpaUpload uploadToJpaUpload(Upload upload){
        JpaUpload jpaUpload = new JpaUpload();
        jpaUpload.setFilepath(upload.getFilepath());
        jpaUpload.setId(upload.getId());
        return jpaUpload;
    }

    public static Upload jpaUploadToUpload(JpaUpload jpaUpload){
        Upload upload = new Upload();
        upload.setFilepath(jpaUpload.getFilepath());
        upload.setId(jpaUpload.getId());
        return upload;
    }
}
