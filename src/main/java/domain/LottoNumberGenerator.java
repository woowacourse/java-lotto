package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import util.Validator;

public class LottoNumberGenerator {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 46;
    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 6;
    private static final String NUMBER_OF_PICK_LOTTO_OVER_LIMIT_EXCEPTION = "[ERROR] 수동로또 개수는 전체 구입한 로또 개수를 초과할 수 없습니다.";
    private static final String NUMBER_OF_LOTTO_OVER_LIMIT_EXCEPTION = "[ERROR] 전체 로또 개수를 초과해 생성했습니다.";

    private int usedNum;
    private final int totalLottoNumber;
    private final List<List<Integer>> pickLottoNumbersBucket;

    public LottoNumberGenerator(int numberOfLotto, List<List<Integer>> pickLottoNumbersBucket) {
        validateNumberOfPickLotto(numberOfLotto, pickLottoNumbersBucket);
        this.usedNum = 0;
        this.totalLottoNumber = numberOfLotto;
        this.pickLottoNumbersBucket = pickLottoNumbersBucket;
    }

    public static LottoNumberGenerator build(int numberOfLotto, List<List<Integer>> pickLottoNumbersBucket) {
        Validator.checkArgumentIsNull(pickLottoNumbersBucket);
        return new LottoNumberGenerator(numberOfLotto, pickLottoNumbersBucket);
    }

    public List<Integer> generateNumbers() {
        if (usedNum > totalLottoNumber) {
            throw new IllegalArgumentException(NUMBER_OF_LOTTO_OVER_LIMIT_EXCEPTION);
        }
        if (isPickLottoTurn()) {
            return pickLottoNumbersBucket.get(usedNum++);
        }
        return generateRandomNumber();
    }

    public boolean isPicked() {
        return usedNum < pickLottoNumbersBucket.size();
    }

    private boolean isPickLottoTurn() {
        return usedNum < pickLottoNumbersBucket.size();
    }

    private List<Integer> generateRandomNumber() {
        List<Integer> lottoNumberCandidates = IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed().collect(Collectors.toList());
        return pickNumbersFromCandidates(lottoNumberCandidates);
    }

    private List<Integer> pickNumbersFromCandidates(List<Integer> cadidates) {
        Collections.shuffle(cadidates);
        return cadidates.subList(MIN_RANGE, MAX_RANGE);
    }

    private void validateNumberOfPickLotto(int numberOfLotto, List<List<Integer>> pickLottoNumbersBucket) {
        if (numberOfLotto < pickLottoNumbersBucket.size()) {
            throw new IllegalArgumentException(NUMBER_OF_PICK_LOTTO_OVER_LIMIT_EXCEPTION);
        }
    }
}
