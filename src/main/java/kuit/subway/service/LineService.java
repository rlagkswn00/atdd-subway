package kuit.subway.service;

import kuit.subway.dto.SaveLineReq;
import kuit.subway.dto.SaveLineRes;

public interface LineService {
    SaveLineRes createLines(SaveLineReq saveLineReq);
}
