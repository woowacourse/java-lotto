package domain;

public class LottoTicketOffice {

    private static final String MANUAL_NUMBER_TOO_LARGE_EXCEPTION = "[ERROR] 수동 로또 개수가 너무 많습니다.";

    public static int buyLottoTicket(Money money) {
        return money.money() / LottoGame.LOTTO_PRICE;
    }

    private static void checkValidManualNumber(int totalNumber, int manualNumber) {
        if (manualNumber > totalNumber) {
            throw new IllegalArgumentException(MANUAL_NUMBER_TOO_LARGE_EXCEPTION);
        }
    }

    public static int calculateAutoNumber(int totalNumber, int manualNumber) {
        checkValidManualNumber(totalNumber, manualNumber);
        return totalNumber - manualNumber;
    }
}
