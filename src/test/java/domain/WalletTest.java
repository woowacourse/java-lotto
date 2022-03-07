package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class WalletTest {
    @Test
    @DisplayName("금액은 1000 이상, 1000단위로 입력해야됨")
    void createInputMoneyTest() {
        // given
        int inputMoney = 1_000;

        // when
        Wallet wallet = new Wallet(inputMoney);

        // then
        assertThat(wallet.getCurrentBalance()).isEqualTo(inputMoney);
    }

    @ParameterizedTest(name = "{0}원 입력 시")
    @ValueSource(ints = {999, -1, 0, 1111})
    @DisplayName("잘못된 로또 구입 금액 입력 시, IAE 발생")
    void createInputMoneyWithInvalidMoneyShouldFail(int inputMoney) {
        assertThatThrownBy(() -> new Wallet(inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("금액은 1000원 이상, 1000원 단위로 입력해주세요");
    }

    @ParameterizedTest(name = "입금 : {0} , 구매수량 : {1}, 기대잔액 : {2}")
    @CsvSource(value = {"10000,1,9000", "10000,5,5000", "10000,10,0"})
    @DisplayName("원하는 수량만큼 수동 로또를 구매하고, 잔액을 확인한다")
    void buyManualLottoByQuantity(int inputMoney, int quantity, int expectedBalanceAfterBuying) {
        // given
        Wallet wallet = new Wallet(inputMoney);

        // when
        wallet.buyManualLottoByQuantity(quantity);

        // then
        assertAll(
                () -> assertThat(wallet.getManualQuantity()).isEqualTo(quantity),
                () -> assertThat(wallet.getCurrentBalance()).isEqualTo(expectedBalanceAfterBuying)
        );
    }

    @ParameterizedTest(name = "구매 시도 수량 : {0}")
    @ValueSource(ints = {-1, -10, -100})
    @DisplayName("수동 로또 구매수량이 0장 미만일 경우, IAE 발생")
    void buyingManualLottoWithInvalidQuantityShouldFail(int invalidQuantity) {
        // given & when
        Wallet wallet = new Wallet(1_000);

        // then
        assertThatThrownBy(() -> wallet.buyManualLottoByQuantity(invalidQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("구매 수량은 \\d+ 이상이어야 합니다");
    }

    @ParameterizedTest(name = "구매 시도 수량 : {0}")
    @ValueSource(ints = {11, 100})
    @DisplayName("구매 가능한 수량(10장) 이상 구매 시도 시 IAE 발생")
    void buyingManualLottoWithIncapableQuantity(int incapableQuantity) {
        // given & when
        Wallet wallet = new Wallet(10_000);

        // then
        assertThatThrownBy(() -> wallet.buyManualLottoByQuantity(incapableQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이상 구매할 수 없습니다 :");
    }

    @Test
    @DisplayName("잔액이 없는 경우, isEmpty 메서드는 true를 반환한다")
    void walletShouldReturnTrueForIsEmptyWhenCurrentBalanceIsZero() {
        // given
        Wallet wallet = new Wallet(10_000);

        // when
        wallet.buyManualLottoByQuantity(10);

        // then
        assertThat(wallet.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("자동, 수동 로또 구매 이후 잔액은 0, 수량은 각각 구매한 수량과 일치해야 한다.")
    void walletOperationTestForBuyingManualAndAutoLottos() {
        // given
        Wallet wallet = new Wallet(10_000);

        // when
        wallet.buyManualLottoByQuantity(3);
        wallet.buyAutoLottoWithCurrentBalance();

        // then
        assertAll(
                () -> assertThat(wallet.isEmpty()).isTrue(),
                () -> assertThat(wallet.getManualQuantity()).isEqualTo(3),
                () -> assertThat(wallet.getAutoQuantity()).isEqualTo(7),
                () -> assertThat(wallet.getCurrentBalance()).isEqualTo(0)
        );
    }
}
