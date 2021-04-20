package day0420;

// 목록 보기에서 전체 학생의 정보가 통째로 나오는 것이 아니라

// 간단한 정보만 나오고
// 해당 학생의 상세 정보보기는 다른데서 처리해주는 버전
// 하지만 우리가 이미 상세 보기와 전체 목록 보기를 메소드를 통해서 분리해놨기 때문에
// 목록 보기 쪽만 손을 봐주면 된다.

import java.util.Scanner;

import util.ArrayUtil;
import util.ScannerUtil;

public class GradeBook01 {

    static final int SIZE_STUDENT = 5;
    static final int SIZE_SUBJECT = 3;
    static final int SCORE_MIN = 0;
    static final int SCORE_MAX = 100;
    static final String STRING_MENU = new String("1.입력 2.출력 3.종료");

    static int[][] infoArray = new int[SIZE_STUDENT][0];
    static String[] nameArray = new String[0];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        showMenu();
    }

    static void showMenu() {
        while (true) {
            int userChoice = ScannerUtil.nextInt(scanner, STRING_MENU, 1, 3);
            if (userChoice == 1) {

                insert();
            } else if (userChoice == 2) {

                print();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다. ");
                break;
            }
        }
        scanner.close();
    }

    static void insert() {

        if (ArrayUtil.size(nameArray) < SIZE_STUDENT) {

            int index = ArrayUtil.size(nameArray);

            String message;

            message = new String("학생의 번호를 입력해주세요. ");
            infoArray[index] = ArrayUtil.add(infoArray[index],
                    ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));

            message = new String("학생의 이름을 입력해주세요. ");
            nameArray = ArrayUtil.add(nameArray, ScannerUtil.nextLine(scanner, message));

            message = new String("학생의 국어 점수를 입력해주세요. ");
            infoArray[index] = ArrayUtil.add(infoArray[index],
                    ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));

            message = new String("학생의 영어 점수를 입력해주세요. ");
            infoArray[index] = ArrayUtil.add(infoArray[index],
                    ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));

            message = new String("학생의 수학 점수를 입력해주세요. ");
            infoArray[index] = ArrayUtil.add(infoArray[index],
                    ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));

        } else {

            System.out.println("더이상 입력할 수 없습니다.");
        }
    }

    static void print() {
        // 기존의 print() 메소드에서는
        // 학생의 정보를 모두 다 출력을 해줬지만
        // 이제 print() 메소드는 간단한 정보
        // ex) 순서 - 번호 몇번 이름
        // 정보만 보여주고 사용자에게 상세보기 할 순서를 입력 받도록 한다.

        if (ArrayUtil.size(nameArray) > 0) {

            for (int i = 0; i < ArrayUtil.size(nameArray); i++) {
                System.out.printf("%d. %d번 학생 %s\n", i + 1, infoArray[i][0], nameArray[i]);

            }
            // for문을 통해 전체 학생의 간단한 목록을 보여준 뒤
            // 사용자에게 상세보기 할 학생의 인덱스를 입력 받는다.
            // 단 사용자가 입력할 때는 1~번호 끝을 입력하는데 우리가 처리는 0~번호 끝 -1까지 되도록 만들어준다.
            String message = new String("몇번째 학생 정보를 상세보기 하겠습니까? ");
            int index = ScannerUtil.nextInt(scanner, message, 1, ArrayUtil.size(nameArray)) - 1;
            // 사용자가 입력한 인덱스를 파라미터로 하여 printOne 메소드를 호출해준다.
            printOne(index);

        } else {

            System.out.println("아직 입력된 학생이 존재하지 않습니다. ");
        }
    }

    static void printOne(int index) {

        String name = ArrayUtil.get(nameArray, index);
        int id = ArrayUtil.get(infoArray[index], 0);
        int korean = ArrayUtil.get(infoArray[index], 1);
        int english = ArrayUtil.get(infoArray[index], 2);
        int math = ArrayUtil.get(infoArray[index], 3);
        int sum = korean + english + math;
        double avg = (double) sum / SIZE_SUBJECT;

        System.out.printf("번호: %03번 이름:%s/n", id, name);
        System.out.printf("국어: %03d점 영어: %03d점 수학: %03d점\n", korean, english, math);
        System.out.printf("총점: %03d점 평균: %06.2f\n", sum, avg);
        String message = new String("1.수정 2.삭제 3.뒤로가기 ");
        int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
        if (userChoice == 1) {
            // 수정 메소드 실행
            update(index);

        } else if (userChoice == 2) {
            // 삭제 메소드 실행
            delete(index);
        }

    }

    // 수정 메소드
    static void update(int index) {
        // 수정 시에는 모든 정보를 전부 다 수정하는 것이 아니라
        // 국어, 영어, 수학 점수만 수정해준다.
        String message;

        message = new String("새로운 국어 점수를 입력해주세요. ");
        ArrayUtil.set(infoArray[index], 1, ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));
        message = new String("새로운 영어 점수를 입력해주세요. ");
        ArrayUtil.set(infoArray[index], 1, ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));
        message = new String("새로운 수학 점수를 입력해주세요. ");
        ArrayUtil.set(infoArray[index], 1, ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));

        printOne(index);
    }

    // 삭제 메소드
    static void delete(int index) {
        // 삭제 시에는 사용자부터 정말로 삭제하시겠습니까 y/n를 물어봐서
        // 사용자가 대소문자 y를 입력했을 경우에만 삭제를 한다.

        // 스트링의 경우, 대소문자 상관 없이 특정 글자와 같은지는
        // equalsIgnoreCase()라는 메소드를 통해서 체크 가능하다.
        String message = new String("정말로 삭제하시겠습니까? Y/N");
        String yesNo = ScannerUtil.nextLine(scanner, message);

        if (yesNo.equalsIgnoreCase("y")) {
            // 어레이유틸의 removeByIndex를 사용하여 해당 인덱스를 삭제하고
            // 목록 보기 메소드를 호출해준다.
            nameArray = ArrayUtil.removeByIndex(nameArray, index);
            infoArray = removeByIndex(infoArray, index);
            print();
        } else {
            // 개별보기 메소드를 호출해준다.
            printOne(index);
        }
    }

    // infoArray의 특정 index를 삭제해주는 메소드
    static int[][] removeByIndex(int[][] array, int index) {
        if (index >= 0 && index <= array.length) {
            int[][] temp = array;
            array = new int[SIZE_STUDENT][0];
            for (int i = 0; i < index; i++) {
                array[i] = temp[i];

            }
            for (int i = index + 1; i < temp.length; i++) {
                array[i - 1] = temp[i];
            }
        }
        return array;
    }
}
