package day0420;
// 게시판 프로그램을 작성하시오.

// 게시글에는 번호, 제목, 작성자, 내용이 들어가게 됩니다.
// 게시판은 처음 목록 보기 화면에서
// 글 작성, 글 개별 보기, 종료를 선택할 수 있습니다.
// 글 작성 시에는 사용자가 글 번호를 직접 입력하는 것이 아니라
// 1부터 시작하여 글을 쓸 때마다 늘어납니다.
// 삭제를 한다고 하여 다음 글 번호가 줄어들지 않습니다.
// 글에는 제목, 작성자, 내용이 들어가게 됩니다.

// 글 개별 보기의 경우
// 수정, 삭제, 뒤로가기가 가능합니다.

import java.util.Scanner;

import util.ArrayUtil;
import util.ScannerUtil;

import java.util.*;

public class Board {
    // 변수
    // 1. 입력에 사용할 Scanner 클래스 변수
    static Scanner scanner = new Scanner(System.in);
    // 2. 글 번호를 저장할 int 배열
    static int[] idArray = new int[0];
    // 3. 글 제목을 저장할 String[] titleArray
    static String[] titleArray = new String[0];
    // 4. 글 작성자를 저장할 String[] writerArray
    static String[] writerArray = new String[0];
    // 5. 글 내용을 저장할 String[] contentArray
    static String[] contentArray = new String[0];
    // 6. 자동으로 늘어날 글 번호를 관리할 int 변수 id
    static int id = 1;

