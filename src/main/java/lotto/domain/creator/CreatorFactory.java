package lotto.domain.creator;

import lotto.domain.util.CustomStringUtils;
import lotto.exception.InvalidLottoNumbersException;

import java.util.ArrayList;
import java.util.List;

public class CreatorFactory {
    private final List<String> inputs;

    public CreatorFactory(List<String> inputs) {
        this.inputs = inputs;
    }

    public List<LottoCreator> createCreators(int manualLottoQuantity, int autoLottoQuantity) {
        List<LottoCreator> creators = new ArrayList<>();

        creators.addAll(createAutoLottoCreators(autoLottoQuantity));
        creators.addAll(createManualLottoCreators(manualLottoQuantity));
        return creators;
    }

    private List<LottoCreator> createAutoLottoCreators(int lottoQuantity) {
        List<LottoCreator> creators = new ArrayList<>();

        for (int i = 0; i < lottoQuantity; i++) {
            creators.add(new AutoLottoCreator());
        }
        return creators;
    }

    private List<LottoCreator> createManualLottoCreators(int lottoQuantity) {
        checkNumberOfInputLotto(inputs, lottoQuantity);

        List<LottoCreator> creators = new ArrayList<>();

        for (String input : inputs) {
            creators.add(new ManualLottoCreator(CustomStringUtils.parseInts(input)));
        }
        return creators;
    }

    private void checkNumberOfInputLotto(List<String> inputs, int lottoQuantity) {
        if (inputs.size() != lottoQuantity) {
            throw new InvalidLottoNumbersException("입력하신 수동 로또 수와 입력하신 번호 셋트의 수가 일치하지 않습니다.");
        }
    }
}
