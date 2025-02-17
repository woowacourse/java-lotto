package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    @DisplayName("로또 번호 개수 테스트")
    public void lottoNumbersCountTest() {
        // given & when
        Lotto lotto = new Lotto();
        List<LottoNumber> numbers = lotto.getNumbers();
        // then
        assertThat(numbers.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("로또 번호 범위 테스트")
    public void lottoNumberBoundTest() {
        // given & when
        Lotto lotto = new Lotto();
        List<LottoNumber> numbers = lotto.getNumbers();
        // then
        for (LottoNumber number : numbers) {
            assertThat(number.getNumber()).isBetween(1, 45);
        }
    }

    @Test
    @DisplayName("로또번호 매칭 테스트")
    public void countMatchNumberTest() {
        // given
        Lotto lotto1 = new Lotto(List.of(4, 5, 6, 7, 8, 9));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        // when
        Integer count = lotto1.countMatchNumber(lotto2);
        //then
        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("보너스 매칭 테스트")
    public void matchBonusTest() {
        // given
        Lotto lotto = new Lotto(List.of(4, 5, 6, 7, 8, 9));
        // when
        Boolean bonusMatch = lotto.bonusMatch(new LottoNumber(4));
        //then
        assertThat(bonusMatch).isTrue();
    }

    @Test
    @DisplayName("숫자 개수 판별 테스트")
    public void validateNumberCountTest() {
        // given
        List<Integer> input = List.of(1, 2, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("숫자 중복 판별 테스트")
    public void validateDuplicateTest() {
        // given
        List<Integer> input = List.of(1, 2, 3, 4, 5, 5);

        // when & then
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복이 아닌 숫자를 입력해주세요");
    }
}
