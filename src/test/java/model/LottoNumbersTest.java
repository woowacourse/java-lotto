package model;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Numbers;
import java.util.List;
import org.junit.jupiter.api.Test;

class LottoNumbersTest {
    @Test
    public void 로또_번호_개수_테스트() {
        // given & when
        LottoNumbers lottoNumbers = new LottoNumbers();
        List<Integer> numbers = lottoNumbers.getNumbers();
        // then
        assertThat(numbers.size()).isEqualTo(Numbers.LOTTO_NUMBER_COUNT);
    }

    @Test
    public void 로또_번호_범위_테스트() {
        // given & when
        LottoNumbers lottoNumbers = new LottoNumbers();
        List<Integer> numbers = lottoNumbers.getNumbers();
        // then
        for (Integer number : numbers) {
            assertThat(number).isBetween(1, 45);
        }

    }

}