    // 메소드 영역
    // 1. 메뉴 보여주는 메소드
    static void printAll() {
        // 1. 현재 배열에 글이 저장되어있는지 체크
        // 2. 만약 저장되어 있으면 글의 내용을 전부 다 보여주는 것이 아니라
        // [인덱스] 제목 - 작성자
        // 이렇게 간단한 형태로만 for문을 이용하여 출력한다.
        // 3. 만약 아무런 글도 저장이 안되어 있으면
        // 아직 아무런 글도 저장되지 않았습니다. 라는 문구만 출력 되게 한다.
        // 4. 사용자에게 새글 작성, 글 개별 보기, 게시판 종료 중 한가지를 선택 받게 한다.
        // 5. 사용자가 만약 새글 작성을 선택하면 새 글을 작성하는 메소드를 호출해준다.
        // 6. 사용자가 글 개별 보기를 선택하면 개별 보기할 글의 인덱스를 입력 받는다.
        // 7. 사용자가 게시판 종료를 선택하면 무한 루프를 종료시킨다.
        while (true) {
            if (ArrayUtil.size(titleArray) > 0) {
                for (int i = 0; i < ArrayUtil.size(titleArray); i++) {
                    System.out.printf("[%d] %s - %s \n", i + 1, ArrayUtil.get(titleArray, i),
                            ArrayUtil.get(writerArray, i));

                }
            } else {
                System.out.println();
                System.out.println("아직 아무런 글도 저장되지 않았습니다. ");
                System.out.println();

            }

            String message = new String("1.새글 작성 2.글 개별 보기 3.게시판 종료");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);

            if (userChoice == 1) {
                // 새글 작성 메소드 호출
                write();
            } else if (userChoice == 2) {
                // 개별 보기할 번호를 입력 받기 전에,
                // 저장된 글이 존재하는지 체크부터 하여
                // 만약 아무런 글도 저장되지 않았을 경우,
                // 메시지만 한번 출력하고 아무런 입력도 받지 않게 한다.
                if (ArrayUtil.size(titleArray) == 0) {
                    System.out.println("아직 입력된 글이 존재하지 않습니다. ");
                } else {
                    message = new String("개별 보기할 번호를 선택해주세요. ");
                    int index = ScannerUtil.nextInt(scanner, message, 1, ArrayUtil.size(titleArray)) - 1;
                    printOne(index);
                }

            } else if (userChoice == 3) {
                // 메시지 출력 후 무한 루프 종료
                System.out.println("사용해주셔서 감사합니다. ");
                break;
            }
        }

    }

    // 2. 글 작성 메소드
    static void write() {
        String message;
        // 1. 제목을 입력 받아서 String 변수 title에 저장
        message = new String("제목을 입력해주세요. ");
        String title = new String(ScannerUtil.nextLine(scanner, message));

        // 2. 작성자를 입력 받아서 String 변수 write에 저장
        message = new String("이름을 입력해주세요. ");
        String writer = new String(ScannerUtil.nextLine(scanner, message));

        // 3. 내용을 입력 받아서 String 변수 content에 저장
        message = new String("내용을 입력해주세요. ");
        String content = new String(ScannerUtil.nextLine(scanner, message));

        // 4. 현재 글 번호를 int 변수 currentId에 저장
        int currentId = id;

        // 5. 각각의 변수들을 해당하는 배열에 추가해준다.
        idArray = ArrayUtil.add(idArray, currentId);
        titleArray = ArrayUtil.add(titleArray, title);
        writerArray = ArrayUtil.add(writerArray, writer);
        contentArray = ArrayUtil.add(contentArray, content);

        // 6. 전역 변수 id의 값을 1 증가시킨다.
        id++;
    }

    // 3. 글 개별보기 메소드
    static void printOne(int index) {
        // 1. 사용자가 입력한 index를 파라미터로 받아왔기 때문에
        // 각 배열의 index에 저장된 값들을 불러와서 각각 변수에 저장한다.
        // 2. 값이 저장된 변수들을 예쁘게 출력해준다.
        // 3. 사용자로부터 1.수정 2.삭제 3.목록으로 중 한가지를 선택하도록 입력 받는다.
        // 4. 사용자가 수정을 선택했으면 수정하는 메소드를 실행한다.
        // 5. 사용자가 삭제를 선택했으면 삭제하는 메소드를 실행한다.
        // 6. 사용자가 목록으로 돌아가기를 선택했을 경우 아무런 처리를 해주지 않으면,
        // 메소드가 종료되고 이 메소드는 무한루프가 있는 목록 보기에서 호출되었기 때문에
        // 그 곳으로 돌아가서 다시 무한 반복문이 시작된다.
        // 따라서 if/else if 처리도 해 줄 필요가 없다.

    }

    // 4. 글 수정 메소드
    static void update(int index) {
        // 1. 사용자로부터 정말로 수정할 것인지 입력을 Y/N의 형태로 입력 받아서
        // 대소문자 상관없이 y일 경우 수정을 시작한다.
        // 2. 수정을 할 때는 번호와 작성자는 변경하지 않고
        // 오직 제목과 내용만 수정되도록 입력을 받아서 각각 변수에 저장한다.
        // 3. 값이 저장된 변수를 각각의 배열에 index번 자리에 있는 값과 교체한다.
        // 4. 사용자가 글을 수정했든 안했든 무조건 index번 글 개별 보기 메소드를 실행시켜서 돌아가게 만들어준다.

    }

    // 5. 글 삭제 메소드
    static void delete(int index) {
        // 1. 사용자로부터 정말로 삭제할 것인지 입력을 Y/N의 형태로 입력 받아서
        // 대소문자 상관없이 y일 경우 삭제를 시작한다.
        // 2. 삭제를 할때는 각 배열의 해당 index를 삭제해준다.
        // 3. 만약 사용자가 삭제를 했으면 아무런 코드실행 없이 가만히 놔두면
        // 코드의 실행 순서상 printAll()메소드로 돌아가서 무한루프가 시작된다.
        // 4. 만약 사용자가 삭제를 하지 않았으면
        // index번 글 개별 보기 메소드를 실행시켜서 돌아가게 만들어준다.

    }

    // 6. 메인 메소드
    public static void main(String[] args) {
        // 1. 글 목록을 무한 루프의 형태로 출력해주는 printAll() 메소드 실행
        printAll();
        // 2. printAll() 메소드가 종료되었다는 것은 사용자가 종료를 선택했다는 의미이므로
        // Scanner 클래스 변수인 scanner가 buffer 메모리를 참조하는 것을 끝내기 위해 close() 메소드를 실행시켜준다.
        scanner.close();

    }

}
