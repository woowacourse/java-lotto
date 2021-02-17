package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.lottogenerator.LottoGenerator;

public class Lotto {

    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto generatedBy(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
            .map(number -> LottoNumber.valueOf(number))
            .collect(Collectors.toList());
        return Lotto.generatedBy(() -> lottoNumbers);
    }

    public static Lotto generatedBy(LottoGenerator lottoGenerator) {
        List<LottoNumber> lottoNumbers = lottoGenerator.generateLottoNumbers();
        validateLotto(lottoNumbers);
        return new Lotto(lottoNumbers);
    }

    private static void validateLotto(List<LottoNumber> lottoNumbers) {
        validateLottoLength(lottoNumbers);
        validateLottoNumberNotDuplicate(lottoNumbers);
    }

    private static void validateLottoLength(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개의 숫자로 이루어져야 합니다.");
        }
    }

    private static void validateLottoNumberNotDuplicate(List<LottoNumber> lottoNumbers) {
        List<LottoNumber> distinctedLottoNumbers = lottoNumbers.stream()
            .distinct()
            .collect(Collectors.toList());

        if (distinctedLottoNumbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 중복된 숫자가 존재할 수 없습니다.");
        }
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
