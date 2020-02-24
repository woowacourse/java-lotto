package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoBundleFactory {

    public static final int START_INDEX = 0;

    public static List<Lotto> createAutoLottoBundle(final int autoLottoCount) {
        List<Lotto> autoLottoBundle = new ArrayList<>();
        for (int index = START_INDEX; index < autoLottoCount; index++) {
            autoLottoBundle.add(LottoFactory.createOneLotto());
        }
        return autoLottoBundle;
    }
}
