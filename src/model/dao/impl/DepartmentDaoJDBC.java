package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.dao.InstantiateEntitiesDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn)
	{
	    this.conn = conn;
	}

    @Override
    public void insert(Department obj)
	{
	    PreparedStatement st = null;
	    try
		{
		    st = this.conn.prepareStatement("INSERT INTO department (Name) VALUES (?)",
			    Statement.RETURN_GENERATED_KEYS);
		    st.setString(1, obj.getName());

		    int rowsAffected = st.executeUpdate();

		    if (rowsAffected > 0)
			{
			    ResultSet rs = st.getGeneratedKeys();
			    if (rs.next())
				{
				    int id = rs.getInt(1);
				    obj.setId(id);
				}
			    DB.closeResultSet(rs);
			} else
			{
			    throw new DbException("Unexpected error! No rows affected.");
			}

		} catch (SQLException e)
		{
		    throw new DbException(e.getMessage());
		} finally
		{
		    DB.closeStatement(st);
		}

	}

    @Override
    public void update(Department obj)
	{
	    PreparedStatement st = null;
	    try
		{
		    st = this.conn.prepareStatement("UPDATE department SET department.Name = ? WHERE Id = ?");
		    st.setString(1, obj.getName());
		    st.setInt(2, obj.getId());

		    st.executeUpdate();

		} catch (SQLException e)
		{
		    throw new DbException(e.getMessage());
		} finally
		{
		    DB.closeStatement(st);
		}

	}

    @SuppressWarnings("resource")
    @Override
    public void deleteById(Integer id)
	{
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try
		{
		    Department dep = this.findById(id);
		    if (dep != null)
			{
			    st = this.conn.prepareStatement("SELECT seller.*,department.Name as DepName "
				    + "FROM seller INNER JOIN department ON seller.DepartmentId = department.Id "
				    + "WHERE DepartmentId = ? ORDER BY Name");
			    st.setInt(1, dep.getId());
			    rs = st.executeQuery();
			    if (rs.next())
				{
				    System.out.println("Warning!! : foreign key constraint violation!");
				    return;
				}
			}

		    st = this.conn.prepareStatement("DELETE FROM department WHERE Id = ?");
		    st.setInt(1, id);

		    int rows = st.executeUpdate();

		    if (rows == 0)
			{
			    System.out.println("Row doesn´t exist! 0 rows deleted!");
			} else
			{
			    System.out.println("Delete sucessfull! " + rows + " row(s) affected!");
			}

		} catch (SQLException e)
		{
		    throw new DbException(e.getMessage());
		} finally
		{
		    DB.closeStatement(st);
		    DB.closeResultSet(rs);
		}

	}

    @Override
    public Department findById(Integer id)
	{
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try
		{
		    st = this.conn.prepareStatement(
			    "SELECT department.name AS DepName, department.id AS DepartmentId from department WHERE department.id = ?");
		    st.setInt(1, id);
		    rs = st.executeQuery();
		    if (rs.next())
			{
			    Department dep = InstantiateEntitiesDao.instantiateDepartment(rs);
			    return dep;
			}
		    return null;
		} catch (SQLException e)
		{
		    throw new DbException(e.getMessage());
		} finally
		{
		    DB.closeStatement(st);
		    DB.closeResultSet(rs);
		}
	}

    @Override
    public List<Department> findAll()
	{
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try
		{
		    st = this.conn.prepareStatement(
			    "SELECT department.Name AS DepName, department.id AS DepartmentId FROM department ORDER BY department.Name");

		    rs = st.executeQuery();
		    List<Department> list = new ArrayList<Department>();
		    Department dep = null;
		    while (rs.next())
			{

			    dep = InstantiateEntitiesDao.instantiateDepartment(rs);

			    list.add(dep);

			}
		    return list;
		} catch (SQLException e)
		{
		    throw new DbException(e.getMessage());
		} finally
		{
		    DB.closeStatement(st);
		    DB.closeResultSet(rs);
		}
	}

}
