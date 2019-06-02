package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoTicket {
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(final List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int getMatchingCount(WinningLotto winningLotto) {
        int count = 0;

        for (LottoNumber lottoNumber : lottoNumbers) {
            count += winningLotto.hasEqualNumber(lottoNumber) ? 1 : 0;
        }

        return count;
    }

    public boolean matchesBonusBall(WinningLotto winningLotto) {
        boolean matchingResult = false;

        for (int i = 0; i < lottoNumbers.size() && !matchingResult; i++) {
            matchingResult = winningLotto.hasEqualBonusBall(lottoNumbers.get(i));
        }

        return matchingResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("[")
                .append(lottoNumbers
                        .stream()
                        .map(n -> n.toString())
                        .collect(Collectors.joining(", ")).toString())
                .append("]");

        return stringBuilder.toString();
    }
}
