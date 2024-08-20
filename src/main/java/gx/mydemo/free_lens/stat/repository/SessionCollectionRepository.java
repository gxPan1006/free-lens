package gx.mydemo.free_lens.stat.repository;

import gx.mydemo.free_lens.stat.dao.SessionCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionCollectionRepository extends JpaRepository<SessionCollection, Long> {
//    SessionCollection findByCollectionName(String collectionName);
//    List<SessionCollection> findAll();
}
