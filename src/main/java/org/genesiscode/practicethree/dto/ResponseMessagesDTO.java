package org.genesiscode.practicethree.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ResponseMessagesDTO<T> {

    private List<T> response;
    private List<String> messages;
}
