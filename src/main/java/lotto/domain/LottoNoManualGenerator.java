package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class LottoNoManualGenerator implements LottoNoGenerator {
    private Stack<Integer> lottoNumbers;

    public LottoNoManualGenerator(List<Integer> lottoNoList) {
        if (lottoNoList.size() != Lotto.LOTTO_SIZE) {
            throw new IllegalArgumentException(String.format("수동 로또는 숫자 %d개를 선택해야 합니다.", Lotto.LOTTO_SIZE));
        }
        lottoNumbers = lottoNoList.stream()
                .collect(Stack::new, Stack::add, Stack::addAll)
        ;
    }

    @Override
    public int generate() {
        return lottoNumbers.pop();
    }

    @Override
    public int size() {
        return lottoNumbers.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNoManualGenerator)) return false;
        LottoNoManualGenerator that = (LottoNoManualGenerator) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
