package org.genesiscode.practicethree.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MultiplicativeResponseDTO {

    private Byte n;
    private Short valueOne;
    private Short valueTwo;
    private Short valueThree;
    private String valueFour;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        MultiplicativeResponseDTO other = (MultiplicativeResponseDTO) obj;
        return this.valueOne.equals(other.valueOne) &&
                this.valueTwo.equals(other.valueTwo) &&
                this.valueThree.equals(other.valueThree) &&
                this.valueFour.equals(other.valueFour);
    }
}
