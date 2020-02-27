package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoBundleFactory {

    private static final int START_INDEX = 0;

    public static LottoBundle generate(LottoCount lottoCount, ManualCount manualCount, String[] manualInputs) {
        LottoBundle lottoBundle = new LottoBundle();
        lottoBundle.addLottoBundle(createLottoBundle(manualCount.getManualCount(), new ManualLottoFactory(manualInputs)));
        lottoBundle.addLottoBundle(createLottoBundle(lottoCount.getAutoLottoCount(manualCount), new AutoLottoFactory()));
        return lottoBundle;
    }

    private static List<Lotto> createLottoBundle(final int lottoCount, LottoFactory lottoFactory) {
        List<Lotto> lottoBundle = new ArrayList<>();
        for (int index = START_INDEX; index < lottoCount; index++) {
            lottoBundle.add(lottoFactory.createOneLotto());
        }
        return lottoBundle;
    }
}
