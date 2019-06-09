package lotto.domain;

import java.util.*;

public class ManualLottoParser {

    public static UserLottos parseManualLottoNumbers(List<String[]> scannedManualLottoNumbers) {
        List<Lotto> manualLottos = new ArrayList<>();

        for (String[] scannedManualLottoNumber : scannedManualLottoNumbers) {
            Set<LottoNumber> manualLotto = new TreeSet<>();
            Arrays.stream(scannedManualLottoNumber)
                    .forEach(number -> manualLotto.add(new LottoNumber(Integer.parseInt(number.trim()))));

            manualLottos.add(new Lotto(manualLotto));
        }

        return new UserLottos(manualLottos);
    }
}
