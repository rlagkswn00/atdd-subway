package kuit.subway.service;

import kuit.subway.dto.*;

import java.util.List;

public interface LineService {
    SaveLineRes createLines(SaveLineReq saveLineReq);
    FindLinesRes findLines(Long id);

    List<FindLinesRes> findAllLines();

    Long deleteLine(Long id);

    Long updateLine(Long id, UpdateLineReq updateLineReq);

    SaveSectionRes createSections(SaveSectionReq saveSectionReq);
}
