package org.genesiscode.practicethree.service;

import org.genesiscode.practicethree.dto.*;

import java.util.List;

public interface MainService {

    List<MiddleSquareResponseDTO> middleSquare(Integer seed, Integer numberOfIterations);

    List<AverageProductResponseDTO> averageProduct(Integer seedOne, Integer seedTwo, Integer numberOfIterations);

    List<ConstantMultiplierResponseDTO> constantMultiplier(Integer seed, Integer constant, Integer numberOfIterations);

    List<MixedResponseDTO> mixed(Integer seed, Integer multiplicativeConstant, Integer additiveConstant, Integer module);

    List<MultiplicativeResponseDTO> multiplicative(Integer seed, Integer multiplicativeConstant, Integer module);
}
