package lotto.domain.lotto;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final String ERROR_CREATE_LOTTO = "[ERROR] 잘못된 숫자 입력입니다.";
    private static final String ERROR_NULL_BLANK = "[ERROR] NULL 또는 공백이 입력되었습니다.";
    private static final int LOTTO_SIZE = 6;
    private List<LottoNumber> lottoNumbers;

    public Lotto() {
    }

    public Lotto(final List<Integer> lottoNumbers) {
        checkNumbers(lottoNumbers);
        this.lottoNumbers = convertLottoNumbers(lottoNumbers);
    }

    private List<LottoNumber> convertLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }

    private void checkNumbers(final List<Integer> lottoNumbers) {
        checkNull(lottoNumbers);
        checkLottoSize(lottoNumbers);
    }

    private void checkNull(final List<Integer> lottoNumbers) {
        if (lottoNumbers == null || lottoNumbers.isEmpty()) {
            throw new IllegalArgumentException(ERROR_NULL_BLANK);
        }
    }

    private static void checkLottoSize(final List<Integer> numbers) {
        if (LOTTO_SIZE != Set.copyOf(numbers).size()) {
            throw new IllegalArgumentException(ERROR_CREATE_LOTTO);
        }
    }

    public void generateRandomNumbers(){
        List<Integer> numbers = LottoNumber.getcandidateLottoNumbers();
        Collections.shuffle(numbers);
        List<Integer> randomNumbers = numbers.subList(0, LOTTO_SIZE);
        Collections.sort(randomNumbers);

        this.lottoNumbers = convertLottoNumbers(randomNumbers);
    }

    public boolean contains(final int number) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(number));
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
