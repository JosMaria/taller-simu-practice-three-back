package org.genesiscode.practicethree.controller;

import lombok.RequiredArgsConstructor;
import org.genesiscode.practicethree.dto.*;
import org.genesiscode.practicethree.annotations.GreaterThan;
import org.genesiscode.practicethree.service.MainService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {

    public static final String MSG_ERROR_TIMES = "'number of iterations' must be greater than zero.";
    public static final String MSG_ERROR_SEED = "'seed' must be greater than or equal to 3 digits.";
    public static final String MSG_ERROR_CONSTANT = "'constant' must be greater than or equal to 3 digits.";

    private final MainService mainService;

    @GetMapping("/middleSquare")
    public ResponseEntity<List<MiddleSquareResponseDTO>> middleSquare(
            @RequestParam @GreaterThan(valueMin = 999, message = MSG_ERROR_SEED) Integer seed,
            @RequestParam(name = "times") @Positive(message = MSG_ERROR_TIMES) Integer numberOfIterations) {
        return ResponseEntity.ok(mainService.middleSquare(seed, numberOfIterations));
    }

    @GetMapping("/averageProduct")
    public ResponseEntity<List<AverageProductResponseDTO>> averageProduct(
            @RequestParam @GreaterThan(valueMin = 999, message = MSG_ERROR_SEED) Integer seedOne,
            @RequestParam @GreaterThan(valueMin = 999, message = MSG_ERROR_SEED) Integer seedTwo,
            @RequestParam(name = "times") @Positive(message = MSG_ERROR_TIMES) Integer numberOfIterations) {
        return ResponseEntity.ok(mainService.averageProduct(seedOne, seedTwo, numberOfIterations));
    }

    @GetMapping("/constantMultiplier")
    public ResponseEntity<List<ConstantMultiplierResponseDTO>> constantMultiplier(
            @RequestParam @GreaterThan(valueMin = 999, message = MSG_ERROR_SEED) Integer seed,
            @RequestParam @GreaterThan(valueMin = 999, message = MSG_ERROR_CONSTANT) Integer constant,
            @RequestParam(name = "times") @Positive(message = MSG_ERROR_TIMES) Integer numberOfIterations) {
        return ResponseEntity.ok(mainService.constantMultiplier(seed, constant, numberOfIterations));
    }

    @GetMapping("/mixed")
    public ResponseEntity<ResponseMessagesDTO<MixedResponseDTO>> mixed(
            @RequestParam Integer seed,
            @RequestParam(name = "multiplicative") Integer multiplicativeConstant,
            @RequestParam(name = "additive") Integer additiveConstant,
            @RequestParam Integer module) {
        return ResponseEntity.ok(mainService.mixed(seed, multiplicativeConstant, additiveConstant, module));
    }

    @GetMapping("/multiplicative")
    public ResponseEntity<List<MultiplicativeResponseDTO>> multiplicative(
            @RequestParam Integer seed,
            @RequestParam("multiplicative") Integer multiplicativeConstant,
            @RequestParam Integer module) {
        return ResponseEntity.ok(mainService.multiplicative(seed, multiplicativeConstant, module));
    }

    @GetMapping("/additive")
    public ResponseEntity<AdditiveResponseDTO> additive() {
        AdditiveResponseDTO row = new AdditiveResponseDTO(List.of(0, 0, 0, 0, 0, 0));
        return ResponseEntity.ok(row);
    }

    @GetMapping("/quadratic")
    public ResponseEntity<QuadraticResponseDTO> quadratic() {
        QuadraticResponseDTO row = new QuadraticResponseDTO(List.of(0, 0, 0, 0, 0));
        return ResponseEntity.ok(row);
    }

    @GetMapping("/blumBlumSub")
    public ResponseEntity<BlumBlumSubResponseDTO> blumBlumSub() {
        BlumBlumSubResponseDTO row = new BlumBlumSubResponseDTO(List.of(0, 0, 0));
        return ResponseEntity.ok(row);
    }
}
