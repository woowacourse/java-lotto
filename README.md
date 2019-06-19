# java-lotto
1. LottoNumber
    - 로또 번호 하나를 저장 (int)
    - 1 ~ 45의 사이의 정수
    - 1 ~ 45까지의 로또 번호를 미리 저장해 놓고 재사용
2. LottoNumbers
    - 6개의 로또 번호만 입력 가능
    - 중복 불가능
    - 다른 로또 번호들과 비교해서 일치 개수 반환
3. LottoNumbersGenerator
    - Auto : Shuffle을 활용해서 List의 앞에서부터 6개의 랜덤한 로또 번호로 LottoNumbers를 만들어 반환
    - Manual : 받은 String 형태의 번호들을 분리해서 LottoNumbers로 만들어 반환
4. Lotto
    - LottoNumbers 저장
    - 받은 번호가 로또에 있는지 확인
    - 다른 로또와 비교해서 일치 개수를 반환(LottoNumbers에 메세지 전달)
5. Lottos
    - Lotto들의 리스트
    - LottoNumbers 입력받으면 해당 번호로 로또를 생성해 추가
    - 다른 Lottos와 결합가능(자동 + 수동)
6. LottoMachine
    - 구매 정보(PurchaseInformation)에 해당하는 Lottos 반환
    - 받은 자동 로또의 개수 만큼 랜덤한 Lottos 반환
    - 받은 수동 로또 번호들로 Lottos 반환  
7. WinningInformation
    - 당첨 번호들을 저장
    - 보너스 번호를 저장
    - 받은 로또의 등수를 반환
8. LottoGame
    - Lottos와 WinningLotto 입력시 LottoResult 반환
9. Rank
    - 당첨 종류를 나타내는 enum
    - 일치 개수를 입력 받으면 해당하는 Rank를 반환
10. LottoResult
    - 당첨 결과 저장 (Map<WinningType, Integer>)
    - 수익률 계산
11. Money
    - 구입 금액 저장
    - 최대 구매가능 금액 계산
    - 최대 구매가능 금액과 최소 구매가능 금액 판단
12. LottoCount
    - 자동 로또와 수동 로또의 개수를 저장
13. PurchaseInformation
    - 로또의 개수 저장
    - 수동 로또 번호들 저장
    - 수동 로또 번호 등록
    - 수동 로또의 개수와 수동 로또 번호들의 수가 같은지 판단

### Input
- 구입 금액
- 수동 로또 개수
- 수동 로또 번호들
- 당첨 번호
- 보너스볼

### Output
- 구입한 로또 개수 및 로또 번호들
- 당첨 통계
- 총 수익률


## Web UI
1. 메인화면
    - 로또 구매 화면으로 이동 가능
    - 회차별 결과 조회 화면으로 이동 가능

2. 로또 구매 화면
    - 메인 화면으로 이동 가능
    - 구입금액, 수동 로또 개수, 수동 로또 번호를 입력하여 로또 구매 가능

3. 구매 결과 화면
    - 메인 화면으로 이동 가능
    - 구매한 로또를 화면에 출력
    - 당첨 번호와 보너스 볼을 입력하여 당첨 결과 확인 가능

3. 당첨 결과 화면
    - 메인 화면으로 이동 가능
    - 당첨 결과를 화면에 출력

4. 회차별 결과 조회 화면
    - 메인 화면으로 이동 가능
    - 회차별로 사용자가 구매한 로또, 당첨 번호, 당첨 결과, 당첨 금액, 수익률을 조회 가능

5. 회차별 결과 화면
    - 메인 화면으로 이동 가능
    - 회차별 당첨 정보, 당첨 통계, 구매한 로또들을 출력
    
6. 에러 화면
    - 메인 화면으로 이동 가능
    - 에러 메세지 출력
    
## DB
1. DatabaseConnection
    - 데이터 베이스 Connection 관리

2. LottoDAO
    - 로또 정보를 데이터 베이스에 저장
    - 특정 회차에 구매한 로또들을 데이터 베이스에서 조회
    
3. WinningInformationAO
    - 당첨 정보를 데이터베이스에 저장
    - 특정 회차의 당첨 정보를 데이터 베이스에서 조회
    - winningInfo 테이블의 모든 데이터를 지워 데이터 베이스의 모든 정보 삭제 (연결된 로또들도 전부 삭제)
    
4. RoundInformationService
    - 회차별 당첨 정보, 구매 로또 로또들을 저장
    - 특정 회차의 구매한 로또들과 당첨 정보를 얻어와 당첨 확인하여 당첨 정보, 당첨 통계, 구매한 로또를 반환
    
### DB EER DIAGRAM
![DB_ERR_DIAGRAM](/schema/lottoDB_EER_Diagram.png)