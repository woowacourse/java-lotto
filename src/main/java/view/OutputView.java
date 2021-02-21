package view;

import dto.LottoTicketsDto;
import dto.PrizeDto;
import dto.SingleLottoTicketDto;
import dto.WinningStaticsDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printLottoQuantity(final int lottoQuantity) {
        System.out.println(String.format("%d개를 구매했습니다.", lottoQuantity));
    }

    public static void printLottoTickets(final LottoTicketsDto lottoTicketsDto) {
        final List<SingleLottoTicketDto> singleLottoTicketDtos = lottoTicketsDto.getSingleLottoTicketDtos();
        singleLottoTicketDtos.forEach(
                ticket -> printLottoNumbers(ticket.getLottoNumbers())
        );
    }

    private static void printLottoNumbers(final List<Integer> numbers) {
        String joinNumbers = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        System.out.println("[" + joinNumbers + "]");
    }

    public static void printWinningStaticsTitle() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void printWinningStatics(final WinningStaticsDto winningStaticsDto) {
        List<PrizeDto> prizeDtos = winningStaticsDto.getPrizeDtos();
        prizeDtos.forEach(
                prizeDto -> printWinningStaticsPerPrize(prizeDto)
        );
    }

    private static void printWinningStaticsPerPrize(final PrizeDto prizeDtos) {
        System.out.println(makePrizeStaticsMessage(prizeDtos));
    }

    private static String makePrizeStaticsMessage(final PrizeDto prizeDto) {
        final int matching = prizeDto.getMatching();
        final boolean bonusMatching = prizeDto.isBonusMatching();
        final long money = prizeDto.getMoney();
        final int winningNumber = prizeDto.getWinningNumber();

        return String.format("%d개 일치 %s(%d원) - %d개",
                matching, getBonusMatchMessage(bonusMatching), money, winningNumber);
    }

    private static String getBonusMatchMessage(final boolean bonusMatching) {
        if (bonusMatching) {
            return ", 보너스 볼 일치";
        }
        return "";
    }

    public static void printProfitRate(final double profitRate) {
        final double flooredProfitRate = Math.floor(profitRate * 100.) / 100.;
        System.out.println(String.format("총 수익률은 %.2f입니다.", flooredProfitRate));
    }
}
