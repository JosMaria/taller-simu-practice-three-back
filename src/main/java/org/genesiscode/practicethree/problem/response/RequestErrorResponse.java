package org.genesiscode.practicethree.problem.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class RequestErrorResponse extends ErrorResponse {

    private String message;
}
