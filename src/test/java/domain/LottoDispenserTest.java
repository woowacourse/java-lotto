package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoDispenserTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999})
    @DisplayName("1000원 이하의 돈이 들어올 시 예외 발생")
    void money_underLottoPrice_throwException(int inputMoney) {
        assertThatThrownBy(() -> new LottoDispenser(inputMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("가능한 수보다 많은 로또를 구매 시도하면 예외 발생")
    void lottoCount_overAvailable_throwException() {
        LottoDispenser lottoDispenser = new LottoDispenser(10000);
        assertThatThrownBy(() -> lottoDispenser.checkEnoughMoneyRemain(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({"1050,0,1", "12000,3,12", "15800,15,15"})
    @DisplayName("금액에 맞는 수의 로또 구매")
    void dispenser_makeMaximumLottos(int money, int manualCount, int expectedTotalLottos) {
        LottoDispenser lottoDispenser = new LottoDispenser(money);
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualLottos.add(lottoDispenser.buyManualLotto(List.of(1, 2, 3, 4, 5, 6)));
        }
        List<Lotto> autoLottos = lottoDispenser.buyAutoLottos();
        List<Lotto> totalLottos = new ArrayList<>();
        totalLottos.addAll(manualLottos);
        totalLottos.addAll(autoLottos);
        assertThat(totalLottos.size()).isEqualTo(expectedTotalLottos);
    }

}
