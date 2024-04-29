package com.jova.jova.dao.impl;

import com.jova.jova.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    boolean existsByUsernameOrMail(String nomUtilisateur, String mail);
    Optional<Utilisateur> findByUsernameAndPassword(String nomUtilisateur, String motDePasse);

}
