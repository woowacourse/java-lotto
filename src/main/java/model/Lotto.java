package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int LOTTO_SIZE = 6;
    private final List<Integer> lotto;

    public Lotto(final List<Integer> lotto) {
        this.lotto = lotto;
    }

    public List<Integer> getLotto() {
        return Collections.unmodifiableList(lotto);
    }

}
