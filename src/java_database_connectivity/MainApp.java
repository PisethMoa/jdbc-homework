package java_database_connectivity;
import pakage2.Topic;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class MainApp{
    private static JdbcImpl jdbc;
    private static Scanner scanner;
    public static void main(String[] args){
        jdbc = new JdbcImpl();
        scanner = new Scanner(System.in);
        Topic topic = new Topic();
//        System.out.print("Please enter name = ");
//        topic.setName(scanner.nextLine());
//        System.out.print("Please enter description = ");
//        topic.setDescription(scanner.nextLine());
//        topic.setStatus(true);
//        insertTopics(topic);
        selectTopics();
        System.out.println("=============== Create Select by Id Operation ===============");
        System.out.print("Please enter id = ");
        Integer id = Integer.parseInt(scanner.nextLine());
        selectTopicById(id);
        System.out.println("=============== Create Select by Name Operation ===============");
        System.out.print("Please enter name = ");
        String name = scanner.nextLine();
        selectTopicByName(name);
        System.out.println("=============== Create Update Operation ===============");
        System.out.print("Please enter your id to update = ");
        topic.setId(Integer.parseInt(scanner.nextLine()));
        System.out.print("Please enter your name = ");
        topic.setName(scanner.nextLine());
        System.out.print("Please enter your description = ");
        topic.setDescription(scanner.nextLine());
        topic.setStatus(true);
        updateTopicById(topic);
        selectTopics();
        System.out.println("=============== Create Delete by Id Operation ===============");
        System.out.print("Please enter id to delete = ");
        topic.setId(Integer.parseInt(scanner.nextLine()));
        deleteTopicById(id);
        selectTopics();
    }
    private static void insertTopics(Topic topic){
        try(Connection connection = jdbc.dataSource().getConnection()){
            String insertSql = "INSERT INTO topics(name, description, status)"+"VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, topic.getName());
            preparedStatement.setString(2, topic.getDescription());
            preparedStatement.setBoolean(3, topic.isStatus());
            int count = preparedStatement.executeUpdate();
            System.out.println(count);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private static void selectTopics(){
        try(Connection connection = jdbc.dataSource().getConnection()){
            String selectSql = "SELECT * FROM topics";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Topic> topics = new ArrayList<>();
            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Boolean status = resultSet.getBoolean("status");
                topics.add(new Topic(id, name, description, status));
            }
            topics.forEach(System.out::println);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private static void selectTopicById(Integer id){
        try(Connection connection = jdbc.dataSource().getConnection()){
            String selectByIdSql = "SELECT * FROM topics WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectByIdSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Topic topic = new Topic();
            while(resultSet.next()){
                topic.setId(resultSet.getInt("id"));
                topic.setName(resultSet.getString("name"));
                topic.setDescription(resultSet.getString("description"));
                topic.setStatus(resultSet.getBoolean("status"));
            }
            System.out.println(topic);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    private static void selectTopicByName(String name){
        try(Connection connection = jdbc.dataSource().getConnection()){
            String selectByNameSql = "SELECT * FROM topics WHERE name ILIKE '%' || ? || '%'";
            PreparedStatement preparedStatement = connection.prepareStatement(selectByNameSql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Topic> topics = new ArrayList<>();
            while(resultSet.next()){
                topics.add(new Topic(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("status")
                ));
            }
            System.out.println(topics);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void updateTopicById(Topic topic){
        try(Connection connection = jdbc.dataSource().getConnection()){
            String updateByIdSql = "UPDATE topics Set name = ?, description = ?, status = ? WHERE id = "+topic.getId();
            PreparedStatement preparedStatement = connection.prepareStatement(updateByIdSql);
            preparedStatement.setString(1, topic.getName());
            preparedStatement.setString(2, topic.getDescription());
            preparedStatement.setBoolean(3, topic.isStatus());
            int count = preparedStatement.executeUpdate();
            System.out.println(count);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void deleteTopicById(Integer id){
        try(Connection connection= jdbc.dataSource().getConnection()){
            String deleteByIdSql = "DELETE FROM topics WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteByIdSql);
            preparedStatement.setInt(1,id);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                System.out.println("Congratulations, You have deleted successfully...!");
            }else{
                System.out.println("You can't deleted...!");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
