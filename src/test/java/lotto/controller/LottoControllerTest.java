package lotto.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoControllerTest {

    LottoController lottoController = new LottoController();

    @ParameterizedTest
    @MethodSource("provideMoneyAndWinningNumbers")
    void 로또_머신_만들기_테스트(String money, String winningNumbers, String bonusNumber) {
        InputStream in = createInputStream(money, winningNumbers, bonusNumber);
        System.setIn(in);
    }

    private static Stream<Arguments> provideMoneyAndWinningNumbers() {
        return Stream.of(
                Arguments.of("3000","1,2,3,4,5,6","7"),
                Arguments.of("10000","1,2,3,4,5","7"),
                Arguments.of("3000","1,2,3,4,5,6,7","8"),
                Arguments.of("3000","1,2,3,4,5,6","1")
        );
    }

    private InputStream createInputStream(String money, String winningNumbers, String bonusNumber) {
        List<InputStream> streams = Arrays.asList(
                generateUserInput(money),
                generateUserInput(winningNumbers),
                generateUserInput(bonusNumber));
        return new SequenceInputStream(Collections.enumeration(streams));
    }

    private static InputStream generateUserInput(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }
}
