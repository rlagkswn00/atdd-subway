package kuit.subway.service;

import kuit.subway.dto.FindLinesRes;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.dto.SaveLineRes;

public interface LineService {
    SaveLineRes createLines(SaveLineReq saveLineReq);
    FindLinesRes findLines(Long id);
}
