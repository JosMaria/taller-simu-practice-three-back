package org.genesiscode.practicethree.service;

import org.genesiscode.practicethree.dto.AverageProductResponseDTO;
import org.genesiscode.practicethree.dto.ConstantMultiplierResponseDTO;
import org.genesiscode.practicethree.dto.MiddleSquareResponseDTO;

import java.util.List;

public interface MainService {

    List<MiddleSquareResponseDTO> middleSquare(Integer seed, Integer numberOfIterations);

    List<AverageProductResponseDTO> averageProduct(Integer seedOne, Integer seedTwo, Integer numberOfIterations);

    List<ConstantMultiplierResponseDTO> constantMultiplier(Integer seed, Integer constant, Integer numberOfIterations);
}
