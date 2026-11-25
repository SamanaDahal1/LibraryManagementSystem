package org.example.daoImpl;

import org.example.dao.BookDAO;
import org.example.dao.MemberDAO;
import org.example.database.DatabaseConnection;
import org.example.model.Book;
import org.example.model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public boolean addMember(Member member) {
        String queryAdd = "insert into members (name,email,phone) values (?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryAdd)){
            preparedStatement.setString(1, member.getName());
            preparedStatement.setString(2, member.getEmail());
            preparedStatement.setString(3, member.getPhone());
            int affect= preparedStatement.executeUpdate();
            if(affect>0){
                return true;
            }
        }
        catch (SQLException e){
            System.out.println("Something went wrong while adding member: "+e.getMessage());
        }
        return false;
    }

    @Override
    public Member getMemberById(int memberId) {
        return null;
    }

    @Override
    public List<Member> getAllMembers() {
        return List.of();
    }

    @Override
    public boolean updateMember(Member member) {
        return false;
    }

    @Override
    public boolean deleteMember(int memberId) {
        return false;
    }

    @Override
    public Member getMemberByEmail(String email) {
        return null;
    }
}
