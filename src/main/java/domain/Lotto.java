package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final String ERROR_MESSAGE_NOT_IN_SIZE = "로또 숫자 갯수는 6개이여야 합니다.";
    private static final String ERROR_MESSAGE_NOT_DUPLICATE = "로또 숫자는 중복일 수 없습니다.";

    public static final int LOTTO_NUMBER_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_IN_SIZE);
        }
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> deDuplicatedNumbers = new HashSet<>(lottoNumbers);

        if (deDuplicatedNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_DUPLICATE);
        }
    }

    public int calculateMatchCount(Lotto otherLotto) {
        List<LottoNumber> copiedNumbers = new ArrayList<>(List.copyOf(lottoNumbers));
        copiedNumbers.retainAll(otherLotto.lottoNumbers);

        return copiedNumbers.size();
    }

    public boolean containsNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
