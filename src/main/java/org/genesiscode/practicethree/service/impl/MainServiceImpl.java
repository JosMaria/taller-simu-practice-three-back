package org.genesiscode.practicethree.service.impl;

import org.genesiscode.practicethree.dto.MiddleSquareResponseDTO;
import org.genesiscode.practicethree.service.MainService;
import org.genesiscode.practicethree.utils.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Override
    public List<MiddleSquareResponseDTO> middleSquare(final Integer seed, final Integer numberOfIterations) {
        List<MiddleSquareResponseDTO> list = new ArrayList<>();
        int d = String.valueOf(seed).length();
        int tempSeed = seed;

        for (int i = 0; i < numberOfIterations; i++) {
            long valueTwo = (long) tempSeed * tempSeed;
            String number = String.valueOf(valueTwo);
            String valueThree = Tool.addZeros(number, d);
            String valueFour = Tool.extractNumber(valueThree, d);
            Double valueFive = Tool.addZeroAndPoint(valueFour);

            list.add(buildMiddleSquare((byte) (i + 1), tempSeed, valueTwo, valueThree, valueFour, valueFive));
            tempSeed = Integer.parseInt(valueFour);
        }
        return list;
    }

    private MiddleSquareResponseDTO buildMiddleSquare(byte numberOfRow, Integer seed, Long valueTwo,
                                                      String valueThree, String valueFour, Double valueFive) {
        return MiddleSquareResponseDTO.builder()
                .numberOfRow(numberOfRow)
                .valueOne(seed)
                .valueTwo(valueTwo)
                .valueThree(valueThree)
                .valueFour(valueFour)
                .valueFive(valueFive)
                .build();
    }
}
