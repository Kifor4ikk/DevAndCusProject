import configs.Database;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try{
            Statement state = Database.connectWithDataBase();

            state.execute("create table test()");
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
