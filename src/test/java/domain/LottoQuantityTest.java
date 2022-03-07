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
    @DisplayName("LottoQuantity 에 0 또는 양의 정수를 전달하면 객체가 생성된다.")
    @ParameterizedTest(name = "{0} 전달")
    @ValueSource(ints = {0, 1, 5, 7})
    void lottoQuantityCreationTest(int quantity) {
        // given
        LottoQuantity lottoQuantity = LottoQuantity.from(quantity);

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
        LottoQuantity lottoQuantity = LottoQuantity.from(inputMoney);

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
        LottoQuantity lottoQuantity = LottoQuantity.from(inputMoney);

        // then
        assertThat(lottoQuantity.getLottoQuantity()).isEqualTo(expected);
    }

    @DisplayName("음수가 생성자에 전달되면 IAE 발생한다.")
    @ParameterizedTest(name = "{0} 전달")
    @ValueSource(ints = {-10, -1})
    void createLottoQuantityWithNegativeShouldFail(int lottoQuantity) {
        assertThatThrownBy(() -> LottoQuantity.from(lottoQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoQuantity.ERROR_MESSAGE_FOR_INVALID_LOTTO_QUANTITY);
    }

    @DisplayName("subtract 메소드는 자기자신과 다른 LottoQuantity 를 뺀 LottoQuantity 를 반환한다.")
    @Test
    void subtract() {
        // given
        LottoQuantity lottoQuantity1 = LottoQuantity.from(10);
        LottoQuantity lottoQuantity2 = LottoQuantity.from(6);
        LottoQuantity expected = LottoQuantity.from(4);

        // when
        LottoQuantity actual = lottoQuantity1.subtract(lottoQuantity2);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("createManual 메소드는 로또 개수와 입력 금액을 받아 LottoQuantity 를 반환한다.")
    @Test
    void createManual() {
        // given
        int manualLottoQuantity = 10;
        InputMoney inputMoney = new InputMoney(15000);

        // when
        LottoQuantity lottoQuantity = LottoQuantity.of(manualLottoQuantity, inputMoney);

        // then
        assertThat(lottoQuantity).isNotNull();
    }

    @DisplayName("createManual 메소드에 전달된 로또 개수 * 로또 금액이 입력 금액을 초과하면 IAE 가 발생한다.")
    @Test
    void createManualWithInvalidLottoQuantityShouldFail() {
        // given
        int manualLottoQuantity = 10;
        InputMoney inputMoney = new InputMoney(5000);

        // when & then
        assertThatThrownBy(() -> LottoQuantity.of(manualLottoQuantity, inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoQuantity.ERROR_MESSAGE_FOR_INVALID_MANUAL_LOTTO_QUANTITY);
    }
}
