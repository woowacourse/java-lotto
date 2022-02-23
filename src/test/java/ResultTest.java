import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Nested
    @DisplayName("결과는")
    class NewResult {

        @Nested
        @DisplayName("생성될 때")
        class Context_with_empty {

            @Test
            @DisplayName("순위 별 개수를 0으로 초기화한다.")
            void it_create_ok() {
                Result result = new Result();

                for (WinningPrice value : WinningPrice.values()) {
                    assertThat(result.getCount(value)).isEqualTo(0);
                }
            }
        }
    }

    @Nested
    @DisplayName("등수의 개수를 올리는 메소드는")
    class AddCount {

        @Nested
        @DisplayName("WinningPrice가 주어지면")
        class Context_with_winning_price {

            @Test
            @DisplayName("해당 WinningPrice의 개수가 1 올라간다.")
            void it_add_count() {
                Result result = new Result();

                result.add(WinningPrice.All);

                assertThat(result.getCount(WinningPrice.All)).isEqualTo(1);
            }
        }
    }
}
