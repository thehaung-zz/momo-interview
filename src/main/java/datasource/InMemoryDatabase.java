package datasource;

import model.Session;

/**
 * @author : Hau Nguyen
 * @created : 6/2/22
 **/

public interface InMemoryDatabase {
    void order(String productKey);
    void insert(Double money);

    Session createSession();

    void endSession();
}
