package lotto.dto;

import lotto.domain.lotto.LottoTicketGroup;

public class LottoHistoryDTO {
    private int round;
    private WinningLottoDTO winningLottoDTO;
    private LottoResultDTO lottoResultDTO;
    private LottoTicketGroup lottos;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public WinningLottoDTO getWinningLottoDTO() {
        return winningLottoDTO;
    }

    public void setWinningLottoDTO(WinningLottoDTO winningLottoDTO) {
        this.winningLottoDTO = winningLottoDTO;
    }

    public LottoResultDTO getLottoResultDTO() {
        return lottoResultDTO;
    }

    public void setLottoResultDTO(LottoResultDTO lottoResultDTO) {
        this.lottoResultDTO = lottoResultDTO;
    }

    public LottoTicketGroup getLottos() {
        return lottos;
    }

    public void setLottos(LottoTicketGroup lottos) {
        this.lottos = lottos;
    }
}
