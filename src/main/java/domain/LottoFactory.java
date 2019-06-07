package domain;

import domain.lottonumber.LottoNumber;
import domain.lottonumber.LottoNumberPool;

import java.util.*;

public class LottoFactory {
    public static List<IssuedLotto> issueLottoWorthOf(PurchaseAmount purchaseAmount) {
        int numberOfLottoToIssue = purchaseAmount.getPurchaseAmount() / IssuedLotto.PRICE;
        List<IssuedLotto> issuedLottos = new ArrayList<>();

        for (int i = 0; i < numberOfLottoToIssue; i++) {
            issuedLottos.add(autoIssueLotto());
        }
        return issuedLottos;
    }

    private static IssuedLotto autoIssueLotto() {
        Set<LottoNumber> randomLottoNumbers = LottoNumberPool.randomPickAsManyAs(Lotto.NUMBER_OF_LOTTO_NUMBERS);
        return new IssuedLotto(randomLottoNumbers);
    }

    public static WinningLotto issueWinningLotto(List<Integer> inputNumbers, LottoNumber bonusNumber) {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();

        for (Integer number : inputNumbers) {
            lottoNumbers.add(LottoNumberPool.pickLottoNumber(number));
        }
        return new WinningLotto(lottoNumbers, bonusNumber);
    }
}

