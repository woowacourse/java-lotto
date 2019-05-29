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
        * [x] winningLotte.contains(bonusNumber) == false
5. Rank
    * 매치수와 로또 당첨금을 가지고 있는다.
4. LottoPaper
    * 사용자의 로또들을 가지고 있는다.
5. LottoVendingMachine
    * 사용자가 커스텀하는 로또를 제외한 금액만큼의 로또를 자동 생성한다.
6. CustomLottoPaper
7. AutoLottoGenerator
    * 1~45의 번호를 가지고 있다.
    * 필요할때 셔플후 앞 6개를 로또로 발급한다.