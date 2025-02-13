package lotto.service;


import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import lotto.domain.AmountPaid;
import lotto.domain.Lotto;
import lotto.domain.LottoBundle;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.utils.NumberGenerator;
import lotto.utils.Parser;
import lotto.utils.Splitter;

public class LottoService {

    public LottoBundle makeLottoBundle(AmountPaid amount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < amount.getLottoQuantity(); i++) {
            lottos.add(makeLotto());
        }
        return new LottoBundle(lottos);
    }


    public WinningNumbers makeWinningNumbers(String winningNumber, String bonusNumber) {
        return new WinningNumbers(Parser.parseToIntegers(Splitter.splitByComma(
                winningNumber)), Parser.parseToInteger(bonusNumber));
    }


    private Lotto makeLotto() {
        return new Lotto(NumberGenerator.numberGeneratorWithUniqueValues(6, 1, 45));
    }

    public EnumMap<Rank, Integer> makeStatistics(LottoBundle lottoBundle, WinningNumbers winningNumbers) {

        EnumMap<Rank, Integer> rankIntegerEnumMap = Rank.makeDefaultMap();

        for (Lotto lotto : lottoBundle.getLottoBundle()) {
            Rank currentRank = Rank.checkPrize(winningNumbers.checkMatchCount(lotto),
                    winningNumbers.checkMatchBonus(lotto));
            rankIntegerEnumMap.put(currentRank, rankIntegerEnumMap.get(currentRank) + 1);
        }

        return rankIntegerEnumMap;
    }
}
