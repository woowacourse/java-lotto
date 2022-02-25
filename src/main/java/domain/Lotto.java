package domain;

import dto.LottoNumberDto;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import utils.Validator;

public class Lotto {

    private static final String INPUT_NUMBER_DELIMITER = ",";
    private static final int INPUT_NUMBER_SPLIT_OPTION = -1;
    private static final int LOTTO_SIZE = 6;
    private static final String ERROR_LOTTO_SIZE_MESSAGE = "로또 번호 6개를 입력해주세요.";

    private final List<LottoNumber> lotto;

    public Lotto(final String inputLotto) {
        List<String> splitNumbers = splitInput(inputLotto);
        Validator.checkDuplication(splitNumbers);
        checkSplitNumbersCount(splitNumbers);
        this.lotto = splitNumbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
    }

    public Lotto(final List<LottoNumber> lotto) {
        this.lotto = lotto;
    }

    private List<String> splitInput(final String inputNumbers) {
        return Arrays.stream(inputNumbers.split(INPUT_NUMBER_DELIMITER, INPUT_NUMBER_SPLIT_OPTION))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    private void checkSplitNumbersCount(final List<String> splitNumbers) {
        if (splitNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE_MESSAGE);
        }
    }

    public boolean isContainNumber(final LottoNumber lottoNumber) {
        return this.lotto.contains(lottoNumber);
    }

    public int compare(final Lotto lotto) {
        return (int) this.lotto.stream()
            .filter(lottoNumber -> lotto.isContainNumber(lottoNumber))
            .count();
    }

    public List<LottoNumberDto> toDto() {
        return this.lotto.stream()
            .map(LottoNumberDto::from)
            .collect(Collectors.toList());
    }
}
