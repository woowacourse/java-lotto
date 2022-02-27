package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@Nested
@DisplayName("로또 뭉치는")
class LottosTest {
    @Nested
    @DisplayName("숫자를 입력으로 받으면")
    class if_input_number {
        @ParameterizedTest
        @ValueSource(ints = {1, 3, 5, 7})
        @DisplayName("해당 숫자만큼 로또를 생성한다.")
        void create_lottos(int count) {
            assertThat(new Lottos(count).getLottos()).hasSize(count);
        }
    }
}
