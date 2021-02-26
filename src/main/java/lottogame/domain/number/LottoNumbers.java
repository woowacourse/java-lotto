package lottogame.domain.number;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class LottoNumbers {

    public static final int DRAWING_COUNT_LIMIT = 6;
    public static final String DELIMITER = ",";

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(final String lottoNumbers) {
        this(getSplitLottoNumbersByString(lottoNumbers));
    }

    public LottoNumbers(final List<LottoNumber> lottoNumbers) {
        validDrawingNumbers(lottoNumbers);
        lottoNumbers.sort((Comparator.comparingInt(LottoNumber::getValue)));
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    private static List<LottoNumber> getSplitLottoNumbersByString(String lottoNumbers) {
        lottoNumbers = lottoNumbers.replaceAll(" ", "");
        final List<LottoNumber> lottoNumberGroup = new ArrayList<>();

        for (String number : lottoNumbers.split(DELIMITER)) {
            lottoNumberGroup.add(LottoNumber.of(number));
        }
        return lottoNumberGroup;
    }

    public boolean contains(final LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> toList() {
        return new ArrayList<>(this.lottoNumbers);
    }

    public int size() {
        return lottoNumbers.size();
    }

    private void validDrawingNumbers(final List<LottoNumber> lottoNumbers) {
        if (new HashSet<>(lottoNumbers).size() != DRAWING_COUNT_LIMIT) {
            throw new IllegalArgumentException("잘못된 로또 번호 집합입니다.");
        }
    }
}
