package lotto.dto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;

public class RequestManualLottoNumbersDto {

    private List<Lotto> manualLottos;

    public RequestManualLottoNumbersDto(List<List<LottoNumber>> manualLottoNumbers) {
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
