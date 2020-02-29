package lotto.domain;

public class Count {
    private static final int ZERO = 0;

    private int ticketCount;

    public Count(String ticketCount){
        validateIntegerNumberFormat(ticketCount);
        validatePositiveNumber(ticketCount);

        this.ticketCount = Integer.parseInt(ticketCount);
    }

    private static void validatePositiveNumber(String input) {
        if (Integer.parseInt(input) < ZERO) {
            throw new IllegalArgumentException("음수입니다. 재입력 해주세요");
        }
    }

    private static void validateIntegerNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (RuntimeException e) {
            throw new NumberFormatException("정수만 입력 가능합니다. 재입력 해주세요.");
        }
    }


    public int getTicketCount() {
        return ticketCount;
    }

    public void calculateTicketCount(int manualTicketCount){
        this.ticketCount -= manualTicketCount;
    }
}
