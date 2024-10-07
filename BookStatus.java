package Mission.BookManagementProgram;

public enum BookStatus
{
    AVAILABLE("가능"),
    RENTAL("불가");



    private String status;

    BookStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }


    public String getKoreanName() {
        return status;
    }

}



