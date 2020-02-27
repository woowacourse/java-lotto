package domain;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;

public class ManualLottoFactory implements LottoFactory{

    private String[] manualLotto;

    public ManualLottoFactory(String[] manualLotto) {
        this.manualLotto = manualLotto;
    }

    @Override
    public Lotto createOneLotto() {
        try {
            return new Lotto(Arrays.stream(manualLotto)
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .mapToObj(AllLottoNumbers::get)
                    .collect(toList()));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("로또 넘버는 숫자여야 합니다.");
        }
    }
}
