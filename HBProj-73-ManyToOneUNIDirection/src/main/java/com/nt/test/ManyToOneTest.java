package com.nt.test;

import com.nt.dao.ManyToOneDao;
import com.nt.dao.ManyToOneDaoImp;

public class ManyToOneTest {

	public static void main(String[] args) {
		//Get DAO
		ManyToOneDao dao=new ManyToOneDaoImp();
		//invoke method
		//dao.saveDataUsingChild();	
		//dao.loadDataUsingChild();
		//dao.deleteAllChildsAndItsParent();
		dao.deleteOneChildFromParent();
	}

}
