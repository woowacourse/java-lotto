package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class UserLotto extends Lotto {

    private static final String OPEN = "[";
    private static final String CLOSE = "]";
    private static final String DELIMITER = ", ";

    public UserLotto(List<LottoNumber> lottoNumbers) {
        super(lottoNumbers);
    }

    @Override
    boolean isDuplicated(List<LottoNumber> scannedNumbers) {
        return scannedNumbers.size() != new HashSet<>(scannedNumbers).size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(OPEN);
        stringBuilder.append(
                this.lottoNumbers.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(DELIMITER)));
        return stringBuilder.append(CLOSE).toString();
    }
}
