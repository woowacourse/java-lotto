package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
    private static final int START_LOTTO_NO = 1;
    private static final int END_LOTTO_NO = 45;
    private static final int LOTTO_FROM_INDEX = 0;
    private static final int LOTTO_TO_INDEX = 6;
    public static final String ERROR_MESSAGE_NULL_POINT_LOTTO_FACTORY = "입력값이 비었습니다.";

    private static List<LottoNo> lottoNumberBox = new ArrayList<>();

    static {
        for (int count = START_LOTTO_NO; count <= END_LOTTO_NO; count++) {
            lottoNumberBox.add(new LottoNo(count));
        }
    }

    public static List<Lotto> createLotteries(Money money) {
        validate(money);
        List<Lotto> lotteries = new ArrayList<>();
        int createCount = money.divideThousand();
        for (int i = 0; i < createCount; i++) {
            lotteries.add(createLotto());
        }
        return lotteries;
    }

    private static void validate(Money money) {
        if (money == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NULL_POINT_LOTTO_FACTORY);
        }
    }

    private static Lotto createLotto() {
        List<LottoNo> lotto = pickSixRandomNo();
        Collections.sort(lotto);
        return new Lotto(lotto);
    }

    private static List<LottoNo> pickSixRandomNo() {
        List<LottoNo> lotto = new ArrayList<>(lottoNumberBox);
        Collections.shuffle(lotto);
        lotto = lotto.subList(LOTTO_FROM_INDEX, LOTTO_TO_INDEX);
        return lotto;
    }
}
