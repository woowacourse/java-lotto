package lotto.service;

import lotto.dao.LottoDao;
import lotto.dao.LottoStatusDao;
import lotto.dao.ResultDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.*;
import lotto.domain.Number;
import lotto.dto.*;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private static final int FIRST_INDEX = 0;
    private static final String RESULT_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String RESULT_FORMAT = "%d개 일치 (%d원)- %d개";
    private static final LottoDao lottoDao = LottoDao.getInstance();
    private static final LottoStatusDao lottoStatusDao = LottoStatusDao.getInstance();
    private static final ResultDao resultDao = ResultDao.getInstance();
    private static final WinningLottoDao winningLottoDao = WinningLottoDao.getInstance();

    public LottoDto offerLottoInfo(int money, int manualRound, String manualNumbers, int lottoRound) {
        int round = new Money(money).getRound();
        int autoRound = getAutoRound(round, manualRound);
        String[] numbers = splitNumbers(manualNumbers);
        UserLotto userLotto = createUserLotto(numbers, autoRound);

        lottoDao.addLotto(userLotto, lottoRound);

        return new LottoDto(round, manualRound, autoRound, userLotto.getUserLotto(), numbers, lottoRound);
    }

    public UserLottoDto offerUserLottoInfo(int round, String userLottoString, int lottoRound) {
        String[] numbers = Parser.parseLottoStrings(userLottoString);
        return new UserLottoDto(round, new UserLotto(Parser.parseLotto(numbers)).getUserLotto(), lottoRound);
    }

    public ResultDto offerResults(RoundInfoDto roundInfoDto) {
        Lotto results = Parser.parseWinningLotto(roundInfoDto.getWinNumber());
        WinningLotto winningLotto = new WinningLotto(results, Number.of(Integer.parseInt(roundInfoDto.getBonusNumber())));
        String[] numbers = Parser.parseLottoStrings(roundInfoDto.getUserLottoString());
        UserLotto userLotto = new UserLotto(Parser.parseLotto(numbers));
        Winners winners = new Winners(winningLotto.makeRankResultList(userLotto));

        winningLottoDao.addWinningLotto(winningLotto, roundInfoDto.getLottoRound());

        List<String> resultRanks = provideResultStatus(winners.getRankResult());

        resultDao.addResult(resultRanks, roundInfoDto.getLottoRound());
        lottoStatusDao.addResultInfo(roundInfoDto.getLottoRound(), winners.getPrizeSum(), winners.calculateResultRate(roundInfoDto.getRound()));

        return new ResultDto(resultRanks, winners.calculateResultRate(roundInfoDto.getRound()), roundInfoDto.getLottoRound());
    }

    public StatusDto offerHitsStatus(int lottoRound) {
        List<String> userLotto = lottoDao.offerUserLottoNumber(lottoRound);
        String winningNumber = winningLottoDao.offerWinningNumber(lottoRound);
        int bonus = winningLottoDao.offerBonusNumber(lottoRound);
        List<String> results = resultDao.offerResults(lottoRound);
        String returnRate = String.valueOf(lottoStatusDao.offerReturnRate(lottoRound));
        int prize = lottoStatusDao.offerPrize(lottoRound);

        return new StatusDto(lottoRound, userLotto, winningNumber, bonus, results, returnRate, prize);
    }

    public LottoRoundDto offerLottoRounds() {
        int lottoRound = lottoDao.offerMaxRound() + 1;
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
