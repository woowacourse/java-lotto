package lotto.domain.lotto;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.domain.LottoRanking;

public class LottoWinningNumberTest {
    @Nested
    @DisplayName("당첨 순위를 알려주는 기능은")
    class getLottoRanking {
        @Nested
        @DisplayName("로또가 주어지면")
        class Context_with_lotto {
            @ParameterizedTest
            @CsvSource(value = {"1|2|3|4|5|6|All", "1|2|3|4|5|7|FiveAndBonus"}, delimiter = '|')
            @DisplayName("당첨 순위를 알려준다.")
            void it_returns_lotto_ranking(
                int first, int second, int third, int fourth, int fifth, int sixth, LottoRanking lottoRanking
            ) {
                Lotto lotto = new Lotto(List.of(first, second, third, fourth, fifth, sixth));
                Lotto winninglotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
                Number bonusNumber = new Number(7);
                LottoWinningNumber lottoWinningNumber = new LottoWinningNumber(winninglotto, bonusNumber);
                Assertions.assertThat(lottoWinningNumber.getLottoRanking(lotto))
                    .isEqualTo(lottoRanking);

            }
        }
    }
}
