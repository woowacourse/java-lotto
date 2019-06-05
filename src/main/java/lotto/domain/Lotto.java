package lotto.domain;

import lotto.utils.LottoNoGenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Lotto {
    static final int LOTTO_PRICE = 1_000;
    public static final int COUNT_OF_NO = 6;

    List<LottoNo> lottoNo;
    private final LottoType lottoType;

    Lotto(List<LottoNo> lottoNo, LottoType lottoType) {
        validateCountOfNo(lottoNo);
        validateDuplicatedNo(lottoNo);
        this.lottoNo = Collections.unmodifiableList(lottoNo);
        this.lottoType = lottoType;
    }

    public static Lotto of(List<LottoNo> lottoNo) {
        lottoNo.sort(LottoNo::compareTo);
        return new Lotto(lottoNo, LottoType.MANUAL);
    }

    static Lotto of() {
        return new Lotto(LottoNoGenerator.generate(), LottoType.AUTOMATIC);
    }

    private void validateDuplicatedNo(List<LottoNo> lottoNo) {
        if (new HashSet<>(lottoNo).size() != COUNT_OF_NO) {
            throw new DuplicatedNumberException("로또 번호에 중복된 번호가 있습니다.");
        }
    }

    private void validateCountOfNo(List<LottoNo> lottoNo) {
        if (lottoNo.size() != COUNT_OF_NO) {
            throw new InvalidSizeException("로또 번호의 갯수가 " + COUNT_OF_NO + "개가 아닙니다.");
        }
    }

    boolean matchNo(LottoNo lotto) {
        return lottoNo.contains(lotto);
    }

    int findCountOfMatchNo(List<LottoNo> lotto) {
        return lotto.stream().filter(lottoNo::contains).collect(Collectors.toList()).size();
    }

    boolean matchType(LottoType type) {
        return type.equals(lottoType);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        for (LottoNo no : lottoNo) {
            sj.add(String.valueOf(no.getNo()));
        }
        return sj.toString();
    }
}