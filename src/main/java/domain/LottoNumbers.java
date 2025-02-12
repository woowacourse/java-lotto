package domain;

import exception.LottoException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LottoNumbers {
    private static final int LOTTO_VALID_SIZE = 6;
    private static final String INVALID_LOTTO_SIZE = "로또 번호는 6개여야 합니다.";
    private final List<LottoNumber> lottoNumbers;
    private final String DUPLICATE_LOTTO_NUMBERS = "로또 번호는 중복될 수 없습니다!";
    private final String BUY_LOTTO_NUMBERS_FORMAT = "[%s]";

    public LottoNumbers(List<Integer> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::new)
                .sorted()
                .toList();
    }

    private void validateLottoNumbers(List<Integer> lottoNumbers) {
        Set<Integer> duplicationSet = new HashSet<>(lottoNumbers);
        if(lottoNumbers.size() != duplicationSet.size()) {
            throw new LottoException(DUPLICATE_LOTTO_NUMBERS);
        }
        if(lottoNumbers.size() != LOTTO_VALID_SIZE){
            throw new LottoException(INVALID_LOTTO_SIZE);
        }
    }

    public String formatNumbers(){
        String formattedLottoNumbers = lottoNumbers.stream()
                .map((number) -> number + "")
                .collect(Collectors.joining(", "));
        return String.format(BUY_LOTTO_NUMBERS_FORMAT,formattedLottoNumbers);
    }
}
