package Mission.BookManagementProgram;

// 도서관 회원
public class Member
{
    private static int count = 1;
    private int id;
    private String name;
    private int age;
    private Gender gender;
    private String SubscriptionTime;// 가입시간
    private String rentalBookName; // 대여한 책 이름


    public Member(String name, int age, String gender, String SubscriptionTime)
    {
        this.name = name;
        this.age = age;
        this.gender = Gender.valueOfTerm(gender);
        this.SubscriptionTime = SubscriptionTime;
        this.id = count++;
        this.rentalBookName = null;
    }


    @Override
    public String toString() {
        return "회원" + id + "{" +
                "이름='" + name + '\'' +
                ", 나이=" + age +
                ", 성별=" + gender +
                ", 등록시간='" + SubscriptionTime + '\'' +
                ", 대여한 책='" + rentalBookName + '\'' +
                '}';
    }

    public String getRentalBookName()
    {
        return rentalBookName;
    }

    public void setRentalBookName(String rentalBookName)
    {
        if (rentalBookName.equals("null"))
            this.rentalBookName = null;
        else
            this.rentalBookName = rentalBookName;
    }

    public String getName()
    {
        return name;
    }


}
