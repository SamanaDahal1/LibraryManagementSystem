package org.example.dao;

import org.example.model.BorrowRecord;

import java.util.List;

public interface BorrowDAO {
    boolean borrowBook(BorrowRecord borrowRecord);
    BorrowRecord getBorrowRecordById(int borrowId);
    List<BorrowRecord> getAllBorrowRecords ();
    boolean returnBook(int borrowId);
    List<BorrowRecord> getBorrowRecordsByMemberId(int memberId);

}
