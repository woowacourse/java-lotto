package view;

public class RateOfReturnOutputView implements OutputView<Double> {

    @Override
    public void printOutputData(Double rateOfReturn) {
        System.out.println("총 수익률은 " + String.format("%.2f", rateOfReturn) + "입니다.");
    }
}

