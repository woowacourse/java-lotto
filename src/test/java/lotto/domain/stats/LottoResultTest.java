package lotto.domain.stats;

import lotto.View.OutputView;
import lotto.domain.Money;
import lotto.domain.lottoData.Lotto;
import lotto.domain.lottoData.Lottos;
import lotto.domain.lottoData.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoResultTest {
    WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");
    Lottos lottos;
    List<Integer> values;

    public LottoResultTest() {
        List<Lotto> list = new ArrayList<>();
//        testCase1(list);
        testCase2(list);
        lottos = new Lottos(list);
    }

    private void testCase2(List<Lotto> list) {
        list.clear();
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
    }

    private void testCase1(List<Lotto> list) {
        values = Arrays.asList(1, 2, 3, 4, 45, 44);     // 50000
        list.add(new Lotto(values));
        values = Arrays.asList(1, 2, 3, 43, 44, 45);    // 5000
        list.add(new Lotto(values));
        values = Arrays.asList(1, 2, 3, 4, 5, 7);   // 30000000
        list.add(new Lotto(values));
        values = Arrays.asList(1, 2, 3, 4, 5, 44);  // 1500000
        list.add(new Lotto(values));
    }

    @Test
    void 출력_테스트() {
        OutputView.printResult(lottos.findMatchLottos(winningLotto, new Money("14000")));
    }
}
