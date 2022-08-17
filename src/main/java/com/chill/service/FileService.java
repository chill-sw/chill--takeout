package com.chill.service;

import com.chill.common.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Result uploadFileOSS(MultipartFile file);
}
