package kuit.subway.service;

import jakarta.transaction.Transactional;
import kuit.subway.domain.Station;
import kuit.subway.dto.SaveStationReq;
import kuit.subway.dto.SaveStationRes;
import kuit.subway.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;

    public SaveStationRes createStation(SaveStationReq saveStationReq) {
        log.info("service 실행");
        Station station = Station.builder()
                .name(saveStationReq.getName())
                .build();
        Long id = stationRepository.save(station).getId();
        return new SaveStationRes(id);
    }
}
