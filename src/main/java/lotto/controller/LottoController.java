package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoOrder;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoTickets;
import lotto.domain.WinningNumbers;
import lotto.domain.lottonumbergenerator.LottoNumberAutoGenerator;
import lotto.domain.lottonumbergenerator.LottoNumberManualGenerator;
import lotto.dto.LottoResultDto;
import lotto.dto.LottoTicketsDto;

public class LottoController {

    private final LottoOrder lottoOrder;
    private LottoTickets lottoTickets;

    public LottoController(LottoOrder lottoOrder) {
        this.lottoOrder = lottoOrder;
    }

    public LottoTicketsDto publishLottoTickets() {
        lottoOrder.billingManualLottoOrder();
        LottoTickets manualLottoTickets = getManualLottoTickets(lottoOrder.getManualLottoNumbers());
        LottoTickets autoLottoTickets = getAutoLottoTickets(lottoOrder.getNumberOfAutoLotto());
        lottoTickets = manualLottoTickets.combine(autoLottoTickets);
        return LottoTicketsDto.from(manualLottoTickets, autoLottoTickets);
    }

    public LottoResultDto matchLottoTickets(List<Integer> lottoNumbers, int lottoNumber) {
        WinningNumbers winningNumbers = new WinningNumbers(getWinningNumbers(lottoNumbers),
                getBonusNumber(lottoNumber));
        LottoStatistics lottoStatistics = new LottoStatistics(lottoTickets.getRanksWithWinningNumbers(winningNumbers));
        return LottoResultDto.from(lottoStatistics, lottoOrder.getYield(lottoStatistics));
    }

    private LottoTickets getManualLottoTickets(List<List<Integer>> numbers) {
        return new LottoTickets(new LottoNumberManualGenerator(numbers), numbers.size());
    }

    private LottoTickets getAutoLottoTickets(int autoLottoCount) {
        return new LottoTickets(new LottoNumberAutoGenerator(), autoLottoCount);
    }

    private LottoNumber getBonusNumber(int lottoNumber) {
        return LottoNumber.valueOf(lottoNumber);
    }

    private List<LottoNumber> getWinningNumbers(List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }
}
