package lotto.service.winningresultservice;

import lotto.dto.WinningResultDTO;
import lotto.model.winninglotto.WinningResult;

public class WinningResultAssembler {
    public static WinningResultDTO toDTO(WinningResult from, int lottoRoundId){
        WinningResultDTO winningResultDTO = new WinningResultDTO();
        long first = from.getRankCount("FIRST");
        long second = from.getRankCount("SECOND");
        long third = from.getRankCount("THIRD");
        long fourth = from.getRankCount("FOURTH");
        long fifth = from.getRankCount("FIFTH");
        long miss = from.getRankCount("MISS");
        long roi = from.getROI();

        winningResultDTO.setLottoRoundId(lottoRoundId);
        winningResultDTO.setFirst(first);
        winningResultDTO.setSecond(second);
        winningResultDTO.setThird(third);
        winningResultDTO.setFourth(fourth);
        winningResultDTO.setFifth(fifth);
        winningResultDTO.setMiss(miss);
        winningResultDTO.setRoi(roi);

        return winningResultDTO;
    }
}
