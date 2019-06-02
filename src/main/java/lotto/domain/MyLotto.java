package lotto.domain;

import java.util.List;

public class MyLotto {
    private final List<Lotto> myLotto;

    public MyLotto(List<Lotto> myLotto) {
        this.myLotto = myLotto;
    }

    public int getSize() {
        return myLotto.size();
    }

    public Lotto getIndexByLotto(int index) {
        return myLotto.get(index);
    }
}
