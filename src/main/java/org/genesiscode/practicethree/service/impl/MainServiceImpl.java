package org.genesiscode.practicethree.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.genesiscode.practicethree.dto.*;
import org.genesiscode.practicethree.problem.exceptions.RelativePrimeException;
import org.genesiscode.practicethree.service.MainService;
import org.genesiscode.practicethree.utils.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.genesiscode.practicethree.utils.Tool.isRelativePrime;

@Slf4j
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
            double valueFive = Tool.addZeroAndPoint(valueFour);

            list.add(buildMiddleSquare((byte) (i + 1), tempSeed, valueTwo, valueThree, valueFour, valueFive));
            tempSeed = Integer.parseInt(valueFour);
        }
        log.info("Middle Squares requests completed successfully");
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

    @Override
    public List<AverageProductResponseDTO> averageProduct(final Integer seedOne, final Integer seedTwo, final Integer numberOfIterations) {
        List<AverageProductResponseDTO> list = new ArrayList<>();
        int d = String.valueOf(seedOne).length();
        int tempSeedOne = seedOne, tempSeedTwo = seedTwo;
        for (int i = 0; i < numberOfIterations; i++) {
            String valueThree = Tool.addZeros(String.valueOf(tempSeedOne * tempSeedTwo), d) ;
            String valueFour = Tool.extractNumber(valueThree, d);
            double valueFive = Tool.addZeroAndPoint(valueFour);

            list.add(buildAverageProduct((byte) (i + 1), tempSeedOne, tempSeedTwo, valueThree, valueFour, valueFive));
            tempSeedOne = tempSeedTwo;
            tempSeedTwo = Integer.parseInt(valueFour);
        }
        log.info("Average Product requests completed successfully");
        return list;
    }

    private AverageProductResponseDTO buildAverageProduct(byte numberOfRow, Integer valueOne, Integer valueTwo,
                                                          String valueThree, String valueFour, double valueFive) {
        return AverageProductResponseDTO.builder()
                .numberOfRow(numberOfRow)
                .valueOne(valueOne)
                .valueTwo(valueTwo)
                .valueThree(valueThree)
                .valueFour(valueFour)
                .valueFive(valueFive)
                .build();
    }

    @Override
    public List<ConstantMultiplierResponseDTO> constantMultiplier(final Integer seed, final Integer constant, final Integer numberOfIterations) {
        List<ConstantMultiplierResponseDTO> list = new ArrayList<>();
        int d = String.valueOf(seed).length();
        int tempSeed = seed;
        for (int i = 0; i < numberOfIterations; i++) {
            String valueThree = Tool.addZeros(String.valueOf(constant * tempSeed), d);
            String valueFour = Tool.extractNumber(valueThree, d);
            double valueFive = Tool.addZeroAndPoint(valueFour);

            list.add(buildConstantMultiplier((byte) (i + 1), constant, tempSeed, valueThree, valueFour, valueFive));
            tempSeed = Integer.parseInt(valueFour);
        }
        log.info("Constant Multiplier requests completed successfully");
        return list;
    }

    private ConstantMultiplierResponseDTO buildConstantMultiplier(byte numberOfRow, Integer valueOne, Integer valueTwo,
                                                                  String valueThree, String valueFour, double valueFive) {
        return ConstantMultiplierResponseDTO.builder()
                .numberOfRow(numberOfRow)
                .valueOne(valueOne)
                .valueTwo(valueTwo)
                .valueThree(valueThree)
                .valueFour(valueFour)
                .valueFive(valueFive)
                .build();
    }

    @Override
    public List<MixedResponseDTO> mixed(final Integer seed, final Integer multiplicativeConstant,
                                        final Integer additiveConstant, final Integer module) {
        // TODO: Implement isRelativePrime
        if (! isRelativePrime(additiveConstant, module)) {
            throw new RelativePrimeException(String.format(
                    "Constante aditiva: %s no es relativamente primo al modulo: %s", additiveConstant, module));
        }

        List<MixedResponseDTO> list = new ArrayList<>();
        int seedTemp = seed;
        boolean isFirstIteration = true;
        MixedResponseDTO firstRow = null;
        boolean isRepeat = false;

        for (int i = 0; i <= module && !isRepeat; i++) {
            int valueTwo = multiplicativeConstant * seedTemp;
            int valueThree = valueTwo + additiveConstant;
            int valueFour = valueThree % module;
            String valueFive = valueFour == 0 ? String.valueOf(valueFour) : String.format("%s/%s", valueFour, module);
            MixedResponseDTO rowActual = buildMixedResponseDTO((byte) (i + 1), seedTemp, valueTwo, valueThree, valueFour, valueFive);

            if (isFirstIteration) {
                firstRow = rowActual;
            } else {
                isRepeat = firstRow.equals(rowActual);
            }
            isFirstIteration = false;
            list.add(rowActual);
            seedTemp = valueFour;
        }
        return list;
    }

    private MixedResponseDTO buildMixedResponseDTO(byte n, int valueOne, int valueTwo, int valueThree, int valueFour, String valueFive) {
        return MixedResponseDTO.builder()
                .n(n)
                .valueOne((short) valueOne)
                .valueTwo((short) valueTwo)
                .valueThree((short) valueThree)
                .valueFour((short) valueFour)
                .valueFive(valueFive)
                .build();
    }

    @Override
    public List<MultiplicativeResponseDTO> multiplicative(Integer seed, Integer multiplicativeConstant, Integer module) {
        if (restrictionsMultiplicative()) {

        }

        List<MultiplicativeResponseDTO> list = new ArrayList<>();
        int seedTemp = seed;
        boolean isRepeat = false;
        boolean isFirstIteration = true;
        MultiplicativeResponseDTO firstRow = null;

        for (int i = 0; i <= module && !isRepeat; i++) {
            int valueTwo = seedTemp * multiplicativeConstant;
            int valueThree = valueTwo % module;
            String valueFour = valueThree == 0 ? String.valueOf(valueThree) : String.format("%s/%s", valueThree, module);
            MultiplicativeResponseDTO rowActual = buildMultiplicativeResponseDTO(i+1, seedTemp, valueTwo, valueThree, valueFour);
            if (isFirstIteration) {
                firstRow = rowActual;
            } else {
                isRepeat = firstRow.equals(rowActual);
            }
            isFirstIteration = false;
            list.add(rowActual);
            seedTemp = valueThree;
        }
        return list;
    }

    private boolean restrictionsMultiplicative() {
    }

    private MultiplicativeResponseDTO buildMultiplicativeResponseDTO(int n, int valueOne, int valueTwo, int valueThree, String valueFour) {
        return MultiplicativeResponseDTO.builder()
                .n((byte) n)
                .valueOne((short) valueOne)
                .valueTwo((short) valueTwo)
                .valueThree((short) valueThree)
                .valueFour(valueFour)
                .build();
    }
}
