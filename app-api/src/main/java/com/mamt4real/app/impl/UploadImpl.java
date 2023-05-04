package com.mamt4real.app.impl;

import com.mamt4real.app.api.IUpload;
import com.mamt4real.app.model.Upload;
import com.mamt4real.app.model.UploadMapper;
import com.mamt4real.entities.JpaUpload;
import play.db.jpa.JPAApi;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class UploadImpl implements IUpload {

    private final JPAApi jpaApi;

    @Inject
    public UploadImpl(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    @Override
    public Optional<Upload> getUpload(long id) {
        JpaUpload jpaUpload = jpaApi.em().find(JpaUpload.class, id);
        return Optional.of(UploadMapper.jpaUploadToUpload(jpaUpload));
    }

    @Override
    public List<Upload> getUploads() {
        return jpaApi.withTransaction(
                em -> em.createQuery("SELECT u from JpaUpload u", Upload.class).getResultList());
    }

    @Override
    public Upload addUpload(Upload upload) {
        JpaUpload jpaUpload = UploadMapper.uploadToJpaUpload(upload);
        jpaApi.em().persist(jpaUpload);
        return UploadMapper.jpaUploadToUpload(jpaUpload);
    }


}
