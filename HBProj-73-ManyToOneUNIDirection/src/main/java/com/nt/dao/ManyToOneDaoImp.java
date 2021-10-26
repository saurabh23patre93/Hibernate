package com.nt.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Department;
import com.nt.entity.EmpDetails;
import com.nt.utility.HibernateUtil;

public class ManyToOneDaoImp implements ManyToOneDao {

	@Override
	public void saveDataUsingChild() {
		//Get Session Object
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		boolean flag=false;
		try {
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//Prepare Objects
			//child object
			EmpDetails emp1=new EmpDetails("Raja","hyd", 9000.0f);
			EmpDetails emp2=new EmpDetails("Rajesh","vizag", 79993.0f);
			EmpDetails emp3=new EmpDetails("Suresh","delhi",70000.0f);
			//Parent obj
			Department dept=new Department("IT","Mumbai",10);
			//Set parent obj to multiple chail obj
			emp1.setDept(dept);emp2.setDept(dept);emp3.setDept(dept);
			//Save Child obj
			ses.save(emp1);ses.save(emp2);ses.save(emp3);
			flag=true;
		} catch (HibernateException e) {
			flag=false;
			e.printStackTrace();
		}
		finally {
			//perform Tx mgmt
			if(flag) {
				tx.commit();
				System.out.println("Objects are save (many to one)");
			}
			else {
				tx.rollback();
				System.out.println("Objects are not save (many to one)");
			}
			//close sessionfactory
			HibernateUtil.closeSessionFactory();
		}//finally

	}
	
	@Override
	public void loadDataUsingChild() {
		//Get Session
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		try {
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//Load Object
			Query query=ses.createQuery("from EmpDetails");
			List<EmpDetails> list=query.getResultList();
			list.forEach(emp->{
				System.out.println("Child ::"+emp.getEno()+" "+emp.getEname()+" "+emp.getEadd()+" "+emp.getSalary());
				//Get Associated parent object
				Department dept=emp.getDept();
				System.out.println("Parent ::"+dept.getDno()+" "+dept.getDname()+" "+dept.getLocation()+" "+dept.getCapacity());
				
			});
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			//Close HB object
			HibernateUtil.closeSessionFactory();
		}
	}
	
	@Override
	public void deleteAllChildsAndItsParent() {
		//Get Session
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		boolean flag=false;
		try {
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//Load All child object
			Query query=ses.createQuery("from EmpDetails");
			List<EmpDetails> list=query.getResultList();
			list.forEach(emp->{
				ses.delete(emp);
			});
			flag=true;
		} catch (HibernateException e) {
			e.printStackTrace();
			flag=false;
		}
		finally {
			//Perform TxMgmt
			if (flag) {
				tx.commit();
				System.out.println("All childs And its Parents are deleted");
			} else {
				tx.rollback();
				System.out.println("All Chidls and its parent is not deleted");
			}
			//Close HB objects
			HibernateUtil.closeSessionFactory();
		}
		
	}
	
//	@Override//Bad Practice
//	public void deleteOneChildFromParent() {
//		//Get Session
//		Session ses=HibernateUtil.getSession();
//		Transaction tx=null;
//		boolean flag=false;
//		try {
//			if(!ses.getTransaction().isActive())
//				tx=ses.beginTransaction();
//			//Load Child object
//			EmpDetails emp=ses.get(EmpDetails.class,3);
//			ses.delete(emp);
//			flag=true;
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			flag=false;
//		}
//		finally {
//			//perform TxMgmt
//			 if(flag) {
//				 tx.commit();
//				 System.out.println("One chid of a parent is deleted ");
//			 }
//			 else {
//				 tx.rollback();
//				 System.out.println("One chid of a parent is not deleted ");
//			 }
//			//close HB objs
//			HibernateUtil.closeSessionFactory();
//		}
//	}
	
	@Override
	public void deleteOneChildFromParent() {
		//Get Session
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		boolean flag=false;
		try {
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			Query query=ses.createQuery("DELETE FROM EmpDetails WHERE eno=:no");
			query.setParameter("no",2);
			
			int count=query.executeUpdate();
			
			flag=true;
		} catch (HibernateException e) {
			e.printStackTrace();
			flag=false;
		}
		finally {
			//perform TxMgmt
			 if(flag) {
				 tx.commit();
				 System.out.println("One chid of a parent is deleted ");
			 }
			 else {
				 tx.rollback();
				 System.out.println("One chid of a parent is not deleted ");
			 }
			//close HB objs
			HibernateUtil.closeSessionFactory();
		}
	}
}
