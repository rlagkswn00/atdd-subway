package kuit.subway.study.step;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.dto.SaveSectionReq;

import static kuit.subway.utils.ExtractableResponseUtil.post;

public class SectionStep {
    private final static String PATH = "/sections";

    public static ExtractableResponse<Response> 지하철_구간_생성(SaveSectionReq saveSectionReq){
        return post(PATH, saveSectionReq);
    }


}
