package lotto.model;

import java.util.List;

public class LottoGame {
    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_NOT_DIVIDED_BY_UNIT_PRICE = "거스름돈을 지급하지 않습니다. 금액이 남지 않게 지불해주세요.";
    public static final String ERROR_DUPLICATION_BONUS_NUMBER = "보너스 볼 번호가 당첨 번호와 중복입니다.";

    private final WinningNumbers winningNumbers;
    private final LottoNumber bonusNumber;
    private final LottoResult lottoResult;

    public LottoGame(List<Integer> winningNumbers, Integer bonusNumber) {
        validateDuplicateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = new WinningNumbers(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
        this.lottoResult = new LottoResult();
    }

    public float calculateYield(int money) {
        return getTotalWinningMoney(lottoResult) / (float) money;
    }

    private Long getTotalWinningMoney(LottoResult lottoResult) {
        return lottoResult.getResult().entrySet().stream()
                .map(entry -> entry.getKey().getMoney() * entry.getValue())
                .mapToLong(i -> i)
                .sum();
    }

    public static Lottos buyLottos(int money) {
        int lottoSize = getLottoSize(money);

        return Lottos.generate(lottoSize);
    }

    public static int getLottoSize(int money) {
        validateUnitPrice(money);
        return money / LOTTO_PRICE;
    }

    private static void validateUnitPrice(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_NOT_DIVIDED_BY_UNIT_PRICE);
        }
    }

    public LottoResult generateLottoResult(Lottos lottos) {
        return lottoResult.generate(lottos, winningNumbers, bonusNumber);
    }

    private void validateDuplicateBonusNumber(List<Integer> winningNumbers, Integer bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }
}
