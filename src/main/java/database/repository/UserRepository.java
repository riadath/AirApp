package database.repository;

import database.DB;

public class UserRepository extends DB {
    public UserRepository() {
        super();
    }
    static String table_name = "user_info";

    public void insert(String[] values) {
        super.insert(table_name, values);
    }
}
