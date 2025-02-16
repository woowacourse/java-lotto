package domain;

import static exception.ExceptionMessage.LOTTO_NUMBER_DUPLICATED_ERROR;
import static exception.ExceptionMessage.LOTTO_RANGE_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import constant.WinningCount;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.RandomGenerator;

class WinningLottoTest {

    @DisplayName("보너스 번호 범위 검증")
    @ParameterizedTest
    @CsvSource({"0", "46", "-100"})
    void validateRange(int bonusNumber) {
        // given
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        // when & then
        assertThatThrownBy(() -> new WinningLotto(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_RANGE_ERROR.getMessage());
    }

    @DisplayName("보너스 번호 중복 검증")
    @Test
    void validateDuplicate() {
        // given
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Integer bonusNumber = 1;
        // when & then
        assertThatThrownBy(() -> new WinningLotto(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_NUMBER_DUPLICATED_ERROR.getMessage());
    }

    @DisplayName("로또 결과 반환 검증")
    @Test
    void lottoResultTest() {
        // given
        Lottos lottos = new Lottos(4000, new RandomNumberGenerate());
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        // when
        Map<WinningCount, Integer> lottosResult = winningLotto.getLottosResult(lottos);
        // then
        assertThat(lottosResult.getOrDefault(WinningCount.NONE, 0)).isEqualTo(0);
        assertThat(lottosResult.getOrDefault(WinningCount.THREE, 0)).isEqualTo(1);
        assertThat(lottosResult.getOrDefault(WinningCount.FOUR, 0)).isEqualTo(0);
        assertThat(lottosResult.getOrDefault(WinningCount.FIVE, 0)).isEqualTo(1);
        assertThat(lottosResult.getOrDefault(WinningCount.FIVE_BONUS, 0)).isEqualTo(1);
        assertThat(lottosResult.getOrDefault(WinningCount.SIX, 0)).isEqualTo(1);
    }

    static class RandomNumberGenerate implements RandomGenerator {
        private static List<Integer> numberList = List.of(1, 2, 3, 4, 5, 6, 1, 2, 3, 8, 9, 10, 1, 2, 3, 4, 5, 10,
                1,2,3,4,5,7);
        private int index = 0;

        @Override
        public int generate() {
            return numberList.get(index++);
        }
    }
}