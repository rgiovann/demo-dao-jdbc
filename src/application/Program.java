package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

    public Program()
	{
	    // TODO Auto-generated constructor stub
	}

    public static void main(String[] args)
	{

	    SellerDao sellerDao = DaoFactory.createSellerDao();
	    System.out.println(" ===TEST 1: seller findById ====");
	    Seller seller = sellerDao.findById(3);
	    System.out.println(seller);
	    System.out.println(" ===TEST 2: seller findByDepartment ====");
	    Department department = new Department(3, null);
	    List<Seller> list = sellerDao.findByDepartment(department);

	    for (Seller obj : list)
		{
		    System.out.println(obj);
		}
	    System.out.println(" ===TEST 3: seller findAll ====");
	    list = sellerDao.findAll();

	    for (Seller obj : list)
		{
		    System.out.println(obj);
		}

	    System.out.println(" ===TEST 4: seller insert ====");
	    Seller newSeller = new Seller(null, "Greg Maars", "greg@email.com", new Date(), 4000.0, department);
	    sellerDao.insert(newSeller);
	    System.out.println("Inserted! New id = " + newSeller.getId());

	    System.out.println(" ===TEST 5: seller update ====");

	    seller = sellerDao.findById(1);
	    seller.setName("Martha Waine");
	    sellerDao.update(seller);
	    System.out.println("Update completed!");

	    System.out.println(" ===TEST 6: seller update ====");
	    sellerDao.deleteById(110);
	    System.out.println("Delete completed!");
	}

}
