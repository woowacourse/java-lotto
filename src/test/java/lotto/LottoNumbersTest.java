package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoNumbersTest {
    @Test
    void 반환한_로또_숫자_개수_확인() {
        LottoNumbers numbers = new LottoNumbers();
        assertThat(numbers.getRandomLottoNumbers().size()).isEqualTo(6);
    }
}
