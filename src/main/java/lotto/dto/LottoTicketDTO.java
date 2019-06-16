package lotto.dto;

import java.util.List;

public class LottoTicketDTO {
    private int round;
    private List<String> lottos;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public List<String> getLottos() {
        return lottos;
    }

    public void setLottos(List<String> lottos) {
        this.lottos = lottos;
    }
}
