package kuit.subway.study.step;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.dto.UpdateLineReq;

import static kuit.subway.utils.ExtractableResponseUtil.*;

public class LineStep {
    private final static String PATH = "/lines";
    public static ExtractableResponse<Response> 지하철_라인_생성(SaveLineReq saveLineReq){
        return post(PATH, saveLineReq);
    }

    public static ExtractableResponse<Response> 지하철_라인_조희(Long lineId){
        return get(PATH + "/" + lineId);
    }

    public static ExtractableResponse<Response> 지하철_라인_목록_조회(){
        return get(PATH);
    }

    public static ExtractableResponse<Response> 지하철_라인_삭제(Long id){
        return delete(PATH + "/" + id);
    }

    public static ExtractableResponse<Response> 지하철_라인_수정(Long id, UpdateLineReq updateLineReq){
        return update(PATH + "/" + id, updateLineReq);
    }

}
