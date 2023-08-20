package kuit.subway.study.fixture;

import kuit.subway.domain.Line;
import kuit.subway.dto.SaveLineReq;

public class LineFixture {
    public static SaveLineReq 라인_픽스처(String color, Long distance, String name, Long upStationId, Long downStationId){
        return new SaveLineReq(color,distance,name,upStationId,downStationId);
    }
}
