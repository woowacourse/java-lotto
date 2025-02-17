package lotto.service;


import static lotto.domain.Rank.calculateTotalPrize;
import static lotto.domain.Rank.checkPrize;
import static lotto.domain.Rank.makeDefaultMap;
import static lotto.utils.NumberGenerator.numberGeneratorWithUniqueValues;
import static lotto.utils.Parser.parseToInteger;
import static lotto.utils.Parser.parseToIntegers;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import lotto.domain.AmountPaid;
import lotto.domain.Lotto;
import lotto.domain.LottoBundle;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.utils.Splitter;

public class LottoManager {

    private static final int MAKE_QUANTITY = 6;
    private static final int MAX_VALUE = 45;
    private static final int MIN_VALUE = 1;

    public LottoBundle makeLottoBundle(AmountPaid amount) {
        List<Lotto> lottoBundle = new ArrayList<>();
        for (int i = 0; i < amount.getLottoQuantity(); i++) {
            lottoBundle.add(makeLotto());
        }
        return new LottoBundle(lottoBundle);
    }

    public WinningNumbers makeWinningNumbers(String winningNumber, String bonusNumber) {

        List<Integer> parsedWinningNumbers = parseToIntegers(Splitter.splitByComma(winningNumber));
        int parsedBonusNumber = parseToInteger(bonusNumber);

        return new WinningNumbers(parsedWinningNumbers, parsedBonusNumber);
    }

    private Lotto makeLotto() {

        List<Integer> lottoNumbers = numberGeneratorWithUniqueValues(MAKE_QUANTITY, MIN_VALUE, MAX_VALUE);
        return new Lotto(lottoNumbers);
    }

    public EnumMap<Rank, Integer> makeStatistics(LottoBundle lottoBundle, WinningNumbers winningNumbers) {

        EnumMap<Rank, Integer> rankIntegerEnumMap = makeDefaultMap();

        for (Lotto lotto : lottoBundle.getLottoBundle()) {
            int matchCount = winningNumbers.checkMatchCount(lotto);
            boolean matchBonus = winningNumbers.checkMatchBonus(lotto);
            Rank currentRank = checkPrize(matchCount, matchBonus);

            rankIntegerEnumMap.put(currentRank, rankIntegerEnumMap.get(currentRank) + 1);
        }

        return rankIntegerEnumMap;
    }

    public String calculateTotalResult(EnumMap<Rank, Integer> lottoResult, AmountPaid amountPaid) {
        int totalPrize = calculateTotalPrize(lottoResult);
        return amountPaid.calculateProfitRate(totalPrize);
    }
}
