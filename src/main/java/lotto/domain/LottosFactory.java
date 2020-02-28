package lotto.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * LottosFactory 클래스
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/21
 */
public class LottosFactory {

    private LottosFactory() {
    }

    public static Lottos createLottosAuto(Money money) {
        validateMoneyIsEnough(money);

        int amountOfLottos = calculateAmountOfLottos(money);
        List<Lotto> paidLotto = new ArrayList<>();

        for (int i = 0; i < amountOfLottos; i++) {
            paidLotto.add(Lotto.createLottoAuto());
        }
        return new Lottos(paidLotto);
    }

    public static Lottos createLottosManual(Money money, List<List<Integer>> lottosInput) {
        validateMoneyIsEnough(money);

        int amountOfLottos = calculateAmountOfLottos(money);
        int amountOfManualLottos = lottosInput.size();
        checkNumberOfManualLottosLegal(amountOfLottos, amountOfManualLottos);

        List<Lotto> paidLotto = new ArrayList<>();
        paidLotto.addAll(makeLottoListManual(lottosInput));
        paidLotto.addAll(makeLottoListAuto(amountOfLottos - amountOfManualLottos));
        return new Lottos(paidLotto);
    }

    private static void checkNumberOfManualLottosLegal(int totalLottosAmount, int manualLottosAmount) {
        if (manualLottosAmount > totalLottosAmount) {
            throw new IllegalArgumentException(
                "수동으로 구매할 로또의 갯수가 총 구매할 로또 갯수보다 클 수 없습니다."
            );
        }
    }

    private static List<Lotto> makeLottoListManual(List<List<Integer>> lottosInput) {
        List<Lotto> lottos = new ArrayList<>();

        for (List<Integer> lotto : lottosInput) {
            lottos.add(Lotto.createLottoManual(lotto));
        }

        return lottos;
    }

    private static List<Lotto> makeLottoListAuto(int amountOfAutoLottos) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < amountOfAutoLottos; i++) {
            lottos.add(Lotto.createLottoAuto());
        }

        return lottos;
    }

    public static void validateMoneyIsEnough(Money inputMoney) {
        if (inputMoney.getMoney() < Lotto.LOTTO_PRICE) {
            throw new IllegalArgumentException(Lotto.LOTTO_PRICE + "원 이상 입력해주세요.");
        }
    }

    private static int calculateAmountOfLottos(Money money) {
        return money.getMoney() / Lotto.LOTTO_PRICE;
    }

    public static int getHowMuchCanBuyLottoWith(Money money) {
        return calculateAmountOfLottos(money);
    }
}
