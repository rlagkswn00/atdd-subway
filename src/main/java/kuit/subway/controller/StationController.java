package kuit.subway.controller;

import kuit.subway.dto.*;
import kuit.subway.service.LineService;
import kuit.subway.service.StationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/stations")
public class StationController {
    private final StationService stationService;

    @PostMapping
    public ResponseEntity<Long> createStation(@RequestBody SaveStationReq stationReq) {
        Long createId = stationService.createStation(stationReq);
        return ResponseEntity.created(URI.create("/stations/" + createId)).body(createId);
    }

    @GetMapping
    public ResponseEntity<List<FindStationsRes>> findStations() {
        List<FindStationsRes> findStations = stationService.findStations();
        return ResponseEntity.ok(findStations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteStation(@PathVariable Long id) {
        Long deleteId = stationService.deleteStation(id);
        return ResponseEntity.ok(deleteId);
    }
}
