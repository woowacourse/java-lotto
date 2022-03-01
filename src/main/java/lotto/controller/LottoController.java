package lotto.controller;

import static lotto.constants.ErrorConstants.ERROR_NULL_MESSAGE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResults;
import lotto.domain.Money;
import lotto.domain.WinningLotto;

public class LottoController {

    private LottoGame lottoGame;

    public void inputMoney(int money) {
        lottoGame = new LottoGame(new Money(money));
    }

    public List<Lotto> purchase(List<List<Integer>> manualNumbers) {
        Objects.requireNonNull(manualNumbers, ERROR_NULL_MESSAGE);
        Objects.requireNonNull(lottoGame, ERROR_NULL_MESSAGE);

        lottoGame.purchase(manualNumbers);
        return lottoGame.getLottos();
    }

    public LottoResults requestLottoResults(List<Integer> winningNumbers, int bonusNumber) {
        Objects.requireNonNull(winningNumbers, ERROR_NULL_MESSAGE);
        Objects.requireNonNull(lottoGame, ERROR_NULL_MESSAGE);

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
