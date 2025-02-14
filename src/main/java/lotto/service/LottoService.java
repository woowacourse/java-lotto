package lotto.service;


import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import lotto.domain.AmountPaid;
import lotto.domain.Lotto;
import lotto.domain.LottoBundle;
import lotto.domain.LottoNumber;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.utils.NumberGenerator;
import lotto.utils.Parser;

public class LottoService {

    public LottoBundle makeLottoBundle(AmountPaid amount) {
        List<Lotto> lottoBundle = new ArrayList<>();
        for (int i = 0; i < amount.getLottoQuantity(); i++) {
            lottoBundle.add(makeLotto());
        }
        return new LottoBundle(lottoBundle);
    }


    public WinningNumbers makeWinningNumbers(String winningNumber, String bonusNumber) {

        List<Integer> lottoNumbers = NumberGenerator.numberGeneratorWithUniqueValues(6, 1, 45);
        List<LottoNumber> lotto = new ArrayList<>();
        for (int lottoNumber : lottoNumbers) {
            lotto.add(new LottoNumber(lottoNumber));
        }
        return new WinningNumbers(new Lotto(lotto), new LottoNumber(Parser.parseToInteger(bonusNumber)));
    }


    private Lotto makeLotto() {
        List<Integer> lottoNumbers = NumberGenerator.numberGeneratorWithUniqueValues(6, 1, 45);
        List<LottoNumber> lotto = new ArrayList<>();
        for (int lottoNumber : lottoNumbers) {
            lotto.add(new LottoNumber(lottoNumber));
        }

        return new Lotto(lotto);
    }

    public String calculateTotalResult(EnumMap<Rank, Integer> lottoResult, AmountPaid amountPaid) {
        int totalPrize = Rank.calculateTotalPrize(lottoResult);
        return amountPaid.calculateProfitRate(totalPrize);
    }
}
