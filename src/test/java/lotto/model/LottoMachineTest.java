package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @Test
    @DisplayName("수동 구매 개수로 음수가 들어올 경우")
    void initTest1() {
        assertThatThrownBy(() -> new LottoMachine(5, -1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("0보다 큰 정수를 입력해 주세요.");
    }

    @Test
    @DisplayName("자동 구매 개수가 잘 계산되는지 확인")
    void initTest2() {
        LottoMachine lottoMachine = new LottoMachine(5, 2);
        int actual = lottoMachine.getAutoCount();
        int expected = 3;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("수동 구매한 로또와 자동 구매한 로또가 잘 구매되었는지 확인")
    void purchaseTest() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto lotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        List<Lotto> manualLottos = new ArrayList<>(List.of(lotto1, lotto2, lotto3));
        LottoMachine lottoMachine = new LottoMachine(5, 3);

        List<Lotto> lottos = lottoMachine.createLottos(manualLottos);

        assertThat(lottos)
            .hasSize(5)
            .contains(lotto1, lotto2, lotto3);
    }
}