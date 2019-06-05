package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {
    @Test
    public void 구입금액과_수동로또로_로또를_잘_만드는지_확인() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        Lotto lotto = new Lotto(lottoNumbers);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            lottos.add(lotto);
        }
        LottoTickets lottoTickets = new LottoTickets(lottos);

        assertThat(LottoMachine.generateTickets(new Money(3000), lottos)).isEqualTo(lottoTickets);
    }
}
