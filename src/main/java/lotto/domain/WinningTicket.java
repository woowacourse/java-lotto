package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.generator.StringInputNumberGenerator;

public class WinningTicket {

    private LottoLine winningLine;
    private LottoNumber bonusBall;

    public WinningTicket(LottoLine winningLine, LottoNumber bonusBall) {
        validateBonusDistinct(winningLine, bonusBall);
        this.winningLine = winningLine;
        this.bonusBall = bonusBall;
    }

    public static WinningTicket from(String inputWinningNumber, String inputBonusBall) {
        LottoLine winningLine = LottoLine.createLine(new StringInputNumberGenerator(inputWinningNumber));
        LottoNumber bonusBall = LottoNumber.from(inputBonusBall);
        return new WinningTicket(winningLine, bonusBall);
    }

    private void validateBonusDistinct(LottoLine winningTicket, LottoNumber bonusBall) {
        if (winningTicket.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public List<LottoRank> compareTicket(LottoTicket ticket) {
        return ticket.getLines()
            .stream()
            .map(this::compareLine)
            .collect(Collectors.toList());
    }

    public LottoRank compareLine(LottoLine lottoLine) {
        int matchCount = lottoLine.countMatchingNumbers(winningLine);
        boolean isBonusMatch = lottoLine.contains(bonusBall);
        return LottoRank.find(matchCount, isBonusMatch);
    }
}
