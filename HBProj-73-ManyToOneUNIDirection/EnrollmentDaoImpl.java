/**@Copyright 2021. All rights are reserved,you should disclose the infromation,  
 * otherwise terms and conditions will apply.
 * 
 */
package com.citi.membership.enrollment.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.EnrollmentDaoRequest;
import com.citi.membership.enrollment.model.EnrollmentDaoResponse;

/**
 * @author    ::asus
 * @Date      ::Apr 29, 2021
 * Description::
 */
public class EnrollmentDaoImpl implements EnrollmentDao {

	private Logger logger=Logger.getLogger(EnrollmentDaoImpl.class);
	public EnrollmentDaoResponse createEnrollment(EnrollmentDaoRequest enrollmentDaoRequest) throws BusinessException, SystemException  {
		logger.debug("Enter into dao--start");
		//1.Get the request from service

		//2.Prepare the request for db i.e. prepare the db queries
/*		String envionrment=System.getProperty("envionrment");
		logger.info("envionrment: "+envionrment);
		String filename="properties/service/enrollment-"+envionrment+"-db.properties";
			
		InputStream inputStream=getClass().getClassLoader().getResourceAsStream(filename);
		Properties properties=new Properties();
		try {
			properties.load(inputStream);
			
			String dburl=System.getProperty("dburl");
			String username=System.getProperty("username");
			String password=System.getProperty("password");
			
		} catch (IOException ioe){
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	*/	
		//3.Call db and get the db response i.e. Resultset

		String dbResponseCode="0";//replace with db Resposense
		String dbResponseMsg="success";


		//4.Prepare the dao response
		EnrollmentDaoResponse daoResponse=null;;
		try {
			daoResponse = new EnrollmentDaoResponse();
			if("0".equals(dbResponseCode)) {
				//Response dao response with ResultSet
				daoResponse.setAckNum("1212abc");
				daoResponse.setEnrollmentStatus("Enrollment Successfull");
				daoResponse.setResponseCode("0");
				daoResponse.setResponseMsg("Success");
				daoResponse.setDiscription("It is enrollment object call");
			}else if("100".equals(dbResponseCode) 
					||"101".equals(dbResponseCode) 
					||"102".equals(dbResponseCode)) {
				//TODO::handle  business exception
				throw new BusinessException(dbResponseCode, dbResponseMsg);

			}else {
				//TODO::handle system exception
				throw new SystemException(dbResponseCode, dbResponseMsg);
			}
		} catch (BusinessException be) {
			// TODO Auto-generated catch block
			//Error log
			//be.printStackTrace();
			logger.error("Business Exception in DAO Layer ",be);
			throw be;
		} catch (SystemException se) {
			// TODO Auto-generated catch block
			//Error log
			logger.error("System Exception in DAO Layer ",se);
			throw se;
		}catch(Exception e) {
			// TODO Auto-generated catch block
			//Error log
			logger.fatal("Exception in DAO Layer ",e);
			throw new SystemException("8888","Unknow Error from database "+e.getMessage());
		}

		logger.info("Exit from dao--end"+daoResponse);
		return daoResponse;
	}

}
