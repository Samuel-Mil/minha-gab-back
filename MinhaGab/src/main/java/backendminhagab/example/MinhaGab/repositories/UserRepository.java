package backendminhagab.example.MinhaGab.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import backendminhagab.example.MinhaGab.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    
    Optional<UserModel> findByCpf(String cpf);  
    
    Optional<UserModel> findByName(String name);

    Optional<UserModel> findByEmail(String email);  
}
