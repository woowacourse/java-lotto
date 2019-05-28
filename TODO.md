1. LottoNumber
    * 1~45 중 하나의 값을 가진다.
        * [x] new Lotto(0) -> Exception
2. Lotto
    * Lotto는 하나에 6개의 숫자를 가진다.
        * [x] Lotto.size == 6
    * 각각의 숫자는 중복되지 않는다.
        * [x] Lotto.size == HashSet<>(lotto).size
3. Money
    * 로또 최소 구입 금액은 1000원이다.
        * [x] Money > 1000
    * 금액은 1000원 단위이다.
        * [x] Money % 1000 == 0
4. WinningLotto
    * 당첨 번호와 보너스 넘버는 중복되지 않는다.
        * winningLotte.contains(bonusNumber) == false
4. LottoPaper
5. LottoVendingMachine
