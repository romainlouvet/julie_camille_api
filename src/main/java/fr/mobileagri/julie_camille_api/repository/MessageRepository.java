package fr.mobileagri.julie_camille_api.repository;

import fr.mobileagri.julie_camille_api.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
  List<Message> findByIp(String ip);

  boolean existsByIp(String ip);
}
