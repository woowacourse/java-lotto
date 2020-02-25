package lotto.domain;

import lotto.util.LottoUtils;
import lotto.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
    private static final int START_LOTTO_NO = 1;
    private static final int END_LOTTO_NO = 45;
    private static final int LOTTO_FROM_INDEX = 0;
    private static final int LOTTO_TO_INDEX = 6;
    private static final String ERROR_MESSAGE_NULL_POINT_LOTTO_FACTORY = "입력값이 비었습니다.";

    private static List<LottoNo> lottoNumberBox = new ArrayList<>();

    static {
        for (int count = START_LOTTO_NO; count <= END_LOTTO_NO; count++) {
            lottoNumberBox.add(new LottoNo(count));
        }
    }

    public static List<Lotto> createLotteries(Money money, String manualLotto) {
        validate(money);
        List<Lotto> lotteries = new ArrayList<>();
        int lottoCount = money.divideThousand();
        if (manualLotto == null || manualLotto.isEmpty()) {
            createAutoLotto(lotteries, lottoCount);
            return lotteries;
        }

        String[] manualLotteries = StringUtils.splitLotto(manualLotto);
        lotteries = createLottoManual(manualLotteries);
        createAutoLotto(lotteries, lottoCount - manualLotteries.length);
        return lotteries;
    }

    private static void createAutoLotto(List<Lotto> lotteries, int autoLottoCount) {
        for (int i = 0; i < autoLottoCount; i++) {
            lotteries.add(createLottoAuto());
        }
    }

    private static void validate(Money money) {
        if (money == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NULL_POINT_LOTTO_FACTORY);
        }
    }

    public static List<Lotto> createLottoManual(String[] manualLotteries) {
        List<Lotto> manualLotto = new ArrayList<>();
        for (String numbers : manualLotteries) {
            List<LottoNo> lotto = LottoUtils.toLottoNoList(StringUtils.splitNumber(numbers));
            manualLotto.add(new Lotto(lotto));
        }
        return manualLotto;
    }

    private static Lotto createLottoAuto() {
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
