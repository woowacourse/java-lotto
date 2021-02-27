package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {
    private Lottos lottos;
    private Lotto lotto;
    private Set<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottos = new Lottos();
        lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::from)
                .collect(Collectors.toSet());
        lotto = new Lotto(lottoNumbers);
    }

    @Test
    void makeLotto() {
        assertThat(lotto).isEqualTo(LottoFactory.makeLotto(lottoNumbers));
    }

    @Test
    void createManualLottos() {
        LottoFactory.createManualLottos(lottos, lottoNumbers);
        assertThat(lottos.toList())
                .contains(new Lotto(lottoNumbers));
    }

    @Test
    void createAutoLottos() {
        Purchase purchase = new Purchase(new Money(2000), 0);
        LottoFactory.createAutoLottos(lottos, purchase.getAutoPurchase());
        assertThat(lottos.toList()).hasSize(2);
    }
}
