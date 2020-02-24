package domain;

import java.util.ArrayList;
import java.util.List;

public class LottosFactory {

    public static final int START_INDEX = 0;

    public static List<Lotto> createAutoLottos(final int autoLottoCount) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int index = START_INDEX; index < autoLottoCount; index++) {
            autoLottos.add(LottoFactory.createOneLotto());
        }
        return autoLottos;
    }
}
