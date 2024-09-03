package backendminhagab.example.MinhaGab.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import backendminhagab.example.MinhaGab.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByCpf(String cpf);  
    
    Optional<UserModel> findByName(String name);

    Optional<UserModel> findByEmail(String email);

    @Modifying
    @Query("UPDATE UserModel u SET u.refreshToken = :refreshToken WHERE u.cpf = :cpf")
    void updateRefreshToken(String cpf, String refreshToken);
}
