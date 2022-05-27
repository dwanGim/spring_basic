package com.ict.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import lombok.extern.log4j.Log4j;

@Log4j
public class OjdbcConnetionTest {
	

		static {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		@Test
		public void testConnetction()	{
			try(Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XEPDB1",
					"mytest",
					"mytest"
					)) {
				log.info(con);
				log.info("정상적으로 연결되었습니다.");
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
	}

}
