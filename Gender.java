package Mission.BookManagementProgram;

public enum Gender
{
    MALE("MALE", "male"),
    FEMALE("FEMALE", "female");


    private String Upper; // 대문자인가?
    private String Lower; // 소문자인가?


    Gender(String Upper, String Lower)
    {
        this.Upper = Upper;
        this.Lower = Lower;
    }


    static Gender valueOfTerm(String str)
    {
        for (Gender gender : Gender.values())
        {
            if (str.equals(gender.Upper) || str.equals(gender.Lower))
                return gender;
        }
        return null;
    }


}

