package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Lotto {
    static final int LOTTO_NUMBER_SIZE = 6;
    private List<Number> lotto = new ArrayList<>();

    public Lotto(List<Integer> lotto) {
        checkLottoSize(lotto);
        checkDuplicate(lotto);
        for (Integer number : lotto) {
            this.lotto.add(Number.of(number.intValue()));
        }
    }

    private void checkLottoSize(List<Integer> lotto) {
        if (lotto.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("6개의 번호를 입력해주세요.");
        }
    }

    private void checkDuplicate(List<Integer> lotto) {
        HashSet<Integer> set = new HashSet(lotto);
        if (lotto.size() != set.size()) {
            throw new IllegalArgumentException("중복이 아닌 번호들로만 입력하세요.");
        }
    }

    boolean isContain(Number number) {
        return lotto.contains(number);
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

    @Override
    public String toString() {
        return "" + lotto;
    }

    //인자는 사용자 로또
    public int matchNumber(Lotto lotto) {
        List<Number> matchNumbers = new ArrayList<>(this.lotto);
        matchNumbers.retainAll(lotto.lotto);
        return matchNumbers.size();
    }
}
