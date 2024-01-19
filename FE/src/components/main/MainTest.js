export const mainPageCardbooks = {
  success: true,
  response: {
    // 기본 카드북들 (배열 타입)
    originCardbooks: [
      {
        cardbookId: 1,
        name: "정보처리기사",
        writer: "관리자",
        updateDate: "2023-11-03",
      },
      {
        cardbookId: 2,
        name: "컴퓨터 활용 능력 1급",
        writer: "관리자",
        updateDate: "2023-11-11",
      },
      {
        cardbookId: 3,
        name: "컴퓨터 활용 능력 2급",
        writer: "관리자",
        updateDate: "2023-11-11",
      },
    ],

    // 나의 카드북
    myCardbooks: [
      {
        cardbookId: 1,
        name: "정보처리기사",
        writer: "관리자",
        updateDate: "2023-11-03",
      },
      {
        cardbookId: 4,
        name: "자료 구조",
        writer: "jongmin",
        updateDate: "2023-11-11",
      },
    ],
  },
  message: "메인 페이지 카드북 목록입니다.",
  apiError: null,
};
