package org.example.model;

import java.time.LocalDate;

public class BorrowRecord {
    private int borrowId;
    private int bookId;
    private int memberId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;

    public BorrowRecord(int borrowId,int bookId,int memberId,LocalDate borrowDate,LocalDate dueDate,LocalDate returnDate,String status){
        this.borrowId=borrowId;
        this.bookId=bookId;
        this.memberId=memberId;
        this.borrowDate=borrowDate;
        this.dueDate=dueDate;
        this.returnDate=returnDate;
        this.status=status;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
