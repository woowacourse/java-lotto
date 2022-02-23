package controller;

import domain.*;
import dto.LottosDto;
import dto.RankDto;

import java.util.ArrayList;
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

    public List<RankDto> calculateRank() {
        List<Result> results = player.judgeAll(winningLotto);
        Rank.calculateAllResult(results);
        List<RankDto> rankDtos = new ArrayList<>();
        int rankNumber = 5;
        for (Rank rank : Rank.values()) {
            rankDtos.add(RankDto.from(rank,rankNumber--));
        }
        return rankDtos;
    }
}
