package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.LottoRanking;

public class WinningLottoTest {

    @Nested
    @DisplayName("당첨 번호를 생성할 때")
    class New {

        @Nested
        @DisplayName("보너스 번호가 당첨 로또와 중복 번호가 있다면")
        class Context_with_bonus_number_in_lotto_numbers {

            @ParameterizedTest
            @ValueSource(ints = {1, 2, 3, 4, 5, 6})
            @DisplayName("예외를 발생시킨다.")
            void it_throw_exception(int bonusNumber) {
                assertThatThrownBy(() -> new WinningLotto(
                    new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Number(bonusNumber)
                )).isInstanceOf(IllegalArgumentException.class);
            }
        }
    }

    @Nested
    @DisplayName("당첨 순위를 알려주는 기능은")
    class getLottoRanking {

        @Nested
        @DisplayName("로또가 주어지면")
        class Context_with_lotto {

            @ParameterizedTest
            @CsvSource(value = {"1|2|3|4|5|6|FIRST", "1|2|3|4|5|7|SECOND"}, delimiter = '|')
            @DisplayName("당첨 순위를 알려준다.")
            void it_returns_lotto_ranking(
                int first, int second, int third, int fourth, int fifth, int sixth, LottoRanking lottoRanking
            ) {
                Lotto lotto = new Lotto(List.of(first, second, third, fourth, fifth, sixth));
                Lotto winninglotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
                Number bonusNumber = new Number(7);
                WinningLotto winningLotto = new WinningLotto(winninglotto, bonusNumber);
                Assertions.assertThat(winningLotto.getLottoRanking(lotto))
                    .isEqualTo(lottoRanking);

            }
        }
    }
}
