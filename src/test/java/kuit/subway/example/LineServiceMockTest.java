package kuit.subway.example;//package kuit.subway.study.mocking;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class LineServiceMockTest {
//    @Mock
//    private LineRepository lineRepository;
//
//    @Mock
//    private StationRepository stationRepository;
//
//    @InjectMocks
//    private LineService lineService;
//
//    @Test
//    void addSection() {
//        // given
//        // lineRepository, stationRepository stub 설정을 통해 초기값 셋팅
//        // https://velog.io/@lxxjn0/Mockito%EC%99%80-BDDMockito%EB%8A%94-%EB%AD%90%EA%B0%80-%EB%8B%A4%EB%A5%BC%EA%B9%8C
//        // BDDMockito를 사용한 given으로 변경 가능
//        // 이름만 다르고 기능은 모두 같음
//        when(stationRepository.findById(1L)).thenReturn(StationFixData.create_강남역());
//        when(stationRepository.findById(2L)).thenReturn(StationFixData.create_성수역());
//        when(lineRepository.save(경춘선)).thenReturn(createEmptyLine_경춘선());
//
//
//        // when
//        // lineService.addSection 호출
//        LineResponse lineResponse = lineService.saveLine(경춘선_요청);
//
//        // then
//        // lineService.findLineById 메서드를 통해 검증
//        assertEquals("경춘", lineResponse.getName());
//        // verify를 통해 해당 메서드가 실제 몇번 불렸는지도 알 수 있다.
//        verify(stationRepository, times(2)).findById();
//    }
//}