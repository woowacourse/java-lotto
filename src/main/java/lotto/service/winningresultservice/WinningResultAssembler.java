package lotto.service.winningresultservice;

import lotto.dto.WinningResultDTO;
import lotto.model.winninglotto.WinningResult;

public class WinningResultAssembler {
    public static WinningResultDTO toDTO(WinningResult from, int lottoRoundId){

        return new WinningResultDTO(
                lottoRoundId,
                from.getRankCount("FIRST"),
                from.getRankCount("SECOND"),
                from.getRankCount("THIRD"),
                from.getRankCount("FOURTH"),
                from.getRankCount("FIFTH"),
                from.getRankCount("MISS"),
                from.getROI()
        );
    }
}
