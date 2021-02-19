package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomLottoGeneratorTest {

    @Test
    @DisplayName("로또 번호가 6개가 생성되는지 확인")
    void nextLottoNumbers() {
        RandomLottoGenerator randomLottoGenerator = RandomLottoGenerator.getInstance();

        assertThat(randomLottoGenerator.nextLottoNumbers().unwrap().size()).isEqualTo(6);
    }
}