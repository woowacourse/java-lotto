package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lottogeneratestrategy.LottoPickStrategy;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @Test
    void 로또를_생성할_수_있다() {
        //given
        LottoPickStrategy fixNumberStrategy = (int maxNumber, int size) -> List.of(1, 2, 3, 4, 5, 6);
        LottoMachine lottoMachine = new LottoMachine(fixNumberStrategy);

        //when
        Lotto lotto = lottoMachine.createLotto();

        //then
        assertThat(lotto)
                .extracting("numbers")
                .isEqualTo(Set.of(
                        new Number(1), new Number(2), new Number(3), new Number(4), new Number(5), new Number(6)
                ));
    }
}
