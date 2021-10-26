package com.nt.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.PhoneNumber;
import com.nt.entity.UserInfo;
import com.nt.utility.HibernateUtil;

import oracle.net.aso.q;

public class OneToManyDAOImpl implements OneToManyDAO {

	@Override
	public void saveDataUsingParent() {
		//Get Session
		Session ses=HibernateUtil.getSession();
		//Prepare Object
		//Child object
		PhoneNumber ph1=new PhoneNumber(899999L,"Resident","airtel");
		PhoneNumber ph2=new PhoneNumber(444563L,"Office","Vi");
		Set<PhoneNumber> phonesSet=Set.of(ph1,ph2);
		//Parent Object
		UserInfo user=new UserInfo("Suresh","hyd");
		//Set Childs to Parent
		user.setPhones(phonesSet);
		
		Transaction tx=null;
		boolean flag=false;
		try {
			//Begin tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//Save object
			ses.save(user);
			flag=true;
		} catch (HibernateException e) {
			e.printStackTrace();
			flag=false;
		}
		finally {
			//Tx Mgmt
			if(flag) {
				tx.commit();
				System.out.println("parent to child objs are saved");
			}
			else {
				tx.rollback();
				System.out.println("parent to child objs are not saved");
			}
				//close session factory
			HibernateUtil.closeSessionFactory();
		}//finally
	}
	
	
	@Override
	public void loadDataUsingParent() {
		//Get Session
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		try {
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//Load Parent Object
			Query query=ses.createQuery("from UserInfo");
			List<UserInfo> list=query.getResultList();
			list.forEach(user->{
				System.out.println("Parent ::"+user.getUserId()+" "+user.getUsername()+" "+user.getAddrs());
				//Get Associated Childs of Parent
				Set<PhoneNumber>childs=user.getPhones();
				System.out.println(childs.isEmpty());
				childs.forEach(ph->System.out.println("Child::"+ph));
			});
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			//Close session factory
			HibernateUtil.closeSessionFactory();
		}
		
	}
}
