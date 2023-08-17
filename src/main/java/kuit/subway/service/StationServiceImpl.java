package kuit.subway.service;

import jakarta.transaction.Transactional;
import kuit.global.exception.DuplicateException;
import kuit.global.exception.NotFoundException;
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
        //중복 역 추가 시도 시 예외 발생
        if(stationRepository.existsStationByName(saveStationReq.getName()))
            throw new DuplicateException(saveStationReq.getName() + "역은 이미 추가되어 있습니다.");

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

        //역이 없는 경우 예외 발생
        if(stations.size() == 0)
            throw new NotFoundException("역이 존재하지 않습니다.");

        return stations;
    }

    @Override
    public Long deleteStation(Long id) {
        //id값이 존재하지 않으면 삭제 불가
        if(!stationRepository.existsById(id))
            throw new NotFoundException(id + "값의 역은 존재하지 않습니다.");

        stationRepository.deleteById(id);
        return id;
    }
}
