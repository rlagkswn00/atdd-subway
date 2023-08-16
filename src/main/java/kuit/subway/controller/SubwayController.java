package kuit.subway.controller;

import kuit.subway.dto.SaveStationReq;
import kuit.subway.dto.SaveStationRes;
import kuit.subway.service.StationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/station")
public class SubwayController {
    private final StationService stationService;

    @PostMapping
    public ResponseEntity<SaveStationRes> createStation(@RequestBody SaveStationReq stationReq) {
        log.info("API 실행");
        SaveStationRes saveStationRes = stationService.createStation(stationReq);
        return ResponseEntity.ok(saveStationRes);
    }
}
