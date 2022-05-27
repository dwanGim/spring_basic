package com.ict.dao;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;



@Runwith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/wbapp/WEP-INF/spring/root-context.xml")
@Log4j
public class OracleConnectionPoolTest {

		@Autowrired
		private DataSource dataSource;
		
		@Test
		public void testConnection() {
			try(Connection con = dataSource.getConnection()){
				log.info(con);
				log.info("hikariCP connection");
				
			} catch(Exception e) {
				fail(e.getMessage());
			}
		}
		
		
	}

}
