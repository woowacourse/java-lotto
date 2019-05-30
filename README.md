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
#### domain
* Money
* LottoTickets: 로또 리스트, 구매할 수동 로또 수
* LottoTicket: 로또 한 장
    * 갖고 있는 번호 중에 당첨 번호가 있는지 확인하기
* LottoTicketFactory
    * 수동 로또인지 자동 로또인지 구분해서 객체 생성하기
    * **자동 로또의 경우**
* LottoNumber
    * **수동 로또의 경우**
    * **자동 로또의 경우**
        * subList해서 6개 전달하는 함수 만들기
            * purchaseAutoNumbers() -> return List<LottoNumber>
            * **[예외]** 중복되는 번호가 있는지 확인
* WinningLotto: 싱글톤 클래스
    * String으로 로또 번호 6개가 입력되면 LottoNumber 리스트에 저장하기
        * "1,2,3,4,5,6" -> [1,2,3,4,5,6]
        * "1,2,3,4,5,5" -> error!
        * "1,2,3,4,5" -> error!
        * **[예외]** 입력된 값에 자연수 외에 다른 값이 있는지 확인
        * **[예외]** 중복된 숫자가 있는지 확인 
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
    * 로또 게임 횟수 리턴하기
        * 1000 -> 1
        * 14000 -> 14
* LottoTickets: 로또 리스트, 구매할 수동 로또 수
    * 구매할 수동 로또 수만큼 리스트에 갖고 있는지 응답하기
* LottoTicket: 로또 한 장
* LottoTicketsFactory
    * **수동 로또의 경우**
        * String으로 로또 번호 6개가 입력되면 LottoNumber 리스트에 저장하기
            * "1,2,3,4,5,6" -> [1,2,3,4,5,6]
            * "1,2,3,4,5,5" -> error!
            * "1,2,3,4,5" -> error!
            * **[예외]** 입력된 값에 자연수 외에 다른 값이 있는지 확인
            * **[예외]** 중복된 숫자가 있는지 확인
* LottoNumber
    * **수동 로또의 경우**
    * 요청한 로또 번호에 대한 객체 리턴
        * LottoNumber.getNumber(3) -> new LottoNumber(3)
        * **[예외]** 요청한 로또 번호가 1~45가 아닐 경우