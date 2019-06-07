package domain;

import java.util.*;

public class LottoFactory {
    private static final List<LottoNumber> lottoNumberPool = new ArrayList<>();

    static {
        for (int number = LottoNumber.MIN_NUMBER; number <= LottoNumber.MAX_NUMBER; number++) {
            lottoNumberPool.add(new LottoNumber(number));
        }
    }

    public static List<IssuedLotto> issueLottoWorthOf(PurchaseAmount purchaseAmount) {
        int numberOfLottoToIssue = purchaseAmount.getPurchaseAmount() / IssuedLotto.PRICE;
        List<IssuedLotto> issuedLottos = new ArrayList<>();

        for (int i = 0; i < numberOfLottoToIssue; i++) {
            issuedLottos.add(new IssuedLotto(generateLottoNumbers()));
        }
        return issuedLottos;
    }

    private static Set<LottoNumber> generateLottoNumbers() {
        Collections.shuffle(lottoNumberPool);
        return new TreeSet<>(lottoNumberPool.subList(0, 6));
    }

    public static WinningLotto issueWinningLotto(List<Integer> inputNumbers, LottoNumber bonusNumber) {
        Set<LottoNumber> lottoNumbers = new TreeSet<>();

        for (Integer number : inputNumbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return new WinningLotto(lottoNumbers, bonusNumber);
    }
}

