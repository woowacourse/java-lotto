package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoQuantityTest {
    @ParameterizedTest(name = "LottoAmount 에 양의 정수를 전달하면 객체가 생성됨 - {0}")
    @ValueSource(ints = {1, 5, 7})
    void lottoQuantityCreationTest(int quantity) {
        // given
        LottoQuantity lottoQuantity = new LottoQuantity(quantity);

        // when
        int extractedLottoQuantityNumber = lottoQuantity.getLottoQuantity();

        // then
        assertThat(extractedLottoQuantityNumber).isEqualTo(quantity);
    }

    @ParameterizedTest(name = "양수가 아닌 값으로 LottoAmount 생성시 IAE 발생 - {0}")
    @ValueSource(ints = {-1, 0})
    void createLottoQuantityWithNegativeOrZeroShouldFail(int lottoQuantity) {
        assertThatThrownBy(() -> new LottoQuantity(lottoQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("개수는 1 보다 작을 수 없습니다.");
    }
}
