package lotto.util;

import lotto.domain.lotto.LottoNumber;

import java.util.Comparator;

public class AscendingNumber implements Comparator<LottoNumber> {

    @Override
    public int compare(LottoNumber o1, LottoNumber o2) {
        return Integer.compare(o1.getNumber(), o2.getNumber());
    }
}
