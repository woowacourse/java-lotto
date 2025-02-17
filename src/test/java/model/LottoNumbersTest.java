package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersTest {
    @Test
    @DisplayName("로또 번호 개수 테스트")
    public void lottoNumbersCountTest() {
        // given & when
        LottoNumbers lottoNumbers = new LottoNumbers();
        List<LottoNumber> numbers = lottoNumbers.getNumbers();
        // then
        assertThat(numbers.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("로또 번호 범위 테스트")
    public void lottoNumberBoundTest() {
        // given & when
        LottoNumbers lottoNumbers = new LottoNumbers();
        List<LottoNumber> numbers = lottoNumbers.getNumbers();
        // then
        for (LottoNumber number : numbers) {
            assertThat(number.getNumber()).isBetween(1, 45);
        }
    }

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
        Boolean bonusMatch = lottoNumbers.bonusMatch(new LottoNumber(4));
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
                .hasMessage("6개의 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("숫자 중복 판별 테스트")
    public void validateDuplicateTest() {
        // given
        List<Integer> input = List.of(1, 2, 3, 4, 5, 5);

        // when & then
        assertThatThrownBy(() -> new LottoNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복이 아닌 숫자를 입력해주세요");
    }
}
