package kuit.subway.study.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kuit.subway.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static kuit.subway.study.fixture.LineFixture.라인_생성_픽스처;
import static kuit.subway.study.fixture.SectionFixture.지하철_구간_생성_픽스처;
import static kuit.subway.study.fixture.StationFixture.지하철_역_생성_픽스처;
import static kuit.subway.study.step.LineStep.지하철_라인_생성;
import static kuit.subway.study.step.SectionStep.지하철_구간_생성;
import static kuit.subway.study.step.StationStep.지하철_역_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;

public class SectionAcceptanceTest extends AcceptanceTest {

    @DisplayName("구간 추가 테스트")
    @Test
    public void 지하철_구간_추가() throws Exception {
        //given - 2개 역, 1개 노선 추가
        지하철_역_생성(지하철_역_생성_픽스처("진접역"));
        지하철_역_생성(지하철_역_생성_픽스처("오남역"));
        지하철_라인_생성(라인_생성_픽스처("green", 2L, "4호선", 1L, 2L));

        //when - 1개 역 추가, 1개 구간 추가
        지하철_역_생성(지하철_역_생성_픽스처("별내별가람역"));// ID : 3L

        //1번 라인에
        //2번 역을 상행, 3번 역을 하행으로 설정, 구간 추가
        ExtractableResponse<Response> response = 지하철_구간_생성(지하철_구간_생성_픽스처(2L, 3L, 1L));

        //then - 201 반환
        assertThat(response.statusCode())
                .isEqualTo(CREATED.value());

    }
}
