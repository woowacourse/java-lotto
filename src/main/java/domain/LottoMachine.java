package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
    private static final List<LottoNumber> lottoBucket = new ArrayList<>();

    static {
        for (int i = LottoNumber.MIN_BOUND; i <= LottoNumber.MAX_BOUND; i++) {
            lottoBucket.add(LottoNumber.valueOf(i));
        }
    }

    public Lottos createManualLottos(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public Lottos createAutoLottos(int autoTicketCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int count = 0; count < autoTicketCount; count++) {
            lottos.add(createAutoLotto());
        }
        return new Lottos(lottos);
    }

    private Lotto createAutoLotto() {
        Collections.shuffle(lottoBucket);
        return new Lotto(lottoBucket.stream()
                .limit(Lotto.LOTTO_SIZE)
                .collect(Collectors.toUnmodifiableSet()));
    }
}
