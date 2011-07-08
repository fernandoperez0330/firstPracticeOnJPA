package Business;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Persistence;
import javax.persistence.TableGenerator;

@Entity
public class Entities implements Serializable {
    private static String PersistenceUnit = "PU1";
    @Id
    @TableGenerator(name="tablegen",table="ID_TABLE",pkColumnName="ID",valueColumnName="NEXT_ID")
    @GeneratedValue(strategy= GenerationType.TABLE,generator="tablegen")
    private int id;
    @Column(length=30,nullable=false)
    private String nombre;
    
    @Column(length=30,nullable=false)
    private String apellido;
    
    public Entities(){}
    
    public Entities(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PersistenceUnit);
        EntityManager em = emf.createEntityManager(); 
        em.getTransaction().begin();
        Entities curEnt = em.find(Entities.class, id);
        this.id = curEnt.getId();
        this.nombre = curEnt.getNombre();
        this.apellido = curEnt.getApellido();
        em.getTransaction().commit();
        em.close();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Entities other = (Entities) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        return hash;
    }
    

}
