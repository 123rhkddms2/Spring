package kr.co.sboard.repository;

import kr.co.sboard.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, String> {
}
