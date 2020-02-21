package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoNumbersGenerator {
    private static Random random = new Random();

    /**
     * 질문: fillUpLottoNumber(lottoNumbers, lottoNumber)의 리턴 형식을 void로 할 지, List<Integer>로 할 지 고민이 됐습니다. 아래 이유 때문입니다.
     * 1. List<Integer>로 둘 경우, 메모리 문제나 성능 상 문제가 생기지 않을까 걱정이 됐습니다.
     * 2. 가독성 측면에서 void가 나은 지, List<Integer>가 나은 지 헷갈렸습니다.
     * 이에 대해 가르침 주시면 감사하겠습니다.
     */
    static List<Integer> generate() {
        List<Integer> lottoNumbers = new ArrayList<>();
        while (isFulfilled(lottoNumbers)) {
            int lottoNumber = pickLottoNumber();
            lottoNumbers = fillUpLottoNumber(lottoNumbers, lottoNumber);
        }
        return lottoNumbers;
    }

    private static List<Integer> fillUpLottoNumber(List<Integer> lottoNumbers, int lottoNumber) {
        if (!isDuplicated(lottoNumbers, lottoNumber)) {
            lottoNumbers.add(lottoNumber);
        }
        return lottoNumbers;
    }

    private static boolean isFulfilled(List<Integer> lottoNumbers) {
        return lottoNumbers.size() != LottoNumberConfig.SIZE;
    }

    private static boolean isDuplicated(List<Integer> lottoNumbers, int lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    private static int pickLottoNumber() {
        return random.nextInt(LottoNumberConfig.MAX - LottoNumberConfig.MIN) + LottoNumberConfig.MIN;
    }
}
