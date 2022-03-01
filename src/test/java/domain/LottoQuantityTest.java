package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("LottoQuantity 테스트")
public class LottoQuantityTest {
    @DisplayName("LottoQuantity 에 양의 정수를 전달하면 객체가 생성된다.")
    @ParameterizedTest(name = "{0} 전달")
    @ValueSource(ints = {1, 5, 7})
    void lottoQuantityCreationTest(int quantity) {
        // given
        LottoQuantity lottoQuantity = new LottoQuantity(quantity);

        // when
        int extractedLottoQuantityNumber = lottoQuantity.getLottoQuantity();

        // then
        assertThat(extractedLottoQuantityNumber).isEqualTo(quantity);
    }

    @DisplayName("양수가 아닌 값으로 LottoAmount 생성시 IAE 발생한다.")
    @ParameterizedTest(name = "{0} 전달")
    @ValueSource(ints = {-1, 0})
    void createLottoQuantityWithNegativeOrZeroShouldFail(int lottoQuantity) {
        assertThatThrownBy(() -> new LottoQuantity(lottoQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoQuantity.ERROR_MESSAGE_FOR_INVALID_TRAIL_NUMBER);
    }
}
