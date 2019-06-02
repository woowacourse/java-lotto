# java-lotto
로또 미션 진행을 위한 저장소


## TODO
#### view
* 구입 금액 입력 받았을 때,
    * null인지 확인
    * isEmpty 확인
    * 자연수인지 확인하기
* 수동 로또 입력값을 받았을 때,
    * null인지 확인
    * isEmpty 확인
    * 빈칸 없애기
* 수동 로또 입력값을 split하고 리스트로 던지기
* 당첨 번호를 입력했을 때,
    * null인지 확인
    * isEmpty 확인
    * 빈칸 없애기
#### domain
* Money
* LottoTicketFactory
    * 수동 로또인지 자동 로또인지 구분해서 객체 생성하기
    * **자동 로또의 경우**
* LottoTicket: 로또 한 장
    * **수동 로또의 경우**
* LottoTicketsFactory
* LottoTickets: 로또 리스트, 구매할 수동 로또 수
* LottoNumber
    * **수동 로또의 경우**
    * **자동 로또의 경우**
        * subList해서 6개 전달하는 함수 만들기
            * purchaseAutoNumbers() -> return List<LottoNumber>
            * **[예외]** 중복되는 번호가 있는지 확인
* LottoNumbersFactory
* WinningLotto: 싱글톤 클래스
* RankType: Enum
* 당첨 시 집계하기
* 수익률 계산하기
* 예외 클래스 생성하기

---
## Done
#### view
#### domain
* Money
    * 구입 금액 저장하기
    * 구입 금액이 해당 조건에 부합하는지 확인하기
        * **[예외]** 입력값이 1000미만일 경우
        * -13 -> error!
        * 4.4 -> error!
    * 로또 게임 횟수 리턴하기1
        * 1000 -> 1
        * 14000 -> 14
    * 당첨금 누적하기
        * 5000 -> 5000
        * 2000 -> 7000
        * 10000 -> 17000
    * 수익률 계산하기
* LottoTicketFactory
    * **수동 로또의 경우**
        * String으로 로또 번호 6개가 입력되면 LottoNumber 리스트에 저장하기
            * "1,2,3,4,5,6" -> [1,2,3,4,5,6]
            * "1,2,3,4,5,5" -> error!
            * "1,2,3,4,5" -> error!
            * **[예외]** 입력된 값에 자연수 외에 다른 값이 있는지 확인
            * **[예외]** 중복된 숫자가 있는지 확인
* LottoTicket: 로또 한 장
    * 당첨 번호와 일치하는 수 카운트해서 리턴하기
    * 보너스 볼과 일치하는 수가 있는지 확인해서 boolean 리턴하기
* LottoTicketsFactory
    * String 배열로 받아온 번호들을 LottoNumber 리스트로 변환해서 리턴하기
* LottoTickets: 로또 리스트, 구매할 수동 로또 수
    * 입력한 수동 로또의 수량과 실제 입력값이 동일한지 확인
* LottoNumber
    * **수동 로또의 경우**
    * 요청한 로또 번호에 대한 객체 리턴
        * LottoNumber.getNumber(3) -> new LottoNumber(3)
        * **[예외]** 요청한 로또 번호가 1~45가 아닐 경우
* WinningLotto
    * String으로 로또 번호 6개가 입력되면 LottoNumber 리스트에 저장하기
        * "1,2,3,4,5,6" -> [1,2,3,4,5,6]
        * "1,2,3,4,5,5" -> error!
        * "1,2,3,4,5" -> error!
        * **[예외]** 입력된 값에 자연수 외에 다른 값이 있는지 확인
        * **[예외]** 중복된 숫자가 있는지 확인
    * 파라미터로 전달받은 번호가 당첨번호와 일치하는지 boolean으로 리턴
        * 일반 번호 확인
        * 보너스 볼 확인
* RankType: Enum
    * 일치하는 번호 수에 따라 다르게 객체 리턴하기
        * valueOf(5, true) -> RankType.SECOND
        * valueOf(6, false) -> RankType.FIRST
    * 일치하는 번호 수 리턴하기 