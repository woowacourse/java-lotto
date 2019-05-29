# 우아한 테크코스 레벨1

## 4주차 미션 - 로또(1단계)

### 구현 기능 목록
1. [입력] 구입금액 입력
	* [예외처리] 0원 이상 입력
	* [예외처리] 1000원 단위로 입력
	* [예외처리] 양의 정수 외의 숫자 오는지 검증

2. [실행] 로또 구입 개수 및 해당 개수에 맞게 번호 등록
	* 로또 번호 1~45까지의 난수 생성
	* 구입 하나당 6개 로또 번호 등록

3. [입력] 당첨 번호 입력
	* [예외처리] 번호 개수가 6개인지 검증
	* [예외처리] 1부터 45까지의 번호인지 검증

4. [실행] 당첨번호와 구매 로또 번호와 비교	
	* 맞춘 번호 개수를 통해 금액 책정
	    * 6개 일치 -> 2000000000원
        * 5개 일치 -> 1500000원 
        * 4개 일치 -> 50000원
        * 3개 일치 -> 5000원
	* 수익률 계산
		* 수익률 = (로또 당첨 금액)/(로또 구매 금액) * 100

5. [출력] 당첨 개수 리스트와 수익률 출력