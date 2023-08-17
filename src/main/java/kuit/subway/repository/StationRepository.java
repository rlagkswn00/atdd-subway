package kuit.subway.repository;

import kuit.subway.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StationRepository extends JpaRepository<Station, Long> {

    boolean existsStationByName(String name);
}
