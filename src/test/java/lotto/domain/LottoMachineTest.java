package lotto.domain;

import lotto.domain.exception.LackOfMoneyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoMachineTest {

    LottoMachine machine;
    LottoFactory lottoFactory;

    @BeforeEach
    public void setUp() {
        machine = new LottoMachine();
        machine.charge(5500);
        lottoFactory = new LottoFactory();
    }

    @Test
    public void 로또_구매_금액이_남았는지_확인() {
        machine.buy();
        machine.buy();
        machine.buy();
        assertThat(machine.isRemainMoney()).isTrue();
    }

    @Test
    public void 설정_금액_이하_구매시_예외발생하는지_확인() {
        assertThatExceptionOfType(LackOfMoneyException.class).isThrownBy(() -> {
            new LottoMachine().charge(500);
        });
    }
}
