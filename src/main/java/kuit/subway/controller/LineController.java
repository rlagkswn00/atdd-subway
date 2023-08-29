package kuit.subway.controller;


import kuit.subway.dto.FindLinesRes;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.dto.UpdateLineReq;
import kuit.subway.service.LineService;
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
@RequestMapping("/lines")
public class LineController {

    private final LineService lineService;

    @PostMapping
    public ResponseEntity<Long> createLine(@RequestBody SaveLineReq saveLineReq){
        Long createId = lineService.createLines(saveLineReq);
        return ResponseEntity.created(URI.create("/lines/" + createId)).body(createId);
    }

    @GetMapping
    public ResponseEntity<List<FindLinesRes>> findAllLines(){
        List<FindLinesRes> allLines = lineService.findAllLine();
        return ResponseEntity.ok(allLines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindLinesRes> findLines(@PathVariable Long id) {
        FindLinesRes findLinesRes = lineService.findLine(id);
        return ResponseEntity.ok(findLinesRes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteLine(@PathVariable Long id) {
        Long deleteId = lineService.deleteLine(id);
        return ResponseEntity.ok(deleteId);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Long> updateLine(@PathVariable Long id, @RequestBody UpdateLineReq updateLineReq) {
        Long responseId = lineService.updateLine(id,updateLineReq);
        return ResponseEntity.ok(responseId);
    }
}
