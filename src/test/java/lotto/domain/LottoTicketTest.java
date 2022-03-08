package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.generator.NumberGenerator;
import lotto.domain.generator.RandomNumberGenerator;

class LottoTicketTest {

    @Test
    @DisplayName("숫자생성기와 금액으로 로또를 생성한다.")
    public void createLottoTicket() {
        // given
        NumberGenerator generator = new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX);
        Money money = Money.from(2000);
        // when
        LottoTicket lottoTicket = LottoTicket.createLottoTicket(generator, money);
        // then
        Assertions.assertThat(lottoTicket.getLines().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("금액이 부족하면 로또를 구매할 수 없다.")
    public void throwsExceptionWhenMoneyLacks() {
        // given
        NumberGenerator generator = new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX);
        Money money = Money.from(999);
        // when
        Assertions.assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(() -> LottoTicket.createLottoTicket(generator, money));
        // then
    }
}