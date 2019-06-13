package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    private Lotto lotto;
    private WinningLotto winningLotto;
    private UserLotto userLotto;

    @BeforeEach
    void setUp() {
        List<Number> lottoNumber = new ArrayList<>();

        for (int i = 1; i < 7; i++) {
            lottoNumber.add(Number.of(i));
        }

        lotto = new Lotto(lottoNumber);
        winningLotto = new WinningLotto(lotto, Number.of(9));

        List<Lotto> allLotto = new ArrayList<>();
        allLotto.add(new Lotto(lottoNumber));
        allLotto.add(new Lotto(lottoNumber));

        userLotto = new UserLotto(allLotto, 4, new LottoNumberGenerator());
    }

    @Test
    void 당첨번호와_중복() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WinningLotto(lotto, Number.of(4));
        });
    }

    @Test
    void 보너스가_당첨번호에_있는지() {
        assertThat(winningLotto.matchBonus(lotto)).isFalse();
    }

    @Test
    void 당첨번호와_일치하는_내_로또와의_갯수() {
        assertThat(winningLotto.match(lotto)).isEqualTo(6);
    }

    @Test
    void 결과리스트() {
        assertThat(winningLotto.makeRankResultList(userLotto)).isEqualTo(winningLotto.makeRankResultList(userLotto));
    }
}
