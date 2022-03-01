package vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ManualLottoQuantityTest {
    @ParameterizedTest(name = "생성 요청 수량 : {0}")
    @ValueSource(ints = {1, 10, 100})
    @DisplayName("수동 로또 생성 테스트")
    void createManualLottoQuantity(int expected) {
        // given
        ManualLottoQuantity manualLottoQuantity = new ManualLottoQuantity(expected);

        // when
        int actual = manualLottoQuantity.getQuantity();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void createManualLottoQuantityWithInvalidQuantity(int invalidQuantity) {
        assertThatThrownBy(() -> new ManualLottoQuantity(invalidQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 로또 수량은 1 이상으로 입력해주세요");
    }
}
