package domain;

import java.util.ArrayList;
import java.util.List;
import util.NumberPicker;

public class LottoMachine {

    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;
    private static final int NUMBER_COUNT = 6;
    private static final int PRICE = 1000;

    private final int purchaseMoney;
    private final NumberPicker numberPicker;

    private LottoMachine(int purchaseMoney, NumberPicker numberPicker) {
        validateMinPrice(purchaseMoney);
        this.purchaseMoney = purchaseMoney;
        this.numberPicker = numberPicker;
    }

    public static LottoMachine of(int purchaseMoney, NumberPicker numberPicker) {
        return new LottoMachine(purchaseMoney, numberPicker);
    }

    public Lottos issueLottos() {
        int lottoCount = calculateTicketCount();
        List<Lotto> lottos = isssueLottoBy(lottoCount);
        return new Lottos(lottos);
    }

    private List<Lotto> isssueLottoBy(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            insertLottoTo(lottos);
        }
        return lottos;
    }

    private void insertLottoTo(List<Lotto> lottos) {
        Numbers numbers = numberPicker.pickUnique(START_NUMBER, END_NUMBER, NUMBER_COUNT);
        Lotto lotto = new Lotto(numbers);
        lottos.add(lotto);
    }

    private int calculateTicketCount() {
        return purchaseMoney / PRICE;
    }

    private void validateMinPrice(int purchaseMoney) {
        if (purchaseMoney < PRICE) {
            throw new IllegalStateException("금액은 1000원 이상이여아 합니다.");
        }
    }
}
