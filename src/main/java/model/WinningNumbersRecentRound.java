package model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class WinningNumbersRecentRound {
    private static int recentRound = new WinningNumbersWeb().round();
    private static LocalDateTime scheduledFetchDate = nextAnnouncementDate();

    public static int getRecentRound() {
        if (LocalDateTime.now().isAfter(scheduledFetchDate)) {
            refresh();
        }
        return recentRound;
    }

    public static int refresh() {
        WinningNumbersWeb recentWinningNumbers = new WinningNumbersWeb();
        if (recentWinningNumbers.round() > recentRound) {
            recentRound = recentWinningNumbers.round();
            scheduledFetchDate = nextAnnouncementDate();
            WinningNumbersDAO.register(recentWinningNumbers);
        }
        return recentRound;
    }

    private static LocalDateTime nextAnnouncementDate() {
        LocalDateTime candidateA = LocalDateTime.now().with(
                TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)
        ).plusHours(21).plusMinutes(30);
        LocalDateTime candidateB = LocalDateTime.now().with(
                TemporalAdjusters.next(DayOfWeek.SATURDAY)
        ).plusHours(21).plusMinutes(30);
        return (LocalDateTime.now().isAfter(candidateA)) ? candidateA : candidateB;
    }
}
