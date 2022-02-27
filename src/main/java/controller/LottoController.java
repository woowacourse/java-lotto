package controller;

import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.LottoGenerator;
import domain.LottoGenerator.WinningLottoGenerator;
import domain.Rank;
import domain.Result;
import domain.player.Player;
import dto.LottosDto;
import dto.RanksDto;

import java.util.List;

public class LottoController {

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
        LottoGenerator lottoGenerator = new WinningLottoGenerator();
        Lotto winningLotto = lottoGenerator.generateWinningLotto(winningNumbers);

        this.winningLotto = new WinningLotto(winningLotto, LottoNumber.valueOf(bonusBall));
    }

    public List<Result> judgeLottos() {
        return player.judgeAll(winningLotto);
    }

    public RanksDto makeResult(List<Result> judgeLottos) {
        double totalIncome = Rank.calculateAllResult(judgeLottos);
        System.out.println(totalIncome);
        double incomeRate = player.calculateIncomeRate(totalIncome);
        System.out.println("incomeRate = " + incomeRate);

        return RanksDto.from(incomeRate);
    }
}
