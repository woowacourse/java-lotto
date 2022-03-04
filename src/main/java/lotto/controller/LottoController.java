package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResults;
import lotto.domain.WinningLotto;
import lotto.utils.LottoGenerateStrategy;

public class LottoController {

    public List<Lotto> purchase(LottoGame lottoGame, List<List<Integer>> manualNumbers,
                                LottoGenerateStrategy lottoGenerateStrategy) {
        lottoGame.purchase(manualNumbers, lottoGenerateStrategy);
        return lottoGame.getLottos();
    }

    public LottoResults requestLottoResults(LottoGame lottoGame, List<Integer> winningNumbers, int bonusNumber) {
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
