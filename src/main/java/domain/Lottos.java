package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import util.Validator;

public class Lottos {

    private List<Lotto> members;

    public Lottos() {
        this.members = new ArrayList<>();
    }

    public static Lottos init() {
        return new Lottos();
    }

    public void add(Lotto lotto) {
        members.add(lotto);
    }

    public int numberOfLottery() {
        return members.size();
    }

    public List<Lotto> getMembers() {
        return Collections.unmodifiableList(members);
    }

    public List<Integer> compareAllLottosWithWinningLotto(Set<LottoNumber> winningNumbers) {
        Validator.checkArgumentIsNull(winningNumbers);
        return members.stream()
                .map(lotto -> lotto.countDuplicatedNumber(winningNumbers))
                .collect(Collectors.toList());
    }

    public List<Boolean> checkAllLottosContainNumber(LottoNumber number) {
        Validator.checkArgumentIsNull(number);
        return members.stream()
                .map(lotto -> lotto.contains(number))
                .collect(Collectors.toList());
    }
}
