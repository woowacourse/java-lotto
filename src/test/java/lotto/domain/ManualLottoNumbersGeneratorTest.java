package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ManualLottoNumbersGeneratorTest {
    @Test
    void generateNumbersTest() {
        ManualLottoNumbersGenerator generator = new ManualLottoNumbersGenerator(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(generator.generateNumbers()).isEqualTo(
                Arrays.asList(1, 2, 3, 4, 5, 6)
                .stream()
                .map(n -> LottoNumber.valueOf(n))
                .collect(Collectors.toList())
        );
    }
}