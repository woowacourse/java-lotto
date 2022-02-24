package controller;

import domain.*;
import dto.LottosDto;
import dto.RankDto;
import dto.RanksDto;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private Player player;
    private WinningLotto winningLotto;

    public LottosDto purchase(int purchaseAmount) {
        player = new Player(new Money(purchaseAmount));
        player.purchaseLotto(new AutoLottoGenerator(), LottoFactory.makeBoundary());
        return LottosDto.from(player.getLottos());
    }

    public void determineWinningNumber(List<String> winningNumber, int bonusBall) {
        LottoGenerator lottoGenerator = new WinningLottoGenerator();
        Lotto winningLotto = lottoGenerator.generateLotto(LottoFactory.from(winningNumber));
        this.winningLotto = new WinningLotto(winningLotto, new LottoNumber(bonusBall));
    }

    public List<Result> judgeLottos() {
        return player.judgeAll(winningLotto);
    }

    public RanksDto makeResult(List<Result> judgeLottos) {
        double totalIncome = Rank.calculateAllResult(judgeLottos);
        double incomeRate = player.calculateIncomeRate(totalIncome);

        return new RanksDto(incomeRate);
    }
}
