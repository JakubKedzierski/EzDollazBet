package ezdollazbet;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.postgresql.jdbc3.Jdbc3SimpleDataSource;


public class TestGameDAO extends DataSourceBasedDBTestCase {

	@Override
	protected DataSource getDataSource() {
		Jdbc3SimpleDataSource dataSource = new Jdbc3SimpleDataSource();
        dataSource.setURL(
          "jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:schema.sql'");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");
        return dataSource;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("data.xml"));
	}
	
	@Override
	protected DatabaseOperation getSetUpOperation() {
	    return DatabaseOperation.REFRESH;
	}

	@Override
	protected DatabaseOperation getTearDownOperation() {
	    return DatabaseOperation.DELETE_ALL;
	}
	
	
	
}
