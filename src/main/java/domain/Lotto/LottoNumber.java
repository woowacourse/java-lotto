package domain.Lotto;

import utils.ExceptionMessage;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
import java.util.ArrayList;
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)
import java.util.List;
import java.util.Objects;
=======
import java.util.*;
>>>>>>> 9f88acd (refactor : LottoNumber 컬렉션 Map으로 변경)

public class LottoNumber {

    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)
    private static final List<LottoNumber> CACHE = new ArrayList<>();
=======
    private static final Map<Integer, LottoNumber> CACHE = new HashMap<>();
>>>>>>> 9f88acd (refactor : LottoNumber 컬렉션 Map으로 변경)

    static {
        for (int number = MINIMUM_LOTTO_NUMBER; number <= MAXIMUM_LOTTO_NUMBER; number++) {
            CACHE.put(number, new LottoNumber(number));
        }
    }
<<<<<<< HEAD
=======
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)

    private final int number;

    public LottoNumber(int number) {
        validateLottoNumberBound(number);
        this.number = number;
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)
=======
    private void validateLottoNumberBound(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_OUT_OF_BOUND);
        }
    }

>>>>>>> 4022ea6 (refactor: 메서드 위치 변경)
    public static LottoNumber valueOf(int number) {
        LottoNumber lottoNumber = CACHE.get(number);

        if (lottoNumber == null) {
            lottoNumber = new LottoNumber(number);
<<<<<<< HEAD
        }
        return lottoNumber;
    }

    public static List<LottoNumber> values() {
        return CACHE;
    }

    private void validateLottoNumberBound(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_OUT_OF_BOUND);
        }
    }

    public int getNumber() {
        return number;
=======
    private void validateLottoNumberBound(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_OUT_OF_BOUND);
=======
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)
        }
        return lottoNumber;
    }

    public static List<LottoNumber> values() {
        return new ArrayList<>(CACHE.values());
    }

<<<<<<< HEAD
    private void validateLottoNumberBound(int number) {
        if (number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_OUT_OF_BOUND);
        }
    }

<<<<<<< HEAD
    @Override
    public int hashCode() {
        return Objects.hash(number);
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
=======
>>>>>>> 4022ea6 (refactor: 메서드 위치 변경)
    public int getNumber() {
        return number;
>>>>>>> 8185971 (feat : 반복되는 LottoNumber 인스턴스 캐싱하기)
    }
}