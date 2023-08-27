package kuit.subway.service;

import kuit.subway.dto.FindLinesRes;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.dto.SaveLineRes;
import kuit.subway.dto.UpdateLineReq;

import java.util.List;

public interface LineService {
    SaveLineRes createLines(SaveLineReq saveLineReq);
    FindLinesRes findLines(Long id);

    List<FindLinesRes> findAllLines();

    Long deleteLine(Long id);

    Long updateLine(Long id, UpdateLineReq updateLineReq);

}
