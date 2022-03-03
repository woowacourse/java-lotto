package domain;

import java.util.ArrayList;
import java.util.List;

public class IntendedLottoNumberGenerator implements LottoNumberGenerator {

    private static final String LACK_OF_LOTTO_NUMBERS_EXCEPTION = "[ERROR] 테스트 코드에서 사용되는 로또 번호 생성의 로또 번호 개수가 부족합니다.";

    private int index;
    private List<List<Integer>> lottoNumbersBucket;

    public IntendedLottoNumberGenerator() {
        init();
    }

    public void addLottoNumbers(List<Integer> lottoNumbers) {
        lottoNumbersBucket.add(lottoNumbers);
    }

    @Override
    public List<Integer> generateLottoNumbers() {
        try {
            return lottoNumbersBucket.get(index++);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException(LACK_OF_LOTTO_NUMBERS_EXCEPTION);
        }
    }

    private void init() {
        index = 0;
        lottoNumbersBucket = new ArrayList<>();
    }
}
