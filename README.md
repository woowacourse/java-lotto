# java-lotto
로또 미션 진행을 위한 저장소


## TODO
### Step 1
* 구입금액 입력받고 로또 장수 저장하기
    * 구입 금액이 해당 조건에 부합하는지 확인하기
        * 1000 -> 1
        * 14000 -> 14
        * 900 -> 0
        * -13 -> error!
        * 4.4 -> error!
* 로또 게임 횟수(?) 저장하기
* 로또 게임 발급하기
* 로또 숫자가 생성되었을 때 규칙에 맞는지 validate
    * 숫자로만 이루어져 있는지 확인
    * 1이상 45이하인지 확인
* 지난 주 당첨 번호 입력받기
    * 1이상 45이하의 자연수만 받기
    * 쉼표로 당첨 번호 구분
        * 1, 2, 3, 4, 5, 6 -> [1, 2, 3, 4, 5, 6]
* 당첨 시 집계하기
* 수익률 계산하기