package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;

    public Department()
	{
	    // TODO Auto-generated constructor stub
	}

    public Department(Integer id, String name)
	{
	    this.id = id;
	    this.name = name;
	}

    public Integer getId()
	{
	    return this.id;
	}

    public void setId(Integer id)
	{
	    this.id = id;
	}

    public String getName()
	{
	    return this.name;
	}

    public void setName(String name)
	{
	    this.name = name;
	}

    @Override
    public int hashCode()
	{
	    return Objects.hash(this.id);
	}

    @Override
    public boolean equals(Object obj)
	{
	    if (this == obj)
		{
		    return true;
		}
	    if (obj == null)
		{
		    return false;
		}
	    if (this.getClass() != obj.getClass())
		{
		    return false;
		}
	    Department other = (Department) obj;
	    return Objects.equals(this.id, other.id);
	}

    @Override
    public String toString()
	{
	    return "Department [id=" + this.id + ", name=" + this.name + "]";
	}

}
