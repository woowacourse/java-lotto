package domain;

import java.util.*;

public class LottoFactory {
    private static final List<LottoNumber> lottoNumberPool = new ArrayList<>();

    static {
        for (int number = LottoNumber.MIN_NUMBER; number <= LottoNumber.MAX_NUMBER; number++) {
            lottoNumberPool.add(new LottoNumber(number));
        }
    }

    public static List<Lotto> issueLottoWorthOf(PurchaseAmount purchaseAmount) {
        int numberOfLottoToIssue = purchaseAmount.getPurchaseAmount() / Lotto.PRICE;
        List<Lotto> issuedLottos = new ArrayList<>();

        for (int i = 0; i < numberOfLottoToIssue; i++) {
            issuedLottos.add(new Lotto(generateLottoNumbers()));
        }
        return issuedLottos;
    }

    private static Set<LottoNumber> generateLottoNumbers() {
        Collections.shuffle(lottoNumberPool);
        return new TreeSet<>(lottoNumberPool.subList(0, 6));
    }
}

