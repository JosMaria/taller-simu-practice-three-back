package org.genesiscode.practicethree.problem.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;

@Builder
@Getter
@Setter
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int value;
    private String name;
    private String path;
    private HashMap<String, String> validations;
}
