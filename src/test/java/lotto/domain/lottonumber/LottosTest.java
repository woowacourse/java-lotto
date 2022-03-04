package lotto.domain.lottonumber;

import lotto.domain.matchkind.LottoMatchKind;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottosTest {
    private final Lotto first = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
    private final Lotto second = new Lotto(Arrays.asList( 2, 3, 4, 5, 6, 7));
    private final Lotto third = new Lotto(Arrays.asList(3, 4, 5, 6, 7, 8));
    private final Lotto fourth = new Lotto(Arrays.asList(4, 5, 6, 7, 8, 9));
    private final Lotto fifth = new Lotto(Arrays.asList(5, 6, 7, 8, 9, 10));
    private final Lotto sixth = new Lotto(Arrays.asList(6, 7, 8, 9, 10, 11));

    private final List<Lotto> manualLottos = Arrays.asList(first, second, third, fourth, fifth, sixth);
    private final Lottos lottos = new Lottos(manualLottos);
    private final WinningNumbers winningNumbers = new WinningNumbers(Arrays.asList(2, 3, 4, 5, 6, 7), 1);

    @Test
    @DisplayName("구매한 모든 로또 숫자들을 반환한다.")
    void getLottos_Test() {
        final List<Lotto> expected = Arrays.asList(first, second, third, fourth, fifth, sixth);
        //when
        final List<Lotto> actual = lottos.getLottos();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("당첨 번호와 구매 금액을 받아 당첨 결과를 반환한다.")
    void getWinningResult_Test() {
        //given
        final Map<LottoMatchKind, Integer> expected = Map.of(
                LottoMatchKind.LOWER_THAN_THREE, 1,
                LottoMatchKind.THREE, 1,
                LottoMatchKind.FOUR, 1,
                LottoMatchKind.FIVE, 1,
                LottoMatchKind.FIVE_BONUS, 1,
                LottoMatchKind.SIX, 1);
        //when
        final Map<LottoMatchKind, Integer> actual = lottos.match(winningNumbers);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("로또들 간의 중복이 있으면 예외를 발생시킨다.")
    void create_exceptionByDuplication_Test() {
        //given
        final Lotto first = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        final Lotto second = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        final List<Lotto> duplicatedLottos = Arrays.asList(first, second);
        final String expectedExceptionMessage = "로또 숫자들 간에 중복이 있습니다.";
        //when //then
        assertThatThrownBy(() -> new Lottos(duplicatedLottos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }
}
