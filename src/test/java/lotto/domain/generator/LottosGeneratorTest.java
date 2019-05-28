package lotto.domain.generator;

import lotto.domain.Lotto;
import lotto.view.InputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottosGeneratorTest {
    InputView inputView;

    @BeforeEach
    void setUp() {
        inputView = new InputView() {
            @Override
            public long inputMoney() {
                return 0;
            }

            @Override
            public int inputCountOfManual() {
                return 0;
            }

            @Override
            public String inputManual() {
                return "8, 21, 23, 41, 42, 43";
            }

            @Override
            public String inputWinningLotto() {
                return null;
            }

            @Override
            public void printInputManual() {

            }

            @Override
            public int inputBonusNo() {
                return 0;
            }
        };

    }

    @Test
    void 유효성_에러_확인() {
        assertThrows(IllegalArgumentException.class,
                () -> LottosGenerator.of(4, 3, inputView).generate());
    }

    @Test
    void 개수_맞는지_확인() {
        int countOfPurchase = 100;
        List<Lotto> lottos = LottosGenerator.of(50, countOfPurchase, inputView).generate();
        assertThat(countOfPurchase).isEqualTo(lottos.size());
    }
}