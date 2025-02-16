package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.numbergenerator.NumberGenerator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    static class FixNumberGenerator implements NumberGenerator {
        int index = 0;
        List<Integer> numbers = List.of(1, 2, 2, 3, 3, 3, 4, 5, 6);

        @Override
        public int generate() {
            return numbers.get(index++);
        }
    }

    @Test
    void 로또를_생성할_수_있다() {
        //given
        LottoMachine lottoMachine = new LottoMachine(new FixNumberGenerator(), 6);

        //when
        Lotto lotto = lottoMachine.createLotto();
        //then
        assertThat(lotto)
                .extracting("numbers")
                .isEqualTo(Set.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)
                ));
    }
}
