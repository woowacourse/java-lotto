package lotto;

import domain.Lotto;
import domain.LottoFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {
    @Test
    void 생성된_로또가_6개의_숫자로_이루어져있는지_테스트() {
        List<Lotto> myLotto = LottoFactory.createLottoTickets(1);
        assertThat(myLotto.get(0).getSize()).isEqualTo(6);
    }
}
