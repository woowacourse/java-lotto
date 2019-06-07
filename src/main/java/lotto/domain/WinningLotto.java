package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class WinningLotto {
    private static final String ERROR_DUPLICATE = "당첨 번호와 중복되었습니다.";

    private final Lotto winnerLotto;
    private final Number bonus;

    WinningLotto(String numbers, Number bonus) {
        this.winnerLotto = getWinningLotto(numbers);
        this.bonus = bonus;

        checkDuplicate();
    }

    public static WinningLotto create(String numbers, Number bonus) {
        return new WinningLotto(numbers, bonus);
    }

    public boolean matchBonus(Lotto lotto) {
        return lotto.isContains(bonus);
    }

    public int match(Lotto lotto) {
        int count = 0;

        for (int i = 0; i < lotto.getSize(); i++) {
            count = addCount(lotto, count, i);
        }

        return count;
    }

    private Lotto getWinningLotto(String numbers) {
        List<Number> lottos = new ArrayList<>();
        String[] winnerNumbers = numbers.split(",");

        for (String s : winnerNumbers) {
            lottos.add(new Number(Integer.parseInt(s)));
        }

        return new Lotto(lottos);
    }

    private int addCount(Lotto lotto, int count, int i) {
        if (winnerLotto.isContains(lotto.getLottoByIndex(i))) {
            count++;
        }
        return count;
    }

    private void checkDuplicate() {
        if (winnerLotto.isContains(bonus)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }
}
