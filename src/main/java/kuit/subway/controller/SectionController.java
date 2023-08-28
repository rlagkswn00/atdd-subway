package kuit.subway.controller;

import kuit.subway.dto.SaveSectionReq;
import kuit.subway.dto.SaveSectionRes;
import kuit.subway.service.LineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sections")
public class SectionController {

    private final LineService lineService;

    @PostMapping
    ResponseEntity<SaveSectionRes> createSection(@RequestBody SaveSectionReq saveSectionReq){
        SaveSectionRes saveSectionRes = lineService.createSections(saveSectionReq);
        return ResponseEntity.created(URI.create("/sections/" + saveSectionRes.getId())).body(saveSectionRes);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<Long> deleteSection(@PathVariable Long id){
        Long responseId = lineService.deleteSection(id);
        return ResponseEntity.ok(responseId);

    }
}
