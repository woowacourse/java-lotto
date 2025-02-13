package domain;

import java.util.List;

public class WinningLotto extends Lotto{

    private int bonus;

    public WinningLotto(String lotto, String bonus) {
        super(lotto);
        this.bonus = validateBonus(bonus);
    }

    // todo : lottos에서 해결할 수 있는 방법 찾아보기
    public boolean matchBonus(List<Integer> numbers) {
        return numbers.contains(bonus);
    }
}
