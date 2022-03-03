package lotto.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.generator.NumberGenerator;
import lotto.domain.generator.RandomNumberGenerator;
import lotto.domain.generator.StringInputNumberGenerator;

class LottoMachineTest {

    @Test
    @DisplayName("숫자 생성기로 티켓을 발급한다.")
    public void createLottoTicketsByGenerator() {
        // given
        NumberGenerator generator = new StringInputNumberGenerator(List.of("1, 2, 3, 4, 5, 6", "7, 8, 9, 10, 11, 12"));
        int moneyAmount = 3000;
        // when
        LottoMachine lottoMachine = new LottoMachine(Money.from(moneyAmount));
        List<LottoTicket> tickets = lottoMachine.createLottos(generator, 2);

        // then
        Assertions.assertThat(tickets.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("금액이 부족하면 로또를 구매할 수 없다.")
    public void throwsExceptionIfMoneyLacks() {
        // given
        NumberGenerator generator = new StringInputNumberGenerator(List.of("1, 2, 3, 4, 5, 6", "7, 8, 9, 10, 11, 12"));
        int moneyAmount = 1000;

        // when
        LottoMachine lottoMachine = new LottoMachine(Money.from(moneyAmount));

        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> lottoMachine.createLottos(generator, 2));
    }

    @Test
    @DisplayName("로또 구매 개수가 입력된 값보다 클 수 없다.")
    public void throwsExceptionIfCountTooBig() {
        // given
        NumberGenerator generator = new StringInputNumberGenerator(List.of("1, 2, 3, 4, 5, 6", "7, 8, 9, 10, 11, 12"));
        int moneyAmount = 3000;
        // when
        LottoMachine lottoMachine = new LottoMachine(Money.from(moneyAmount));
        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> lottoMachine.createLottos(generator, 5));
    }

    @Test
    @DisplayName("금액이 다 떨어지면 로또를 구매할 수 없다.")
    public void throwsExceptionWhenIfMoneyConsumption() {
        // given
        NumberGenerator generator = new StringInputNumberGenerator(List.of("1, 2, 3, 4, 5, 6", "7, 8, 9, 10, 11, 12"));
        int moneyAmount = 2000;
        // when
        LottoMachine lottoMachine = new LottoMachine(Money.from(moneyAmount));
        lottoMachine.createLottos(generator, 2);

        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> lottoMachine.createLottos(new RandomNumberGenerator(1, 10), 1));
    }
}