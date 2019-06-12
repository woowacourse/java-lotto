package lotto.domain.creator;

import lotto.domain.lotto.Lotto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AutoLottoCreatorTest {
    @Test
    void 랜덤으로_로또가_잘_생성되는지_확인() {
        LottoCreator creator = new AutoLottoCreator();

        Lotto lotto1 = creator.createLotto();
        Lotto lotto2 = creator.createLotto();
        assertFalse(lotto1.getNumbers().containsAll(lotto2.getNumbers()));
    }
}
