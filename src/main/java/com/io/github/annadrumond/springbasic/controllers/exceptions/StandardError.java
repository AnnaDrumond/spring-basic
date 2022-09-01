package com.io.github.annadrumond.springbasic.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@RequiredArgsConstructor
public class StandardError implements Serializable {

    @Getter @Setter @NonNull
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp;

    @Getter @Setter @NonNull
    private Integer status;

    @Getter @Setter @NonNull
    private String message;

    @Getter @Setter @NonNull
    private String requestPath;



}
