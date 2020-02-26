package lotto.domain;

import lotto.util.LottoUtils;
import lotto.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LottoFactory {
    private static final int START_LOTTO_NO = 1;
    private static final int END_LOTTO_NO = 45;
    private static final int LOTTO_FROM_INDEX = 0;
    private static final int LOTTO_TO_INDEX = 6;
    private static final String ERROR_MESSAGE_NULL_POINT_LOTTO_FACTORY = "입력값이 비었습니다.";

    private static List<LottoNo> lottoNumberBox = new ArrayList<>();

    static {
        for (int number = START_LOTTO_NO; number <= END_LOTTO_NO; number++) {
            lottoNumberBox.add(new LottoNo(number));
        }
    }

    public static List<Lotto> createLotteries(Money money, String userLottoNumbers) {
        validate(money);
        List<Lotto> lotteries = new ArrayList<>();
        if (userLottoNumbers == null || userLottoNumbers.isEmpty()) {
            return addAutoLotto(lotteries, money.findBuyAmount());
        }

        String[] manualLotteries = StringUtils.splitLotto(userLottoNumbers);
        lotteries = createUserLotto(manualLotteries);
        return addAutoLotto(lotteries, money.findBuyAmount() - manualLotteries.length);
    }

    private static void validate(Money money) {
        if (money == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NULL_POINT_LOTTO_FACTORY);
        }
    }

    public static List<Lotto> createUserLotto(String[] manualLotteries) {
        List<Lotto> manualLotto = new ArrayList<>();
        for (String numbers : manualLotteries) {
            Set<LottoNo> lotto = LottoUtils.toLottoNoSet(StringUtils.splitNumber(numbers));
            manualLotto.add(new Lotto(lotto));
        }
        return manualLotto;
    }

    private static List<Lotto> addAutoLotto(List<Lotto> lotteries, int autoLottoCount) {
        for (int i = 0; i < autoLottoCount; i++) {
            lotteries.add(createLottoAuto());
        }
        return lotteries;
    }

    private static Lotto createLottoAuto() {
        Set<LottoNo> lotto = new TreeSet<>(pickSixRandomNo());
        return new Lotto(lotto);
    }

    private static List<LottoNo> pickSixRandomNo() {
        List<LottoNo> lotto = new ArrayList<>(lottoNumberBox);
        Collections.shuffle(lotto);
        lotto = lotto.subList(LOTTO_FROM_INDEX, LOTTO_TO_INDEX);
        return lotto;
    }
}
