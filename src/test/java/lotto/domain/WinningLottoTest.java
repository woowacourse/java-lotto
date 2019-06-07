package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    List<Number> lottos;
    Lotto lotto;
    WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        lottos = new ArrayList<>();

        for (int i = 1; i < 7; i++) {
            lottos.add(new Number(i));
        }

        lotto = new Lotto(lottos);
        winningLotto = WinningLotto.create("9,10,11,12,13,14", new Number(4));
    }

    @Test
    void 당첨번호와_중복() {

        assertThrows(IllegalArgumentException.class, () -> {
            WinningLotto.create("1,2,3,4,5,6", new Number(4));
        });
    }

    @Test
    void 보너스가_당첨번호에_있는지() {
        assertThat(winningLotto.matchBonus(lotto)).isTrue();
    }

    @Test
    void 당첨번호와_일치하는_내_로또와의_갯수() {
        assertThat(winningLotto.match(lotto)).isEqualTo(0);
    }

    @Test
    void 당첨번호와_일치하는_내_로또와의_갯수2() {
        lottos.set(3, new Number(13));
        assertThat(winningLotto.match(lotto)).isEqualTo(1);
    }
}
