package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoFactoryTest {
    @ParameterizedTest(name = "생성 시도한 수량 : {0}")
    @ValueSource(ints = {1, 10, 100})
    @DisplayName("로또 팩토리에 수량을 전달해서 Lottos를 반환받는다")
    void createAutoLottosByQuantity(int quantity) {
        // given
        Lottos autoLottosByQuantity = LottoFactory.createAutoLottosByQuantity(quantity);

        // when
        int createLottoSize = autoLottosByQuantity
                .getLottos()
                .size();

        // when & then
        assertThat(createLottoSize).isEqualTo(quantity);
    }

    @Test
    @DisplayName("멀티스레드를 활용한 로또 생성 대응")
    void createAutoLottosWithMultiThread() {
        assertThatCode(
                () -> IntStream.rangeClosed(0, 100)
                        .parallel()
                        .forEach(i -> LottoFactory.createAutoLottosByQuantity(10)))
                .doesNotThrowAnyException();

    }

    @ParameterizedTest(name = "생성 시도한 수량 : {0}")
    @ValueSource(ints = {-1, 0})
    @DisplayName("LottoFactory에 1 미만의 수량을 요청할 경우, IAE발생")
    void createAutoLottosWithInvalidQuantityShouldFail(int invalidQuantity) {
        assertThatThrownBy(() -> LottoFactory.createAutoLottosByQuantity(invalidQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동 로또 생성 수량은 1 이상으로 입력해주세요");
    }
}
