package kuit.subway.study.fixture;

import kuit.subway.dto.SaveSectionReq;

public class SectionFixture {
    public static SaveSectionReq 지하철_구간_생성_픽스처(Long upStationId, Long downStationId, Long lineId){
        return new SaveSectionReq(upStationId,downStationId,lineId);
    };
}
