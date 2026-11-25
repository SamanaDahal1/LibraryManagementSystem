package org.example.daoImpl;

import org.example.dao.BorrowDAO;
import org.example.database.DatabaseConnection;
import org.example.model.BorrowRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BorrowDAOImpl implements BorrowDAO {
    @Override
    public boolean borrowBook(BorrowRecord borrowRecord) {
        String queryBorrow = "insert into borrow_records (book_id,member_id,borrow_date,due_date,status) values (?,?,?,?,?)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryBorrow))
        {
            preparedStatement.setInt(1,borrowRecord.getBookId());
            preparedStatement.setInt(2,borrowRecord.getMemberId());
            preparedStatement.setDate(3, java.sql.Date.valueOf( borrowRecord.getBorrowDate()));
            preparedStatement.setDate(4,java.sql.Date.valueOf(borrowRecord.getDueDate()));
            preparedStatement.setString(5,borrowRecord.getStatus());
            int affect = preparedStatement.executeUpdate();
            if(affect>0){
                return true;
            }
        }
        catch (SQLException e){
            System.out.println("Something went wrong while keeping record of borrowing: "+e.getMessage());
        }
        return false;
    }

    @Override
    public BorrowRecord getBorrowRecordById(int borrowId) {
        return null;
    }

    @Override
    public List<BorrowRecord> getAllBorrowRecords() {
        return List.of();
    }

    @Override
    public boolean returnBook(int borrowId) {
        return false;
    }

    @Override
    public List<BorrowRecord> getBorrowRecordsByMemberId(int memberId) {
        return List.of();
    }
}
