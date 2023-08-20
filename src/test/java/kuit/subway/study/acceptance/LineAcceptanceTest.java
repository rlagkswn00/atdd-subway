package kuit.subway.study.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kuit.subway.AcceptanceTest;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.study.fixture.LineFixture;
import kuit.subway.study.step.LineStep;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static kuit.subway.study.fixture.LineFixture.라인_픽스처;
import static kuit.subway.study.fixture.StationFixture.지하철_역_생성_픽스처;
import static kuit.subway.study.step.LineStep.지하철_라인_생성;
import static kuit.subway.study.step.LineStep.지하철_라인_조희;
import static kuit.subway.study.step.StationStep.지하철_역_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;

public class LineAcceptanceTest extends AcceptanceTest {

    @DisplayName("지하철 노선 생성 테스트")
    @Test
    public void 지하철_노선_생성() {
        //given - 상행 종점 진접역, 하행 종점 오이도역 생성
        지하철_역_생성(지하철_역_생성_픽스처("진접역"));
        지하철_역_생성(지하철_역_생성_픽스처("오이도역"));

        //when
        ExtractableResponse<Response> response = 지하철_라인_생성(라인_픽스처("green",22L,"4호선",1L,2L));

        //then -
        assertThat(response.statusCode()).isEqualTo(CREATED.value());
    }

    @DisplayName("지하철 노선 조회 테스트")
    @Test
    public void 지하철_노선_조회() {
        //given - 상행 종점 진접역, 하행 종점 오이도역 생성 및 1개 라인 생성
        지하철_역_생성(지하철_역_생성_픽스처("진접역"));
        지하철_역_생성(지하철_역_생성_픽스처("오이도역"));
        지하철_라인_생성(라인_픽스처("green",22L,"4호선",1L,2L));

        //when - 라인 조회
        ExtractableResponse<Response> response = 지하철_라인_조희(1L);

        //then - HTTP Status 200 OK 확인
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
