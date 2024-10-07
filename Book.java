package Mission.BookManagementProgram;

public class Book
{
    private String bookName;
    private String author;
    private int id;
    private String status;
    private static int counter = 1;

    Book(String bookName, String author)
    {
        this.bookName = bookName;
        this.author = author;
        this.id = counter++;
        this.status = BookStatus.AVAILABLE.getKoreanName();
    }

    @Override
    public String toString() {
        return "제목='" + bookName + '\'' +
                ", 저자='" + author + '\'' +
                ", 책ID=" + this.id + '\'' +
                ", 대여상태=" + this.status;
    }

    public String getBookName() {
        return bookName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public int getId() {
        return id;
    }
}
