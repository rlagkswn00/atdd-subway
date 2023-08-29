package kuit.subway.service;

import kuit.subway.domain.Station;
import kuit.subway.dto.FindStationsRes;
import kuit.subway.dto.SaveStationReq;

import java.util.List;

public interface StationService {

    Long createStation(SaveStationReq saveStationReq);
    List<FindStationsRes> findStations();

    Long deleteStation(Long id);
}
