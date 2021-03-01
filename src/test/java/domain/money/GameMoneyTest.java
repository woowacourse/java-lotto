package domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class GameMoneyTest {
    @DisplayName("GameMoney 정상 생성 테스트")
    @Test
    void GameMoneyConstructorTest() {
        assertThatCode(() -> new GameMoney(1000))
                .doesNotThrowAnyException();
    }

    @DisplayName("GameMoney에 최소가격 1000원 미만이 들어오면 에러를 출력한다")
    @ParameterizedTest
    @ValueSource(ints = {999, 500, 0, -100})
    void GameMoneyErrorTest(int money) {
        assertThatThrownBy(() -> new GameMoney(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("GameMoney를 입력받고, 수동으로 살 로또 갯수를 입력 받고 가능한지 검증")
    @Test
    void GameMoneyCheckManualBuyingAvailableTest() {
        final GameMoney gameMoney = new GameMoney(10000);
        assertThatThrownBy(() -> gameMoney.checkManualBuyingAvailable(15))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입할 수 없는 수량입니다.");
    }

    @DisplayName("GameMoney로 살 수 있는 최대한의 로또 갯수를 반환한다.")
    @Test
    void checkMaxLottoAvailableTest() {
        final GameMoney gameMoney = new GameMoney(10000);
        assertThat(gameMoney.checkMaxLottoAvailable()).isEqualTo(10);
    }

    @DisplayName("GameMoney를 입력받고, buyLotto를 통해 구매 후 남은 돈을 계산한다.")
    @Test
    void GameMoneyBuyLotto() {
        final GameMoney gameMoney = new GameMoney(3000);
        assertThatCode(() -> gameMoney.buyLotto(3))
                .doesNotThrowAnyException();
    }

    @DisplayName("입력 받은 GameMoney로 구매할 수 없는 수량의 로또를 구매하려고 하는 경우, 예외가 발생한다.")
    @Test
    void GameMoneyBuyLottoException() {
        final GameMoney gameMoney = new GameMoney(3000);
        assertThatThrownBy(() -> gameMoney.buyLotto(5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("돈이 부족합니다.");
    }
}

