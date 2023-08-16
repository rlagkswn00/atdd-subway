package kuit.subway.service;

import jakarta.transaction.Transactional;
import kuit.subway.domain.Station;
import kuit.subway.dto.FindStationsRes;
import kuit.subway.dto.SaveStationReq;
import kuit.subway.dto.SaveStationRes;
import kuit.subway.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;

    @Override
    public SaveStationRes createStation(SaveStationReq saveStationReq) {
        Station station = Station.builder()
                .name(saveStationReq.getName())
                .build();
        Long id = stationRepository.save(station).getId();
        return new SaveStationRes(id);
    }

    @Override
    public List<FindStationsRes> findStations() {
        List<FindStationsRes> stations = stationRepository.findAll().stream()
                .map(s -> FindStationsRes.builder()
                        .id(s.getId())
                        .name(s.getName()).build())
                .toList();

        return stations;
    }
}
