package fr.mobileagri.julie_camille_api.repository;

import fr.mobileagri.julie_camille_api.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
  boolean existsByIp(String ip);
}
