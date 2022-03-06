package lotto.controller;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import lotto.model.generator.CustomLottoGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoControllerTest {

    private LottoController lottoController = new LottoController(new CustomLottoGenerator());

    @ParameterizedTest
    @MethodSource("provideNormalInput")
    void test1(String money, String count, String manualLotto, String winningNumbers, String bonusNumber) {

        InputStream in = createInputStream(money, count, manualLotto, winningNumbers, bonusNumber);
        System.setIn(in);

        assertThatCode(() -> lottoController.run())
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> provideNormalInput() {
        return Stream.of(
                Arguments.of("3000\n", "1\n", "1,2,3,4,5,6\n", "1,2,3,4,5,6\n", "7")
        );
    }

    private InputStream createInputStream(String money, String count, String manualLotto, String winningNumbers, String bonusNumber) {
        List<InputStream> streams = Arrays.asList(
                new ByteArrayInputStream(money.getBytes()),
                new ByteArrayInputStream(count.getBytes()),
                new ByteArrayInputStream(manualLotto.getBytes()),
                new ByteArrayInputStream(winningNumbers.getBytes()),
                new ByteArrayInputStream(bonusNumber.getBytes()));
        return new SequenceInputStream(Collections.enumeration(streams));
    }
}
