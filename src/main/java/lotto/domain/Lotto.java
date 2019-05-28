package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Lotto {
    private List<Number> lotto = new ArrayList<>();

    public Lotto(List<Integer> lotto) {
        checkDuplicate(lotto);
        for(Integer number : lotto) {
            this.lotto.add(Number.of(number.intValue()));
        }
    }

    private void checkDuplicate(List<Integer> lotto) {
        HashSet<Integer> set = new HashSet(lotto);
        if(lotto.size() != set.size()) {
            throw new IllegalArgumentException("중복이 아닌 번호들로만 입력하세요.");
        }
    }

    public boolean isContain(Number bonusBall) {
        return lotto.contains(bonusBall);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(lotto, lotto1.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }
}
