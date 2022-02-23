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
        List<Lotto> lottos = player.getLottos();
        return LottosDto.from(lottos);
    }

    public void determineWinningNumber(List<String> winningNumber, int bonusBall) {
        List<LottoNumber> lottoNumbers = LottoFactory.generateWinningLotto(winningNumber);
        winningLotto = new WinningLotto(lottoNumbers, new LottoNumber(bonusBall));
    }

    public RanksDto makeResult(){
        List<RankDto> rankDtos = calculateRank();
        double incomeRate = calculateIncomeRate(rankDtos);
        return new RanksDto(rankDtos,incomeRate);
    }

    private List<RankDto> calculateRank() {
        List<Result> results = player.judgeAll(winningLotto);
        Rank.calculateAllResult(results);
        List<RankDto> rankDtos = new ArrayList<>();
        int rankNumber = 5;

        for (Rank rank : Rank.values()) {
            rankDtos.add(RankDto.from(rank, rankNumber--));
        }
        return rankDtos;
    }

    private double calculateIncomeRate(List<RankDto> rankDtos) {
        double totalIncome = 0;
        for (RankDto rankDto : rankDtos) {
            totalIncome += rankDto.getReward() * rankDto.getHitCount();
        }
        return totalIncome / player.getMoney();
    }
}
