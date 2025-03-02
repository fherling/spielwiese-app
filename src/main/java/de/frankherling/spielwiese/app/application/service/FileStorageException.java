package de.frankherling.spielwiese.app.application.service;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String couldNotUploadFile) {
        super(couldNotUploadFile);
    }
}
