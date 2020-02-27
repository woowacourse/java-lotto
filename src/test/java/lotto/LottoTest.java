package lotto;

import domain.Lotto;
import domain.LottoFactory;
import domain.RandomNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    void 로또_번호_갯수_6개인지_확인(){
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        List<Lotto> lotto = LottoFactory.createLottoTickets(1, randomNumberGenerator);
        assertThat(lotto.get(0).getSize()).isEqualTo(6);

        assertThatThrownBy(() -> {
            Lotto.checkLottoSizeSix(7);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또의 번호는 6개의 숫자로 이루어져 있어야 합니다.");
    }
}
