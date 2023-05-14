package au.com.attari.ivcweb.crud.repository;

import au.com.attari.ivcweb.crud.model.RoleLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleLookupRepository extends JpaRepository<RoleLookup, Long> {
    Optional<RoleLookup> findByRole(String role);
}
