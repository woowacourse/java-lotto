package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResults;
import lotto.domain.Money;
import lotto.domain.WinningLotto;

public class LottoController {

    private final LottoGame lottoGame;

    public LottoController(int money) {
        lottoGame = new LottoGame(new Money(money));
    }

    public List<Lotto> purchase(List<List<Integer>> manualNumbers) {
        lottoGame.purchase(manualNumbers);
        return lottoGame.getLottos();
    }

    public LottoResults requestLottoResults(List<Integer> winningNumbers, int bonusNumber) {
        WinningLotto winningLotto = requestWinningNumbers(winningNumbers, bonusNumber);
        return lottoGame.confirmWinnings(winningLotto);
    }

    private WinningLotto requestWinningNumbers(List<Integer> winningNumbers, int rawBonusNumber) {
        List<LottoNumber> winningLottoNumbers = new ArrayList<>();
        for (Integer number : winningNumbers) {
            winningLottoNumbers.add(new LottoNumber(number));
        }
        LottoNumber bonusLottoNumber = new LottoNumber(rawBonusNumber);

        return new WinningLotto(new Lotto(winningLottoNumbers), bonusLottoNumber);
    }

}
