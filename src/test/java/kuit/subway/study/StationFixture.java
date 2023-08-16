package kuit.subway.study;

import kuit.subway.dto.SaveStationReq;

public class StationFixture {
    public static SaveStationReq 지하철_역_생성_픽스처(String name){
        return new SaveStationReq(name);
    }
}
