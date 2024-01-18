package LogInSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<userData, Long> {

    boolean existsByUserIdAndPassword(String userId, String password);

    boolean existsByUserIdAndDob(String userId, String dob);
    

    userData findByUserId(String userId); 
    boolean existsByUserId(String userId);
}
