package lotto.dto;

public class StatisticsDTO {
    private String round;
    private String result;
    private String returnOfRate;

    public StatisticsDTO(String round, String result, String returnOfRate) {
        this.round = round;
        this.result = result;
        this.returnOfRate = returnOfRate;
    }

    public String getRound() {
        return round;
    }

    public String getResult() {
        return result;
    }

    public String getReturnOfRate() {
        return returnOfRate;
    }
}
