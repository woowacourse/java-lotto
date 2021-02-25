package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {
    private Lottos lottos;
    private Lotto lotto;
    private Set<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottos = new Lottos();

        LottoNumber lotto1 = new LottoNumber(1);
        LottoNumber lotto2 = new LottoNumber(2);
        LottoNumber lotto3 = new LottoNumber(3);
        LottoNumber lotto4 = new LottoNumber(4);
        LottoNumber lotto5 = new LottoNumber(5);
        LottoNumber lotto6 = new LottoNumber(6);
        lottoNumbers = new HashSet<>(Arrays.asList(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6));
        lotto = new Lotto(lottoNumbers);
    }

    @Test
    void createManualLottos() {
        lottos = LottoFactory.createManualLottos(lottos, Arrays.asList(lotto));
        assertThat(lottos.toList())
                .contains(new Lotto(lottoNumbers));
    }

    @Test
    void createAutoLottos() {
        Purchase purchase = new Purchase(new Money(2000), 0);
        lottos = LottoFactory.createAutoLottos(lottos, purchase);
        assertThat(lottos.toList().size()).isEqualTo(2);
    }
}
