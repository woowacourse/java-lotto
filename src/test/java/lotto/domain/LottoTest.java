package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("로또 생성 검증")
    void testLotto() {
        assertThat(new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)))).isNotNull();

    }

    @Test
    @DisplayName("로또 번호가 6개가 아니라면 예외를 발생시킨다.")
    void testLottoNumber_sizeException() {
        assertThatThrownBy(() -> new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호 가 1에서 45 사이가 아니라면 예외를 발생시킨다.")
    void testLottoNumber_rangeException() {
        assertThatThrownBy(() -> new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 100))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("MatchCount()에서 로또 당첨 개수와 보너스 여부를 반환한다.")
    void test_MatchCount() {
        Lotto lotto = new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));
        Lotto matchLotto = new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 8)));
        int bonus = 6;
        MatchResult dto = lotto.countMatchingNumbers(matchLotto, bonus);

        assertThat(dto.matchCount()).isEqualTo(5);
        assertThat(dto.isBonusMatched()).isTrue();
    }

    @Test
    @DisplayName("로또에 중복된 번호가 있으면 예외가 발생한다.")
    void test_validateDuplicate() {
        assertThatThrownBy(() -> new Lotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 5))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 오름차순으로 정렬되어야 한다.")
    @Test
    void testSort_lottoNumber() {
        // given
        List<Integer> list = new ArrayList<>(List.of(3, 5, 4, 1, 6, 2));
        Lotto lotto = new Lotto(list);

        List<Integer> sortedList = list.stream().sorted().toList();

        assertThat(lotto.toString()).isEqualTo(sortedList.toString());
    }

}
