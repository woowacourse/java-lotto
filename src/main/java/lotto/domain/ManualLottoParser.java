package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManualLottoParser {

    public static List<Lotto> parseManualLottoNumbers(List<String[]> scannedManualLottoNumbers) {
        List<Lotto> manualLottos = new ArrayList<>();

        for (String[] scannedManualLottoNumber : scannedManualLottoNumbers) {
            List<LottoNumber> manualLotto = new ArrayList<>();
            Arrays.stream(scannedManualLottoNumber)
                    .forEach(number -> manualLotto.add(new LottoNumber(Integer.parseInt(number.trim()))));

            manualLottos.add(new UserLotto(manualLotto));
        }

        return manualLottos;
    }
}
