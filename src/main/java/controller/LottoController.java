package controller;

import domain.*;
import domain.Lotto.Lotto;
import domain.Lotto.LottoNumberFactory;
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.LottoGenerator;
import domain.LottoGenerator.WinningLottoGenerator;
import domain.player.Money;
import domain.player.Player;
import dto.LottosDto;
import dto.RanksDto;

import java.util.List;

public class LottoController {

    private Player player;
    private WinningLotto winningLotto;

    public LottosDto purchase(int purchaseAmount) {
        player = new Player(new Money(purchaseAmount));
        player.purchaseLotto(new AutoLottoGenerator(), LottoNumberFactory.makeBoundary());
        return LottosDto.from(player.getLottos());
    }

    public void determineWinningNumber(List<String> winningNumber, int bonusBall) {
        LottoGenerator lottoGenerator = new WinningLottoGenerator();
        Lotto winningLotto = lottoGenerator.generateLotto(LottoNumberFactory.from(winningNumber));
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
