package org.example.daoImpl;

import org.example.dao.BookDAO;
import org.example.dao.MemberDAO;
import org.example.database.DatabaseConnection;
import org.example.model.Book;
import org.example.model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
       String queryGet = "select * from members where member_id = ?";
       try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryGet))
       {
           preparedStatement.setInt(1,memberId);
           try (ResultSet resultSet = preparedStatement.executeQuery()){
               if(resultSet.next()){
                   return new Member(
                      resultSet.getInt("member_id"),
                      resultSet.getString("name"),
                      resultSet.getString("email"),
                      resultSet.getString("phone")
                   );
               }
           }
       }
       catch (SQLException e){
           System.out.println("Something went wrong while retrieving member: "+e.getMessage());
       }
       return null;
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String queryMember = "select * from members";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryMember);
            ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()){
                Member member = new Member(
                        resultSet.getInt("member_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
                members.add(member);
            }
            return members;
        }
        catch (SQLException e){
            System.out.println("Something went wrong while retrieving all member: "+e.getMessage());
        }
        return members;
    }

    @Override
    public boolean updateMember(Member member) {
       String queryUpdate = "update members set name=?,email=?,phone=? where member_id = ?";
       try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(queryUpdate))
       {
           preparedStatement.setString(1,member.getName());
           preparedStatement.setString(2, member.getEmail());
           preparedStatement.setString(3, member.getPhone());
           preparedStatement.setInt(4,member.getMemberId());
           int affect = preparedStatement.executeUpdate();
           if(affect>0){
               return true;
           }
       }
       catch (SQLException e){
           System.out.println("Something went wrong while updating member: "+e.getMessage());
       }
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
