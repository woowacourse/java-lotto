package lotto.domain;

import lotto.utils.LottoNoGenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    public static final int COUNT_OF_NO = 6;

    private List<LottoNo> lottoNo;

    static Lotto of(List<LottoNo> lottoNo) {
        lottoNo.sort(LottoNo::compareTo);
        return new Lotto(lottoNo);
    }

    static Lotto of() {
        return new Lotto(LottoNoGenerator.generate());
    }

    private Lotto(List<LottoNo> lottoNo) {
        validateCountOfNo(lottoNo);
        validateDuplicatedNo(lottoNo);
        this.lottoNo = Collections.unmodifiableList(lottoNo);
    }

    private void validateDuplicatedNo(List<LottoNo> lottoNo) {
        if (new HashSet<>(lottoNo).size() != COUNT_OF_NO) {
            throw new InvalidNumberException("로또 번호에 중복된 번호가 있습니다.");
        }
    }

    private void validateCountOfNo(List<LottoNo> lottoNo) {
        if (lottoNo.size() != COUNT_OF_NO) {
            throw new InvalidNumberException("로또 번호의 갯수가 " + COUNT_OF_NO + "개가 아닙니다.");
        }
    }

    private boolean matchNo(LottoNo lotto) {
        return lottoNo.contains(lotto);
    }

    int getCountOfMatchNo(Lotto lotto) {
        return lottoNo.stream().filter(lotto::matchNo).collect(Collectors.toList()).size();
    }
}