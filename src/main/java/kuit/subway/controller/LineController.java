package kuit.subway.controller;


import kuit.subway.dto.FindLinesRes;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.dto.SaveLineRes;
import kuit.subway.service.LineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/lines")
public class LineController {

    private final LineService lineService;

    @PostMapping
    public ResponseEntity<SaveLineRes> createLine(@RequestBody SaveLineReq saveLineReq){
        SaveLineRes saveLineRes = lineService.createLines(saveLineReq);
        return ResponseEntity.created(URI.create("/lines/" + saveLineRes.getId())).body(saveLineRes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindLinesRes> findLines(@PathVariable Long id){
        FindLinesRes findLinesRes = lineService.findLines(id);
        return ResponseEntity.ok(findLinesRes);
    }
}
