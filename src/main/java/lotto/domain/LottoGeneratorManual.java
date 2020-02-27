package lotto.domain;

import lotto.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGeneratorManual implements LottoGenerator {
    private static final String ERROR_MESSAGE_NOT_INTEGER = "숫자가 아닌 문자를 입력하였습니다.";

    @Override
    public List<Lotto> generator(Customer customer) {
        String[] manualLottoNumbers = StringUtils.splitByLineSeparator(
                customer.getManualLottoNumber());
        return createManualLotto(manualLottoNumbers);
    }

    private static List<Lotto> createManualLotto(String[] manualLottoNumbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (String numbers : manualLottoNumbers) {
            String[] winLottoNumbers = StringUtils.splitByComma(numbers);
            List<LottoNo> lotto = toLottoNoList(winLottoNumbers);
            lottos.add(new Lotto(lotto));
        }
        return lottos;
    }

    private static List<LottoNo> toLottoNoList(String[] winLotto) {
        try {
            return Arrays.stream(winLotto)
                    .map(Integer::parseInt)
                    .map(LottoNo::new)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NOT_INTEGER);
        }
    }
}
