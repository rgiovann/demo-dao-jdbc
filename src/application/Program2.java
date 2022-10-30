package application;

import java.util.List;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

    public Program2()
	{
	    // TODO Auto-generated constructor stub
	}

    public static void main(String[] args)
	{

	    DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
	    System.out.println(" ===TEST 1: department findById ====");
	    Department department = departmentDao.findById(3);
	    System.out.println(department);

	    System.out.println(" ===TEST 2: department findAll ====");
	    List<Department> list = departmentDao.findAll();

	    for (Department obj : list)
		{
		    System.out.println(obj);
		}

	    System.out.println(" ===TEST 3: department insert ====");
	    Department newDepartment = new Department(null, "Moda Feminina");
	    departmentDao.insert(newDepartment);
	    System.out.println("Inserted! New id = " + newDepartment.getId());

	    System.out.println(" ===TEST 4: department update ====");

	    department = departmentDao.findById(6);
	    department.setName("Desktops");
	    departmentDao.update(department);
	    System.out.println("Update completed!");

	    System.out.println(" ===TEST 5: department delete ====");
	    departmentDao.deleteById(9);

	    DB.closeConnection();
	}

}
