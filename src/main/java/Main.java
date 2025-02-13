import controller.LottoController;
import domain.Lotto;
import service.LottoIssue;

public class Main {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoIssue());
        lottoController.start();
    }

}
