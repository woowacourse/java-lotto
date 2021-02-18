package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final int SPLIT_THRESHOLD = -1;
    private static final String DELIMITER = ", ";

    private final List<LottoNumber> lottoNumbers;

    private LottoNumbers(final List<LottoNumber> lottoNumbers) {
        List<LottoNumber> copy = new ArrayList<>(lottoNumbers);
        validateLottoNumbers(copy);
        this.lottoNumbers = copy;
    }

    public LottoNumbers(final String lottoNumbers) {
        String[] temp = lottoNumbers.split(DELIMITER, SPLIT_THRESHOLD);
        List<LottoNumber> tempLottoNumbers = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            tempLottoNumbers.add(LottoNumber.of(temp[i]));
        }
        validateLottoNumbers(tempLottoNumbers);
        this.lottoNumbers = tempLottoNumbers;
    }

    public static LottoNumbers of(final List<LottoNumber> lottoNumbers) {
        return new LottoNumbers(lottoNumbers);
    }

    public static LottoNumbers generate(final List<Integer> lottoNumbers) {
        List<LottoNumber> lottoNumberGroup = lottoNumbers.stream()
                .map(lottoNumber -> new LottoNumber(lottoNumber))
                .collect(Collectors.toList());
        return new LottoNumbers(lottoNumberGroup);
    }

    private void validateLottoNumbers(final List<LottoNumber> lottoNumbers) {
        validateDuplicate(lottoNumbers);
        validateSize(lottoNumbers);
    }

    private void validateDuplicate(final List<LottoNumber> lottoNumbers) {
        boolean isUnique = lottoNumbers.stream()
                .allMatch(new HashSet<>()::add);
        if (!isUnique) {
            throw new IllegalArgumentException("로또 번호에 중복된 값이 있습니다. 다시 입력해주세요.");
        }
    }

    private void validateSize(final List lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("6개의 로또 번호가 필요합니다.");
        }
    }

    public boolean containNumber(final BonusNumber bonusNumber) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumber -> bonusNumber.isSameNumber(lottoNumber));
    }

    public List<LottoNumber> getLottoNumbers() {
        List<LottoNumber> copy = new ArrayList<>(this.lottoNumbers);
        return Collections.unmodifiableList(copy);
    }
}
