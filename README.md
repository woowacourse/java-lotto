# java-lotto
로또 미션 진행을 위한 저장소


## TODO
#### view
* 수동 로또 입력값을 받았을 때,
    * null인지 확인
    * isEmpty 확인
    * 빈칸 없애기
* 수동 로또 입력값을 split하고 리스트로 던지기
#### domain
* LottoTickets: 로또 리스트, 구매할 수동 로또 수
* LottoTicket: 로또 한 장
    * 당첨 번호 로또(WinningLotto)에 번호 하나하나 묻기
* LottoTicketFactory
    * 수동 로또
    * 
* 수동 로또 클래스(ManualLotto)가 리스트를 받아서
* LottoNumber
    * 요청한 로또 번호에 대한 객체 리턴
        * LottoNumber.getNumber(3) -> new LottoNumber(3)
        * **[예외]** 요청한 로또 번호가 1~45가 아닐 경우
    * 자동 로또일 때, subList해서 6개 전달하는 함수 만들기
        * purchaseAutoNumbers() -> return List<LottoNumber>

* 구입금액 입력받고 로또 장수 저장하기
    * 구입 금액이 해당 조건에 부합하는지 확인하기
        * 1000 -> 1
        * 14000 -> 14
        * 900 -> 0
        * -13 -> error!
        * 4.4 -> error!
* 로또 게임 횟수(?) 저장하기
* 로또 게임 발급하기
    * 중복이 있으면 안됨
* 로또 숫자가 생성되었을 때 규칙에 맞는지 validate
    * 숫자로만 이루어져 있는지 확인
    * 1이상 45이하인지 확인
* 지난 주 당첨 번호 입력받기
    * 당첨 번호 클래스(WinningLotto)는 싱글톤 클래스
    * 1이상 45이하의 자연수만 받기
    * 쉼표로 당첨 번호 구분
        * 1, 2, 3, 4, 5, 6 -> [1, 2, 3, 4, 5, 6]
    * 중복이 있는 경우 예외처리 
* 당첨 시 집계하기
* 수익률 계산하기
* 예외 클래스 생성하기

---
## Done
#### view
#### domain
* LottoTickets: 로또 리스트, 구매할 수동 로또 수
    * 구매할 수동 로또 수만큼 리스트에 갖고 있는지 응답하기
* LottoTicket: 로또 한 장
    * String으로 로또 번호 6개가 입력되면 LottoNumber 리스트에 저장하기
        * "1,2,3,4,5,6" -> [1,2,3,4,5,6]
        * "1,2,3,4,5,5" -> error!
        * "1,2,3,4,5" -> error!
        * **[예외]** 입력된 값에 자연수 외에 다른 값이 있는지 확인
        * **[예외]** 중복된 숫자가 있는지 확인
* LottoTicketsFactory
* LottoNumber