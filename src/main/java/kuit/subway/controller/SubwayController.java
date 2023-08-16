package kuit.subway.controller;

import kuit.subway.dto.FindStationsRes;
import kuit.subway.dto.SaveStationReq;
import kuit.subway.dto.SaveStationRes;
import kuit.subway.service.StationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SubwayController {
    private final StationService stationService;

    @PostMapping("/station")
    public ResponseEntity<SaveStationRes> createStation(@RequestBody SaveStationReq stationReq) {
        SaveStationRes saveStationRes = stationService.createStation(stationReq);
        return ResponseEntity.ok(saveStationRes);
    }

    @GetMapping("/stations")
    public ResponseEntity<List<FindStationsRes>> findStations() {
        List<FindStationsRes> findStations = stationService.findStations();
        return ResponseEntity.ok(findStations);
    }
}
