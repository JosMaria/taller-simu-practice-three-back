package org.genesiscode.practicethree.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MixedResponseDTO {

    private Byte numberOfRow;
    private Short valueOne;
    private Short valueTwo;
    private Short valueThree;
    private Short valueFour;
    private String valueFive;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        MixedResponseDTO other = (MixedResponseDTO) obj;
        return this.valueOne.equals(other.valueOne) &&
                this.valueTwo.equals(other.valueTwo) &&
                this.valueThree.equals(other.valueThree) &&
                this.valueFour.equals(other.valueFour) &&
                this.valueFive.equals(other.valueFive);
    }
}
