package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersTest {
    @Test
    @DisplayName("로또번호 매칭 테스트")
    public void countMatchNumberTest() {
        // given
        LottoNumbers lottoNumbers1 = new LottoNumbers(List.of(4, 5, 6, 7, 8, 9));
        LottoNumbers lottoNumbers2 = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        // when
        Integer count = lottoNumbers1.countMatchNumber(lottoNumbers2);
        //then
        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("보너스 매칭 테스트")
    public void matchBonusTest() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(4, 5, 6, 7, 8, 9));
        // when
        Boolean bonusMatch = lottoNumbers.bonusMatch(4);
        //then
        assertThat(bonusMatch).isTrue();
    }

    @Test
    @DisplayName("숫자 개수 판별 테스트")
    public void validateNumberCountTest() {
        // given
        List<Integer> input = List.of(1, 2, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> new LottoNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_COUNT_EXCEPTION);
    }

    @Test
    @DisplayName("숫자 범위 판별 테스트")
    public void validateBoundTest() {
        // given
        List<Integer> input = List.of(1, 2, 3, 4, 5, 46);

        // when & then
        assertThatThrownBy(() -> new LottoNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_BOUND_EXCEPTION);
    }

    @Test
    @DisplayName("숫자 중복 판별 테스트")
    public void validateDuplicateTest() {
        // given
        List<Integer> input = List.of(1, 2, 3, 4, 5, 5);

        // when & then
        assertThatThrownBy(() -> new LottoNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_DUPLICATE_EXCEPTION);
    }
}
