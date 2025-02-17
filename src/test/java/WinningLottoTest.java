import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoWallet;
import domain.Money;
import domain.WinningInfo;
import domain.WinningLotto;
import domain.WinningResult;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {
    private List<Lotto> lottos = List.of(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 5, 8)),
            new Lotto(List.of(1, 2, 3, 4, 10, 11)),
            new Lotto(List.of(1, 2, 3, 10, 11, 12)),
            new Lotto(List.of(10, 11, 12, 13, 14, 15)));
    private LottoWallet lottoWallet = new LottoWallet(lottos);
    private WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7));

    @Test
    @DisplayName("당첨 번호에 보너스 번호가 존재하면 예외가 발생한다")
    void should_throw_exception_when_bonus_number_is_in_winning_numbers() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusLottoNumber = new LottoNumber(1);

        // when & then
        assertThatThrownBy(() -> {
            new WinningLotto(winningNumbers, bonusLottoNumber);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> correctMatchedCountArguments() {
        return Stream.of(arguments(new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7)),
                        new Lotto(List.of(1, 2, 3, 43, 44, 45)), 3),
                arguments(new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7)),
                        new Lotto(List.of(1, 2, 3, 7, 44, 45)), 3)

        );
    }

    @ParameterizedTest
    @DisplayName("로또 번호가 주어졌을 때 적중 개수를 정확히 반환한다")
    @MethodSource("correctMatchedCountArguments")
    void should_return_correct_matched_count(WinningLotto winningLotto, Lotto lotto, int expected) {
        // when
        final int matchedCount = winningLotto.countMatchedNumber(lotto);

        // then
        assertThat(matchedCount).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("로또 번호가 주어졌을 때 보너스 번호의 적중 여부를 정확히 반환한다")
    @CsvSource(value = {"6, true", "7, false"})
    void should_return_correct_bonus_match(int bonusNumber, boolean expected) {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 45));
        LottoNumber bonus = new LottoNumber(bonusNumber);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        final boolean isBonusMatched = winningLotto.isBonusNumberMatched(lotto);

        // then
        assertThat(isBonusMatched).isEqualTo(expected);
    }


    @Test
    @DisplayName("로또와 당첨 번호가 주어졌을 때 로또 순위별 당첨 횟수를 정확히 계산한다")
    void check_each_winning_count_correctly() {
        // when
        WinningResult winningResult = winningLotto.calculateWinningResult(lottoWallet);

        // then
        for (WinningInfo value : WinningInfo.values()) {
            assertThat(winningResult.getCount(value)).isEqualTo(1);
        }
    }
}