package backendminhagab.example.MinhaGab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backendminhagab.example.MinhaGab.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer>{

}
