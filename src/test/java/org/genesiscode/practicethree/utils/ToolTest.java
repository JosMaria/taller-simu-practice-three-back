package org.genesiscode.practicethree.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.genesiscode.practicethree.utils.Tool.addZeros;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ToolTest {

    @DisplayName("verify when adding zeros to the left")
    @MethodSource("addZerosData")
    @ParameterizedTest(name = "#{index} - Test with number: {0}, D: {1}, output: {2}")
    void addZerosTest(String number, int d, String expected) {
        // WHEN
        String actual = addZeros(number, d);

        // THEN
        assertEquals(expected, actual);
    }

    static Stream<Arguments> addZerosData() {
        return Stream.of(
                arguments("1234321", 4, "01234321"),
                arguments("5489649", 4, "05489649"),
                arguments("15129", 4, "00015129"),
                arguments("65526362", 5, "065526362"),
                arguments("3745", 3, "03745")
        );
    }

    @DisplayName("verify than extract numbers, it depends of D")
    @MethodSource("extractNumberData")
    @ParameterizedTest(name = "#{index} - Test with number: {0}, D: {1}, output: {2}")
    void extractNumberTest(String number, int d, String expected) {
        // WHEN
        String actual = Tool.extractNumber(number, d);

        // THEN
        assertEquals(expected, actual);
    }

    static Stream<Arguments> extractNumberData() {
        return Stream.of(
                arguments("00015129", 4, "0151"),
                arguments("06012304", 4, "0123"),
                arguments("94245264", 4, "2452"),
                arguments("23224", 3, "322")
        );
    }
}