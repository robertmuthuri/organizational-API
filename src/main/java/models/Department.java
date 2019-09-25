package models;

import java.util.Objects;

public class Department {

    private int id;
    private String name;
    private String description;
    private int total_employees;

    public Department(String name, String description, int total_employees) {
        this.name = name;
        this.description = description;
        this.total_employees = total_employees;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getTotal_employees() { return total_employees; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setTotal_employees(int total_employees) { this.total_employees = total_employees; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return getId() == that.getId() &&
                getTotal_employees() == that.getTotal_employees() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getTotal_employees());
    }
}
