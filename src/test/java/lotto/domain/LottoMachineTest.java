package lotto.domain;

import lotto.domain.exception.LackOfMoneyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoMachineTest {

    LottoMachine machine;
    LottoFactory lottoFactory;

    @BeforeEach
    public void setUp() {
        machine = new LottoMachine(5500);
        lottoFactory = new LottoFactory();
    }

    @Test
    public void 로또_기계에서_구입이_잘이루어지는지_검사() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = machine.buy(numbers);

        assertEquals(lottoFactory.create(numbers), lotto);
    }

    @Test
    public void 로또_구매_금액이_남았는지_확인() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        machine.buy(numbers);
        machine.buy(numbers);
        machine.buy(numbers);
        assertThat(machine.isRemainMoney()).isTrue();
    }

    @Test
    public void 설정_금액_이하_구매시_예외발생하는지_확인() {
        assertThatExceptionOfType(LackOfMoneyException.class).isThrownBy(() -> {
            new LottoMachine(500);
        });
    }
}
