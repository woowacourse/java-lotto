package lotto.domain;

import java.util.List;

public class UserLotto {
    private final List<Lotto> userLotto;

    public UserLotto(List<Lotto> userLotto, int round, NumberGenerator numberGenerator) {
        this.userLotto = userLotto;
        createLotto(numberGenerator, round);
    }

    public int getSize() {
        return userLotto.size();
    }

    public Lotto getIndexByLotto(int index) {
        return userLotto.get(index);
    }

    private void createLotto(NumberGenerator numberGenerator, int round) {
        for (int i = 0; i < round; i++) {
            userLotto.add(new Lotto(numberGenerator.getNumbers()));
        }
    }
}
