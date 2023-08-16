package kuit.subway.service;

import kuit.subway.domain.Station;
import kuit.subway.dto.SaveStationReq;
import kuit.subway.dto.SaveStationRes;

public interface StationService {

    SaveStationRes createStation(SaveStationReq saveStationReq);

}
