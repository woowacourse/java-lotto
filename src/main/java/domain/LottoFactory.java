package domain;

import domain.lottonumber.LottoNumber;
import domain.lottonumber.LottoNumberPool;

import java.util.*;

public class LottoFactory {
    public static IssuedLottos autoIssueLottoWorthOf(Money purchaseAmount) {
        int numberOfLottoToIssue = purchaseAmount.getAmount() / IssuedLotto.PRICE;
        List<IssuedLotto> lottos = new ArrayList<>();

        for (int i = 0; i < numberOfLottoToIssue; i++) {
            lottos.add(autoIssueLotto());
        }
        return IssuedLottos.of(lottos);
    }

    private static IssuedLotto autoIssueLotto() {
        Set<LottoNumber> randomLottoNumbers = LottoNumberPool.randomPickAsManyAs(Lotto.NUMBER_OF_LOTTO_NUMBERS);
        return new IssuedLotto(randomLottoNumbers);
    }

    public static WinningLotto issueWinningLotto(List<Integer> inputNumbers, LottoNumber bonusNumber) {
        Set<LottoNumber> lottoNumbers = getLottoNumbersOf(inputNumbers);
        return new WinningLotto(lottoNumbers, bonusNumber);
    }

    private static Set<LottoNumber> getLottoNumbersOf(List<Integer> inputNumbers) {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();

        for (Integer number : inputNumbers) {
            lottoNumbers.add(LottoNumberPool.pickLottoNumber(number));
        }
        return lottoNumbers;
    }

    public static IssuedLotto manualIssueLottoBy(List<Integer> inputNumbers) {
        Set<LottoNumber> lottoNumbers = getLottoNumbersOf(inputNumbers);
        return new IssuedLotto(lottoNumbers);
    }
}

