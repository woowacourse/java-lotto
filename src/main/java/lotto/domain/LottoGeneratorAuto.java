package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LottoGeneratorAuto implements LottoGenerator {
    private static final int START_LOTTO_NO = 1;
    private static final int END_LOTTO_NO = 45;
    private static final int LOTTO_FROM_INDEX = 0;
    private static final String ERROR_MESSAGE_NULL_POINT_LOTTO_FACTORY = "입력값이 비었습니다.";

    private static List<LottoNo> lottoNumberBox = new ArrayList<>();

    static {
        for (int number = START_LOTTO_NO; number <= END_LOTTO_NO; number++) {
            lottoNumberBox.add(new LottoNo(number));
        }
    }

    @Override
    public List<Lotto> generator(Customer customer) {
        validate(customer);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < customer.calculatorAutoLottoCount(); i++) {
            lottos.add(createLottoAuto());
        }
        return lottos;
    }

    private static void validate(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NULL_POINT_LOTTO_FACTORY);
        }
    }

    private static Lotto createLottoAuto() {
        Set<LottoNo> lotto = new TreeSet<>(pickSixRandomNo());
        return new Lotto(lotto);
    }

    private static List<LottoNo> pickSixRandomNo() {
        List<LottoNo> lotto = new ArrayList<>(lottoNumberBox);
        Collections.shuffle(lotto);
        lotto = lotto.subList(LOTTO_FROM_INDEX, Lotto.LOTTO_SIZE);
        return lotto;
    }
}
