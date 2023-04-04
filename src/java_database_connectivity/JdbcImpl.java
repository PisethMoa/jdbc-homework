package java_database_connectivity;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;
public class JdbcImpl{
    public DataSource dataSource(){
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setUser("postgres");
        pgSimpleDataSource.setPassword("piseth");
        pgSimpleDataSource.setDatabaseName("postgres");
        pgSimpleDataSource.setPortNumbers(new int[]{5433});
        return pgSimpleDataSource;
    }
}
