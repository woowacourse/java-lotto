import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WinningPriceTest {

    @Nested
    @DisplayName("멤버 변수로 객체를 찾는 기능은")
    class Of {

        @Nested
        @DisplayName("개수와 boolean값이 주어지면")
        class Context_with_count_and_contain_bonus {

            @ParameterizedTest
            @CsvSource(value = {"6|false|All", "5|true|FiveAndBonus", "5|false|Five", "4|false|Four",
                "3|false|Three", "3|true|Three", "2|true|Fail", "1|true|Fail", "0|true|Fail"}, delimiter = '|')
            @DisplayName("객체를 반환한다.")
            void it_create_ok(int count, boolean containBonus, WinningPrice expected) {
                WinningPrice winningPrice = WinningPrice.of(count, containBonus);

                Assertions.assertThat(winningPrice).isEqualTo(expected);
            }
        }
    }
}
