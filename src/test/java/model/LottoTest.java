package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 로또의_로또_번호는_6개이다() {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(Set.of(
                    new Number(1),
                    new Number(2),
                    new Number(3),
                    new Number(4),
                    new Number(5),
                    new Number(6)
            ));
        });
    }

    @Test
    void 로또의_등수를_계산한다() {
        Set<Number> numbers = new TreeSet<>();
        numbers.add(new Number(1));
        numbers.add(new Number(2));
        numbers.add(new Number(3));
        numbers.add(new Number(4));
        numbers.add(new Number(5));
        numbers.add(new Number(6));
        Lotto winningLotto = new Lotto(numbers);

        Set<Number> numbers2 = new TreeSet<>();
        numbers2.add(new Number(7));
        numbers2.add(new Number(8));
        numbers2.add(new Number(3));
        numbers2.add(new Number(4));
        numbers2.add(new Number(5));
        numbers2.add(new Number(6));
        Lotto lotto = new Lotto(numbers2);

        WinningLotto winningLotto1 = new WinningLotto(winningLotto, new Number(19));
        Prize prize = lotto.calculatePrize(winningLotto1).get();
        assertThat(prize).isEqualTo(Prize._4TH);
    }

    @Test
    void 보너스_번호가_포함된_로또의_등수를_계산한다() {
        Set<Number> numbers = new TreeSet<>();
        numbers.add(new Number(1));
        numbers.add(new Number(8));
        numbers.add(new Number(3));
        numbers.add(new Number(4));
        numbers.add(new Number(5));
        numbers.add(new Number(6));
        Lotto winningLotto = new Lotto(numbers);

        Set<Number> numbers2 = new TreeSet<>();
        numbers2.add(new Number(7));
        numbers2.add(new Number(8));
        numbers2.add(new Number(3));
        numbers2.add(new Number(4));
        numbers2.add(new Number(5));
        numbers2.add(new Number(6));
        Lotto lotto = new Lotto(numbers2);

        WinningLotto winningLotto1 = new WinningLotto(winningLotto, new Number(7));
        Prize prize = lotto.calculatePrize(winningLotto1).get();
        assertThat(prize).isEqualTo(Prize._2ND);
    }
}
