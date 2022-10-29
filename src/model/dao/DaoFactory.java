package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    public DaoFactory()
	{
	    // TODO Auto-generated constructor stub
	}

    public static SellerDao createSellerDao()
	{
	    return new SellerDaoJDBC();
	}

}
