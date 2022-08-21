package org.genesiscode.practicethree.problem.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;

@SuperBuilder
@Getter
@Setter
public class ValidationErrorResponse extends ErrorResponse {

    private HashMap<String, String> validations;
}
