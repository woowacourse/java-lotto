package domain.lottonumber;

import java.util.*;

public class LottoNumberPool {
    private static final int BASE_SEED = 0;
    private static final List<LottoNumber> pool = new ArrayList<>();

    static {
        for (int number = LottoNumber.MIN_NUMBER; number <= LottoNumber.MAX_NUMBER; number++) {
            pool.add(LottoNumber.of(number));
        }
    }

    public static LottoNumber pickLottoNumber(int number) {
        return pool.stream()
                .filter(lottonumber -> lottonumber.isOf(number))
                .findFirst()
                .orElseThrow(IllegalLottoNumberException::new);
    }

    public static Set<LottoNumber> randomPickAsManyAs(int numberOfLottoNumber) {
        if (numberOfLottoNumber > pool.size()) {
            throw new IllegalArgumentException("뽑을 수 있는 로또 번호의 개수는 45개를 넘을 수 없습니다.");
        }

        Collections.shuffle(pool);
        return new TreeSet<>(pool.subList(BASE_SEED, numberOfLottoNumber + BASE_SEED));
    }
}
