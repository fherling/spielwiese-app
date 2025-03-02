package de.frankherling.spielwiese.app.infrastructure.adapter.rest.impl;

import de.frankherling.spielwiese.app.application.service.MinoFilestorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/file")
@Validated
@RequiredArgsConstructor
public class FileController {

    private final MinoFilestorageService minioFileStorageService;


    @PostMapping("/upload")
    public ResponseEntity<BaseResponse> uploadFile(@RequestPart("file") MultipartFile file) {

        minioFileStorageService.uploadFile(file);
        return new ResponseEntity<>(new BaseResponse(new FileResponse(file.getOriginalFilename(), file.getContentType(), file.getSize()), "File uploaded with success!"), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> deleteFile(@RequestParam("fileName") String fileName) {
        minioFileStorageService.deleteFile(fileName);

        return new ResponseEntity<>(new BaseResponse(null, "file [" + fileName + "] removing request submitted successfully."), HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("fileName") String fileName) {
        InputStreamResource resource = minioFileStorageService.downloadFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


}
