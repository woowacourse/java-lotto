package lotto.service;

import lotto.domain.*;
import lotto.domain.Number;
import lotto.dto.LottoDto;
import lotto.dto.ResultDto;
import lotto.dto.UserLottoDto;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private static final int FIRST_INDEX = 0;
    private static final String RESULT_BONUS_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String RESULT_FORMAT = "%d개 일치 (%d원)- %d개";

    public LottoDto offerLottoInfo(int money, int manualRound, String manualNumbers) {
        int round = new Money(money).getRound();
        int autoRound = getAutoRound(round, manualRound);
        String[] numbers = splitNumbers(manualNumbers);
        UserLotto userLotto = createUserLotto(numbers, autoRound);

        return new LottoDto(round, manualRound, autoRound, userLotto.getUserLotto(), numbers);
    }

    public UserLottoDto offerUserLottoInfo(int round, String userLottoString) {
        String[] numbers = Parser.parseLottoStrings(userLottoString);
        return new UserLottoDto(round, new UserLotto(Parser.parseLotto(numbers)).getUserLotto());
    }

    public ResultDto offerResults(String winNumber, String bonusNumber, String userLottoString, int round) {
        Lotto results = Parser.parseWinningLotto(winNumber);
        WinningLotto winningLotto = new WinningLotto(results, Number.of(Integer.parseInt(bonusNumber)));
        String[] numbers = Parser.parseLottoStrings(userLottoString);
        UserLotto userLotto = new UserLotto(Parser.parseLotto(numbers));
        Winners winners = new Winners(winningLotto.makeRankResultList(userLotto));
        List<String> resultRanks = provideResultStatus(winners.getRankResult());

        return new ResultDto(resultRanks, winners.calculateResultRate(round));
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

    public static List<String> provideResultStatus(List<Rank> ranks) {
        List<String> results = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            results.add(getResult(ranks, rank));
        }

        return results;
    }

    private static String getResult(List<Rank> ranks, Rank rank) {
        if (Rank.MISS.equals(rank)) {
            return null;
        }

        if (Rank.SECOND.equals(rank)) {
            return String.format(RESULT_BONUS_FORMAT, rank.getCount(), rank.getPrize(), rank.getMatchingCount(ranks));
        }

        return String.format(RESULT_FORMAT, rank.getCount(), rank.getPrize(), rank.getMatchingCount(ranks));
    }
}
