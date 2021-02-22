import static org.junit.Assert.*;

import org.junit.Test;

import com.wipro.sales.dao.StockDao;

public class testunit {

	@Test
	public void test() throws Exception {
		StockDao sd=new StockDao();
		sd.deleteStock("ma1006");
		
		
	}

}
