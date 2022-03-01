package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import domain.constant.LottoConstant;

public class Lotto {

    private static final String ERROR_MESSAGE_NOT_IN_SIZE = "로또 숫자 갯수는 6개이여야 합니다.";
    private static final String ERROR_MESSAGE_NOT_DUPLICATE = "로또 숫자는 중복일 수 없습니다.";
    private static final String ERROR_MESSAGE_NULL = "값이 null 입니다.";

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        Objects.requireNonNull(lottoNumbers, ERROR_MESSAGE_NULL);
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LottoConstant.LOTTO_NUMBER_SIZE) {
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
