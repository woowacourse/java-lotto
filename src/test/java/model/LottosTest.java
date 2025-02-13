package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 내가_구매한_모든_로또에_대해서_통계_확인한다() {
        Lotto winningLotto = generateTestLotto(1,2,3,4,5,6);
        Number bonus = new Number(10);
        Lottos lottos = new Lottos(Arrays.asList(
                generateTestLotto(1,2,3,6,7,8),
                generateTestLotto(10,11,12,4,5,6),
                generateTestLotto(1,2,3,4,10,11),
                generateTestLotto(1,2,3,4,5,10)
        ));

        LottoEvaluator lottoEvaluator = new LottoEvaluator(new WinningLotto(winningLotto, bonus));
        Map<Prize, Integer> result = lottoEvaluator.getResult(lottos);

        assertThat(result.get(Prize._5TH)).isEqualTo(1);
        assertThat(result.get(Prize._4TH)).isEqualTo(2);
        assertThat(result.get(Prize._2ND)).isEqualTo(1);
    }

    @Test
    void 수익률을_계산한다() {
        Lotto winningLotto = generateTestLotto(1,2,3,4,5,6);
        Number bonus = new Number(10);

        Lottos lottos = new Lottos(Arrays.asList(
                generateTestLotto(1,2,3,6,7,8),
                generateTestLotto(10,11,12,4,5,6),
                generateTestLotto(1,2,3,4,10,11),
                generateTestLotto(1,2,3,4,5,10)
        ));
        LottoEvaluator lottoEvaluator = new LottoEvaluator(new WinningLotto(winningLotto, bonus));
        assertThat(lottoEvaluator.computeProfit(lottos)).isEqualTo(7526.25);

    }

    public Lotto generateTestLotto(int ... values){
        Set<Number> numbers = new TreeSet<>();
        for (int value : values){
            numbers.add(new Number(value));
        }

        return new Lotto(numbers);
    }
}
