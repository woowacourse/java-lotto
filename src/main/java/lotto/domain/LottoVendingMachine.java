package lotto.domain;

import java.util.List;

public class LottoVendingMachine {
    private static final int LOTTO_PRICE = 1000;
    private final Money money;
    private int numOfRandomLottos;
    private int numOfCustomLottos;

    public LottoVendingMachine(Money money) {
        validateMoney(money);
        this.money = money;
    }

    public static void validateMoney(Money money) {
        if (money.mod(LOTTO_PRICE) != 0) {
            throw new InvalidLottoBuyingMoneyException("로또 금액(" + LOTTO_PRICE + ") 의 배수에 해당하는 돈을 입력하셔야 합니다.");
        }
    }

    public void validateNumOfCustomLottos(int numOfCustomLottos) {
        if (money.divideBy(LOTTO_PRICE) < numOfCustomLottos || numOfCustomLottos < 0) {
            throw new InvalidNumOfCustomLottosException("수동으로 구매할 로또 수는 구매 가능한 로또 수보다 클 수 없고 0보다 작을 수 없습니다.");
        }
        this.numOfRandomLottos = (money.divideBy(LOTTO_PRICE)) - numOfCustomLottos;
        this.numOfCustomLottos = numOfCustomLottos;
    }

    public Lottos getLottos(List<List<Integer>> lottoNumbers) {
        if (lottoNumbers.size() != numOfCustomLottos) {
            throw new InvalidCustomLottoNumbersException("수동으로 구매하기로 한 로또 수와 일치하지 않습니다.");
        }
        Lottos userLottos = LottoFactory.createCustomLottos(lottoNumbers);
        return userLottos.add(LottoFactory.createRandomLottos(numOfRandomLottos));
    }
}