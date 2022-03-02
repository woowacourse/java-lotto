package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import lotto.exception.MoneyException;
import lotto.validator.MoneyValidator;

public class User {

    private long money;
    private long spentMoney;
    private final List<Lotto> lottos;
    private int countOfManualLotto;
    private int countOfAutoLotto;

    public User(long money) {
        MoneyValidator.validate(money);
        this.money = money;
        this.spentMoney = 0;
        this.lottos = new ArrayList<>();
        this.countOfManualLotto = 0;
        this.countOfAutoLotto = 0;
    }

    public static User generateUserByString(String money) {
        return new User(Long.parseLong(money));
    }

    public void buyLottoByManual(Lotto lotto) {
        spendMoney(Lotto.LOTTO_PRICE);
        lottos.add(lotto);
        countOfManualLotto++;
    }

    public void buyAllLottosByAuto() {
        int countCanBuy = calculateCountCanBuy();
        spendMoney(countCanBuy * Lotto.LOTTO_PRICE);
        IntStream.range(0, countCanBuy)
                .mapToObj(i -> Lotto.generateLottoByAuto())
                .forEach(lottos::add);
        countOfAutoLotto += countCanBuy;
    }

    public void spendMoney(long moneyToSpend) {
        if (money < spentMoney + moneyToSpend) {
            throw new MoneyException(MoneyException.MONEY_SPENT_LIMIT_ERROR_MESSAGE);
        }
        spentMoney += moneyToSpend;
    }

    public int calculateCountCanBuy() {
        return (int) ((money - spentMoney) / Lotto.LOTTO_PRICE);
    }

    public double getProfitRate(long totalPrize) {
        return (double) totalPrize / (double) spentMoney;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getCountOfManualLotto() {
        return countOfManualLotto;
    }

    public int getCountOfAutoLotto() {
        return countOfAutoLotto;
    }
}
