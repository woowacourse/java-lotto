package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Lotto> userLottos = new ArrayList<>();
    private final Money money;
    private final int countOfLotto;

    public User(int money) {
        this.money = new Money(money);
        this.countOfLotto = this.money.countOfLotto();
        generateUserLottos();
    }

    private void generateUserLottos() {
        for (int i = 0; i < countOfLotto; i++) {
            userLottos.add(new Lotto(LottoAutoGenerator.generateAutoLotto()));
        }
    }

    public List<Lotto> getUserLottos() {
        return this.userLottos;
    }
}
