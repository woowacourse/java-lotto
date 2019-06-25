package model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class WinningNumbersRecentRound {
    private static int recentRound = new WinningNumbersWeb().round();
    private static LocalDateTime scheduledFetchDate = nextAnnouncementDate();

    protected static int getRecentRound() {
        if (LocalDateTime.now().isAfter(scheduledFetchDate)) {
            refresh();
        }
        return recentRound;
    }

    protected static void refresh() {
        WinningNumbersWeb recentWinningNumbers = new WinningNumbersWeb();
        if (recentWinningNumbers.round() > recentRound) {
            recentRound = recentWinningNumbers.round();
            scheduledFetchDate = nextAnnouncementDate();
            WinningNumbersDAO.register(recentWinningNumbers);
        }
    }

    private static LocalDateTime nextAnnouncementDate() {
        LocalDateTime nextSaturdayOrToday = LocalDateTime.now().with(
                TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)
        ).plusHours(21).plusMinutes(30);
        LocalDateTime nextSaturday = LocalDateTime.now().with(
                TemporalAdjusters.next(DayOfWeek.SATURDAY)
        ).plusHours(21).plusMinutes(30);
        return (nextSaturdayOrToday.equals(nextSaturday)) ? nextSaturday : nextSaturdayOrToday;
    }
}
