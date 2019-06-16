package lotto.service.winninglottoservice;

import lotto.dto.LottoTicketDTO;
import lotto.dto.WinningLottoDTO;
import lotto.model.winninglotto.WinningLotto;

import java.util.stream.Collectors;

public class WinningLottoAssembler {
    public static WinningLottoDTO toDTO(WinningLotto from) {
        String winningLottoTicket = from.getWinningLotto()
                .stream()
                .map(lottoNumber -> String.valueOf(lottoNumber.getLottoNumber()))
                .collect(Collectors.joining(", "));
        int bonusNumber = from.getBonusNumber().getLottoNumber();

        return new WinningLottoDTO(winningLottoTicket, bonusNumber);
    }
}
