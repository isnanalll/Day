package day0420;
// 숫자 맞추기 게임

// 단 최고 점수 배열은 끝없이 늘어나지만
// 최고 점수 
import java.util.Random;
import java.util.Scanner;

import util.ArrayUtil;
import util.ScannerUtil;

public class RandomGame {
    // 상수
    static final int LIMIT = 5;
    static final int NUMBER_MAX = 100;
    static final int NUMBER_MIN = 1;

    // 변수
    static Scanner scanner = new Scanner(System.in);
    static int[] scoreArray = new int[0];
    static String[] nameArray = new String[0];

    // 1. 점수를 정렬하는 메소드
    static void sort() {
        for (int i = 0; i < ArrayUtil.size(scoreArray) - 1; i++) {
            if (ArrayUtil.get(scoreArray, i) > ArrayUtil.get(scoreArray, i + 1)) {
                String tempString = ArrayUtil.set(nameArray, i, ArrayUtil.get(nameArray, i + 1));
                ArrayUtil.set(nameArray, i + 1, tempString);

                int tempInt = ArrayUtil.set(scoreArray, i, ArrayUtil.get(scoreArray, i + 1));
                ArrayUtil.set(scoreArray, i + 1, tempInt);

                i -= 1;

            }
        }
    }

    // 2. 플레이 메소드
    static void playGame() {
        // 1. 현재 점수를 저장할 int 변수 생성
        int currentScore = 1;

        // 2. 난수를 생성할 랜덤 클래스 변수 생성
        Random random = new Random();

        // 3. 컴퓨터 숫자 생성
        int computerNumber = random.nextInt(NUMBER_MAX) + 1;
        // 테스트용 코드
        // 컴퓨터 숫자를 보여준다.
        // 단 테스트가 끝나면 해당 코드를 삭제한다.
        System.out.println("컴퓨터 숫자 :" + computerNumber);

        // 4. 사용자에게서 1~100 사이 숫자 입력 받음
        String message = new String("1에서 100사이 숫자를 입력해주세요.");
        int userNumber = ScannerUtil.nextInt(scanner, message, NUMBER_MIN, NUMBER_MAX);

        // 5. 사용자 숫자가 컴퓨터 숫자와 다른 '동안'
        // 입력을 받되 마지막 입력과 컴퓨터 숫자를 비교하여
        // UP 혹은 DOWN 출력
        while (userNumber != computerNumber) {
            // 1. UP 혹은 DOWN 출력
            if (userNumber > computerNumber) {
                System.out.println("DOWN");
            } else {
                System.out.println("UP");
            }

            // 2. 새로운 숫자 입력
            userNumber = ScannerUtil.nextInt(scanner, message, NUMBER_MIN, NUMBER_MAX);

            // 3. 현재 점수 1 증가
            currentScore++;
        }

        // 6. 사용자가 숫자를 맞췄을 경우 이름을 입력 받고 점수와 이름을 각각 배열에 추가해준다.
        System.out.println("짝짝짝 정답입니다. ");
        System.out.println("사용자의 점수 :" + currentScore);
        message = new String(" 이름을 입력해주세요. ");
        String name = new String(ScannerUtil.nextLine(scanner, message));

        scoreArray = ArrayUtil.add(scoreArray, currentScore);
        nameArray = ArrayUtil.add(nameArray, name);
        // 7. 정렬한다.
        sort();
    }

    // 3. 최고기록 보기 메소드
    static void showRecord() {
        // 1. nameArray의 크기를 체크하여 0이 아닌지 확인한다.
        // 2. nameArray의 크기와 5를 비교해서 더 작은 것 만큼 for문을 반복시켜서 점수를 출력한다.
        if (ArrayUtil.size(nameArray) > 0) {
            int iEnd = LIMIT;

            if (ArrayUtil.size(nameArray) < LIMIT) {
                iEnd = ArrayUtil.size(nameArray);

            }
            for (int i = 0; i < iEnd; i++) {
                System.out.printf("%d등. %d번 by %s\n", i + 1, ArrayUtil.get(scoreArray, i), ArrayUtil.get(nameArray, i));

            }
        } else {
            System.out.println("아직 플레이 기록이 존재하지 않습니다.");
        }

    }

    // 4. 메뉴 메소드
    static void showMenu() {
        while (true) {
            String message = new String("1.플레이 2.기록보기 3.종료 ");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if (userChoice == 1) {
                // 플레이 메소드 실행
                playGame();
            } else if (userChoice == 2) {
                // 기록보기 메소드 실행
                showRecord();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                scanner.close();
                break;
            }
        }
    }

    // 5. 메인 메소드
    public static void main(String[] args) {
        // 메뉴 메소드를 호출해준다.
        showMenu();
    }

}
