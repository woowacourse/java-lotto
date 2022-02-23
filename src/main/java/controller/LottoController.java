package controller;

import domain.*;
import dto.LottosDto;

import java.util.List;

public class LottoController {

    private Player player;
    private WinningLotto winningLotto;

    public LottosDto purchase(int purchaseAmount) {
        player = new Player(new Money(purchaseAmount));
        List<Lotto> lottos = player.getLottos();
        return LottosDto.from(lottos);
    }

    public void determineWinningNumber(List<String> winningNumber, int bonusBall) {
        List<LottoNumber> lottoNumbers = LottoFactory.generateWinningLotto(winningNumber);
        winningLotto = new WinningLotto(lottoNumbers, new LottoNumber(bonusBall));
    }

    public void calculateRank() {
        List<Result> results = player.judgeAll(winningLotto);
        Rank.calculateAllResult(results);
        for (Rank value : Rank.values()) {
            System.out.println(value.getCriteria());
            System.out.println(value.getHitCount());
            System.out.println(value.getReward());
            System.out.println("----------");
        }
    }
}
