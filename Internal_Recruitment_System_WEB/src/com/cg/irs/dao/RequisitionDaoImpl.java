package com.cg.irs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cg.irs.entity.ProjectBean;
import com.cg.irs.entity.RequisitionBean;
import com.cg.irs.exception.IRSException;

@Repository("requisitionDao")
public class RequisitionDaoImpl implements IRequisitionDao {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public RequisitionBean insertRequisition(RequisitionBean requisitionBean)
			throws IRSException {
		try
		{
			requisitionBean.setRequisitionId(generateRequisitionIdUsingSeq());
			entityManager.persist(requisitionBean);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new IRSException("Unable to Requisition");
		}
		return requisitionBean;
	}
	
	/*
     * Generate Question Id using Sequence.
     */
    public String generateRequisitionIdUsingSeq()
    {
        Query query = entityManager.createNativeQuery("select requisition_id_seq.nextval from dual");
        String id = ""+query.getSingleResult();
        return id;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<RequisitionBean> getAllRequisition() throws IRSException
	{
		List<RequisitionBean> requisitions =null;
		Query query=entityManager.createNamedQuery("getAllRequisitions", ProjectBean.class);
	
		try 
		{
			requisitions=query.getResultList();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new IRSException("Unable to fetch Requisitions No requisitions found ");
		}
		return requisitions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RequisitionBean> getSpecificRequisition(String rmId)
			throws IRSException 
	{
		
		List<RequisitionBean> requisitions =null;
		Query query=entityManager.createNamedQuery("getSpecificRequisition", RequisitionBean.class);
		query.setParameter("userId",rmId);
		try 
		{
			requisitions=query.getResultList();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new IRSException("Unable to fetch Requisitions raised by RM : "+rmId+" No requisitions found");
		}
		return requisitions;
	}

	@Override
	public void updateStatus(String requisitionId, String currentStatus)throws IRSException 
	{
		RequisitionBean requisitionBean=getRequisition(requisitionId);
		requisitionBean.setCurrentStatus(currentStatus);
		try 
		{
			entityManager.merge(requisitionBean);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new IRSException("Unable to update status of requisition with id : "+requisitionId);
		}
	}
	
	private RequisitionBean getRequisition(String requisitionId) throws IRSException
	{
		RequisitionBean requisitionBean=null;
		try
		{
			requisitionBean=entityManager.find(RequisitionBean.class, requisitionId);
			if(requisitionBean==null)
			{
				throw new Exception("Requsition not found with id : "+requisitionId);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new IRSException(e.getMessage());
		}
		return requisitionBean;
		
	}
}
