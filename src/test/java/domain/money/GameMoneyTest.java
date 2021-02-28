package domain.money;

import domain.lotto.Lotto;
import domain.lotto.LottoBundle;
import domain.number.LottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

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

    @DisplayName("GameMoney를 입력받고, 구매할 로또를 입력한 후 구매한다.")
    @Test
    void GameMoneyBuyManualLottoTest() {
        final GameMoney gameMoney = new GameMoney(10000);

        final Lotto lotto1 = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        final Lotto lotto2 = Lotto.of(Arrays.asList(2, 3, 4, 5, 6, 7));
        final Lotto lotto3 = Lotto.of(Arrays.asList(3, 4, 5, 6, 7, 8));

        final List<List<Integer>> manualLottoBought = Arrays.asList(Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(2, 3, 4, 5, 6, 7),
                Arrays.asList(3, 4, 5, 6, 7, 8));

        final LottoBundle manualLottoBundle = gameMoney.buyLotto(manualLottoBought);

        assertThat(manualLottoBundle.countNumberOfLotto()).isEqualTo(3);
        assertThat(manualLottoBundle.getLottoBundle()).contains(lotto1);
        assertThat(manualLottoBundle.getLottoBundle()).contains(lotto2);
        assertThat(manualLottoBundle.getLottoBundle()).contains(lotto3);
    }

    @DisplayName("GameMoney를 입력받고, buyAutoLotto를 테스트 한다.")
    @Test
    void GameMoneyBuyAutoLotto() {
        //Given
        final GameMoney gameMoney = new GameMoney(2000);
        final LottoNumberGenerator orderLottoNumberGenerator = new OrderLottoNumberGenerator();
        //When
        final LottoBundle autoLottoBundle = gameMoney.buyAutoLotto(orderLottoNumberGenerator);
        //Then
        final List<Lotto> lottoBundle = autoLottoBundle.getLottoBundle();
        assertThat(lottoBundle).containsExactly(
                        Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)),
                        Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6))
        );
    }

    class OrderLottoNumberGenerator implements LottoNumberGenerator {
        @Override
        public List<Integer> createLottoNumber() {
            return Arrays.asList(1, 2, 3, 4, 5, 6);
        }
    }
}

