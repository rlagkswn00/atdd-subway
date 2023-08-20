package kuit.subway.service;

import jakarta.transaction.Transactional;
import kuit.global.BaseResponseStatus;
import kuit.global.exception.SubwayException;
import kuit.subway.domain.Line;
import kuit.subway.dto.SaveLineReq;
import kuit.subway.dto.SaveLineRes;
import kuit.subway.repository.LineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LineServiceImpl implements LineService{

    private final LineRepository lineRepository;

    @Override
    public SaveLineRes createLines(SaveLineReq saveLineReq) {
        if(lineRepository.existsLineByName(saveLineReq.getName()))
            throw new SubwayException(BaseResponseStatus.DUPLICATE_LINE);

        Line line = Line.builder()
                .name(saveLineReq.getName())
                .color(saveLineReq.getColor())
                .downStationId(saveLineReq.getDownStationId())
                .upStationId(saveLineReq.getUpStationId())
                .distance(saveLineReq.getDistance())
                .build();

        Long id = lineRepository.save(line).getId();
        return new SaveLineRes(id);
    }
}
