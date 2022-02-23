package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private static final String ERROR_MESSAGE_NOT_IN_SIZE = "로또 숫자 갯수는 6개이여야 합니다.";
    private static final String ERROR_MESSAGE_NOT_DUPLICATE = "로또 숫자는 중복일 수 없습니다.";

    private static final int LOTTO_NUMBER_SIZE = 6;

    private final List<LottoNumber> lottoNumbers = new ArrayList<>();

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers.addAll(lottoNumbers);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_IN_SIZE);
        }
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        List<LottoNumber> deDuplicatedNumbers = lottoNumbers.stream()
            .distinct()
            .collect(Collectors.toList());
        if (deDuplicatedNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_DUPLICATE);
        }
    }
}

