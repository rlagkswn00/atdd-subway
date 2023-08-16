package kuit.subway.service;

import kuit.subway.domain.Station;
import kuit.subway.dto.FindStationsRes;
import kuit.subway.dto.SaveStationReq;
import kuit.subway.dto.SaveStationRes;

import java.util.List;

public interface StationService {

    SaveStationRes createStation(SaveStationReq saveStationReq);
    List<FindStationsRes> findStations();
}
