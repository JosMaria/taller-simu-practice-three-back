package org.genesiscode.practicethree.controller;

import org.genesiscode.practicethree.dto.*;
import org.genesiscode.practicethree.validation.GreaterThan;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/exercises")
public class MainController {

    public static final String MSG_ERROR_TIMES = "'number of iterations' must be greater than zero.";
    public static final String MSG_ERROR_SEED = "'seed' must be greater than or equal to 3 digits.";

    @GetMapping("/middleSquare")
    public ResponseEntity<List<MiddleSquareResponseDTO>> middleSquare(
            @RequestParam @GreaterThan(valueMin = 999, message = MSG_ERROR_SEED) Long seed,
            @RequestParam(name = "times") @Positive(message = MSG_ERROR_TIMES) Integer numberOfIterations) {
        MiddleSquareResponseDTO row1 = new MiddleSquareResponseDTO((byte) 0, 0, 0L, "", "", 0.0);
        MiddleSquareResponseDTO row2 = new MiddleSquareResponseDTO((byte) 0, 0, 0L, "", "", 0.0);
        return ResponseEntity.ok(List.of(row1, row2));
    }

    @GetMapping("/averageProduct")
    public ResponseEntity<List<AverageProductResponseDTO>> averageProduct() {
        AverageProductResponseDTO row1 = new AverageProductResponseDTO((byte) 0, 0, 0, 0L, "", 0.0);
        AverageProductResponseDTO row2 = new AverageProductResponseDTO((byte) 0, 0, 0, 0L, "", 0.0);
        return ResponseEntity.ok(List.of(row1, row2));
    }

    @GetMapping("/constantMultiplier")
    public ResponseEntity<List<ConstantMultiplierResponseDTO>> constantMultiplier() {
        ConstantMultiplierResponseDTO row1 = new ConstantMultiplierResponseDTO((byte) 0, 0, 0, 0L, "", 0.0);
        ConstantMultiplierResponseDTO row2 = new ConstantMultiplierResponseDTO((byte) 0, 0, 0, 0L, "", 0.0);
        return ResponseEntity.ok(List.of(row1, row2));
    }

    @GetMapping("/mixed")
    public ResponseEntity<List<MixedResponseDTO>> mixed() {
        MixedResponseDTO row1 = new MixedResponseDTO((byte) 0, (short) 0, (short) 0, (short) 0, (short) 0, " 0/0");
        MixedResponseDTO row2 = new MixedResponseDTO((byte) 0, (short) 0, (short) 0, (short) 0, (short) 0, " 0/0");
        return ResponseEntity.ok(List.of(row1, row2));
    }

    @GetMapping("/multiplicative")
    public ResponseEntity<List<MultiplicativeResponseDTO>> multiplicative() {
        MultiplicativeResponseDTO row1 = new MultiplicativeResponseDTO((byte) 0, 0, 0, 0, "");
        MultiplicativeResponseDTO row2 = new MultiplicativeResponseDTO((byte) 0, 0, 0, 0, "");
        return ResponseEntity.ok(List.of(row1, row2));
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
