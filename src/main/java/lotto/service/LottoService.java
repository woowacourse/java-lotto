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
        List<Lotto> lottoBundle = new ArrayList<>();
        for (int i = 0; i < amount.getLottoQuantity(); i++) {
            lottoBundle.add(makeLotto());
        }
        return new LottoBundle(lottoBundle);
    }


    public WinningNumbers makeWinningNumbers(String winningNumber, String bonusNumber) {
        return new WinningNumbers(Parser.parseToIntegers(Splitter.splitByComma(
                winningNumber)), Parser.parseToInteger(bonusNumber));
    }


    private Lotto makeLotto() {
        return new Lotto(NumberGenerator.numberGeneratorWithUniqueValues(6, 1, 45));
    }

    public String calculateTotalResult(EnumMap<Rank, Integer> lottoResult, AmountPaid amountPaid) {
        int totalPrize = Rank.calculateTotalPrize(lottoResult);
        return amountPaid.calculateProfitRate(totalPrize);
    }
}
