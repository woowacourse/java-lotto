package lotto.domain;

import lotto.util.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {
    @DisplayName("6개의 로또 번호를 생성하는지 테스트")
    @Test
    void check_six() {
        List<Integer> lottoNumbers = LottoGenerator.make();

        assertThat(lottoNumbers.size()).isEqualTo(6);
    }
}