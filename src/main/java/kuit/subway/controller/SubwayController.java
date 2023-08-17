package kuit.subway.controller;

import kuit.global.exception.DuplicateException;
import kuit.global.exception.NotFoundException;
import kuit.subway.dto.FindStationsRes;
import kuit.subway.dto.SaveStationReq;
import kuit.subway.dto.SaveStationRes;
import kuit.subway.service.StationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SubwayController {
    private final StationService stationService;

    @PostMapping("/station")
    public ResponseEntity<SaveStationRes> createStation(@RequestBody SaveStationReq stationReq) {
        try {
            SaveStationRes saveStationRes = stationService.createStation(stationReq);
            return ResponseEntity.ok(saveStationRes);
        } catch (DuplicateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/stations")
    public ResponseEntity<List<FindStationsRes>> findStations() {
        try {
            List<FindStationsRes> findStations = stationService.findStations();
            return ResponseEntity.ok(findStations);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/station/{id}")
    public ResponseEntity<Long> deleteStation(@PathVariable Long id) {
        try {
            Long deleteId = stationService.deleteStation(id);
            return ResponseEntity.ok(deleteId);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
