package com.mamt4real.app.impl;

import com.google.inject.AbstractModule;
import com.mamt4real.app.api.IUpload;

public class UploadModule extends AbstractModule {
    protected void configure(){
        bind(IUpload.class).to(UploadImpl.class);
    }
}
