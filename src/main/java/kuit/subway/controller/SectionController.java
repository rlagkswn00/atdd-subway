package kuit.subway.controller;

import kuit.subway.dto.SaveSectionReq;
import kuit.subway.dto.SaveSectionRes;
import kuit.subway.service.LineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
