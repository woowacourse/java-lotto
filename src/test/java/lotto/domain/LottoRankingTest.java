package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoRankingTest {

    @Nested
    @DisplayName("로또 순위를 알려주는 기능은")
    class Of {

        @Nested
        @DisplayName("맞춘 개수와 보너스 번호를 맞췄는지에 대해 주어지면")
        class Context_with_Lotto_and_WinningLotto {

            @ParameterizedTest
            @CsvSource(value = {"3|false|FIFTH", "5|false|THIRD", "5|true|SECOND"}, delimiter = '|')
            @DisplayName("당첨 순위를 알려준다.")
            void it_returns_lotto_ranking(
                int count, boolean containsBonusNumber, LottoRanking lottoRanking
            ) {
                Assertions.assertThat(LottoRanking.of(count, containsBonusNumber)).isEqualTo(lottoRanking);
            }
        }
    }
}
