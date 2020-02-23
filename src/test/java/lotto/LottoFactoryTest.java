package lotto;

import domain.Lotto;
import domain.LottoFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {
    @Test
    void 생성된_로또가_6개의_숫자로_이루어져있는지_테스트() {
        Lotto myLotto = LottoFactory.createOneLotto();
        assertThat(myLotto.getLotto().size()).isEqualTo(6);
    }
}
