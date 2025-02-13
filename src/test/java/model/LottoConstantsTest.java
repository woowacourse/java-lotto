package model;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Constants;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoConstantsTest {
    @Test
    @DisplayName("로또 번호 개수 테스트")
    public void lottoNumbersCountTest() {
        // given & when
        LottoNumbers lottoNumbers = new LottoNumbers();
        List<Integer> numbers = lottoNumbers.getNumbers();
        // then
        assertThat(numbers.size()).isEqualTo(Constants.LOTTO_NUMBER_COUNT);
    }

    @Test
    @DisplayName("로또 번호 범위 테스트")
    public void lottoNumberBoundTest() {
        // given & when
        LottoNumbers lottoNumbers = new LottoNumbers();
        List<Integer> numbers = lottoNumbers.getNumbers();
        // then
        for (Integer number : numbers) {
            assertThat(number).isBetween(1, 45);
        }
    }
}
