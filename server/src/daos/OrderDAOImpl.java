package daos;

import common.model.Customer;
import common.model.DateTime;
import common.model.Order;
import common.model.Product;
import java.sql.*;
import java.util.HashMap;

public class OrderDAOImpl implements OrderDAO{
    private  static OrderDAOImpl instance;

    private OrderDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static  synchronized OrderDAOImpl getInstance() throws  SQLException{
        if(instance == null){
            instance = new OrderDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASSWORD);
    }

    @Override
    public Order create(HashMap<Product, Integer> products, DateTime date, Customer customer, String status, String comment) throws SQLException{
        try(Connection connection = getConnection()){
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO \"order\"(date, email, status, coment) VALUES(?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setObject(1, date.getSortableDate(), Types.DATE);
            statement.setString(2, customer.getEmail());
            statement.setString(3, status);
            statement.setString(4, comment);
            statement.executeUpdate();
            ResultSet keys =  statement.getGeneratedKeys();
            if(keys.next()){
                Order order = new Order(String.valueOf(keys.getInt(1)),products, customer, status, comment);
                order.setDate(date);
                addToProductOrder(products, order.getId());
                return order;
            }
            else throw new SQLException("No keys granted");
        }
    }

    @Override
    public void update(Order order) throws SQLException{
        try(Connection connection = getConnection()){
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE order SET date = ?, email = ?, status =?, coment = ? WHERE id = ?");
            statement.setObject(1, order.getDate(), Types.DATE);
            statement.setString(2, order.getCustomer().getEmail());
            statement.setString(3, order.getStatus());
            statement.setString(4, order.getComment());
            statement.setInt(5, Integer.parseInt(order.getId()));
            statement.executeUpdate();
            updateProductOrder(order);
        }
    }

    @Override
    public void updateProductOrder(Order order) throws SQLException{
        try(Connection connection = getConnection()) {
            for (HashMap.Entry<Product,Integer> entry : order.getProducts().entrySet()) {
                PreparedStatement statement =
                        connection.prepareStatement("UPDATE productorder SET quantity = ?) WHERE orderid = ? AND productid = ?;");
                statement.setInt(1, entry.getValue());
                statement.setInt(2, Integer.parseInt(order.getId()));
                statement.setInt(3, Integer.parseInt(entry.getKey().getId()));
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Order order) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM order WHERE id = ?");
            statement.setInt(1, Integer.parseInt(order.getId()));
            statement.executeUpdate();
        }
    }

    @Override
    public void addToProductOrder(HashMap<Product, Integer> products, String orderId) throws SQLException {
        try(Connection connection = getConnection()){
            for (HashMap.Entry<Product,Integer> entry : products.entrySet()) {
                PreparedStatement statement =
                        connection.prepareStatement("INSERT INTO productorder(orderid, productid, quantity) VALUES(?, ?, ?);");
                statement.setInt(1, Integer.parseInt(orderId));
                statement.setInt(2, Integer.parseInt(entry.getKey().getId()));
                statement.setInt(3, entry.getValue());
                statement.executeUpdate();
            }
        }

    }
}
