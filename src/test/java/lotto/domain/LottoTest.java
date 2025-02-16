package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("로또 생성 검증")
    void testLotto() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        assertThat(new Lotto(LottoNumber.from(list))).isNotNull();

    }

    @Test
    @DisplayName("로또 번호가 6개가 아니라면 예외를 발생시킨다.")
    void testLottoNumber_sizeException() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        assertThatThrownBy(() -> new Lotto(LottoNumber.from(list)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("MatchCount()에서 로또 당첨 개수와 보너스 여부를 반환한다.")
    void test_MatchCount() {
        //given
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(LottoNumber.from(list));

        List<Integer> matchList = List.of(1, 2, 3, 4, 5, 7);
        Lotto matchLotto = new Lotto(LottoNumber.from(matchList));

        int bonus = 6;

        //when
        MatchResult dto = lotto.countMatchingNumbers(matchLotto, new LottoNumber(bonus));

        //then
        assertThat(dto.matchCount()).isEqualTo(5);
        assertThat(dto.isBonusMatched()).isTrue();
    }

    @Test
    @DisplayName("로또에 중복된 번호가 있으면 예외가 발생한다.")
    void test_validateDuplicate() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(() -> new Lotto(LottoNumber.from(list)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
