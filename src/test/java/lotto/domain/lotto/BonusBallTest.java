package lotto.domain.lotto;

import lotto.domain.InvalidBounusBall;
import lotto.domain.InvalidLottoNumberException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BonusBallTest {

    private Lotto winningLotto;

    @BeforeEach
    void setUp() {
        List<LottoNumber> lottos = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            lottos.add(new LottoNumber(i));
        }
        winningLotto = new Lotto(lottos);
    }

    @Test
    void 보너스볼이_객체에_잘들어_가는지() {
        BonusBall bonusBall = new BonusBall(winningLotto, "45");
        assertThat(bonusBall).isEqualTo(new BonusBall(winningLotto, "45"));
    }

    @Test
    void 보너스볼에_문자를_입력했을때() {
        assertThrows(InvalidBounusBall.class, () -> {
            new BonusBall(winningLotto, "a");
        });
    }

    @Test
    void 보너스볼에_1부터45까지의_수를_입력하지_않았을때() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            new BonusBall(winningLotto, "0");
        });
        assertThrows(InvalidLottoNumberException.class, () -> {
            new BonusBall(winningLotto, "46");
        });
    }

    @Test
    void 보너스볼에_중복된_숫자를_입력했을때() {
        assertThrows(InvalidBounusBall.class, () -> {
            new BonusBall(winningLotto, "6");
        });
    }

    @AfterEach
    void tearDown() {
        winningLotto = null;
    }
}
