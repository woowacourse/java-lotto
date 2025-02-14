public class AppConfig {
    public LottoController controller() {
        return new LottoController(new InputView(), new OutputView(), lottoManager());
    }

    private LottoManager lottoManager() {
        return new LottoManager(randomNumbersGenerator());
    }

    private RandomNumbersGenerator randomNumbersGenerator() {
        return new RandomLottoNumbersGenerator();
    }
}
