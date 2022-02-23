package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.domain.Lotto;
import lotto.domain.Number;
import lotto.domain.WinningPrice;

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
                        actual ++;
                    }
                }
                assertThat(actual).isEqualTo(6);
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
    class GetWinningPrice {

        @Nested
        @DisplayName("당첨번호와 보너스번호가 주어지면")
        class Context_with_winning_numbers_and_bonus_number {

            @ParameterizedTest
            @CsvSource(value = {"1|2|3|4|5|6|7|All", "1|2|3|4|5|45|6|FiveAndBonus"}, delimiter = '|')
            @DisplayName("당첨 순위를 알려준다.")
            void it_returns_winning_price(String first, String second, String third, String fourth, String fifth, String sixth,
                String bonus, WinningPrice key
            ) {
                Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

                List<Number> numbers = Stream.of(first, second, third, fourth, fifth, sixth)
                    .map(Number::new)
                    .collect(Collectors.toList());
                Number bonusNumber = new Number(bonus);

                assertThat(lotto.getWinningPrice(numbers, bonusNumber)).isEqualTo(key);
            }
        }
    }
}
