package lottogame.domain;

import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.LottoNumber;
import lottogame.utils.CannotBuyLottoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoMachineTest {
    private LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine();
    }

    @DisplayName("로또를 구매할 수 없는 경우(1000원 미만) 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"100", "0", "450"})
    void 로또_구매_기능_테스트(int value) {
        Money money = new Money(value);
        assertThrows(CannotBuyLottoException.class,
                () -> lottoMachine.purchaseQuantity(money));
    }

    @DisplayName("로또 구매 개수가 올바르게 출력되는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"14000:14", "1500:1", "3000:3"}, delimiter = ':')
    void 로또_구매_기능_테스트(int value, int expected) {
        Money money = new Money(value);
        assertEquals(expected, lottoMachine.purchaseQuantity(money));
    }

    @DisplayName("로또가 정상 구매 되는 지 확인")
    @Test
    void 로또_구매_기능_테스트() {
        List<Lotto> lottos = lottoMachine.buyLotto(4);
        assertThat(lottos).hasSize(4);
    }

    @DisplayName("로또 생성 시 중복 숫자를 방지하는 기능이 잘 동작하는 지 확인")
    @Test
    void 로또_생성_기능_테스트() {
        List<Lotto> lottos = lottoMachine.buyLotto(3);
        assertThat(lottos).hasSize(3);

        for (Lotto lotto : lottos) {
            List<LottoNumber> lottoNumbers = lotto.values();
            assertThat(lottoNumbers.stream().distinct().count()).isEqualTo(lottoNumbers.size());
        }
    }
}