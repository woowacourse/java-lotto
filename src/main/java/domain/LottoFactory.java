package domain;

import domain.lottonumber.LottoNumber;
import domain.lottonumber.RandomLottoNumberGenerator;
import domain.money.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LottoFactory {
    public static IssuedLottos autoIssueLottoWorthOf(Money purchaseAmount) {
        int numberOfLottoToIssue = purchaseAmount.getAmount() / Lotto.PRICE;
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < numberOfLottoToIssue; i++) {
            lottos.add(autoIssueLotto());
        }
        return IssuedLottos.of(lottos);
    }

    private static Lotto autoIssueLotto() {
        Set<LottoNumber> randomLottoNumbers = RandomLottoNumberGenerator.generateNumbersAsManyAs(Lotto.NUMBER_OF_LOTTO_NUMBERS);
        return new Lotto(randomLottoNumbers);
    }

    public static WinningLotto getWinningLotto(List<Integer> inputNumbers, LottoNumber bonusNumber) {
        Set<LottoNumber> lottoNumbers = getLottoNumbersOf(inputNumbers);
        Lotto winningLotto = new Lotto(lottoNumbers);
        return new WinningLotto(winningLotto, bonusNumber);
    }

    private static Set<LottoNumber> getLottoNumbersOf(List<Integer> inputNumbers) {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();

        for (Integer number : inputNumbers) {
            lottoNumbers.add(LottoNumber.valueOf(number));
        }
        return lottoNumbers;
    }

    public static Lotto manualIssueLottoBy(List<Integer> inputNumbers) {
        Set<LottoNumber> lottoNumbers = getLottoNumbersOf(inputNumbers);
        return new Lotto(lottoNumbers);
    }
}

