package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumbersGeneratorTest {

    @Test
    @Disabled
    @DisplayName("입력한 수 만큼 로또 발급 테스트")
    void createLottoNumbersTest() {
        LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator();
        List<LottoNumbers> lottoNumbersList = lottoNumbersGenerator.generate(3);
        assertThat(lottoNumbersList).hasSize(3);
    }
}
