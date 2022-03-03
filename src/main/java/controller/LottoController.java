package controller;

import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.LottoGenerator;
import domain.LottoGenerator.ManualLottoGenerator;
import domain.Result;
import domain.ResultStatus;
import domain.player.Player;
import dto.LottoCountDto;
import dto.LottosDto;
import dto.RanksDto;

import java.util.List;

public class LottoController {

    private Player player;
    private WinningLotto winningLotto;

    public LottoCountDto selectLottoCount(int purchaseAmount, int ManualLottoCount) {
        player = new Player(purchaseAmount);
        return player.selectLottoCount(ManualLottoCount);
    }

    public void purchaseManualLotto(List<List<Integer>> manualNumber) {
        LottoGenerator lottoGenerator = new ManualLottoGenerator();
        for (List<Integer> numbers : manualNumber) {
            Lotto lotto = lottoGenerator.generateLotto(numbers);
            player.purchaseLotto(lotto);
        }
    }

    public LottosDto purchase() {
        LottoGenerator lottoGenerator = new AutoLottoGenerator();
        while (player.canBuyLotto()) {
            player.purchaseLotto(lottoGenerator.generateLotto());
        }
        return LottosDto.from(player.getLottos());
    }

    public void determineWinningNumber(List<Integer> winningNumbers, int bonusBall) {
        LottoGenerator lottoGenerator = new ManualLottoGenerator();
        Lotto winningLotto = lottoGenerator.generateLotto(winningNumbers);
        this.winningLotto = new WinningLotto(winningLotto, LottoNumber.valueOf(bonusBall));
    }

    public RanksDto makeResult() {
        List<Result> results = player.judgeAll(winningLotto);
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.judgeResult(results);
        double totalIncome = resultStatus.calculateTotalIncome();
        double incomeRate = player.calculateIncomeRate(totalIncome);
        return RanksDto.from(resultStatus, incomeRate);
    }
}
