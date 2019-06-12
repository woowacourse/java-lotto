package lotto.domain.lotto;

import lotto.domain.BonusBall;
import lotto.domain.LottoNumber;
import lotto.domain.exception.InvalidBounusBallException;
import lotto.domain.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BonusBallTest {
    private static final int NUMBER_MIN = 1;
    private static final int NUMBER_MAX = 45;
    private static final int LOTTO_LENGTH = 6;

    private List<LottoNumber> winningLottos;

    @BeforeEach
    void setUp() {
        winningLottos = new ArrayList<>();
        for (int i = NUMBER_MIN; i < NUMBER_MIN + LOTTO_LENGTH ; i++) {
            winningLottos.add(new LottoNumber(i));
        }
    }

    @Test
    void 보너스볼이_객체에_잘들어_가는지() {
        assertDoesNotThrow(() -> new BonusBall(winningLottos, String.valueOf(NUMBER_MAX)));
    }

    @Test
    void 보너스볼에_문자를_입력했을때() {
        assertThrows(InvalidBounusBallException.class, () -> {
            new BonusBall(winningLottos, "a");
        });
    }

    @Test
    void 보너스볼에_1부터45까지의_수를_입력하지_않았을때() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            new BonusBall(winningLottos, String.valueOf(NUMBER_MIN - 1));
        });
        assertThrows(InvalidLottoNumberException.class, () -> {
            new BonusBall(winningLottos, String.valueOf(NUMBER_MAX + 1));
        });
    }

    @Test
    void 보너스볼에_중복된_숫자를_입력했을때() {
        assertThrows(InvalidBounusBallException.class, () -> {
            new BonusBall(winningLottos, String.valueOf(NUMBER_MIN));
        });
    }

    @AfterEach
    void tearDown() {
        winningLottos = null;
    }
}
