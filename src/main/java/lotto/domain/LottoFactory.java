package lotto.domain;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import lotto.util.StringUtil;

import static lotto.domain.Number.MAX_LOTTO_NUMBER;
import static lotto.domain.Number.MIN_LOTTO_NUMBER;

public class LottoFactory {
    public static final int LOWER_BOUND = 0;
    public static final int UPPER_BOUND = 6;

    private static final Map<Integer, Number> numberPool = new ConcurrentHashMap<>();

    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            numberPool.put(i, new Number(Integer.toString(i)));
        }
    }

    public static List<Number> getNumberPool() {
        return new ArrayList<>(numberPool.values());
    }

    public static Number of(String number) {
        Number.validate(number);
        return numberPool.get(Integer.parseInt(number));
    }

    /**
     * 로또 한장을 만드는 메소드
     *
     * @return Lotto
     */
    public static Lotto create() {
        List<Number> numbers = getNumberPool();
        Collections.shuffle(numbers);
        return new Lotto(numbers.subList(LOWER_BOUND, UPPER_BOUND));
    }

    /**
     * 당첨 로또를 생성하는 메소
     *
     * @param winningNumbers
     * @return Lotto
     */
    public static Lotto create(String winningNumbers) {
        List<String> numbers
                = StringUtil.parseByComma(StringUtil.removeBlank(winningNumbers));
        List<Number> lotto = numbers.stream()
                .map(LottoFactory::of)
                .collect(Collectors.toList());
        Collections.shuffle(lotto);
        return new Lotto(lotto);
    }

    /**
     * 구매한 금액만큼 자동으로 로또 여러장을 생성하는 메소드
     *
     * @param count
     * @return Lottos
     */
    public static Lottos create(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(create());
        }
        return new Lottos(lottos);
    }

    public static Lottos create(List<String> list, int autoCount){
        List<Lotto> lottos = new ArrayList<>();
        for (String input : list) {
            lottos.add(create(input));
        }
        for(int i =0; i< autoCount;i++) {
            lottos.add(create());
        }
        return new Lottos(lottos);
    }
}
