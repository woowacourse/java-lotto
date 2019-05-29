package lotto.domain;

import java.util.*;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final String EX_LOTTO_SIZE_MESSAGE = "로또 숫자는 6개여야 합니다.";
    private static final String EX_LOTTO_DUPLICATE_MESSAGE = "중복된 숫자는 사용할 수 없습니다";
    private final List<LottoNumber> lottoNumbers;


    private Lotto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        checkLottoNumberCondition();
    }

    public static Lotto create(List<LottoNumber> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    public static Lotto generate(List<Integer> lottoNumbers) {
        return new Lotto(generateLottoNumbers(lottoNumbers));
    }

    private static List<LottoNumber> generateLottoNumbers(List<Integer> lottoNumbers) {
        List<LottoNumber> generateLottoNumbers = new ArrayList<>();
        for (Integer lottoNumber : lottoNumbers) {
            generateLottoNumbers.add(new LottoNumber(lottoNumber));
        }
        return generateLottoNumbers;
    }

    private void checkLottoNumberCondition() {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(EX_LOTTO_SIZE_MESSAGE);
        }

        if (lottoNumbers.size() != new ArrayList<>(new HashSet<>(lottoNumbers)).size()) {
            throw new IllegalArgumentException(EX_LOTTO_DUPLICATE_MESSAGE);
        }
    }

    public String toStringWithFormat(String startSymbol, String endSymbol, String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startSymbol);
        StringJoiner stringJoiner = new StringJoiner(separator);
        for (LottoNumber lottoNumber : lottoNumbers) {
            stringJoiner.add(lottoNumber.toString());
        }
        stringBuilder.append(stringJoiner).append(endSymbol);
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
