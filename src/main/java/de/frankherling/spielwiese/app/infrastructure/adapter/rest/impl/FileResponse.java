package de.frankherling.spielwiese.app.infrastructure.adapter.rest.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
    private String fileName;
    private String contentType;
    private long size;


}
