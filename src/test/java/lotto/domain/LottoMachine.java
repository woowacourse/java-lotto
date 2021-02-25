package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachine {
    Set<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {
        LottoNumber lotto1 = new LottoNumber(1);
        LottoNumber lotto2 = new LottoNumber(2);
        LottoNumber lotto3 = new LottoNumber(3);
        LottoNumber lotto4 = new LottoNumber(4);
        LottoNumber lotto5 = new LottoNumber(5);
        LottoNumber lotto6 = new LottoNumber(6);
        lottoNumbers = new HashSet<>(Arrays.asList(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6));
    }

    @Test
    void createLottos() {
        List<Lotto> lottoTickets = Arrays.asList(new Lotto(lottoNumbers));
        Lottos lottos = LottoMachine.createLotto(purchase, lottoTickets);
        assertThat(lottos).isEqualTo(new Lottos(lottoTickets));
    }
}
