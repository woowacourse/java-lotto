package domain;

import domain.lotto.Lotto;
import domain.lotto.LottoBall;
import domain.lotto.LottoBundle;
import domain.money.GameMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
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

    @DisplayName("GameMoney로 로또를 구매하기")
    @Test
    void GameMoneyBuyLottoTest() {
        final GameMoney gameMoney = new GameMoney(15000);
        final LottoBundle lottoBundle = gameMoney.buyLotto();
        assertThat(lottoBundle.countNumberOfLotto()).isEqualTo(15);
    }

    @DisplayName("GameMoney를 입력받고, 수동으로 살 로또 갯수를 입력 받고 가능한지 검증")
    @Test
    void GameMoneyCheckManualBuyingAvailableTest() {
        final GameMoney gameMoney = new GameMoney(10000);
        assertThatThrownBy(() -> gameMoney.checkManualBuyingAvailable(15))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입금액 이상으로 로또를 구매할 수 없습니다.");
    }

    @DisplayName("GameMoney를 입력받고, 수동으로 구매할 금액을 입력받고, 수동으로 구매할 로또를 입력해주고, 자동으로까지 다 산다.")
    @Test
    void GameMoneyBuyLottoManuallyTest() {
        final GameMoney gameMoney = new GameMoney(10000);
        List<Lotto> lottoBoughtManually = new ArrayList<>();

        final Lotto lotto1 = makeLottoManually(1, 6);
        final Lotto lotto2 = makeLottoManually(2, 7);
        final Lotto lotto3 = makeLottoManually(3, 8);

        lottoBoughtManually.add(lotto1);
        lottoBoughtManually.add(lotto2);
        lottoBoughtManually.add(lotto3);

        final LottoBundle lottoBundle = gameMoney.buyLottoManually(lottoBoughtManually);

        assertThat(lottoBundle.countNumberOfLotto()).isEqualTo(10);
        assertThat(lottoBundle.getLottoBundle()).contains(lotto1);
        assertThat(lottoBundle.getLottoBundle()).contains(lotto2);
        assertThat(lottoBundle.getLottoBundle()).contains(lotto3);
    }

    private Lotto makeLottoManually(int from, int to) {
        List<LottoBall> lotto = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            lotto.add(LottoBall.valueOf(i));
        }
        return new Lotto(lotto);
    }
}

