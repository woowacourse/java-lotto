public class LottoNumber {
	private int lottoNumber;

	public LottoNumber(String lottoNumber) {
		this.lottoNumber = Integer.parseInt(lottoNumber);
		if (this.lottoNumber < 1 || this.lottoNumber > 45) {
			throw new IllegalArgumentException("로또 범위를 벗어난 숫자입니다.");
		}
	}
}
