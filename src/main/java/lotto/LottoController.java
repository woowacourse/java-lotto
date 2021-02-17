package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {
    private static final String DELIMITER = ",";
    private static Lottos lottos;

    public void startLotto() {
        lottos = new Lottos(LottoView.requestMoney());
        LottoView.buyLotto(lottos.getCount());
        lottos.generateLottos();
        LottoView.printLottos(lottos);
        Lotto winLotto = new Lotto(changeToArrayList());
    }

    private ArrayList<Integer> changeToArrayList() {
        String numberInput = LottoView.requestWinningNumber();
        List<Integer> winningNumbers = Arrays.stream(numberInput.split(DELIMITER, -1))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        ArrayList<Integer> winningNums = new ArrayList<Integer>();
        winningNums.addAll(winningNumbers);
        return winningNums;
    }
}
