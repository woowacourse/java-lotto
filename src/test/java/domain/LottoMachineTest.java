package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.numberstrategy.NumberPickStrategy;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    static class FixNumberPickStrategy implements NumberPickStrategy {
        int index = 0;
        List<Integer> numbers = List.of(1, 2, -1, 46, 2, 3, 3, 3, 4, 5, 6);

        @Override
        public int pickNumber(int min, int max) {
            while (numbers.get(index) < min || numbers.get(index) > max) {
                index++;
            }
            return numbers.get(index++);
        }
    }

    @Test
    void 로또를_생성할_수_있다() {
        //given
        LottoMachine lottoMachine = new LottoMachine(new FixNumberPickStrategy());

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
