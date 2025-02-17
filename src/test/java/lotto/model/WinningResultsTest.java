package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningResultsTest {
    @DisplayName("3000원으로 4개를 맞춘 로또가 있으면 수익률이 16.66이 나와야한다.")
    @MethodSource("provideLottos")
    @ParameterizedTest
    void calculateReturnRatio(List<Lotto> provideLottos) {
        Money money = new Money(3_000);
        Lotto lotto = new Lotto(
                List.of(1, 2, 3, 4, 5, 6).stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
        );
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        Lottos lottos = new Lottos();
        for (Lotto provideLotto : provideLottos) {
            lottos.add(provideLotto);
        }
        WinningResults winningResults = new WinningResults(winningLotto, lottos);
        winningResults.calculateWinningResults();
        Assertions.assertThat(money.calculateReturnRatio(winningResults.calculateEarnedMoney())).isEqualTo(16.66);
    }

    private static Stream<Arguments> provideLottos() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Lotto(
                                        List.of(1, 2, 3, 4, 15, 16).stream()
                                                .map(LottoNumber::new)
                                                .collect(Collectors.toList())
                                ),
                                new Lotto(
                                        List.of(11, 21, 31, 41, 15, 16).stream()
                                                .map(LottoNumber::new)
                                                .collect(Collectors.toList())
                                ),
                                new Lotto(
                                        List.of(12, 22, 32, 42, 15, 16).stream()
                                                .map(LottoNumber::new)
                                                .collect(Collectors.toList())
                                )
                        )
                )
        );
    }
}