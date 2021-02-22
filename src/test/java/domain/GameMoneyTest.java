package domain;

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

    @DisplayName("GameMoney로 로또를 구매하기")
    @Test
    void GameMoneyBuyLottoTest() {
        final GameMoney gameMoney = new GameMoney(15000);
        final LottoBundle lottoBundle = gameMoney.buyLotto();
        assertThat(lottoBundle.countNumberOfLotto()).isEqualTo(15);
    }
}
