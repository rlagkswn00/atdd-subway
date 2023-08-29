package kuit.subway.service;

import kuit.subway.dto.*;

import java.util.List;

public interface LineService {
    Long createLines(SaveLineReq saveLineReq);
    FindLinesRes findLine(Long id);

    List<FindLinesRes> findAllLine();

    Long deleteLine(Long id);

    Long updateLine(Long id, UpdateLineReq updateLineReq);

    Long createSections(SaveSectionReq saveSectionReq);

    Long deleteSection(Long lineId);
}
