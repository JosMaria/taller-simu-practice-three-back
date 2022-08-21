package org.genesiscode.practicethree.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ConstantMultiplierResponseDTO {

    private Byte numberOfRow;
    private Integer valueOne;
    private Integer valueTwo;
    private String valueThree;
    private String valueFour;
    private Double valueFive;
}
