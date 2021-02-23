package lottogame.domain.stats;

import lottogame.domain.lotto.*;
import lottogame.view.OutputView;
import lottogame.domain.Money;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class LottoResultTest {
    private WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");
    private Lottos lottos;
    private List<Integer> values;

    @Test
    void 로또_결과_테스트() {
        List<Lotto> list = new ArrayList<>();
        values = Arrays.asList(8, 21, 23, 41, 42, 43);
        list.add(new Lotto(makeLottoNumberList(values)));
        values = Arrays.asList(3, 5, 11, 16, 32, 38);
        list.add(new Lotto(makeLottoNumberList(values)));
        values = Arrays.asList(7, 11, 16, 35, 36, 44);
        list.add(new Lotto(makeLottoNumberList(values)));
        values = Arrays.asList(1, 8, 11, 31, 41, 42);
        list.add(new Lotto(makeLottoNumberList(values)));
        values = Arrays.asList(13, 14, 16, 38, 42, 45);
        list.add(new Lotto(makeLottoNumberList(values)));
        values = Arrays.asList(7, 11, 30, 40, 42, 43);
        list.add(new Lotto(makeLottoNumberList(values)));
        values = Arrays.asList(2, 13, 22, 32, 38, 45);
        list.add(new Lotto(makeLottoNumberList(values)));
        values = Arrays.asList(23, 25, 33, 36, 39, 41);
        list.add(new Lotto(makeLottoNumberList(values)));
        values = Arrays.asList(1, 3, 5, 14, 22, 45);
        list.add(new Lotto(makeLottoNumberList(values)));
        values = Arrays.asList(5, 9, 38, 41, 43, 44);
        list.add(new Lotto(makeLottoNumberList(values)));
        values = Arrays.asList(2, 8, 9, 18, 19, 21);
        list.add(new Lotto(makeLottoNumberList(values)));
        values = Arrays.asList(13, 14, 18, 21, 23, 35);
        list.add(new Lotto(makeLottoNumberList(values)));
        values = Arrays.asList(17, 21, 29, 37, 42, 45);
        list.add(new Lotto(makeLottoNumberList(values)));
        values = Arrays.asList(3, 8, 27, 30, 35, 44);
        list.add(new Lotto(makeLottoNumberList(values)));
        lottos = new Lottos(list);
        LottoGame lottoGame = new LottoGame(lottos, new Money("14000"));
        OutputView.printResult(lottoGame.Results(winningLotto));
    }

    List<LottoNumber> makeLottoNumberList(List<Integer> numbers) {
        return numbers.stream().map(number -> new LottoNumber(number)).collect(Collectors.toList());
    }
}
