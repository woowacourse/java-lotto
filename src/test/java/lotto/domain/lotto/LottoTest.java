package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.LottoRanking;

public class LottoTest {

    @Nested
    @DisplayName("로또는")
    class NewLotto {

        @Nested
        @DisplayName("크기가 6이 아닌 숫자 리스트를 주면")
        class Context_with_not_six_length_list {

            @Test
            @DisplayName("예외를 발생시킨다.")
            void it_throw_exception() {
                assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("6개의 숫자가 필요합니다.");

                assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("6개의 숫자가 필요합니다.");
            }
        }

        @Nested
        @DisplayName("숫자 리스트가 중복이 있으면")
        class Context_with_duplicate {

            @Test
            @DisplayName("예외를 발생시킨다.")
            void it_throw_exception() {
                assertThatThrownBy(() -> new Lotto(List.of(1, 1, 3, 4, 5, 6)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("중복은 허용하지 않습니다.");
            }
        }

        @Nested
        @DisplayName("무작위로 생성하면")
        class Context_with_random_create {

            @Test
            @DisplayName("1~45의 숫자 중 중복되지 않은 6개를 가진다.")
            void it_contains_six_number_1_to_45() {
                Lotto lotto = new Lotto();
                int actual = 0;
                for (int i = 1; i < 46; i++) {
                    if (lotto.contains(new Number(String.valueOf(i)))) {
                        actual++;
                    }
                }
                assertThat(actual).isEqualTo(6);
            }
        }
    }
    
    @Nested
    @DisplayName("숫자와 구분자로 이루어진 문자열을 로또로 바꾸는 기능은")
    class Of {
        @Nested
        @DisplayName("', '로 구분한 숫자가 주어지면")
        class Context_with_delimiter {
            @ParameterizedTest
            @ValueSource(strings = {"1, 2, 3, 4, 5, 6"})
            @DisplayName("구분된 숫자 6개로 객체를 반환한다.")
            void it_returns_lotto(String input) {
                assertDoesNotThrow(() -> Lotto.of(input));
            }
        }
    }
    
    @Nested
    @DisplayName("어떤 번호가 포함되었는지 판단하는 메소드는")
    class Contains {

        @Nested
        @DisplayName("포함되는 번호가 주어지면")
        class Context_with_contains_number {

            @Test
            @DisplayName("true를 반환한다.")
            void it_returns_true() {
                Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
                assertThat(lotto.contains(new Number("1"))).isTrue();
            }
        }

        @Nested
        @DisplayName("포함되지 않는 번호가 주어지면")
        class Context_with_not_contains_number {

            @Test
            @DisplayName("false를 반환한다.")
            void it_returns_true() {
                Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
                assertThat(lotto.contains(new Number("7"))).isFalse();
            }
        }
    }

    @Nested
    @DisplayName("당첨 순위를 알려주는 메소드는")
    class GetLottoRanking {

        @Nested
        @DisplayName("당첨번호와 보너스번호가 주어지면")
        class Context_with_winning_numbers_and_bonus_number {

            @ParameterizedTest
            @CsvSource(value = {"1|2|3|4|5|6|7|All", "1|2|3|4|5|45|6|FiveAndBonus"}, delimiter = '|')
            @DisplayName("당첨 순위를 알려준다.")
            void it_returns_winning_price(int first, int second, int third, int fourth, int fifth,
                int sixth,
                int bonus, LottoRanking key
            ) {
                Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

                Lotto winningLotto = new Lotto(List.of(first, second, third, fourth, fifth, sixth));
                Number bonusNumber = new Number(bonus);

                assertThat(lotto.getWinningPrice(winningLotto, bonusNumber)).isEqualTo(key);
            }
        }
    }
}
