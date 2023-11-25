## Commit Convention
1. Type 태그
    - **feat : 새로운 기능을 추가하는 경우**
    - **fix : 버그를 고친 경우**
    - **docs : 문서를 수정한 경우**
    - **style : 코드 포맷 변경, 세미콜론 누락, 코드 수정이 없는 경우**
    - **refactor : 코드 리펙토링**
    - **test : 테스트 코드. 리펙토링 테스트 코드를 추가했을 때**
    - **chore : 빌드 업무 수정, 패키지 매니저 수정**
    - **design : CSS 등 사용자가 UI 디자인을 변경했을 때**
    - **rename : 파일 명(or 폴더 명)을 수정한 경우**
    - **remove : 코드(파일) 의 삭제가 있을 때. "Clean", "Eliminate"를 사용하기도 함**
2. Body는 필요할 때만 작성
3. footer는 거의 사용 안 할 듯

## PR 제목
1. 예시
    1. [FE/FEAT] FE 환경설정
    2. [BE/FEAT] BE 로그인 기능 구현
    3. [BE/FIX] BE DB 연동 수정
    4. [BE/DOCS] 이슈 템플릿 작성
2. 이런 식으로 **(FE/BE) + git commit 컨벤션 타입 과 작업 내용**


## PR 템플릿
```markdown
## PR 종류 (하나 이상 선택) 

- [ ] 기능 추가
- [ ] 기능 수정
- [ ] 기능 삭제
- [ ] 리팩토링
- [ ] 버그 수정
- [ ] 문서 작업

## 작업 내용 📃
- 

## 관련 이슈 🚩
-
```

## 이슈 제목
1. 예시
    1. [FE/FEAT] FE 환경설정
    2. [BE/FEAT] BE 로그인 기능 구현
    3. [BE/FIX] BE DB 연동 수정
    4. [BE/DOCS] 이슈 템플릿 작성
2. PR 제목 형식과 동일

## 이슈 템플릿
```markdown
## 이슈 설명

## 작업 내용

## 기타
```

## 이슈/PR 라벨
1. BE : BE가 작성한 라벨
2. FE : FE가 작성한 라벨
3. Feature : 기능 개발에 관한 라벨
4. Setting : 초기 환경 세팅 및 설정에 관한 라벨
5. Document : 문서 작업에 관한 라벨
6. Bugfix : 버그 수정에 관한 라벨
7. Refactoring : 리팩토링 과정에 관한 라벨
8. Test : 테스트에 관한 라벨
