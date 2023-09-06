package com.itcentercrmquva.quvaitcentercrm.service;

import com.itcentercrmquva.quvaitcentercrm.entity.ImageStore;
import com.itcentercrmquva.quvaitcentercrm.repository.ImageStoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
@AllArgsConstructor
public class ImageStoreService {

    private final ImageStoreRepository imageStoreRepository;

    public ResponseEntity<String> getImage(long id) {
        final ImageStore image = imageStoreRepository.findById(id).orElse(null);
        if (image != null) {
            final HttpHeaders headers = new HttpHeaders();
            final String filename = image.getFilename();
            String ext = filename.substring(filename.lastIndexOf("."));
            //headers.add("Content-Disposition", "inline;filename=" + filename);
            headers.setContentType((ext.contains("png")) ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG);
            return ResponseEntity.ok().headers(headers).body(Base64Utils.encodeToString(image.getContent()));
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
