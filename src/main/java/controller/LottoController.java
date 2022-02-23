package controller;

<<<<<<< HEAD
import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.LottoGenerator;
import domain.LottoGenerator.ManualLottoGenerator;
import domain.Result;
import domain.ResultStatus;
import domain.player.Player;
import dto.LottosDto;
import dto.RanksDto;
=======
import domain.*;
import dto.LottosDto;
>>>>>>> 6a49a84 (feat: 로또 구매 및 당첨번호 세팅 기능 구현)

import java.util.List;

public class LottoController {
<<<<<<< HEAD

    private Player player;
    private WinningLotto winningLotto;

    public LottosDto purchase(int purchaseAmount) {
        player = new Player(purchaseAmount);
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
=======
    public LottosDto purchase(int purchaseAmount) {
        Player player = new Player(new Money(purchaseAmount));
        List<Lotto> lottos = player.getLottos();
        return LottosDto.from(lottos);
    }

    public void determineWinningNumber(List<String> winningNumber, int bonusBall) {
        List<LottoNumber> lottoNumbers = LottoFactory.generateWinningLotto(winningNumber);
        WinningLotto winningLotto = new WinningLotto(lottoNumbers, new LottoNumber(bonusBall));
>>>>>>> 6a49a84 (feat: 로또 구매 및 당첨번호 세팅 기능 구현)
    }
}
