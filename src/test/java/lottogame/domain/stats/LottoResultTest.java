package lottogame.domain.stats;

import lottogame.view.OutputView;
import lottogame.domain.Money;
import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.Lottos;
import lottogame.domain.lotto.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LottoResultTest {
    private WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");
    private Lottos lottos;
    private List<Integer> values;

    @Test
    void 로또_결과_테스트() {
        List<Lotto> list = new ArrayList<>();
        values = Arrays.asList(8, 21, 23, 41, 42, 43);
        list.add(new Lotto(values));
        values = Arrays.asList(3, 5, 11, 16, 32, 38);
        list.add(new Lotto(values));
        values = Arrays.asList(7, 11, 16, 35, 36, 44);
        list.add(new Lotto(values));
        values = Arrays.asList(1, 8, 11, 31, 41, 42);
        list.add(new Lotto(values));
        values = Arrays.asList(13, 14, 16, 38, 42, 45);
        list.add(new Lotto(values));
        values = Arrays.asList(7, 11, 30, 40, 42, 43);
        list.add(new Lotto(values));
        values = Arrays.asList(2, 13, 22, 32, 38, 45);
        list.add(new Lotto(values));
        values = Arrays.asList(23, 25, 33, 36, 39, 41);
        list.add(new Lotto(values));
        values = Arrays.asList(1, 3, 5, 14, 22, 45);
        list.add(new Lotto(values));
        values = Arrays.asList(5, 9, 38, 41, 43, 44);
        list.add(new Lotto(values));
        values = Arrays.asList(2, 8, 9, 18, 19, 21);
        list.add(new Lotto(values));
        values = Arrays.asList(13, 14, 18, 21, 23, 35);
        list.add(new Lotto(values));
        values = Arrays.asList(17, 21, 29, 37, 42, 45);
        list.add(new Lotto(values));
        values = Arrays.asList(3, 8, 27, 30, 35, 44);
        list.add(new Lotto(values));
        lottos = new Lottos(list);
        OutputView.printResult(lottos.findMatchLottos(winningLotto, new Money("14000")));
    }
}
