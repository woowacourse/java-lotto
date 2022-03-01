package lotto.dto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;

public class RequestManualLottoNumbers {

    private List<Lotto> manualLottos;

    public RequestManualLottoNumbers(List<List<LottoNumber>> manualLottoNumbers) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (List<LottoNumber> manualLottoNumber : manualLottoNumbers) {
            manualLottos.add(new Lotto(manualLottoNumber));
        }
        this.manualLottos = manualLottos;
    }

    public List<Lotto> getManualLottos() {
        return manualLottos;
    }
}
