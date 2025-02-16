import controller.LottoController;
import service.LottoMachine;
import service.LottoWinningChecker;
import view.InputHandler;
import view.OutputHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            InputHandler inputHandler = new InputHandler(bufferedReader);
            OutputHandler outputHandler = new OutputHandler();

            LottoMachine lottoMachine = new LottoMachine();
            LottoWinningChecker lottoWinningChecker = new LottoWinningChecker();

            LottoController lottoController = new LottoController(inputHandler, outputHandler, lottoMachine, lottoWinningChecker);
            lottoController.start();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("잘못된 입력이 감지되었습니다: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("예상치 못한 오류가 발생했습니다. 프로그램을 종료합니다.");
        }
    }
}