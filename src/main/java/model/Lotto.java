package model;

import java.util.Comparator;
import java.util.List;

public class Lotto {

    private final List<Integer> lottoNumber;

    public Lotto(List<Integer> lottoNumber) {
        lottoNumber.sort(Comparator.naturalOrder());
        this.lottoNumber = lottoNumber;
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }
}
