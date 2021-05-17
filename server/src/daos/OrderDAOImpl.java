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
    public Order create(HashMap<Product, Integer> products, DateTime date, Customer customer) throws SQLException{
        try(Connection connection = getConnection()){
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO order(date, email) VALUES(?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setObject(1, date, Types.DATE);
            statement.setString(2, customer.getEmail());
            statement.executeUpdate();
            ResultSet keys =  statement.getGeneratedKeys();
            if(keys.next()){
                Order order = new Order(String.valueOf(keys.getInt(1)),products, customer);
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
                    connection.prepareStatement("UPDATE order SET date = ?, email = ? WHERE id = ?");
            statement.setObject(1, order.getDate(), Types.DATE);
            statement.setString(2, order.getCustomer().getEmail());
            statement.setString(3, order.getId());
            statement.executeUpdate();
            updateProductOrder(order);
        }
    }

    @Override
    public void updateProductOrder(Order order) throws SQLException{
        try(Connection connection = getConnection()) {
            int size = order.getProducts().size();
            Product[] product = order.getProducts().keySet().toArray(Product[]::new);
            Integer[] quantities = order.getProducts().values().toArray(new Integer[0]);
            for (int i = 0; i < size; i++) {
                PreparedStatement statement =
                        connection.prepareStatement("UPDATE productorder SET quantity = ?) WHERE orderid = ? AND productid = ?;");
                statement.setInt(1, quantities[i]);
                statement.setString(2, order.getId());
                statement.setString(3, product[i].getId());
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
            int size = products.size();
            Product[] product = products.keySet().toArray(Product[]::new);
            Integer[] quantities = products.values().toArray(new Integer[0]);
            for(int i = 0; i<size; i++){
                PreparedStatement statement =
                        connection.prepareStatement("INSERT INTO productorder(orderid, productid, quantity) VALUES(?, ?, ?);");
                statement.setString(1, orderId);
                statement.setString(2, product[i].getId());
                statement.setInt(3, quantities[i]);
                statement.executeUpdate();
            }
        }

    }
}
