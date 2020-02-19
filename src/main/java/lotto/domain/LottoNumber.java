package lotto.domain;

import org.eclipse.jetty.server.LowResourceMonitor;

import java.util.Hashtable;
import java.util.Map;

public class LottoNumber {
    private static final Map<Integer, LottoNumber> lottoNumberMapper = new Hashtable<>();

    private int number;

    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 45;

    static {
        for (int i = LOWER_BOUND; i <= UPPER_BOUND; i++) {
            lottoNumberMapper.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int number) {
        validateBound(number);
        return lottoNumberMapper.get(number);
    }

    public int getNumber() {
        return number;
    }

    private static void validateBound(int number) {
        if(LOWER_BOUND > number || UPPER_BOUND < number) {
            throw new IllegalArgumentException("범위를 벗어난 숫자가 생성되었습니다.");
        }
    }
}
