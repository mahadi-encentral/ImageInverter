package com.mamt4real.app.api;

import com.mamt4real.app.model.Upload;
import java.util.List;
import java.util.Optional;

public interface IUpload {
    Optional<Upload> getUpload(long id);
    List<Upload> getUploads();
    Upload addUpload(Upload upload);
}
