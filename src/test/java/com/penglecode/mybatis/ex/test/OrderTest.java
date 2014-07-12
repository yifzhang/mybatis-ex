package com.penglecode.mybatis.ex.test;

import static com.penglecode.mybatis.ex.test.util.MapperUtils.getMapperKey;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.junit.Test;

import com.penglecode.mybatis.ex.ExSqlSession;
import com.penglecode.mybatis.ex.test.bean.ChildOrder;
import com.penglecode.mybatis.ex.test.bean.MainOrder;

public class OrderTest extends BaseTestCase {

	/**
	 * @throws Exception
	 */
	@Test
	public void testInsertOrder() throws Exception {
		MainOrder mainOrder = new MainOrder();
		mainOrder.setOrderId(100001L);
		mainOrder.setUserId(1L);
		mainOrder.setTotalMoney(210.0);
		mainOrder.setTotalFreight(10.0);
		mainOrder.setDiscount(1.0);
		mainOrder.setOrderTime("2014-07-11 14:55:35");
		mainOrder.setStatus("0");
		List<ChildOrder> childOrderList = new ArrayList<ChildOrder>();
		for(int i = 1; i <= 5; i++){
			ChildOrder childOrder = new ChildOrder();
			childOrder.setOrderId(Long.valueOf(mainOrder.getOrderId().toString() + i));
			childOrder.setMainOrderId(mainOrder.getOrderId());
			childOrder.setProductId(10001L);
			childOrder.setProductName("iPhone 5S 硅胶皮套");
			childOrder.setBuyNum(1);
			childOrder.setUnitPrice(20.0);
			childOrder.setFreight(2.0);
			childOrder.setOrderTime(mainOrder.getOrderTime());
			childOrder.setSubTotal(22.0);
			childOrderList.add(childOrder);
		}
		ExSqlSession sqlSession = null;
		try {
			sqlSession = (ExSqlSession) getSqlSessionFactory().openSession(ExecutorType.SIMPLE, false);
			int affectedRow = sqlSession.insert(getMapperKey(MainOrder.class, "insertMainOrder"), mainOrder);
			System.out.println(">>> inserted rows " + affectedRow);
			int[] affectedRows = sqlSession.batchInsert(getMapperKey(ChildOrder.class, "insertChildOrder"), childOrderList);
			System.out.println(">>> inserted rows " + affectedRows.length);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		} finally {
			if(sqlSession != null){
				sqlSession.close();
			}
		}
	}
	
}
