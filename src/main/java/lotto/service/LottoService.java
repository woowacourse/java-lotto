package lotto.service;

import lotto.dao.LottoDao;
import lotto.domain.*;
import lotto.domain.Number;
import lotto.dto.LottoDto;
import lotto.dto.LottoRoundDto;
import lotto.dto.ResultDto;
import lotto.dto.UserLottoDto;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private static final int FIRST_INDEX = 0;
    private static final String RESULT_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String RESULT_FORMAT = "%d개 일치 (%d원)- %d개";

    public LottoDto offerLottoInfo(int money, int manualRound, String manualNumbers, int lottoRound) {
        int round = new Money(money).getRound();
        int autoRound = getAutoRound(round, manualRound);
        String[] numbers = splitNumbers(manualNumbers);
        UserLotto userLotto = createUserLotto(numbers, autoRound);
        LottoDao.addLotto(userLotto, lottoRound);

        return new LottoDto(round, manualRound, autoRound, userLotto.getUserLotto(), numbers, lottoRound);
    }

    public UserLottoDto offerUserLottoInfo(int round, String userLottoString, int lottoRound) {
        String[] numbers = Parser.parseLottoStrings(userLottoString);
        return new UserLottoDto(round, new UserLotto(Parser.parseLotto(numbers)).getUserLotto(), lottoRound);
    }

    public ResultDto offerResults(String winNumber, String bonusNumber, String userLottoString, int round, int lottoRound) {
        Lotto results = Parser.parseWinningLotto(winNumber);
        WinningLotto winningLotto = new WinningLotto(results, Number.of(Integer.parseInt(bonusNumber)));
        String[] numbers = Parser.parseLottoStrings(userLottoString);
        UserLotto userLotto = new UserLotto(Parser.parseLotto(numbers));
        Winners winners = new Winners(winningLotto.makeRankResultList(userLotto));
        LottoDao.addWinningLotto(winningLotto, lottoRound);
        List<String> resultRanks = provideResultStatus(winners.getRankResult());
        LottoDao.addResult(resultRanks, lottoRound);
        LottoDao.addResultInfo(lottoRound, winners.getPrizeSum(), winners.calculateResultRate(round));

        return new ResultDto(resultRanks, winners.calculateResultRate(round), lottoRound);
    }

    public LottoRoundDto offerLottoRounds() {
        int lottoRound = LottoDao.offerMaxRound() + 1;
        return new LottoRoundDto(lottoRound);
    }

    private UserLotto createUserLotto(String[] numbers, int autoRound) {
        return new UserLotto(Parser.parseLotto(numbers), autoRound, new LottoNumberGenerator());
    }

    private String[] splitNumbers(String numbers) {
        if (numbers.length() == 0) {
            return new String[FIRST_INDEX];
        }
        return numbers.split("\r\n");
    }

    private int getAutoRound(int round, int manualRound) {
        return round - manualRound;
    }

    private List<String> provideResultStatus(List<Rank> ranks) {
        List<String> results = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            results.add(getResult(ranks, rank));
        }

        return results;
    }

    private String getResult(List<Rank> ranks, Rank rank) {
        if (Rank.MISS.equals(rank)) {
            return null;
        }

        if (Rank.SECOND.equals(rank)) {
            return String.format(RESULT_BONUS_FORMAT, rank.getCount(), rank.getPrize(), rank.getMatchingCount(ranks));
        }

        return String.format(RESULT_FORMAT, rank.getCount(), rank.getPrize(), rank.getMatchingCount(ranks));
    }
}
