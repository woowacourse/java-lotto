package lotto.service;

import lotto.dao.LottoInfo;
import lotto.dao.LottoStatus;
import lotto.dao.Result;
import lotto.dao.WinningLottoInfo;
import lotto.domain.*;
import lotto.domain.Number;
import lotto.dto.*;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private static final int FIRST_INDEX = 0;
    private static final String RESULT_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String RESULT_FORMAT = "%d개 일치 (%d원)- %d개";
    private static final LottoInfo LOTTO_INFO = LottoInfo.getInstance();
    private static final LottoStatus LOTTO_STATUS = LottoStatus.getInstance();
    private static final Result RESULT = Result.getInstance();
    private static final WinningLottoInfo WINNING_LOTTO_INFO = WinningLottoInfo.getInstance();

    public LottoInfoDto offerLottoInfo(int money, int manualRound, String manualNumbers, int lottoRound) {
        int round = new Money(money).getRound();
        int autoRound = getAutoRound(round, manualRound);
        String[] numbers = splitNumbers(manualNumbers);
        UserLotto userLotto = createUserLotto(numbers, autoRound);
        RoundDto roundDto = new RoundDto(round, manualRound, autoRound, lottoRound);

        LOTTO_INFO.addLotto(userLotto, lottoRound);

        return new LottoInfoDto(roundDto, userLotto.getUserLotto(), numbers);
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

        WINNING_LOTTO_INFO.addWinningLotto(winningLotto, roundInfoDto.getLottoRound());

        List<String> resultRanks = provideResultStatus(winners.getRankResult());

        RESULT.addResult(resultRanks, roundInfoDto.getLottoRound());
        LOTTO_STATUS.addResultInfo(roundInfoDto.getLottoRound(), winners.getPrizeSum(), winners.calculateResultRate(roundInfoDto.getRound()));

        return new ResultDto(resultRanks, winners.calculateResultRate(roundInfoDto.getRound()), roundInfoDto.getLottoRound());
    }

    public StatusDto offerHitsStatus(int lottoRound) {
        List<String> userLotto = LOTTO_INFO.offerUserLottoNumber(lottoRound);
        String winningNumber = WINNING_LOTTO_INFO.offerWinningNumber(lottoRound);
        int bonus = WINNING_LOTTO_INFO.offerBonusNumber(lottoRound);
        List<String> results = RESULT.offerResults(lottoRound);
        String returnRate = String.valueOf(LOTTO_STATUS.offerReturnRate(lottoRound));
        int prize = LOTTO_STATUS.offerPrize(lottoRound);

        return new StatusDto(lottoRound, userLotto, winningNumber, bonus, results, returnRate, prize);
    }

    public LottoRoundDto offerLottoRounds() {
        int lottoRound = LOTTO_INFO.offerMaxRound() + 1;
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
