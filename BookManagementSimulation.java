package Mission.BookManagementProgram;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookManagementSimulation
{


    static void run()
    {

        List<Book> books = getBookInputArray();
        List<Member> memberArrayList = new ArrayList<>();
        Member members = null;
        System.out.println("도서관리 프로그램을 시작하겠습니다.");

        while (true)
        {
            int Select;
            Scanner sc = new Scanner(System.in);
            System.out.print("회원가입(1)  회원목록(2)  도서대여(3)  도서반납(4)  종료(5) >>");
            Select = sc.nextInt();
            sc.nextLine();
            if (Select == 5)
            {
                sc.close();
                break;
            }

            switch (Select)
            {
                case 1:
                {
                    subscription(sc, null, memberArrayList);
                    break;
                }
                case 2:
                {
                    printMemberList(memberArrayList);
                    break;
                }
                case 3:
                {
                    bookRental(books, memberArrayList, sc);
                    break;
                }
                case 4:
                {
                    bookReturn(books, memberArrayList, sc);
                    break;
                }
                default:
                {
                    System.out.println("잘못된 입력입니다.");
                    break;
                }
            }
        }
    }

    private static List<Book> getBookInputArray()
    {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book("해리 포터와 마법사의 돌", "J.K. 롤링");
        Book book2 = new Book("1984", "조지 오웰");
        Book book3 = new Book("호밀밭의 파수꾼", "J.D. 샐린저");
        Book book4 = new Book("모비 딕", "허먼 멜빌");
        Book book5 = new Book("위대한 개츠비", "F. 스콧 피츠제럴드");
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        return books;
    }


    static void bookReturn(List<Book> books, List<Member> memberArrayList, Scanner sc)
    {
        System.out.println();
        char Select;
        String memberName;
        System.out.println("----도서반납 화면입니다.----");
        System.out.print("로그인할 회원 이름을 입력하세요 >>");
        memberName = sc.nextLine();
        Member member = checkMember(memberName, memberArrayList);
        if (member != null && member.getRentalBookName() != null)
        {
            System.out.println(memberName + "님 환영합니다." + "(" + "대여하신 책=" + member.getRentalBookName() + ")");
            System.out.println();
            System.out.print("반납하시겠어요(Y/N) >>");
            Select = sc.next().charAt(0);
            sc.nextLine();
            //특정 객체가 null 이 아닌지 확인하는 데 사용됩니다. 이 메서드는 객체가 null 일 경우 NullPointerException 을 던지며, null이 아닌 경우에는 해당 객체를 그대로 반환
            checkReturnAvailable(books, Select, Objects.requireNonNull(checkMember(memberName, memberArrayList)));
        }
        else if (member != null && member.getRentalBookName() == null)
            System.out.println(member.getName() + "님 현재 대여하신 책이 없습니다.");
        else
            System.out.println("존재하지 않는 회원입니다.");
    }


    static void checkReturnAvailable(List<Book> books, char Select, Member member)
    {
        if (Select == 'Y' || Select == 'y')
        {
            for (Book book : books)
            {
                if (member.getRentalBookName().equals(book.getBookName()) && book.getStatus().equals("불가"))
                {
                    System.out.println(member.getRentalBookName() + "이 반납되었습니다.");
                    member.setRentalBookName("null");
                    book.setStatus("가능");
                    return;
                }
            }
        }
        else if (Select == 'N' || Select == 'n')
            System.out.println("반납을 취소합니다.");
        else
            System.out.println("잘못된 입력입니다.");


    }


    static void bookRental(List<Book> books, List<Member> memberArrayList, Scanner sc)
    {
        System.out.println();
        int bookID;
        String memberName;
        System.out.println("----도서대여 화면입니다.----");
        System.out.print("로그인할 회원 이름을 입력하세요 >>");
        memberName = sc.nextLine();
        if (checkMember(memberName, memberArrayList) != null)
        {
            System.out.println(memberName + "님 환영합니다.");
            System.out.println();
            printBookList(books);

            System.out.print("대여하실 책ID 를 입력해주세요 >>");
            bookID = sc.nextInt();
            sc.nextLine();
            //특정 객체가 null 이 아닌지 확인하는 데 사용됩니다. 이 메서드는 객체가 null 일 경우 NullPointerException 을 던지며, null이 아닌 경우에는 해당 객체를 그대로 반환
            checkRentalAvailable(books, bookID, Objects.requireNonNull(checkMember(memberName, memberArrayList)));

        }
        else
            System.out.println("존재하지 않는 회원입니다.");

    }



    static void checkRentalAvailable(List<Book> books, int bookID, Member member)
    {
        Optional<String> stringOptional = Optional.ofNullable(member.getRentalBookName());
        //TODO: 책 아이디와 책의 대여가능여부를 판단 후 대여 성공시 대여불가능 상태로 바꾸기 구현
        for (Book book : books)
        {
            if (book.getId() == bookID && stringOptional.isPresent())
            {
                System.out.println("이미 대여하신 책이 존재합니다. 반납 후에 다른 책 대여 가능합니다.");
                System.out.println("대여하신 책= " + member.getRentalBookName());
                return;
            }
            else if (book.getId() == bookID && book.getStatus().equals("가능"))
            {
                System.out.println(book.getBookName() + "를 대여하셨습니다.");
                member.setRentalBookName(book.getBookName());
                book.setStatus("불가");
                return;
            }
            else if (book.getId() == bookID && book.getStatus().equals("불가"))
            {
                System.out.println("이미 대여된 책입니다. 대여가 불가능합니다.");
                return;
            }

        }
        System.out.println("존재하지 않는 책입니다. 다시 입력하세요");

    }


    static void printBookList(List<Book> books)
    {
        System.out.println("------------------ 도서 목록 ----------------");

        for (int i = 0; i < books.size(); i++)
        {
            System.out.println(books.get(i).toString());
        }
        System.out.println("---------------------------------------------");

    }

    static Member checkMember(String memberName, List<Member> memberArrayList)
    {
        for (Member member : memberArrayList)
        {
            if (member.getName().equals(memberName))
                return member;
        }
        return null;

    }


    static void subscription(Scanner sc, Member members, List<Member>memberArrayList) // 회원가입
    {
        String name;
        int age;
        String gender;
        System.out.println("----회원가입 페이지입니다.----");

        try
        {
            System.out.print("이름: ");
            name = sc.nextLine();
            if (name.isEmpty())
                throw new NotStringException("이름을 입력안하셨습니다.");

            System.out.print("나이: ");
            age = sc.nextInt();
            sc.nextLine();


            System.out.print("성별: ");
            gender = sc.nextLine();
            GenderCheck(gender);



        }
        catch (InputMismatchException e)
        {
            throw new NotIntegerException("숫자를 입력하세요");
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(dateTimeFormatter);

        members = new Member(name, age, gender, date);
        memberArrayList.add(members);
    }


    static void GenderCheck(String gender)
    {
        if (!(gender.equals("male") || gender.equals("female") || gender.equals("MALE") || gender.equals("FEMALE"))) {
            throw new NotGenderTypeException("올바르지 않은 성별입니다.");
        }


    }

    static void printMemberList(List<Member> memberArrayList)
    {
        if (memberArrayList.isEmpty())
        {
            System.out.println("회원이 존재하지 않습니다.");
            return;
        }

        System.out.println("------------------회원 리스트--------------------");

        for (Member member : memberArrayList)
        {
            System.out.println(member);
        }
        System.out.println("------------------------------------------------");
        System.out.println();

    }
}
