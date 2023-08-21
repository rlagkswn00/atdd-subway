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

import static kuit.subway.study.fixture.LineFixture.라인_수정_픽스처;
import static kuit.subway.study.fixture.LineFixture.라인_픽스처;
import static kuit.subway.study.fixture.StationFixture.지하철_역_생성_픽스처;
import static kuit.subway.study.step.LineStep.*;
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
        ExtractableResponse<Response> response = 지하철_라인_생성(라인_픽스처("green", 22L, "4호선", 1L, 2L));

        //then -
        assertThat(response.statusCode())
                .isEqualTo(CREATED.value());
    }

    @DisplayName("지하철 노선 조회 테스트")
    @Test
    public void 지하철_노선_조회() {
        //given - 상행 종점 진접역, 하행 종점 오이도역 생성 및 1개 라인 생성
        지하철_역_생성(지하철_역_생성_픽스처("진접역"));
        지하철_역_생성(지하철_역_생성_픽스처("오이도역"));
        지하철_라인_생성(라인_픽스처("green", 22L, "4호선", 1L, 2L));

        //when - 라인 조회
        ExtractableResponse<Response> response = 지하철_라인_조희(1L);

        //then - HTTP Status 200 OK 확인
        assertThat(response.statusCode())
                .isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("지하철 노선 목록 조회")
    @Test
    public void 지하철_노선_목록_조회() throws Exception {
        //given - 노선 두개 생성
        지하철_역_생성(지하철_역_생성_픽스처("진접역"));
        지하철_역_생성(지하철_역_생성_픽스처("오이도역"));
        지하철_라인_생성(라인_픽스처("green", 22L, "4호선", 1L, 2L));

        지하철_역_생성(지하철_역_생성_픽스처("도봉산역"));
        지하철_역_생성(지하철_역_생성_픽스처("온수역"));
        지하철_라인_생성(라인_픽스처("khaki", 25L, "7호선", 3L, 4L));

        //when
        ExtractableResponse<Response> response = 지하철_라인_목록_조회();

        //then
        assertThat(response.statusCode())
                .isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("지하철 노선 삭제 테스트")
    @Test
    public void 지하철_노선_삭제_테스트() throws Exception {
        //given - 2개 역, 1개 노선 생성
        지하철_역_생성(지하철_역_생성_픽스처("진접역"));
        지하철_역_생성(지하철_역_생성_픽스처("오이도역"));
        지하철_라인_생성(라인_픽스처("green", 22L, "4호선", 1L, 2L));

        //when - 지하철 노선 삭제
        ExtractableResponse<Response> response = 지하철_라인_삭제(1L);

        //then
        assertThat(response.statusCode())
                .isEqualTo(HttpStatus.OK.value());

    }

    @DisplayName("지하철 노선 수정 테스트")
    @Test
    public void 지하철_노선_수정_테스트() throws Exception {
        //given - 2개 역, 1개 노선 생성
        지하철_역_생성(지하철_역_생성_픽스처("진접역"));
        지하철_역_생성(지하철_역_생성_픽스처("오이도역"));
        지하철_라인_생성(라인_픽스처("green", 22L, "4호선", 1L, 2L));

        //when - 지하철 노선 수정
        ExtractableResponse<Response> response = 지하철_라인_수정(1L,
                라인_수정_픽스처("green", 33L, "4호선", 2L, 1L));

        //then - 200 정상 코드 반환
        assertThat(response.statusCode())
                .isEqualTo(HttpStatus.OK.value());
    }
}
