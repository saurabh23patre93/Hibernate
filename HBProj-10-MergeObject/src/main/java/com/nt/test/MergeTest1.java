package com.nt.test;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Membership;
import com.nt.utility.HibernateUtil;

public class MergeTest1 {
	
	public static void main(String[] args) {
		Session ses=null;
		Membership member=null;
		Transaction tx=null;
		boolean flag=false;
		
		//Get Session Object
		ses=HibernateUtil.getSession();
		//Prepare Object
		member=new Membership();
		member.setMid(4L);
		member.setName("Saurabh");
		member.setAddrs("Hyd");
		member.setRewardPoints(34L);
		
		try {
			//Begin tx
			tx=ses.beginTransaction();
			System.out.println(ses.merge(member));
			flag=true;
		} catch (HibernateException he) {
			flag=false;
			he.printStackTrace();
		}
		finally {
			//Perform tx mgmt
			if (flag) {
				tx.commit();
				System.out.println("Object is saved or updated");
			} else {
				tx.rollback();
				System.out.println("Object is not saved or updated");
			}
			//Close objects
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
		}
	}
}
