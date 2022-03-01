package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @DisplayName("LottoQuantity 는 생성자에서 InputMoney 를 전달 받아 생성될 수 있다.")
    @Test
    void createLottoQuantityWithInputMoney() {
        // given
        InputMoney inputMoney = new InputMoney(5000);

        // when
        LottoQuantity lottoQuantity = new LottoQuantity(inputMoney);

        // then
        assertThat(lottoQuantity).isNotNull();
    }

    @DisplayName("생성자에 InputMoney 를 전달받으면, 개수는 InputMoney / 로또 한장 가격 이어야 한다.")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "5000,5", "100000,100"})
    void createLottoQuantityWithInputMoneyShouldHaveValidQuantity(int input, int expected) {
        // given
        InputMoney inputMoney = new InputMoney(input);

        // when
        LottoQuantity lottoQuantity = new LottoQuantity(inputMoney);

        // then
        assertThat(lottoQuantity.getLottoQuantity()).isEqualTo(expected);
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
