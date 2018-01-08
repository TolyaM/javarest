package rnd.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rnd.users.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Photo findOneByUrl(String url);
}
